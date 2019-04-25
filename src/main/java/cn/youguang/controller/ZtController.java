package cn.youguang.controller;


import cn.youguang.entity.Zt;
import cn.youguang.service.HyService;
import cn.youguang.service.ZtService;
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
 * @description：状态管理
 */
@Controller
@RequestMapping("/zt")
@Api(value = "状态Controller",tags = {"状态操作接口"})
public class ZtController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ZtController.class);



    @Autowired
    private HyService hyService;


    @Autowired
    private ZtService ztService;


    /**
     *  分页读取状态列表
     * @param ztmc
     * @param pageInfo
     * @return
     */

    @ApiOperation(value="分页获取状态信息",notes="pageinfo必须给定nowpage（当前页），pagesize（每页的记录数信息）,sort为排序（默认id，可给可不给）")
    @RequestMapping(value = "datatables",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo dataTables(@ApiParam(name="ztmc",value="状态名称")@RequestParam(required = false) String ztmc, @ModelAttribute PageInfo pageInfo) {


        Map<String, Object> condition = new HashMap<String, Object>();

        if (ztmc != null) {
            condition.put("ztmc", ztmc);
        }
        pageInfo.setCondition(condition);
        ztService.findDataTables(pageInfo);
        return pageInfo;
    }


    @ApiOperation(value="集合获取状态信息",notes="hymc为查询条件")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@ApiParam(name="ztmc",value="状态名称") @RequestParam(required = false) String ztmc){

        Result result = new Result();
        Map<String, Object> condition = new HashMap<String, Object>();
        List<Zt> zts;

        if (ztmc != null) {
            condition.put("ztmc", ztmc.trim());
        }

       try {
           zts =  ztService.findList(condition);
           result.setObj(zts);
           result.setSuccess(true);
       } catch (Exception e){
           result.setMsg(e.getMessage());
       }
        return result;

    }


    /**
     * 添加状态
     *
     * @return
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Zt zt) {

        Result result = new Result();
        try {
            ztService.save(zt);
            result.setSuccess(true);
        } catch (Exception e){
            result.setMsg(e.getMessage());

        }

        return result;

    }


    /**
     * 编辑状态
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Result edit(@RequestBody Zt zt) {
        Result result = new Result();
        try {
            ztService.save(zt);
            result.setSuccess(true);
            result.setMsg("修改成功！");
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }

    /**
     * 删除状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Long id) {
        Result result = new Result();
        try {
            ztService.deleteById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
        } catch (RuntimeException e) {
            LOGGER.error("删除用户失败：{}", e);
            result.setMsg(e.getMessage());

        }
        return result;
    }


}
