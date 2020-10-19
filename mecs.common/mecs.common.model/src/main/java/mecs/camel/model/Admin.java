package mecs.camel.model;

import java.io.Serializable;

/**
 * 管理员model
 * 
 * @author Administrator
 *
 */
public class Admin implements Serializable {
	private String adminId;// 工作员id
	private String adminAcc;// 账号
	private String adminPawd;// 密码
	private String adminName;// 姓名
	private String depId;// 科室id
	private String adminState;// 人员状态
	private String adminMemo;// 备注
	private String adminCtime;// 创建时间
	private String adminUtime;// 修改时间

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminId, String adminAcc, String adminPawd, String adminName, String depId, String adminState,
			String adminMemo, String adminCtime, String adminUtime) {
		super();
		this.adminId = adminId;
		this.adminAcc = adminAcc;
		this.adminPawd = adminPawd;
		this.adminName = adminName;
		this.depId = depId;
		this.adminState = adminState;
		this.adminMemo = adminMemo;
		this.adminCtime = adminCtime;
		this.adminUtime = adminUtime;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminAcc() {
		return adminAcc;
	}

	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}

	public String getAdminPawd() {
		return adminPawd;
	}

	public void setAdminPawd(String adminPawd) {
		this.adminPawd = adminPawd;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getAdminState() {
		return adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getAdminMemo() {
		return adminMemo;
	}

	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}

	public String getAdminCtime() {
		return adminCtime;
	}

	public void setAdminCtime(String adminCtime) {
		this.adminCtime = adminCtime;
	}

	public String getAdminUtime() {
		return adminUtime;
	}

	public void setAdminUtime(String adminUtime) {
		this.adminUtime = adminUtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Admin [adminId=");
		builder.append(adminId);
		builder.append(", adminAcc=");
		builder.append(adminAcc);
		builder.append(", adminPawd=");
		builder.append(adminPawd);
		builder.append(", adminName=");
		builder.append(adminName);
		builder.append(", depId=");
		builder.append(depId);
		builder.append(", adminState=");
		builder.append(adminState);
		builder.append(", adminMemo=");
		builder.append(adminMemo);
		builder.append(", adminCtime=");
		builder.append(adminCtime);
		builder.append(", adminUtime=");
		builder.append(adminUtime);
		builder.append("]");
		return builder.toString();
	}

}
