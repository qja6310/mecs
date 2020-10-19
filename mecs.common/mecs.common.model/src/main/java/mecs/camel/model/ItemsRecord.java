package mecs.camel.model;

/**
 * 项目记录模型

     * <p>Title : ItemsRecord</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月16日 下午10:32:27

     * @version : 0.0.1
 */
public class ItemsRecord {

	private String irId;//项目记录ID
	private String mrId;//体检记录ID
	private String itemsName;//项目名
	private String itemsRes;//结果
	private String itemsReq;//建议
	private String itemsAdmin;//项目医生
	private String irState;//项目状态
	private String itemsPrice;//项目价格
	private String irTime;//体检时间
	private String irMemo;//备注
	private String uTime;//修改时间
	private String rmTime;//退费时间
	private String depId;//科室id
	public ItemsRecord() {
		super();
	}
	public ItemsRecord(String irId, String mrId, String itemsName, String itemsRes, String itemsReq, String itemsAdmin,
			String irState, String itemsPrice, String irTime, String irMemo, String uTime, String rmTime,
			String depId) {
		super();
		this.irId = irId;
		this.mrId = mrId;
		this.itemsName = itemsName;
		this.itemsRes = itemsRes;
		this.itemsReq = itemsReq;
		this.itemsAdmin = itemsAdmin;
		this.irState = irState;
		this.itemsPrice = itemsPrice;
		this.irTime = irTime;
		this.irMemo = irMemo;
		this.uTime = uTime;
		this.rmTime = rmTime;
		this.depId = depId;
	}
	public String getIrId() {
		return irId;
	}
	public void setIrId(String irId) {
		this.irId = irId;
	}
	public String getMrId() {
		return mrId;
	}
	public void setMrId(String mrId) {
		this.mrId = mrId;
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
	public String getItemsReq() {
		return itemsReq;
	}
	public void setItemsReq(String itemsReq) {
		this.itemsReq = itemsReq;
	}
	public String getItemsAdmin() {
		return itemsAdmin;
	}
	public void setItemsAdmin(String itemsAdmin) {
		this.itemsAdmin = itemsAdmin;
	}
	public String getIrState() {
		return irState;
	}
	public void setIrState(String irState) {
		this.irState = irState;
	}
	public String getItemsPrice() {
		return itemsPrice;
	}
	public void setItemsPrice(String itemsPrice) {
		this.itemsPrice = itemsPrice;
	}
	public String getIrTime() {
		return irTime;
	}
	public void setIrTime(String irTime) {
		this.irTime = irTime;
	}
	public String getIrMemo() {
		return irMemo;
	}
	public void setIrMemo(String irMemo) {
		this.irMemo = irMemo;
	}
	public String getuTime() {
		return uTime;
	}
	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	public String getRmTime() {
		return rmTime;
	}
	public void setRmTime(String rmTime) {
		this.rmTime = rmTime;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemsRecord [irId=");
		builder.append(irId);
		builder.append(", mrId=");
		builder.append(mrId);
		builder.append(", itemsName=");
		builder.append(itemsName);
		builder.append(", itemsRes=");
		builder.append(itemsRes);
		builder.append(", itemsReq=");
		builder.append(itemsReq);
		builder.append(", itemsAdmin=");
		builder.append(itemsAdmin);
		builder.append(", irState=");
		builder.append(irState);
		builder.append(", itemsPrice=");
		builder.append(itemsPrice);
		builder.append(", irTime=");
		builder.append(irTime);
		builder.append(", irMemo=");
		builder.append(irMemo);
		builder.append(", uTime=");
		builder.append(uTime);
		builder.append(", rmTime=");
		builder.append(rmTime);
		builder.append(", depId=");
		builder.append(depId);
		builder.append("]");
		return builder.toString();
	}
	
}
