package mecs.camel.model;

/**
 * 套餐表
 * 
 * @author Administrator
 *
 */
public class Combo {
	private String comboId;// 套餐id
	private String comboName;// 套餐名
	private String comboPrice;// 套餐价格
	private String comboState;// 套餐状态
	private String comboMemo;// 备注
	private String comboCtime;// 创建时间
	private String comboUtime;// 修改时间

	public Combo() {
		// TODO Auto-generated constructor stub
	}

	public Combo(String comboId, String comboName, String comboPrice, String comboState, String comboMemo,
			String comboCtime, String comboUtime) {
		super();
		this.comboId = comboId;
		this.comboName = comboName;
		this.comboPrice = comboPrice;
		this.comboState = comboState;
		this.comboMemo = comboMemo;
		this.comboCtime = comboCtime;
		this.comboUtime = comboUtime;
	}

	public String getComboId() {
		return comboId;
	}

	public void setComboId(String comboId) {
		this.comboId = comboId;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public String getComboPrice() {
		return comboPrice;
	}

	public void setComboPrice(String comboPrice) {
		this.comboPrice = comboPrice;
	}

	public String getComboState() {
		return comboState;
	}

	public void setComboState(String comboState) {
		this.comboState = comboState;
	}

	public String getComboMemo() {
		return comboMemo;
	}

	public void setComboMemo(String comboMemo) {
		this.comboMemo = comboMemo;
	}

	public String getComboCtime() {
		return comboCtime;
	}

	public void setComboCtime(String comboCtime) {
		this.comboCtime = comboCtime;
	}

	public String getComboUtime() {
		return comboUtime;
	}

	public void setComboUtime(String comboUtime) {
		this.comboUtime = comboUtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Combo [comboId=");
		builder.append(comboId);
		builder.append(", comboName=");
		builder.append(comboName);
		builder.append(", comboPrice=");
		builder.append(comboPrice);
		builder.append(", comboState=");
		builder.append(comboState);
		builder.append(", comboMemo=");
		builder.append(comboMemo);
		builder.append(", comboCtime=");
		builder.append(comboCtime);
		builder.append(", comboUtime=");
		builder.append(comboUtime);
		builder.append("]");
		return builder.toString();
	}

}
