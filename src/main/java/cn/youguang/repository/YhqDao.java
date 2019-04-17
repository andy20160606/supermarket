package cn.youguang.repository;

import cn.youguang.entity.User;
import cn.youguang.entity.Yhhd;
import cn.youguang.entity.Yhq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface YhqDao extends JpaRepository<Yhq, Long> {


    Page<Yhq> findByUser(User user, Pageable pagerequest);

    List<Yhq> findByUser(User user);

    Yhq findByUserAndYhhd(User user, Yhhd yhhd);
}
