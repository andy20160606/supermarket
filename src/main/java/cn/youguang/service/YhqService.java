
package cn.youguang.service;

import cn.youguang.entity.User;
import cn.youguang.entity.Yhhd;
import cn.youguang.entity.Yhq;
import cn.youguang.repository.UserDao;
import cn.youguang.repository.YhhdDao;
import cn.youguang.repository.YhqDao;
import cn.youguang.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//Spring Bean的标识.
@Service
@Transactional
public class YhqService {



	@Autowired
	private YhqDao yhqDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private YhhdDao yhhdDao;



	public void save(Yhq yhq) {
		yhqDao.save(yhq);
	}


	public Yhq findById(Long id){


		return yhqDao.findOne(id);
	}





    public void findDataTables(PageInfo pageInfo) {

		Page<Yhq> yhqs;
		Long userId = (Long) pageInfo.getCondition().get("userId");


		if(userId != null){
			User user = userDao.findById(userId);
			yhqs = yhqDao.findByUser(user,pageInfo.getPagerequest());
		} else {
			yhqs = yhqDao.findAll(pageInfo.getPagerequest());
		}



		pageInfo.setRows(yhqs.getContent());
		pageInfo.setTotal(yhqs.getTotalElements());


    }

	public List<Yhq> findList(Map<String, Object> condition) {

		Long userId = (Long) condition.get("userId");
		List<Yhq> yhqs;
		if(userId != null){
			User user = userDao.findById(userId);
			yhqs = yhqDao.findByUser(user);
		} else {
			yhqs = yhqDao.findAll();
		}

		return yhqs;

	}

	public Yhq check(Long userId, Long yhhdId) {
		User user = userDao.findById(userId);
		Yhhd yhhd = yhhdDao.findOne(yhhdId);
		Yhq yhq =  yhqDao.findByUserAndYhhd(user,yhhd);
		return  yhq;

	}



	public Yhq initFromYhhdAndUser(Yhhd yhhd, User user) {
		Yhq yhq = new Yhq();
		yhq.setJe(yhhd.getJe());
		yhq.setKsrq(yhhd.getKsrq());
		yhq.setJsrq(yhhd.getJsrq());
		return yhq;
	}
}
