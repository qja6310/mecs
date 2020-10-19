package mecs.camel.model.dto;

import mecs.camel.model.Card;
import mecs.camel.model.Dict;
import mecs.camel.model.User;

public class CardDto extends Card {

	private User user;
	private Dict dict;

	public CardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardDto(User user, Dict dict) {
		super();
		this.user = user;
		this.dict = dict;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CardDto [user=");
		builder.append(user);
		builder.append(", dict=");
		builder.append(dict);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
