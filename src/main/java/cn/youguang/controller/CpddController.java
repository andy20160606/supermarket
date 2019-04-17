package cn.youguang.controller;


import cn.youguang.entity.Cp;
import cn.youguang.entity.Cpdd;
import cn.youguang.entity.User;
import cn.youguang.service.CpService;
import cn.youguang.service.CpddService;
import cn.youguang.service.UserService;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：产品订单
 */
@Controller
@RequestMapping("/cpdd")
@Api(value = "产品订单Controller",tags = {"产品订单操作接口"})
public class CpddController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CpddController.class);




    @Autowired
    private CpService cpService;

    @Autowired
    private CpddService cpddService;


    @Autowired
    private UserService userService;


    /**
     *  分页读取产品订单
     * @param
     * @param pageInfo
     * @return
     */

    @ApiOperation(value="分页获取产品订单信息",notes="pageinfo必须给定nowpage（当前页），pagesize（每页的记录数信息）,sort为排序（默认id，可给可不给）")
    @RequestMapping(value = "datatables",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo dataTables(@ApiParam(name="cpmc",value="产品名称")@RequestParam(required = false) String ddlx,@ApiParam(name="userId",value="用户id")@RequestParam(required = false) Long userId,@ApiParam(name="ddzt",value="订单状态")@RequestParam(required = false)Integer ddzt,@ModelAttribute PageInfo pageInfo) {


        Map<String, Object> condition = new HashMap<String, Object>();
        if (ddlx != null) {
            condition.put("ddlx", ddlx);
        }
        if (userId != null) {
            condition.put("userId", userId);
        }
        if (ddzt != null) {
            condition.put("ddzt", ddzt);
        }

        pageInfo.setCondition(condition);
        cpddService.findDataTables(pageInfo);
        return pageInfo;
    }


    @ApiOperation(value="集合获取产品订单信息",notes="cpmc,hymc为查询条件")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@ApiParam(name="cpmc",value="产品名称") @RequestParam(required = false) String ddlx,@ApiParam(name="userId",value="用户id")@RequestParam(required = false) Long userId,@ApiParam(name="ddzt",value="订单状态")@RequestParam(required = false) Integer ddzt){

        Result result = new Result();
        List<Cpdd> cpdds;
        Map<String, Object> condition = new HashMap<String, Object>();

        if (ddlx != null) {
            condition.put("ddlx", ddlx);
        }
        if (userId != null) {
            condition.put("userId", userId);
        }
        if (ddzt != null) {
            condition.put("ddzt", ddzt);
        }

       try {
           cpdds =  cpddService.findList(condition);
           result.setObj(cpdds);
           result.setSuccess(true);
       } catch (Exception e){
           result.setMsg(e.getMessage());
       }
        return result;

    }


    /**
     * 添加产品订单
     *
     * @return
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public Result add(@ApiParam(name="userId",value="用户ID") @RequestParam Long userId,@ApiParam(name="cpId",value="产品Id")@RequestParam Long cpId,@RequestBody Cpdd cpdd) {
        Result result = new Result();
        try {
            User user = userService.findUserById(userId);
            Cp cp = cpService.findById(cpId);
            cpdd.setUser(user);
            cpdd.setCp(cp);
            cpddService.save(cpdd);
            result.setSuccess(true);
        } catch (Exception e){
            result.setMsg(e.getMessage());

        }

        return result;

    }


    /**
     * 更新产品订单
     *
     * @param
     * @return
     */
    @RequiresPermissions("/none/authc")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Result edit(@ApiParam(name="userId",value="用户ID")Long userId,@ApiParam(name="cpId",value="产品Id")Long cpId,@RequestBody Cpdd cpdd) {
        Result result = new Result();
        try {
            User user = userService.findUserById(userId);
            Cp cp = cpService.findById(cpId);
            cpdd.setUser(user);
            cpdd.setCp(cp);
            cpddService.save(cpdd);
            result.setSuccess(true);
        } catch (Exception e){
            result.setMsg(e.getMessage());

        }

        return result;
    }

    /**
     * 删除产品订单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            cpddService.delete(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
        } catch (RuntimeException e) {
            LOGGER.error("删除用户失败：{}", e);
            result.setMsg(e.getMessage());

        }
        return result;
    }


}
