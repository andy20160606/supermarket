package cn.youguang.controller;


import cn.youguang.entity.Cp;
import cn.youguang.entity.User;
import cn.youguang.service.CpService;
import cn.youguang.util.PageInfo;
import cn.youguang.util.Result;
import cn.youguang.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import weixin.popular.api.TicketAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.ticket.Ticket;
import weixin.popular.bean.token.Token;
import weixin.popular.util.JsUtil;
import weixin.popular.util.JsonUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description：微信信息获取接口
 */
@Controller
@RequestMapping("/wx")
@Api(value = "微信Controller",tags = {"微信操作接口"})
public class WxController {


    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);


    @RequestMapping(value = "getJsCfgByUrl", method = RequestMethod.GET)
    @ResponseBody
    public Result getJsCfgByUrl(@RequestParam String url) {
        Result result = new Result();
        try {
            Token token = TokenAPI.token(appid,secret);
            Ticket ticket = TicketAPI.ticketGetticket(token.getAccess_token());
            String jsCfg = JsUtil.generateConfigJson(ticket.getTicket(),true,appid,url,null);
            HashMap<String,String> jsCfgMap = JsonUtil.parseObject(jsCfg,HashMap.class);
            result.setObj(jsCfgMap);
            result.setSuccess(true);
        } catch (Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }




}
