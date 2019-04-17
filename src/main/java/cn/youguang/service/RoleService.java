package cn.youguang.service;

import cn.youguang.entity.Resource;
import cn.youguang.entity.Role;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Tree;
import cn.youguang.repository.ResourceDao;
import cn.youguang.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//Spring Bean的标识.
@Service
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ResourceDao resDao;

	/**
	 * 保存角色
	 * 
	 * @param
	 */
	public void saveRole(Role role) {
		roleDao.save(role);
	}

	public Role findRoleById(Long id) {
		return roleDao.findOne(id);
	}

	public void delRoleById(long id) {
		roleDao.delete(id);
	}

	public void findDataGrid(PageInfo pageInfo) {
		System.out.println(pageInfo);
		pageInfo.setRows(roleDao.findAll(pageInfo.getPagerequest()));
		pageInfo.setTotal(roleDao.count());
	}

	public void addRole(Role role) {
		roleDao.save(role);

	}

	public void deleteRoleById(Long id) {
		roleDao.delete(id);

	}

	public void updateRole(Role role) {
		roleDao.save(role);

	}

	public List<Resource> findResListByRoleId(Long id) {

		return roleDao.findOne(id).getRess();
	}

	public List<Long> findRoleIdListByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tree> findTree() {
		List<Tree> trees = new ArrayList<Tree>();
		for (Role role : roleDao.findAll()) {
			Tree tree = new Tree();
			tree.setId(role.getId());
			tree.setText(role.getName());

			trees.add(tree);
		}
		return trees;

	}

	public void updateRoleResource(Long id, String resourceIds) {
		Role role = roleDao.findOne(id);
		if (resourceIds == null || "".equals(resourceIds.trim()))
			role.setRess(null);
		else {
			String[] resources = resourceIds.split(",");
			ArrayList<Resource> ress = new ArrayList<Resource>();
			for (String string : resources) {
				ress.add(resDao.findOne(Long.parseLong(string)));
			}
			role.setRess(ress);
			this.updateRole(role);
		}

	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}
}
