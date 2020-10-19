package mecs.camel.model.dto;

import java.util.List;
import mecs.camel.model.Card;
import mecs.camel.model.MecRecord;
import mecs.camel.model.User;

public class SettleAccountsUserDto extends User{
	//一个人记录表
	private List<MecRecord> mecRecordlist;

	//一个人可能有多张卡 这个是卡号去查,只能是一张卡
	//List<Card> cardList;
	private Card card;
	
	//记得点下 父类的to String() 点下
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SettleAccountsUserDto [mecRecordlist=");
		builder.append(mecRecordlist);
		builder.append(", card=");
		builder.append(card);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public SettleAccountsUserDto() {
		super();
	}

	public SettleAccountsUserDto(List<MecRecord> mecRecordlist, Card card) {
		super();
		this.mecRecordlist = mecRecordlist;
		this.card = card;
	}

	public List<MecRecord> getMecRecordlist() {
		return mecRecordlist;
	}

	public void setMecRecordlist(List<MecRecord> mecRecordlist) {
		this.mecRecordlist = mecRecordlist;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	
	
	
	


	
	
}
