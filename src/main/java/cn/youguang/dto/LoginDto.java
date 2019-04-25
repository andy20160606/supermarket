package cn.youguang.dto;

public class LoginDto {


    private String wxcode;  //微信code
    private String logintype; //登录类型
    private String username;
    private String password;
    private String verifycode;


    public String getWxcode() {
        return wxcode;
    }

    public void setWxcode(String wxcode) {
        this.wxcode = wxcode;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "wxcode='" + wxcode + '\'' +
                ", logintype='" + logintype + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verifycode='" + verifycode + '\'' +
                '}';
    }
}
