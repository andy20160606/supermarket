package cn.youguang.shiro;

import cn.youguang.entity.User;
import cn.youguang.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {


    @Autowired
    private UserService userService;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.openid.sign.login}")
    private String wxsign;


    public boolean wxConnect(String wxopenid) {
        boolean ret = false;

        User user = userService.findByOpenid(wxopenid);
        if (user != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    @SuppressWarnings(value = "all")
    public boolean doCredentialsMatch(AuthenticationToken token,
                                      AuthenticationInfo info) {
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        System.out.println("upt-username=" + upt.getUsername());
        System.out.println("upt.getPassword="
                + String.valueOf(upt.getPassword()));
        System.out.println("upt.getCredentials" + upt.getCredentials());
        System.out.println("upt.getPrincipal=" + upt.getPrincipal());

        String user = upt.getUsername();
        String pass = String.valueOf(upt.getPassword());


        if (wxsign.equals(user)) {  //如果是微信标识登录
            return wxConnect(pass);
        }
        if (user.equals("root") && pass.equals("root")) {
            return true;
        }

//		if ("root".equals(user) && "pass".equals(pass))
//			return true;
//		else
//			return connect("121.41.41.30", "389", user, pass);

        return false;
    }

}
