package cn.youguang.controller;

import cn.youguang.entity.Organization;
import cn.youguang.entity.Resource;
import cn.youguang.entity.Role;
import cn.youguang.entity.User;
import cn.youguang.service.OrganizationService;
import cn.youguang.service.ResourceService;
import cn.youguang.service.RoleService;
import cn.youguang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("db")
public class DbController {

	@Autowired
	private ResourceService resService;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/ressave")
	public Resource resSave() {
		Resource res = new Resource();
		res.setCreatedate(new Date());
		res.setDescription("系统管理");
		res.setUrl("system");
		res.setName("res-name-1");
		res.setResourcetype(0);
		resService.saveRes(res);
		Resource res1=new Resource();
		res1.setCreatedate(new Date());
		res1.setDescription("系统管理/用户管理");
		res1.setUrl("system/user");
		res1.setName("用户管理");
		res1.setResourcetype(0);
		res1.setParent(res);
		resService.saveRes(res1);

		Resource res2=new Resource();
		res2.setCreatedate(new Date());
		res2.setDescription("系统管理/用户管理/添加");
		res2.setUrl("system/user/add");
		res2.setName("添加");
		res2.setResourcetype(1);
		res2.setParent(res1);
		resService.saveRes(res2);
		
		Resource res3=new Resource();
		res3.setCreatedate(new Date());
		res3.setDescription("系统管理/用户管理/修改");
		res3.setUrl("system/user/edit");
		res3.setName("修改");
		res3.setResourcetype(1);
		res3.setParent(res1);
		resService.saveRes(res3);
		
		Resource res4=new Resource();
		res4.setCreatedate(new Date());
		res4.setDescription("系统管理/用户管理/删除");
		res4.setUrl("system/user/del");
		res4.setName("删除");
		res4.setResourcetype(1);
		res4.setParent(res1);
		resService.saveRes(res4);

		return res;
	}

	@ResponseBody
	@RequestMapping("/orgsave")
	public Organization orgSave() {
		Organization org = new Organization();
		org.setAddress("行政楼");
		org.setCode("111000");
		org.setCreatedate(new Date());
		org.setDescription("desc");
		org.setName("办公室");
		orgService.saveOrg(org);
		Organization org1 = new Organization();
		org1.setAddress("行政楼111");
		org1.setCode("111000");
		org1.setCreatedate(new Date());
		org1.setDescription("desc");
		org1.setName("办公室1");
		org1.setParent(org);
		orgService.saveOrg(org1);
		Organization org2 = new Organization();
		org2.setAddress("行政楼222");
		org2.setCode("111000");
		org2.setCreatedate(new Date());
		org2.setDescription("desc");
		org2.setName("办公室1");
		org2.setParent(org1);
		orgService.saveOrg(org2);
		return org;

	}

	@ResponseBody
	@RequestMapping("/rolesave")
	public Role roleSave() {
		Role role = new Role();
		role.setDescription("desc");
		role.setName("超级用户");
		role.setSeq(1);
		role.setStatus(1);
		ArrayList<Resource> ress = new ArrayList<Resource>();
		ress.add(resService.findResById(1l));
		role.setRess(ress);
		roleService.saveRole(role);
		return role;

	}

	@ResponseBody
	@RequestMapping("/usersave")
	public User userSave() {
		User user = new User();
		user.setAge(20);
		user.setCreatedate(new Date());
		user.setLoginname("root");
		user.setLoginpass("root");
		user.setPhone("139000000000");
		user.setSex(1);
		user.setStatus(1);
		user.setUsername("张三");
		ArrayList<Organization> orgs = new ArrayList<Organization>();
		orgs.add(orgService.findOrgById(1L));
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findRoleById(1L));
		user.setOrgs(orgs);
		user.setRoles(roles);
		userService.saveUser(user);
		return user;

	}

	@ResponseBody
	@RequestMapping("/deluserbyid/{id}")
	public void delUser(@PathVariable long id) {
		userService.delUserById(id);
	}

	@ResponseBody
	@RequestMapping("/delrolebyid/{id}")
	public void delRole(@PathVariable long id) {
		roleService.delRoleById(id);
	}

	@ResponseBody
	@RequestMapping("/delorgbyid/{id}")
	public void delOrg(@PathVariable long id) {
		orgService.delOrgById(id);
	}

	@ResponseBody
	@RequestMapping("/delresbyid/{id}")
	public void delRes(@PathVariable long id) {
		resService.delResById(id);
	}
	
	@ResponseBody
	@RequestMapping("/editresbyid/{id}")
	public Resource editRes(@PathVariable long id) {
		Resource res=resService.findResById(id);
		res.setName("教学资料");
		res.setId(100l);
		resService.saveRes(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/getorgbyid/{id}")
	public Organization getOrg(@PathVariable long id) {
		Organization org=orgService.findOrgById(id);
	
		return org;
	}
	
	@ResponseBody
	@RequestMapping("/getrolebyid/{id}")
	public Role getRole(@PathVariable long id) {
		Role role=roleService.findRoleById(id);
	
		return role;
	}

	@ResponseBody
	@RequestMapping("/getresbyid/{id}")
	public Resource getRes(@PathVariable long id) {
		Resource res=resService.findResById(id);
	
		return res;
	}
}
