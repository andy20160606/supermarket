package cn.youguang.repository;

import cn.youguang.entity.Hy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HyDao extends JpaRepository<Hy, Long> {


    Page<Hy> findByHymc(String hymc, Pageable pageable);

    List<Hy> findByHymcLike(String hymc);

    List<Hy> findByIdIn(List<Long> ids);
}
