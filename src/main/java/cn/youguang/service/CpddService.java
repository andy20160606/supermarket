
package cn.youguang.service;

import cn.youguang.entity.Cpdd;
import cn.youguang.entity.User;
import cn.youguang.repository.CpDao;
import cn.youguang.repository.CpddDao;
import cn.youguang.repository.HyDao;
import cn.youguang.repository.UserDao;
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
public class CpddService {


	@Autowired
	private CpDao cpDao;


	@Autowired
	private HyDao hyDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CpddDao cpddDao;




	/**
	 * 保存/更新产品
	 *
	 * @param
	 *
	 */
	public void save(Cpdd cpdd) {
		cpddDao.save(cpdd);
	}



	public void delete(long id) {
		cpddDao.delete(id);
	}


    public void findDataTables(PageInfo pageInfo) {

		Page<Cpdd> cpdds;

		String ddlx = (String) pageInfo.getCondition().get("ddlx");

		Long userId = (Long) pageInfo.getCondition().get("userId");

		Integer ddzt = (Integer) pageInfo.getCondition().get("ddzt");




		if(StringUtils.isNoneBlank(ddlx) && userId!=null){
			User user = userDao.findById(userId);
			cpdds = cpddDao.findByDdlxAndUser(ddlx,user,pageInfo.getPagerequest());

		} else if(StringUtils.isNoneBlank(ddlx)){
			cpdds = cpddDao.findByDdlx(ddlx,pageInfo.getPagerequest());

		} else {

			cpdds = cpddDao.findAll(pageInfo.getPagerequest());

		}

		pageInfo.setRows(cpdds.getContent());
		pageInfo.setTotal(cpdds.getTotalElements());



	}


	/**
	 *
	 * @param condition
	 * @return
	 */
	public List<Cpdd> findList(Map<String, Object> condition) {

		List<Cpdd> cpdds;
		String ddlx = (String) condition.get("ddlx");

		Long userid = (Long) condition.get("userid");

		Integer ddzt = (Integer) condition.get("ddzt");

		if(StringUtils.isNoneBlank(ddlx) && userid!=null){
			User user = userDao.findById(userid);
			cpdds = cpddDao.findByDdlxAndUser(ddlx,user);

		} else if(StringUtils.isNoneBlank(ddlx)){
			cpdds = cpddDao.findByDdlx(ddlx);

		} else {

			cpdds = cpddDao.findAll();

		}



		return  cpdds;

	}
}
