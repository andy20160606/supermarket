package cn.youguang.repository;

import cn.youguang.entity.Organization;
import cn.youguang.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface UserDao extends JpaRepository<User, Long> {
	
	public User findById(long id);
	public List<User> findAllByOrderByOrgsAscUsernameAsc();


	public List<User> findByUsername(@Param("username") String username);

	public List<User> findByLoginname(@Param("loginname") String loginname);

	public Page<User> findByUsernameLike(@Param("username") String username, Pageable pageable);



	public Page<User> findByLoginnameLike(String loginname, Pageable pageable);

	public Page<User> findDistinctByOrgsIn(List<Organization> orgs, Pageable pageable);

	@Query("select a from User a where a.createdate >=?1 ")
	public Page<User> findConditionBy(Date startTime, Pageable pageable);

	public Page<User> findByUsernameAndLoginnameAndCreatedateBetween(String username, String loginname, Date startTime,
                                                                     Date endTime, Pageable pageable);

	public Page<User> findByUsernameAndCreatedateBetween(String username, Date startTime, Date endTime, Pageable pageable);

	public Page<User> findByLoginnameAndCreatedateBetween(String loginname, Date startTime, Date endTime, Pageable pageable);

	public Page<User> findByCreatedateBetween(Date startTime, Date endTime, Pageable pageable);


    public List<User> findByUsertype(int i);

    public List<User> findByUsernameContaining(@Param("username") String sqrname);

    User findByWxopenid(String openid);
}
