package mecs.camel.model;
/**
 * 体检细项model
 * @author Administrator
 *
 */
public class Item {
	private String itemId;//细项id
	private String itemName;//细项名称
	private String itemUnit;//细项项目单位
	private String itemMemo;//备注
	private String itemCtime;//创建时间
	private String itemUtime;//修改时间
	public Item() {
		// TODO Auto-generated constructor stub
	}
	public Item(String itemId, String itemName, String itemUnit, String itemMemo, String itemCtime, String itemUtime) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemUnit = itemUnit;
		this.itemMemo = itemMemo;
		this.itemCtime = itemCtime;
		this.itemUtime = itemUtime;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getItemMemo() {
		return itemMemo;
	}
	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}
	public String getItemCtime() {
		return itemCtime;
	}
	public void setItemCtime(String itemCtime) {
		this.itemCtime = itemCtime;
	}
	public String getItemUtime() {
		return itemUtime;
	}
	public void setItemUtime(String itemUtime) {
		this.itemUtime = itemUtime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [itemId=");
		builder.append(itemId);
		builder.append(", itemName=");
		builder.append(itemName);
		builder.append(", itemUnit=");
		builder.append(itemUnit);
		builder.append(", itemMemo=");
		builder.append(itemMemo);
		builder.append(", itemCtime=");
		builder.append(itemCtime);
		builder.append(", itemUtime=");
		builder.append(itemUtime);
		builder.append("]");
		return builder.toString();
	}

}
