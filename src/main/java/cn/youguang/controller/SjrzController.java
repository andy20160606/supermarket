package cn.youguang.controller;


import cn.youguang.dto.SjzlDto;
import cn.youguang.entity.Cp;
import cn.youguang.entity.Sjrz;
import cn.youguang.entity.Yhhd;
import cn.youguang.service.*;
import cn.youguang.util.DateUtil;
import cn.youguang.util.Result;
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
import java.util.Map;

/**
 * @description：转发日志
 */
@Controller
@RequestMapping("/sjrz")
@Api(value = "数据日志Controller", tags = {"数据日志操作接口"})
public class SjrzController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SjrzController.class);


    @Autowired
    private ZfrzService zfrzService;

    @Autowired
    private UserService userService;

    @Autowired
    private CpService cpService;

    @Autowired
    private YhhdService yhhdService;

    @Autowired
    private SjrzService sjrzService;


    /**
     * 更新本天数据统计
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public Result add(@RequestParam(name = "cpId", required = false) Long cpId, @RequestParam(name = "hdId", required = false) Long hdId, @RequestParam(name = "sjxw") String sjxw) {

        Result result = new Result();
        try {

            Cp cp = cpId == null ? null : cpService.findById(cpId);
            Yhhd yhhd = hdId == null ? null : yhhdService.findById(hdId);
            Date sjrq = DateUtil.getTimesmorning();
            Sjrz sjrzdb = sjrzService.findByCpAndYhhdAndSjrqAndSjxw(cp, yhhd, sjrq, sjxw);
            if (sjrzdb == null) {
                sjrzdb = new Sjrz();
                sjrzdb.setCp(cp);
                sjrzdb.setYhhd(yhhd);
                sjrzdb.setCount(1L);
                sjrzdb.setSjrq(sjrq);
                sjrzdb.setSjxw(sjxw);
            } else {
                sjrzdb.setCount(sjrzdb.getCount() + 1L);
            }
            sjrzService.save(sjrzdb);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg(e.getMessage());

        }

        return result;

    }


    //todo

    @RequestMapping(value = "/getByMonth", method = RequestMethod.GET)
    @ResponseBody
    public Result findById(@RequestParam(name = "date") Date date) {
        Result result = new Result();
        try {
            SjzlDto sjzlDto = sjrzService.findByMonth(date);
            result.setObj(sjzlDto);
            result.setSuccess(true);

        } catch (Exception e) {

            result.setMsg(e.getMessage());

        }
        return result;
    }


    @RequestMapping(value = "/getHdllByMonth", method = RequestMethod.GET)
    @ResponseBody
    public Result getHdllByMonth(@RequestParam(name = "date") Date date) {
        Result result = new Result();
        try {
            Map<String, List> zlrs = sjrzService.getHdllByMonth(date);
            result.setObj(zlrs);
            result.setSuccess(true);

        } catch (Exception e) {

            result.setMsg(e.getMessage());

        }
        return result;
    }

    @RequestMapping(value = "/getCpllByMonth", method = RequestMethod.GET)
    @ResponseBody
    public Result getCpllByMonth(@RequestParam(name = "date") Date date) {
        Result result = new Result();
        try {
            Map<String, List> zlrs = sjrzService.getCpllByMonth(date);
            result.setObj(zlrs);
            result.setSuccess(true);

        } catch (Exception e) {

            result.setMsg(e.getMessage());

        }
        return result;
    }


    @RequestMapping(value = "/getZlrsByMonth", method = RequestMethod.GET)
    @ResponseBody
    public Result getZlrsByMonth(@RequestParam(name = "date") Date date) {
        Result result = new Result();
        try {
            Map<String, List> zlrs = sjrzService.getZlrsByMonth(date);
            result.setObj(zlrs);
            result.setSuccess(true);

        } catch (Exception e) {

            result.setMsg(e.getMessage());

        }
        return result;
    }


    @RequestMapping(value = "/getHlrsByMonth", method = RequestMethod.GET)
    @ResponseBody
    public Result getHlrsByMonth(@RequestParam(name = "date") Date date) {
        Result result = new Result();
        try {
            Map<String, List> hlrs = sjrzService.getHlrsByMonth(date);
            result.setObj(hlrs);
            result.setSuccess(true);

        } catch (Exception e) {

            result.setMsg(e.getMessage());

        }
        return result;
    }

    @RequestMapping(value = "/getZfslByMonth", method = RequestMethod.GET)
    @ResponseBody
    public Result getZfslByMonth(@RequestParam(name = "date") Date date) {
        Result result = new Result();
        try {
            Map<String, List> zfsl = sjrzService.getZfslByMonth(date);
            result.setObj(zfsl);
            result.setSuccess(true);

        } catch (Exception e) {

            result.setMsg(e.getMessage());

        }
        return result;
    }

}
