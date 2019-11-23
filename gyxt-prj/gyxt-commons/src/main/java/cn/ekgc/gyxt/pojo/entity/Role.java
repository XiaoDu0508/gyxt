package cn.ekgc.gyxt.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <b>角色实体类</b>
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long roleId;     			// 角色主键
	private String roleName;			// 角色名称
	private List<Menu> menuList;		// 菜单列表
	private Status status;				// 状态
	private Date createTime;			// 创建时间
	private Date updateTime;			// 修改时间
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
