package cn.youguang.repository;

import cn.youguang.entity.Cpdd;
import cn.youguang.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CpddDao extends JpaRepository<Cpdd, Long> {


    Page<Cpdd> findByDdlxAndUser(String ddlx, User user, Pageable pagerequest);

    Page<Cpdd> findByDdlx(String ddlx, Pageable pagerequest);

    List<Cpdd> findByDdlxAndUser(String ddlx, User user);

    List<Cpdd> findByDdlx(String ddlx);
}
