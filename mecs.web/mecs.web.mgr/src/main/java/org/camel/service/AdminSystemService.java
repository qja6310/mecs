package org.camel.service;

import java.util.List;
import java.util.Set;

import mecs.camel.model.Admin;
import mecs.camel.model.dto.MenuDto;

public interface AdminSystemService {

	/**
	 * 根据登录者获取相应的菜单
	 * @param admin
	 * @return
	 */
	Set<MenuDto> menuList(Admin admin);
	
}
