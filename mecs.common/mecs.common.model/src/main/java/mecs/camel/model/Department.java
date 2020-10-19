package mecs.camel.model;
/**
 * 科室
 * @author Administrator
 *
 */
public class Department {
     private String depId;//科室id
     private String depName;//科室名
     private String depState;//科室状态
     private String depMemo;//科室备注
     private String depCtime;//科室创建时间
     private String depUtime;//科室修改时间
     public Department() {
		// TODO Auto-generated constructor stub
	}

     
	public Department(String depId, String depName, String depState, String depMemo, String depCtime, String depUtime) {
		super();
		this.depId = depId;
		this.depName = depName;
		this.depState = depState;
		this.depMemo = depMemo;
		this.depCtime = depCtime;
		this.depUtime = depUtime;
	}

	
	public String getDepState() {
		return depState;
	}


	public void setDepState(String depState) {
		this.depState = depState;
	}


	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepMemo() {
		return depMemo;
	}
	public void setDepMemo(String depMemo) {
		this.depMemo = depMemo;
	}
	public String getDepCtime() {
		return depCtime;
	}
	public void setDepCtime(String depCtime) {
		this.depCtime = depCtime;
	}
	public String getDepUtime() {
		return depUtime;
	}
	public void setDepUtime(String depUtime) {
		this.depUtime = depUtime;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [depId=");
		builder.append(depId);
		builder.append(", depName=");
		builder.append(depName);
		builder.append(", depState=");
		builder.append(depState);
		builder.append(", depMemo=");
		builder.append(depMemo);
		builder.append(", depCtime=");
		builder.append(depCtime);
		builder.append(", depUtime=");
		builder.append(depUtime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
