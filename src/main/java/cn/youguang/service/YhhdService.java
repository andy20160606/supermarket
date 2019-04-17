
package cn.youguang.service;

import cn.youguang.entity.Hy;
import cn.youguang.entity.Yhhd;
import cn.youguang.repository.HyDao;
import cn.youguang.repository.UserDao;
import cn.youguang.repository.YhhdDao;
import cn.youguang.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//Spring Bean的标识.
@Service
@Transactional
public class YhhdService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private HyDao hyDao;


	@Autowired
	private YhhdDao yhhdDao;




	/**
	 * 保存/更新行业
	 *
	 * @param
	 */
	public void save(Yhhd yhhd) {
		yhhdDao.save(yhhd);
	}


	public Yhhd findById(Long id){


		return yhhdDao.findOne(id);
	}





    public void findDataTables(PageInfo pageInfo) {

		Page<Yhhd> yhhds;

		String hdmc = (String) pageInfo.getCondition().get("hdmc");


		if(StringUtils.isNoneBlank(hdmc)){
			yhhds = yhhdDao.findByHdmc(hdmc,pageInfo.getPagerequest());
		} else {


			yhhds = yhhdDao.findAll(pageInfo.getPagerequest());
		}

		pageInfo.setRows(yhhds.getContent());
		pageInfo.setTotal(yhhds.getTotalElements());


    }

	public List<Yhhd> findList(Map<String, Object> condition) {
		String hdmc = (String) condition.get("hdmc");
		if(StringUtils.isNoneBlank(hdmc)){
			return yhhdDao.findByHdmcLike(hdmc);
		} else {
			return  yhhdDao.findAll();
		}


	}
}
