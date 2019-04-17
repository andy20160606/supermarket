package cn.youguang.repository;

import cn.youguang.entity.Cp;
import cn.youguang.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InfoDao extends JpaRepository<Info, Long> {


    List<Info> findByKey(String key);




    List<Info> findByKeyContaining(String key);

    void deleteByKey(String key);
}
