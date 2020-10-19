package org.camel.service.impl;

import java.util.List;
import java.util.Set;

import org.camel.dao.AdminSystemMapper;
import org.camel.service.AdminSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.Admin;
import mecs.camel.model.dto.MenuDto;

@Service
public class AdminSystemServiceImpl implements AdminSystemService {

	@Autowired
	private AdminSystemMapper adminSystemMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<MenuDto> menuList(Admin admin) {
		// TODO Auto-generated method stub
		/**
		 * 获取角色ID
		 */
		List<String> roleIdList = adminSystemMapper.getRoleIdByAdminId(admin.getAdminId());
		/*
		 * 批量查找
		 */
		Set<MenuDto> menuList = adminSystemMapper.getMenuDtoByRoleId(roleIdList);
		return menuList;
	}

}
