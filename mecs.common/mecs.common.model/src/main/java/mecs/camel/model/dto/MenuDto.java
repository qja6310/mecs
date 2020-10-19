package mecs.camel.model.dto;

import java.util.List;

import mecs.camel.model.Menu;

/**
 * 拓展Menu

     * <p>Title : MenuDto</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月22日 下午3:57:45

     * @version : 0.0.1
 */
public class MenuDto extends Menu {

	public List<Menu> menuSet;

	public MenuDto() {
		super();
	}

	public MenuDto(List<Menu> menuSet) {
		super();
		this.menuSet = menuSet;
	}

	public List<Menu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(List<Menu> menuSet) {
		this.menuSet = menuSet;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuDto [menuSet=");
		builder.append(menuSet);
		builder.append("]");
		return builder.toString();
	}
	
}
