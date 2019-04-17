
package cn.youguang.service;

import cn.youguang.entity.Organization;
import cn.youguang.entity.User;
import cn.youguang.util.ComboxItem;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Tree;
import cn.youguang.repository.OrganizationDao;
import cn.youguang.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Spring Bean的标识.
@Service
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrganizationDao orgDao;

	/**
	 * 保存用户
	 *
	 * @param user
	 */
	public void saveUser(User user) {
		userDao.save(user);
	}

	public List<User> findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public User findUserById(long id) {
		return this.userDao.findById(id);
	}

	public Long countUsers() {
		return this.userDao.count();
	}

	public void delUserById(long id) {
		userDao.delete(id);
	}

	public User findUserByLoginName(String loginname) {
		List<User> list = userDao.findByLoginname(loginname);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public void findDataGrid(PageInfo pageInfo) {
		System.out.println("pageinfo=" + pageInfo);
		try {

			Page<User> users;

			String username = (String) pageInfo.getCondition().get("username");
			String loginname = (String) pageInfo.getCondition().get("loginname");
			Date startTime = (Date) pageInfo.getCondition().get("startTime");
			Date endTime = (Date) pageInfo.getCondition().get("endTime");

			if (pageInfo.getCondition().containsKey("organizationId")) {
				Long organizationId = (Long) pageInfo.getCondition().get("organizationId");
				System.out.println("startTime=" + startTime + ",endTime=" + endTime);
				List<Organization> orgs = new ArrayList<Organization>();
				Organization org = orgDao.findOne(organizationId);
				orgs.add(org);
				if (org.getChildren() != null && org.getChildren().size() > 0) {
					for (Organization org1 : org.getChildren()) {
						orgs.add(org1);
						if (org1.getChildren() != null && org1.getChildren().size() > 0) {
							for (Organization org2 : org1.getChildren()) {
								orgs.add(org2);
								if (org2.getChildren() != null && org2.getChildren().size() > 0) {
									for (Organization org3 : org2.getChildren()) {
										orgs.add(org3);

									}
								}

							}
						}
					}
				}

				users = userDao.findDistinctByOrgsIn(orgs, pageInfo.getPagerequest());
			} else if (pageInfo.getCondition().containsKey("username")
					&& pageInfo.getCondition().containsKey("loginname")
					&& pageInfo.getCondition().containsKey("startTime")
					&& pageInfo.getCondition().containsKey("endTime")) {
				users = userDao.findByUsernameAndLoginnameAndCreatedateBetween(username, loginname, startTime, endTime,
						pageInfo.getPagerequest());
			} else if (pageInfo.getCondition().containsKey("username")
					&& pageInfo.getCondition().containsKey("startTime")
					&& pageInfo.getCondition().containsKey("endTime")) {
				users = userDao.findByUsernameAndCreatedateBetween(username, startTime, endTime,
						pageInfo.getPagerequest());

			} else if (pageInfo.getCondition().containsKey("loginname")
					&& pageInfo.getCondition().containsKey("startTime")
					&& pageInfo.getCondition().containsKey("endTime")) {
				users = userDao.findByLoginnameAndCreatedateBetween(loginname, startTime, endTime,
						pageInfo.getPagerequest());

			} else if (pageInfo.getCondition().containsKey("startTime")
					&& pageInfo.getCondition().containsKey("endTime")) {
				users = userDao.findByCreatedateBetween(startTime, endTime, pageInfo.getPagerequest());

			} else if (pageInfo.getCondition().containsKey("loginname")) {
				users = userDao.findByLoginnameLike(loginname, pageInfo.getPagerequest());

			} else if (pageInfo.getCondition().containsKey("username")) {
				users = userDao.findByUsernameLike(username, pageInfo.getPagerequest());

			} else {
				users = userDao.findAll(pageInfo.getPagerequest());
			}

			pageInfo.setRows(users);
			pageInfo.setTotal(users.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addUser(User user) {
	/*	user.setLoginpass(DigestUtils.md5Hex(user.getLoginpass()));*/
		userDao.save(user);

	}

	public void updateUser(User user) {
		userDao.save(user);

	}

	public void deleteUserById(Long id) {
		userDao.delete(id);

	}

	public List<ComboxItem> itemFindAll() {
		List<ComboxItem> list = new ArrayList<ComboxItem>();
		for (User user : userDao.findAllByOrderByOrgsAscUsernameAsc()) {
			ComboxItem ci = new ComboxItem();
			ci.setValue(user.getId().toString());
			ci.setText(user.getUsername());
			ci.setGroup(user.getOrgs().get(0).getName());
			list.add(ci);
		}
		return list;
	}

	public List<Tree> findTree() {
		List<Tree> trees = new ArrayList<Tree>();
		for (User user : userDao.findAll()) {
			Tree tree = new Tree();
			tree.setId(user.getId());
			tree.setText(user.getUsername());

			trees.add(tree);
		}
		return trees;

	}

	public List findAll() {
		return userDao.findAll();
	}

	public List<User> findUserByIds(String[] ids) {
		List<Long > ids2 = new ArrayList<Long>();
		for(String id : ids){
			ids2.add(Long.parseLong(id));
		}
		return userDao.findAll(ids2);

	}

    public List<User> findByUsertype(int i) {
		return userDao.findByUsertype(i);
    }
}
