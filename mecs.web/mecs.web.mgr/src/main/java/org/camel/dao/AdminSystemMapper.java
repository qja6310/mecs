package org.camel.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.dto.MenuDto;

/**
 * 登录主页dao

     * <p>Title : AdminSystemMapper</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月22日 下午4:04:07

     * @version : 0.0.1
 */
public interface AdminSystemMapper {

	/**
	 * 获取登录者的角色ID
	 * @param adminId
	 * @return
	 */
	List<String> getRoleIdByAdminId(@Param("adminId") String adminId);
	
	/**
	 * 获取menudto
	 * @param roleIdList
	 * @return
	 */
	Set<MenuDto> getMenuDtoByRoleId(@Param("roleIdList") List<String> roleIdList);
}
