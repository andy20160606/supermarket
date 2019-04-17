package cn.youguang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User extends IdEntity {

    @NotBlank
    private String loginname;
    private String loginpass;
    private String username;
    private String usertype; //用户类型
    private Integer sex;
    private Integer age;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdate;
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Organization> orgs;




    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cp> cps;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public List<Cp> getCps() {
        return cps;
    }

    public void setCps(List<Cp> cps) {
        this.cps = cps;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Organization> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<Organization> orgs) {
        this.orgs = orgs;
    }




    public String getOrgids() {
        String ret = null;
        for (Organization organization : orgs) {
            if (ret == null)
                ret = organization.getId().toString();
            else
                ret = ret + "," + organization.getId();
        }
        return ret;
    }

    public String getRoleids() {
        String ret = null;
        for (Role role : roles) {
            if (ret == null)
                ret = role.getId().toString();
            else
                ret = ret + "," + role.getId();
        }
        return ret;
    }




}
