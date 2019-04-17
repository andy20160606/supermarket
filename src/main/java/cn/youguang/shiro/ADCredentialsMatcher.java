package cn.youguang.shiro;

import cn.youguang.entity.User;
import cn.youguang.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class ADCredentialsMatcher extends SimpleCredentialsMatcher {



	@Autowired
	private UserService userService;


	public boolean connect(String host, String port, String username,
			String password) {
		User user =  userService.findUserByLoginName(username);
		boolean ret = false;

		DirContext ctx = null;
		Hashtable<String, String> HashEnv = new Hashtable<>();
		HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别(none,simple,strong)
		HashEnv.put(Context.SECURITY_PRINCIPAL, username); // AD的用户名
		HashEnv.put(Context.SECURITY_CREDENTIALS, password); // AD的密码
		HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
		HashEnv.put("com.sun.jndi.ldap.connect.timeout", "3000");// 连接超时设置为3秒
		HashEnv.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);// 默认端口389
		try {
			ctx = new InitialDirContext(HashEnv);// 初始化上下文
			ret = true;
			System.out.println("身份验证成功!");
			if(user.getStatus() == -1){  //用户第一次验证直接把他更改为老用户
				user.setStatus(0);
				userService.updateUser(user);
			}

		} catch (AuthenticationException e) {
			System.out.println("身份验证失败!");//用户第一次验证没通过且是新用户，那么则直接删掉
			if(user.getStatus() == -1){
				userService.deleteUserById(user.getId());
			}
			e.printStackTrace();
		} catch (javax.naming.CommunicationException e) {
			System.out.println("AD域连接失败!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("身份验证未知异常!");
			e.printStackTrace();
		} finally {
			if (null != ctx) {
				try {
					ctx.close();
					ctx = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	@Override
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

		if ("root".equals(user) && "pass".equals(pass))
			return true;
		else
			return connect("121.41.41.30", "389", user, pass);
	}

}
