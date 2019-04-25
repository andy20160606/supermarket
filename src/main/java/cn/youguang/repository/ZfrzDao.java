package cn.youguang.repository;

import cn.youguang.entity.User;
import cn.youguang.entity.Zfrz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ZfrzDao extends JpaRepository<Zfrz, Long> {


    List<Zfrz> findByZfr(User user);

    Zfrz findByWybs(String wybs);
}
