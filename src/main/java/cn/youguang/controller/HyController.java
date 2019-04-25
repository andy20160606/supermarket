package cn.youguang.controller;


import cn.youguang.entity.Hy;
import cn.youguang.service.HyService;
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
 * @description：行业管理
 */
@Controller
@RequestMapping("/hy")
@Api(value = "行业Controller",tags = {"行业操作接口"})
public class HyController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HyController.class);



    @Autowired
    private HyService hyService;


    /**
     *  分页读取行业列表
     * @param hymc
     * @param pageInfo
     * @return
     */

    @ApiOperation(value="分页获取行业信息",notes="pageinfo必须给定nowpage（当前页），pagesize（每页的记录数信息）,sort为排序（默认id，可给可不给）")
    @RequestMapping(value = "datatables",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo dataTables(@ApiParam(name="hymc",value="行业名称")@RequestParam(required = false) String hymc, @ModelAttribute PageInfo pageInfo) {


        Map<String, Object> condition = new HashMap<String, Object>();

        if (hymc != null) {
            condition.put("hymc", hymc);
        }
        pageInfo.setCondition(condition);
        hyService.findDataTables(pageInfo);
        return pageInfo;
    }


    @ApiOperation(value="集合获取行业信息",notes="hymc为查询条件")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@ApiParam(name="hymc",value="行业名称") @RequestParam(required = false) String hymc){

        Result result = new Result();
        Map<String, Object> condition = new HashMap<String, Object>();
        List<Hy> hys;

        if (hymc != null) {
            condition.put("hymc", hymc.trim());
        }

       try {
           hys =  hyService.findList(condition);
           result.setObj(hys);
           result.setSuccess(true);
       } catch (Exception e){
           result.setMsg(e.getMessage());
       }
        return result;

    }


    /**
     * 添加行业
     *
     * @return
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Hy hy) {

        Result result = new Result();
        try {
            hyService.save(hy);
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
    public Result edit(@RequestBody Hy hy) {
        Result result = new Result();
        try {
            hyService.save(hy);
            result.setSuccess(true);
            result.setMsg("修改成功！");
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }

    /**
     * 删除行业
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Long id) {
        Result result = new Result();
        try {
            hyService.delete(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
        } catch (RuntimeException e) {
            LOGGER.error("删除行业失败：{}", e);
            result.setMsg(e.getMessage());

        }
        return result;
    }


}
