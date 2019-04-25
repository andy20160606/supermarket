package cn.youguang.controller;


import cn.youguang.entity.Cp;
import cn.youguang.entity.User;
import cn.youguang.entity.Yhhd;
import cn.youguang.entity.Zfrz;
import cn.youguang.service.CpService;
import cn.youguang.service.UserService;
import cn.youguang.service.YhhdService;
import cn.youguang.service.ZfrzService;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Result;
import cn.youguang.util.StringUtils;
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
 * @description：转发日志
 */
@Controller
@RequestMapping("/zfrz")
@Api(value = "转发日志Controller", tags = {"转发日志操作接口"})
public class ZfrzController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ZfrzController.class);


    @Autowired
    private ZfrzService zfrzService;

    @Autowired
    private UserService userService;

    @Autowired
    private CpService cpService;

    @Autowired
    private YhhdService yhhdService;


    /**
     * 添加产品
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestParam(name = "userId") Long userId, @RequestParam(name = "cpId", required = false) Long cpId, @RequestParam(name = "hdId", required = false) Long hdId, @RequestBody Zfrz zfrz) {

        Result result = new Result();
        try {

            User user = userService.findUserById(userId);
            Cp cp = cpId == null ? null : cpService.findById(cpId);
            Yhhd yhhd = hdId == null ? null : yhhdService.findById(hdId);
            zfrz.setZfr(user);
            zfrz.setZfsj(new Date());
            zfrz.setCp(cp);
            zfrz.setYhhd(yhhd);
            zfrz.setLlcs(1L);
            zfrzService.save(zfrz);

            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg(e.getMessage());

        }

        return result;

    }


    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    @ResponseBody
    public Result findByUserId(@RequestParam Long userId) {
        Result result = new Result();
        try {


            List<Zfrz> zfrzs = zfrzService.findByUserId(userId);

            result.setSuccess(true);
            result.setObj(zfrzs);
        } catch (Exception e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }


    @ApiOperation(value = "分享浏览次数累加", notes = "必须给定唯一标识")
    @RequestMapping(value = "/ppLlcsByWybs", method = RequestMethod.GET)
    @ResponseBody
    public Result ppLlcsByWybs(@RequestParam String wybs) {
        Result result = new Result();
        try {


            Zfrz zfrz = zfrzService.findByWybs(wybs);
            zfrz.setLlcs(zfrz.getLlcs() + 1L);
            zfrzService.save(zfrz);

            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg(e.getMessage());

        }
        return result;
    }


}
