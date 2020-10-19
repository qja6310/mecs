package mecs.camel.model;

/**
 * 体检项目
 * 
 * @author Administrator
 *
 */
public class Items {
	private String itemsId;// 体检项目id
	private String itemsName;// 体检项目名字
	private String itemsPrice;// 体检项目价格
	private String depId;// 项目所属科室id
	private String itemsDemo;// 备注
	private String itemsCtime;// 创建时间
	private String itemsUtime;// 修改时间

	public Items() {
	}

	public Items(String itemsId, String itemsName, String itemsPrice, String depId, String itemsDemo, String itemsCtime,
			String itemsUtime) {
		super();
		this.itemsId = itemsId;
		this.itemsName = itemsName;
		this.itemsPrice = itemsPrice;
		this.depId = depId;
		this.itemsDemo = itemsDemo;
		this.itemsCtime = itemsCtime;
		this.itemsUtime = itemsUtime;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getItemsPrice() {
		return itemsPrice;
	}

	public void setItemsPrice(String itemsPrice) {
		this.itemsPrice = itemsPrice;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getItemsDemo() {
		return itemsDemo;
	}

	public void setItemsDemo(String itemsDemo) {
		this.itemsDemo = itemsDemo;
	}

	public String getItemsCtime() {
		return itemsCtime;
	}

	public void setItemsCtime(String itemsCtime) {
		this.itemsCtime = itemsCtime;
	}

	public String getItemsUtime() {
		return itemsUtime;
	}

	public void setItemsUtime(String itemsUtime) {
		this.itemsUtime = itemsUtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Items [itemsId=");
		builder.append(itemsId);
		builder.append(", itemsName=");
		builder.append(itemsName);
		builder.append(", itemsPrice=");
		builder.append(itemsPrice);
		builder.append(", depId=");
		builder.append(depId);
		builder.append(", itemsDemo=");
		builder.append(itemsDemo);
		builder.append(", itemsCtime=");
		builder.append(itemsCtime);
		builder.append(", itemsUtime=");
		builder.append(itemsUtime);
		builder.append("]");
		return builder.toString();
	}

}
