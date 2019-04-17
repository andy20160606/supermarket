package cn.youguang.controller;


import cn.youguang.entity.Yhhd;
import cn.youguang.entity.Yhq;
import cn.youguang.service.HyService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：优惠活动
 */
@Controller
@RequestMapping("/yhhd")
@Api(value = "优惠活动Controller",tags = {"优惠活动操作接口"})
public class YhhdController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(YhhdController.class);



    @Autowired
    private HyService hyService;


    @Autowired
    private YhhdService yhhdService;


    @Autowired
    private YhqService yhqService;









    /**
     *  分页读取行业列表
     * @param hdmc
     * @param pageInfo
     * @return
     */

    @ApiOperation(value="分页获取优惠活动信息",notes="pageinfo必须给定nowpage（当前页），pagesize（每页的记录数信息）,sort为排序（默认id，可给可不给）")
    @RequestMapping(value = "datatables",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo dataTables(@ApiParam(name="hdmc",value="活动名称")@RequestParam(required = false) String hdmc, @ModelAttribute PageInfo pageInfo) {


        Map<String, Object> condition = new HashMap<String, Object>();

        if (hdmc != null) {
            condition.put("hdmc", hdmc);
        }
        pageInfo.setCondition(condition);
        yhhdService.findDataTables(pageInfo);
        return pageInfo;
    }


    @ApiOperation(value="集合获取活动信息",notes="hdmc为查询条件")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@ApiParam(name="hdmc",value="活动名称") @RequestParam(required = false) String hdmc){

        Result result = new Result();
        Map<String, Object> condition = new HashMap<String, Object>();
        List<Yhhd> yhhds;

        if (hdmc != null) {
            condition.put("hymc", hdmc.trim());
        }

       try {
           yhhds =  yhhdService.findList(condition);
           result.setObj(yhhds);
           result.setSuccess(true);
       } catch (Exception e){
           result.setMsg(e.getMessage());
       }
        return result;

    }


    /**
     * 添加活动
     *
     * @return
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Yhhd yhhd) {

        Result result = new Result();
        try {
            yhhdService.save(yhhd);
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




    @ApiOperation(value="校验是否参与过活动，已获得优惠券",notes="用户Id,优惠活动Id为必填")
    @RequestMapping(value = "checkYhhd",method = RequestMethod.GET)
    @ResponseBody
    public Result checkYhhd(@ApiParam(name="userId",value="用户Id") @RequestParam Long userId,@ApiParam(name="yhhdId",value="优惠活动Id") @RequestParam Long yhhdId){

        Result result = new Result();
        Yhq yhq = yhqService.check(userId,yhhdId);

        if(yhq != null){
            result.setSuccess(true);
            return  result;
        }

        return result;

    }





}
