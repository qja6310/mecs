package mecs.camel.model;

/**
 * 对账表model
 * 
 * @author Administrator
 *
 */
public class Account {
	private String accId;// 对账id
	private String cardNum;// 对账卡号
	private String accType;// 对账类型
	private String accDescribe;// 对账描述
	private String accMoney;// 金额
	private String accMemo;// 备注
	private String accCtime;// 创建时间

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String accId, String cardNum, String accType, String accDescribe, String accMoney, String accMemo,
			String accCtime) {
		super();
		this.accId = accId;
		this.cardNum = cardNum;
		this.accType = accType;
		this.accDescribe = accDescribe;
		this.accMoney = accMoney;
		this.accMemo = accMemo;
		this.accCtime = accCtime;
	}

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getAccDescribe() {
		return accDescribe;
	}

	public void setAccDescribe(String accDescribe) {
		this.accDescribe = accDescribe;
	}

	public String getAccMoney() {
		return accMoney;
	}

	public void setAccMoney(String accMoney) {
		this.accMoney = accMoney;
	}

	public String getAccMemo() {
		return accMemo;
	}

	public void setAccMemo(String accMemo) {
		this.accMemo = accMemo;
	}

	public String getAccCtime() {
		return accCtime;
	}

	public void setAccCtime(String accCtime) {
		this.accCtime = accCtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [accId=");
		builder.append(accId);
		builder.append(", cardNum=");
		builder.append(cardNum);
		builder.append(", accType=");
		builder.append(accType);
		builder.append(", accDescribe=");
		builder.append(accDescribe);
		builder.append(", accMoney=");
		builder.append(accMoney);
		builder.append(", accMemo=");
		builder.append(accMemo);
		builder.append(", accCtime=");
		builder.append(accCtime);
		builder.append("]");
		return builder.toString();
	}

}
