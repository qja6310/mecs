package mecs.camel.model;

import java.io.Serializable;

/**
 * 体检卡表
 * 
 * @author Administrator
 *
 */
public class Card implements Serializable{
	private String cardId;// 卡id
	private String cardNum;// 卡号
	private String userId;// 体检人id
	private String balance;// 卡余额
	private String enterAdminId;// 入库人id
	private String sellAdminId;// 售卡人id
	private String cardState;// 卡状态
	private String cardMemo;// 备注
	private String cardCtime;// 卡创建时间
	private String cardUtime;// 卡出售时间

	public Card() {
		// TODO Auto-generated constructor stub
	}

	public Card(String cardId, String cardNum, String userId, String balance, String enterAdminId, String sellAdminId,
			String cardState, String cardMemo, String cardCtime, String cardUtime) {
		super();
		this.cardId = cardId;
		this.cardNum = cardNum;
		this.userId = userId;
		this.balance = balance;
		this.enterAdminId = enterAdminId;
		this.sellAdminId = sellAdminId;
		this.cardState = cardState;
		this.cardMemo = cardMemo;
		this.cardCtime = cardCtime;
		this.cardUtime = cardUtime;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getEnterAdminId() {
		return enterAdminId;
	}

	public void setEnterAdminId(String enterAdminId) {
		this.enterAdminId = enterAdminId;
	}

	public String getSellAdminId() {
		return sellAdminId;
	}

	public void setSellAdminId(String sellAdminId) {
		this.sellAdminId = sellAdminId;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public String getCardMemo() {
		return cardMemo;
	}

	public void setCardMemo(String cardMemo) {
		this.cardMemo = cardMemo;
	}

	public String getCardCtime() {
		return cardCtime;
	}

	public void setCardCtime(String cardCtime) {
		this.cardCtime = cardCtime;
	}

	public String getCardUtime() {
		return cardUtime;
	}

	public void setCardUtime(String cardUtime) {
		this.cardUtime = cardUtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card [cardId=");
		builder.append(cardId);
		builder.append(", cardNum=");
		builder.append(cardNum);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", enterAdminId=");
		builder.append(enterAdminId);
		builder.append(", sellAdminId=");
		builder.append(sellAdminId);
		builder.append(", cardState=");
		builder.append(cardState);
		builder.append(", cardMemo=");
		builder.append(cardMemo);
		builder.append(", cardCtime=");
		builder.append(cardCtime);
		builder.append(", cardUtime=");
		builder.append(cardUtime);
		builder.append("]");
		return builder.toString();
	}

}
