package cn.youguang.controller;


import cn.youguang.entity.User;
import cn.youguang.service.OrganizationService;
import cn.youguang.service.RoleService;
import cn.youguang.service.UserService;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Result;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：用户管理
 */


@Api(value = "用戶Controller",tags = {"用戶操作接口"})
@Controller
@RequestMapping("/user")
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService orgService;
    @Autowired
    private RoleService roleService;


//    @RequestMapping(value = "datatables", method = RequestMethod.GET)
//    @ResponseBody
//    public PageInfo datatables(User user, Long organizationId, Date createdateStart, Date createdateEnd, PageInfo pageInfo) {
//
//
//        System.out.println("user=" + user);
//        System.out.println("organizationId=" + organizationId);
//        System.out.println("createdateStart=" + createdateStart);
//        System.out.println("createdateEnd=" + createdateEnd);
//        Map<String, Object> condition = new HashMap<String, Object>();
//        System.out.println("map");
//        if (user != null && StringUtils.isNoneBlank(user.getLoginname())) {
//            condition.put("loginname", user.getLoginname());
//        }
//        if (user != null && StringUtils.isNoneBlank(user.getUsername())) {
//            condition.put("username", user.getUsername());
//        }
//        if (organizationId != null) {
//            condition.put("organizationId", organizationId);
//        }
//        if (createdateStart != null) {
//            condition.put("startTime", createdateStart);
//        }
//        if (createdateEnd != null) {
//            condition.put("endTime", createdateEnd);
//        }
//        pageInfo.setCondition(condition);
//        userService.findDataGrid(pageInfo);
//        System.out.println("row size=" + pageInfo);
//        return pageInfo;
//    }

    /**
     * 添加用户
     *
     * @return
     */
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @ResponseBody
//    public Result add(User user) {
//        Result result = new Result();
//        try {
//            User u = userService.findUserByLoginName(user.getLoginname());
//            if (u != null) {
//                result.setMsg("登录名已存在!");
//                return result;
//            }
//            List<User> u2 = userService.findUserByUsername(user.getUsername());
//            if (u2 != null && u2.size() > 0) {
//                result.setMsg("姓名已存在!");
//                return result;
//            }
//            user.setCreatedate(new Date());
//            // 放在service层以便DbController可以使用
//            //user.setLoginpass(DigestUtils.md5Hex(user.getLoginname()));
//            userService.addUser(user);
//            result.setSuccess(true);
//            result.setMsg("添加成功");
//            return result;
//        } catch (RuntimeException e) {
//            LOGGER.error("添加用户失败：{}", e);
//            result.setMsg(e.getMessage());
//            return result;
//        }
//    }


    /**
     * 编辑用户
     *
     * @param
     * @return
     */
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    @ResponseBody
//    public Result edit(User user) {
//        Result result = new Result();
//        User userdb = userService.findUserByLoginName(user.getLoginname());
//
//        try {
//            if (userdb != null && userdb.getId() != user.getId()) {
//                result.setMsg("登录名已存在!");
//                return result;
//            }
//            List<User> u2 = userService.findUserByUsername(user.getUsername());
//            if (u2 != null && u2.size() > 0 && u2.get(0).getId() != user.getId()) {
//                result.setMsg("姓名已存在!");
//                return result;
//            }
//
//
//            if (user.getLoginpass() == null || user.getLoginpass().equals("")) {
//                user.setLoginpass(userdb.getLoginpass());
//            }
//            userService.updateUser(user);
//            result.setSuccess(true);
//            result.setMsg("修改成功！");
//            return result;
//        } catch (RuntimeException e) {
//            LOGGER.error("修改用户失败：{}", e);
//            result.setMsg(e.getMessage());
//            return result;
//        }
//    }


    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
//    @RequestMapping(value = "/editUserPwd", method = RequestMethod.POST)
//    @ResponseBody
//    public Result editUserPwd(Long id, String oldPwd, String pwd) {
//        Result result = new Result();
//        User user = userService.findUserById(id);
//        if (!user.getLoginpass().equals(oldPwd)) {
//            result.setMsg("老密码不正确!");
//            return result;
//        } else {
//            user.setLoginpass(pwd);
//        }
//
//        try {
//            userService.saveUser(user);
//            result.setSuccess(true);
//            Subject subject = SecurityUtils.getSubject();
//            subject.logout();
//            result.setMsg("密码修改成功！您将重新登录");
//            return result;
//        } catch (Exception e) {
//            LOGGER.error("修改密码失败：{}", e);
//            result.setMsg(e.getMessage());
//            return result;
//        }
//    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @ResponseBody
//    public Result delete(Long id) {
//        Result result = new Result();
//        try {
//            userService.deleteUserById(id);
//            result.setMsg("删除成功！");
//            result.setSuccess(true);
//            return result;
//        } catch (RuntimeException e) {
//            LOGGER.error("删除用户失败：{}", e);
//            result.setMsg(e.getMessage());
//            return result;
//        }
//    }


    @RequestMapping(value = "getUserInfoByCode", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserInfoByCode(@RequestParam String code) {
        Result result = new Result();
        try {
            SnsToken snsToken = SnsAPI.oauth2AccessToken(appid, secret, code);
            weixin.popular.bean.user.User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
            if(user.isSuccess()){
                User userdb = userService.findByOpenid(user.getOpenid());
                if(userdb == null) {
                    userdb = userService.initUserFromWxUser(user);
                    userdb = userService.saveUser(userdb);
                }
                result.setSuccess(true);
                result.setObj(userdb);
            }
        } catch (Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }



    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserById(@RequestParam Long id) {
        Result result = new Result();
        try {
           User user = userService.findUserById(id);
           result.setObj(user);
        } catch (Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }


}
