
package cn.youguang.service;

import cn.youguang.entity.User;
import cn.youguang.entity.Zfrz;
import cn.youguang.repository.UserDao;
import cn.youguang.repository.ZfrzDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Spring Bean的标识.
@Service
@Transactional
public class ZfrzService {


    @Autowired
    private ZfrzDao zfrzDao;

    @Autowired
    private UserDao userDao;


    public Zfrz save(Zfrz zfrz) {


        return zfrzDao.save(zfrz);
    }


    public List<Zfrz> findByUserId(Long id) {
        User user = userDao.findById(id);

        return zfrzDao.findByZfr(user);

    }


    public Zfrz findById(Long id) {
        return zfrzDao.findOne(id);
    }

    public Zfrz findByWybs(String wybs) {
        return zfrzDao.findByWybs(wybs);
    }
}
