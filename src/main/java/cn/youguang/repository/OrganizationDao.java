package cn.youguang.repository;

import cn.youguang.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrganizationDao extends JpaRepository<Organization, Long> {
	List<Organization> findByParentIsNull();

}
