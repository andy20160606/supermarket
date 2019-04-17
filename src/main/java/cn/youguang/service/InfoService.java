
package cn.youguang.service;

import cn.youguang.entity.Hy;
import cn.youguang.entity.Info;
import cn.youguang.repository.HyDao;
import cn.youguang.repository.InfoDao;
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
public class InfoService {


	@Autowired
	private InfoDao infoDao;
	public List<Info> findAll(){

		return  infoDao.findAll();
	}


	public List<Info> findByKey(String key){

		return infoDao.findByKey(key);
	}


	public void save(Info info) {
		infoDao.save(info);
	}

    public List<Info> findByKeyContaining(String key) {
		return  infoDao.findByKeyContaining(key);
    }

    public void deleteById(Long id) {
		infoDao.delete(id);
    }

	public void deleteByKey(String key) {
		infoDao.deleteByKey(key);
	}
}
