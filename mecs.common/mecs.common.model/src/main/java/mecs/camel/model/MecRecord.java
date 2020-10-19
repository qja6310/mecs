package mecs.camel.model;

/**
 *  
 *  体检记录模型

     * <p>Title : MecRecord</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月16日 下午10:06:32

     * @version : 0.0.1
 */
public class MecRecord {

	private String mrId;//体检记录ID
	private String mrNum;//体检号码
	private String cardId;//卡的ID
	private String comboName;//套餐名,可能会是一个集合,所以以","分割
	private String mrPrice;//体检价格
	private String bilAdmin;//开单
	private String saAdmin;//结算人
	private String docAdmin;//总检医生
	private String mrReq;//总检建议
	private String mrState;//状态
	private String bilTime;//开单时间
	private String saTime;//结算时间
	private String mrGuide;//指导
	private String mrMemo;//备注
	public MecRecord() {
		super();
	}
	public MecRecord(String mrId, String mrNum, String cardId, String comboName, String mrPrice, String bilAdmin,
			String saAdmin, String docAdmin, String mrReq, String mrState, String bilTime, String saTime, String mrGuide,
			String mrMemo) {
		super();
		this.mrId = mrId;
		this.mrNum = mrNum;
		this.cardId = cardId;
		this.comboName = comboName;
		this.mrPrice = mrPrice;
		this.bilAdmin = bilAdmin;
		this.saAdmin = saAdmin;
		this.docAdmin = docAdmin;
		this.mrReq = mrReq;
		this.mrState = mrState;
		this.bilTime = bilTime;
		this.saTime = saTime;
		this.mrGuide = mrGuide;
		this.mrMemo = mrMemo;
	}
	public String getMrId() {
		return mrId;
	}
	public void setMrId(String mrId) {
		this.mrId = mrId;
	}
	public String getMrNum() {
		return mrNum;
	}
	public void setMrNum(String mrNum) {
		this.mrNum = mrNum;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getComboName() {
		return comboName;
	}
	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	public String getMrPrice() {
		return mrPrice;
	}
	public void setMrPrice(String mrPrice) {
		this.mrPrice = mrPrice;
	}
	public String getBilAdmin() {
		return bilAdmin;
	}
	public void setBilAdmin(String bilAdmin) {
		this.bilAdmin = bilAdmin;
	}
	public String getSaAdmin() {
		return saAdmin;
	}
	public void setSaAdmin(String saAdmin) {
		this.saAdmin = saAdmin;
	}
	public String getDocAdmin() {
		return docAdmin;
	}
	public void setDocAdmin(String docAdmin) {
		this.docAdmin = docAdmin;
	}
	public String getMrReq() {
		return mrReq;
	}
	public void setMrReq(String mrReq) {
		this.mrReq = mrReq;
	}
	public String getMrState() {
		return mrState;
	}
	public void setMrState(String mrState) {
		this.mrState = mrState;
	}
	public String getBilTime() {
		return bilTime;
	}
	public void setBilTime(String bilTime) {
		this.bilTime = bilTime;
	}
	public String getSaTime() {
		return saTime;
	}
	public void setSaTime(String saTime) {
		this.saTime = saTime;
	}
	public String getMrMemo() {
		return mrMemo;
	}
	public void setMrMemo(String mrMemo) {
		this.mrMemo = mrMemo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MecRecord [mrId=");
		builder.append(mrId);
		builder.append(", mrNum=");
		builder.append(mrNum);
		builder.append(", cardId=");
		builder.append(cardId);
		builder.append(", comboName=");
		builder.append(comboName);
		builder.append(", mrPrice=");
		builder.append(mrPrice);
		builder.append(", bilAdmin=");
		builder.append(bilAdmin);
		builder.append(", saAdmin=");
		builder.append(saAdmin);
		builder.append(", docAdmin=");
		builder.append(docAdmin);
		builder.append(", mrReq=");
		builder.append(mrReq);
		builder.append(", mrState=");
		builder.append(mrState);
		builder.append(", bilTime=");
		builder.append(bilTime);
		builder.append(", saTime=");
		builder.append(saTime);
		builder.append(", mrGuide=");
		builder.append(mrGuide);
		builder.append(", mrMemo=");
		builder.append(mrMemo);
		builder.append("]");
		return builder.toString();
	}
	public String getMrGuide() {
		return mrGuide;
	}
	public void setMrGuide(String mrGuide) {
		this.mrGuide = mrGuide;
	}
	
}
