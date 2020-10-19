package mecs.camel.model.dto;

import java.util.List;

import mecs.camel.model.Item;
import mecs.camel.model.Items;

/**
 * 体检细项model 一个体检细项对应多个体检项目
 * 
 * @author Administrator
 *
 */
public class ItemsDto extends Items {
	private List<Item> itemList;// 体检项目集合

	public ItemsDto() {
		// TODO Auto-generated constructor stub
	}

	public ItemsDto(List<Item> itemList) {
		super();
		this.itemList = itemList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemsDto [itemList=");
		builder.append(itemList);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
