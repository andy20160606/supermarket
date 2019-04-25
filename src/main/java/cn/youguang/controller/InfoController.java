package cn.youguang.controller;


import cn.youguang.entity.Info;
import cn.youguang.service.*;
import cn.youguang.util.Result;
import cn.youguang.util.StringUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description：行业管理
 */
@Controller
@RequestMapping("/info")
@Api(value = "信息Controller",tags = {"信息操作接口 key-val"})
public class InfoController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);



    @Autowired
    private InfoService infoService;

    /**
     * 添加信息
     *
     * @return
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Info info) {

        Result result = new Result();
        try {

            if(infoService.findByKey(info.getKey()).size() != 0){
                result.setMsg("key is already exist!");
                return result;
            }
            infoService.save(info);
            result.setSuccess(true);
        } catch (Exception e){
            result.setMsg(e.getMessage());

        }

        return result;

    }


    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Info info) {

        Result result = new Result();
        try {
            infoService.save(info);
            result.setSuccess(true);
        } catch (Exception e){
            result.setMsg(e.getMessage());

        }

        return result;

    }


    @RequestMapping(value = "/updateByKey" , method = RequestMethod.POST)
    @ResponseBody
    public Result updateByKey(@RequestBody Info info) {

        Result result = new Result();
        try {
            List<Info> infodbs = infoService.findByKey(info.getKey());

            Info infodb = infodbs.size() == 0 ?  null : infodbs.get(0);
            infodb.setVal(info.getVal());
            infoService.save(infodb);
            result.setSuccess(true);
        } catch (Exception e){
            result.setMsg(e.getMessage());

        }

        return result;

    }



    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@RequestParam String key) {
        Result result = new Result();
        try {
            infoService.deleteByKey(key);
            result.setSuccess(true);
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }


    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public Result get(@RequestParam String key) {
        Result result = new Result();
        try {
            List<Info>infos = infoService.findByKey(key);
            Info info = infos.size()>0? infos.get(0) : null;
            result.setObj(info);
            result.setSuccess(true);
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }


    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findByKeyContaining",method = RequestMethod.GET)
    @ResponseBody
    public Result findByKeyContaining(@RequestParam String key) {
        Result result = new Result();
        try {
            List<Info>infos = infoService.findByKeyContaining(key);
            result.setObj(infos);
            result.setSuccess(true);
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }







    /**
     * 信息值递增 + 1
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/ppByKey",method = RequestMethod.GET)
    @ResponseBody
    public Result ppByKey(@RequestParam String key) {
        Result result = new Result();
        try {
            List<Info>infos = infoService.findByKey(key);
            Info info = infos.size()>0? infos.get(0) : null;
            if(info == null || !StringUtils.isNumeric(info.getVal())){
                result.setMsg("不合法的key");
                return  result;
            }
            info.setVal(Integer.parseInt(info.getVal()) + 1 + "");  //递增+1
            infoService.save(info);
            result.setSuccess(true);
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }




}
