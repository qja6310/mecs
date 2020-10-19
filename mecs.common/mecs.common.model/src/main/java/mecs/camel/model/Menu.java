package mecs.camel.model;
/**
 * 菜单model
 * @author Administrator
 *
 */
public class Menu {
	  private String menuId;//菜单id
	  private String menuName;//菜单名字
	  private String menuPid;//菜单父id
	  private String menuUrl;//菜单路径
	  private String menuIcon;//菜单图标代码
	  private String menuMemo;//备注
	  private String menuCtime;//创建时间
	  private String menuUtime;//修改时间
	  public Menu() {
		// TODO Auto-generated constructor stub
	}
	public Menu(String menuId, String menuName, String menuPid, String menuUrl, String menuIcon, String menuMemo,
			String menuCtime, String menuUtime) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPid = menuPid;
		this.menuUrl = menuUrl;
		this.menuIcon = menuIcon;
		this.menuMemo = menuMemo;
		this.menuCtime = menuCtime;
		this.menuUtime = menuUtime;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPid() {
		return menuPid;
	}
	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuMemo() {
		return menuMemo;
	}
	public void setMenuMemo(String menuMemo) {
		this.menuMemo = menuMemo;
	}
	public String getMenuCtime() {
		return menuCtime;
	}
	public void setMenuCtime(String menuCtime) {
		this.menuCtime = menuCtime;
	}
	public String getMenuUtime() {
		return menuUtime;
	}
	public void setMenuUtime(String menuUtime) {
		this.menuUtime = menuUtime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [menuId=");
		builder.append(menuId);
		builder.append(", menuName=");
		builder.append(menuName);
		builder.append(", menuPid=");
		builder.append(menuPid);
		builder.append(", menuUrl=");
		builder.append(menuUrl);
		builder.append(", menuIcon=");
		builder.append(menuIcon);
		builder.append(", menuMemo=");
		builder.append(menuMemo);
		builder.append(", menuCtime=");
		builder.append(menuCtime);
		builder.append(", menuUtime=");
		builder.append(menuUtime);
		builder.append("]");
		return builder.toString();
	}
	
}
