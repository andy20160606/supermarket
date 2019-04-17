package cn.youguang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="t_role")
public class Role extends IdEntity {

	@NotBlank
    private String name;
    private Integer seq;
    private String description;
    private Integer status;
    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY,mappedBy="roles")
	private List<User> users;

    @ManyToMany(fetch=FetchType.LAZY)
	private List<Resource> ress;


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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Resource> getRess() {
		return ress;
	}
	public void setRess(List<Resource> ress) {
		this.ress = ress;
	}


	

	

	
}
