
package cn.youguang.service;

import cn.youguang.entity.Cp;
import cn.youguang.entity.Cpdd;
import cn.youguang.entity.Hy;
import cn.youguang.entity.User;
import cn.youguang.repository.CpDao;
import cn.youguang.repository.CpddDao;
import cn.youguang.repository.HyDao;
import cn.youguang.repository.UserDao;
import cn.youguang.util.PageInfo;
import io.swagger.annotations.Api;
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
public class CpService {


	@Autowired
	private CpDao cpDao;


	@Autowired
	private HyDao hyDao;





	/**
	 * 保存/更新产品
	 *

	 *
	 */
	public void save(Cp cp) {
		cpDao.save(cp);
	}



	public void delete(long id) {
		cpDao.delete(id);
	}


    public void findDataTables(PageInfo pageInfo) {

		Page<Cp> cps;

		String cpmc = (String) pageInfo.getCondition().get("cpmc");

		String hymc = (String) pageInfo.getCondition().get("hymc");

		String cplb = (String) pageInfo.getCondition().get("cplb");

		List<Hy> hys;

		if(StringUtils.isNoneBlank(cpmc) &&StringUtils.isNoneBlank(hymc) ){
			hys = hyDao.findByHymcLike(hymc);
			cps = cpDao.findByCpmcLikeAndHysIn(cpmc,hys,pageInfo.getPagerequest());
		} else if(StringUtils.isNoneBlank(cpmc)){
			cps = cpDao.findByCpmcLike(cpmc,pageInfo.getPagerequest());

		} else if (StringUtils.isNoneBlank(hymc)){
			hys = hyDao.findByHymcLike(hymc);
			cps = cpDao.findByHysIn(hys,pageInfo.getPagerequest());
		}else if (StringUtils.isNoneBlank(cplb)){

			cps = cpDao.findByCplbLike(cplb,pageInfo.getPagerequest());
		}else {


			cps = cpDao.findAll(pageInfo.getPagerequest());
		}

		pageInfo.setRows(cps.getContent());
		pageInfo.setTotal(cps.getTotalElements());


    }

	public List<Cp> findList(Map<String, Object> condition) {
		String cpmc = (String) condition.get("cpmc");

		String hymc = (String) condition.get("hymc");

		List<Hy> hys;
		List<Cp> cps;

		if(StringUtils.isNoneBlank(cpmc) &&StringUtils.isNoneBlank(hymc) ){
			hys = hyDao.findByHymcLike(hymc);
			cps = cpDao.findByCpmcLikeAndHysIn(cpmc,hys);

		} else if(StringUtils.isNoneBlank(cpmc)){
			cps = cpDao.findByCpmcLike(cpmc);

		} else if (StringUtils.isNoneBlank(hymc)){
			hys = hyDao.findByHymcLike(hymc);
			cps = cpDao.findByHysIn(hys);
		}else {


			cps = cpDao.findAll();
		}
		return  cps;

	}

	public Cp findById(Long cpId) {

		return cpDao.findOne(cpId);
	}
}
