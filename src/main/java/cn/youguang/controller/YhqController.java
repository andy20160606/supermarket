package cn.youguang.controller;


import cn.youguang.entity.User;
import cn.youguang.entity.Yhhd;
import cn.youguang.entity.Yhq;
import cn.youguang.service.HyService;
import cn.youguang.service.UserService;
import cn.youguang.service.YhhdService;
import cn.youguang.service.YhqService;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description：优惠活动
 */
@Controller
@RequestMapping("/yhq")
@Api(value = "优惠券Controller",tags = {"优惠券操作接口"})
public class YhqController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(YhqController.class);




    @Autowired
    private YhhdService yhhdService;

    @Autowired
    private YhqService yhqService;

    @Autowired
    private UserService userService;



    /**
     *  分页读取优惠券
     * @param userId
     * @param pageInfo
     * @return
     */

    @ApiOperation(value="分页获取优惠券信息",notes="pageinfo必须给定nowpage（当前页），pagesize（每页的记录数信息）,sort为排序（默认id，可给可不给）")
    @RequestMapping(value = "datatables",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo dataTables(@ApiParam(name="userId",value="用户ID")@RequestParam(required = false) Long userId, @ModelAttribute PageInfo pageInfo) {


        Map<String, Object> condition = new HashMap<String, Object>();

        if (userId != null) {
            condition.put("userId", userId);
        }
        pageInfo.setCondition(condition);
        yhqService.findDataTables(pageInfo);
        return pageInfo;
    }


    @ApiOperation(value="集合获取优惠券信息",notes="userId为查询条件")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@ApiParam(name="userId",value="用户ID")@RequestParam(required = false) Long userId){

        Result result = new Result();
        Map<String, Object> condition = new HashMap<String, Object>();
        List<Yhq> yhqs;

        if (userId != null) {
            condition.put("userId", userId);
        }

       try {
           yhqs =  yhqService.findList(condition);
           result.setObj(yhqs);
           result.setSuccess(true);
       } catch (Exception e){
           result.setMsg(e.getMessage());
       }
        return result;

    }


    /**
     * 添加優惠券
     *
     * @return
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestParam Long userId,@RequestParam Long yhhdId) {

        Result result = new Result();
        try {

            if(yhqService.check(userId,yhhdId) != null) {
                result.setSuccess(false);
                result.setMsg("该活动已经参与过，不可重复获得优惠券");
                return result;
            }
            User user = userService.findUserById(userId);
            Yhhd yhhd = yhhdService.findById(yhhdId);
            Yhq yhq = yhqService.initFromYhhdAndUser(yhhd,user);
            yhq.setYhm(UUID.randomUUID().toString());
            yhqService.save(yhq);
            result.setSuccess(true);
        } catch (Exception e){
            result.setMsg(e.getMessage());

        }

        return result;

    }


    /**
     * 编辑行业
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Result edit(@RequestBody Yhhd yhhd) {
        Result result = new Result();
        try {
            yhhdService.save(yhhd);
            result.setSuccess(true);
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }






}
