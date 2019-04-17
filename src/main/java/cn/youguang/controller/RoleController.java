package cn.youguang.controller;


import cn.youguang.entity.Resource;
import cn.youguang.entity.Role;
import cn.youguang.service.RoleService;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Result;
import cn.youguang.util.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：权限管理
 */
@Controller
@RequestMapping("/role")
public class RoleController  {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 权限管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/role";
    }

    /**
     * 权限列表
     *
     * @param role
     * @param page
     * @return
     */
    @RequestMapping(value = "/datatables")//, method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(Role role, Integer page, PageInfo pageInfo) {
//        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);

        roleService.findDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 权限树
     *
     * @return
     */
    @RequestMapping(value = "/tree") // method = RequestMethod.POST
    @ResponseBody
    public List<Tree> tree() {
        return roleService.findTree();
    }

    /**
     * 添加权限页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/admin/roleAdd";
    }

    /**
     * 添加权限
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Role role) {
        Result result = new Result();
        try {
            roleService.addRole(role);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("添加角色失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            roleService.deleteRoleById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            logger.error("删除角色失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 编辑权限页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Role role = roleService.findRoleById(id);
        request.setAttribute("role", role);
        return "/admin/roleEdit";
    }

    /**
     * 删除权限
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Role role) {
        Result result = new Result();
        try {
            roleService.updateRole(role);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("编辑角色失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 授权页面
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/grantPage")
    public String grantPage(HttpServletRequest request, Long id, Model model) {
        model.addAttribute("id", id);
        System.out.println("/admin/roleGrant?id="+id);
        return "/admin/roleGrant";
    }

    /**
     * 授权页面页面根据角色查询资源
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Result findResourceByRoleId(HttpServletRequest request, Long id, Model model) {
        Result result = new Result();
        try {
        	System.out.println("roleid="+id);
        	
            List<Long> resources=new ArrayList<Long>();
            for (Resource res : roleService.findResListByRoleId(id)) {
            	resources.add(res.getId());
			}
            		
            System.out.println("resources="+resources);
            result.setSuccess(true);
            result.setObj(resources);
            return result;
        } catch (RuntimeException e) {
            logger.error("角色查询资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 授权
     *
     * @param id
     * @param resourceIds
     * @return
     */
    @RequestMapping("/grant")
    @ResponseBody
    public Result grant(Long id, String resourceIds) {
        Result result = new Result();
        System.out.println("do role/grant");
        try {
            roleService.updateRoleResource(id, resourceIds);
            result.setMsg("授权成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            logger.error("授权成功失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
