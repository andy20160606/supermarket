package cn.youguang.controller;

import cn.youguang.entity.Resource;
import cn.youguang.entity.User;
import cn.youguang.service.ResourceService;
import cn.youguang.service.UserService;
import cn.youguang.util.Result;
import cn.youguang.util.Tree;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description：资源管理
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resService;
	@Autowired
	private UserService userService;

	/**
	 * 菜单树
	 *
	 * @return
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<Tree> tree() {
		User user = userService.findUserById((Long) SecurityUtils.getSubject().getPrincipal());
		List<Tree> tree = resService.findTree(user);
		return tree;
	}

	/**
	 * 资源管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "admin/resource";
	}

	/**
	 * 资源管理列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/treeGrid")
	@ResponseBody
	public List<Resource> treeGrid() {
		List<Resource> treeGrid = resService.findResTop();
		return treeGrid;
	}

	/**
	 * 添加资源页
	 *
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage() {
		return "/admin/resourceAdd";
	}

	/**
	 * 添加资源
	 *
	 * @param resource
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(Resource res, Long pid) {
		Result result = new Result();
		try {
			if (pid != null)
				res.setParent(resService.findResById(pid));
			resService.addRes(res);
			result.setSuccess(true);
			result.setMsg("添加成功！");
			return result;
		} catch (RuntimeException e) {
			logger.error("添加资源失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 二级资源树
	 *
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree() {
		System.out.println("findalltree.....");
		return resService.findAllTree();
	}

	/**
	 * 三级资源树
	 *
	 * @return
	 */
	@RequestMapping(value = "/allTrees") // , method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> allTrees() {
		return resService.findAllTrees();
	}

	/**
	 * 编辑资源页
	 *
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Resource res = resService.findResById(id);
		request.setAttribute("resource", res);
		return "/admin/resourceEdit";
	}

	/**
	 * 编辑资源
	 *
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(Resource res, Long pid) {
		// System.out.println("parent id="+pid);
		Result result = new Result();
		try {
			if (pid != null)
				res.setParent(resService.findResById(pid));
			resService.updateRes(res);
			result.setSuccess(true);
			result.setMsg("编辑成功！");
			return result;
		} catch (RuntimeException e) {
			logger.error("编辑资源失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 删除资源
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			resService.deleteResById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			logger.error("删除资源失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

}
