package cn.youguang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_res")
public class Resource extends IdEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@NotBlank
    private String name;
    private Integer seq;
    private String description;
    private Integer status;
    private String url;
    @JsonProperty("iconCls")
    private String icon;
    private Integer resourcetype;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pid")
    private Resource parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @OrderBy("id")
    private List<Resource> children;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;
    @JsonIgnore
    @ManyToMany( cascade={CascadeType.MERGE},fetch=FetchType.LAZY,mappedBy="ress")
    List<Role> roles;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(Integer resourcetype) {
		this.resourcetype = resourcetype;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	public List<Resource> getChildren() {
		return children;
	}
	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	
}
