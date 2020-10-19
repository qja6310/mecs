package mecs.camel.model;
/**
 * 角色model
 * @author Administrator
 *
 */
public class Role {
     private String roleId;//角色id
     private String roleName;//角色名字
     private String roleMemo;//角色备注
     private String roleCtime;//创建时间
     private String roleUtime;//修改时间
     public Role() {
		// TODO Auto-generated constructor stub
	}
	public Role(String roleId, String roleName, String roleMemo, String roleCtime, String roleUtime) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleMemo = roleMemo;
		this.roleCtime = roleCtime;
		this.roleUtime = roleUtime;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleMemo() {
		return roleMemo;
	}
	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}
	public String getRoleCtime() {
		return roleCtime;
	}
	public void setRoleCtime(String roleCtime) {
		this.roleCtime = roleCtime;
	}
	public String getRoleUtime() {
		return roleUtime;
	}
	public void setRoleUtime(String roleUtime) {
		this.roleUtime = roleUtime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [roleId=");
		builder.append(roleId);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", roleMemo=");
		builder.append(roleMemo);
		builder.append(", roleCtime=");
		builder.append(roleCtime);
		builder.append(", roleUtime=");
		builder.append(roleUtime);
		builder.append("]");
		return builder.toString();
	}
	
}
