package mecs.camel.model;
/**
 * 

     * <p>Title : Log</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月13日 下午3:35:43

     * @version : 0.0.1
 */
public class Log {

	private String logId;//日志ID
	private String logUserId;//操作人
	private String logType;//日志类型,如登录,人员新增   直接写文字
	private String logRemark;//日志描述
	private String logMemo;//日志备注
	private String logCtime;//操作时间
	public Log() {
		// TODO Auto-generated constructor stub
	}
	public Log(String logId, String logUserId, String logType, String logRemark, String logMemo, String logCtime) {
		super();
		this.logId = logId;
		this.logUserId = logUserId;
		this.logType = logType;
		this.logRemark = logRemark;
		this.logMemo = logMemo;
		this.logCtime = logCtime;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getLogUserId() {
		return logUserId;
	}
	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogRemark() {
		return logRemark;
	}
	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}
	public String getLogMemo() {
		return logMemo;
	}
	public void setLogMemo(String logMemo) {
		this.logMemo = logMemo;
	}
	public String getLogCtime() {
		return logCtime;
	}
	public void setLogCtime(String logCtime) {
		this.logCtime = logCtime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FrontLog [logId=");
		builder.append(logId);
		builder.append(", logUserId=");
		builder.append(logUserId);
		builder.append(", logType=");
		builder.append(logType);
		builder.append(", logRemark=");
		builder.append(logRemark);
		builder.append(", logMemo=");
		builder.append(logMemo);
		builder.append(", logCtime=");
		builder.append(logCtime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
