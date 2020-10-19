package mecs.camel.model;

/**
 * 为方便体检综合查询,新建一个体检综合模型

     * <p>Title : MecSynthesize</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月19日 下午9:59:23

     * @version : 0.0.1
 */
public class MecSynthesize {

	private String userName;//用户名
	private String userPhone;//手机号码
	private String mecCode;//体检码
	private String itemsName;//体检项目
	private String itemsRes;//体检小结
	private String mrDoc;//体检总结医生
	private String irPeTime;//体检时间
	public MecSynthesize() {
		super();
	}
	public MecSynthesize(String userName, String userPhone, String mecCode, String itemsName, String itemsRes,
			String mrDoc, String irPeTime) {
		super();
		this.userName = userName;
		this.userPhone = userPhone;
		this.mecCode = mecCode;
		this.itemsName = itemsName;
		this.itemsRes = itemsRes;
		this.mrDoc = mrDoc;
		this.irPeTime = irPeTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getMecCode() {
		return mecCode;
	}
	public void setMecCode(String mecCode) {
		this.mecCode = mecCode;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public String getItemsRes() {
		return itemsRes;
	}
	public void setItemsRes(String itemsRes) {
		this.itemsRes = itemsRes;
	}
	public String getMrDoc() {
		return mrDoc;
	}
	public void setMrDoc(String mrDoc) {
		this.mrDoc = mrDoc;
	}
	public String getIrPeTime() {
		return irPeTime;
	}
	public void setIrPeTime(String irPeTime) {
		this.irPeTime = irPeTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MecSynthesize [userName=");
		builder.append(userName);
		builder.append(", userPhone=");
		builder.append(userPhone);
		builder.append(", mecCode=");
		builder.append(mecCode);
		builder.append(", itemsName=");
		builder.append(itemsName);
		builder.append(", itemsRes=");
		builder.append(itemsRes);
		builder.append(", mrDoc=");
		builder.append(mrDoc);
		builder.append(", irPeTime=");
		builder.append(irPeTime);
		builder.append("]");
		return builder.toString();
	}
	
	
}
