package cn.youguang.repository;


import cn.youguang.entity.Yhhd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface YhhdDao extends JpaRepository<Yhhd, Long> {


    Page<Yhhd> findByHdmc(String hdmc, Pageable pagerequest);



    List<Yhhd> findByHdmcLike(String hdmc);
}
