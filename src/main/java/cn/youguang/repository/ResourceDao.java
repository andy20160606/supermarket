package cn.youguang.repository;


import cn.youguang.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResourceDao extends JpaRepository<Resource, Long> {


	List<Resource> findByParentIsNullAndResourcetype(Integer resourcetype);

}
