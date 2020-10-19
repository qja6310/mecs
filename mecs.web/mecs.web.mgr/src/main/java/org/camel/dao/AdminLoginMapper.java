package org.camel.dao;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Admin;
import mecs.camel.model.Log;

public interface AdminLoginMapper {
	
	
	/**
	 * 修改密码前面，先查询旧密码是否正确
	 * @param oldPsd
	 * @param adminId
	 * @return
	 */
	Admin queryOldPsd(@Param("oldPsd")String oldPsd,@Param("adminId")String adminId);
	
	
	/**
	 * 修改密码，
	 * @param adminId 用户ID
	 * @return 返回大于0，表示修改成功
	 */
	Integer changPsd(@Param("newPsd")String newPsd,@Param("adminId")String adminId);
	
	
	/**
	 * 通过 账户和密码查询到用户，查询的时候，没以非禁用状态为条件
	 * @param admin
	 * @return
	 */
	Admin loginQueryAdmin(Admin admin);
																	
	/**
	 * 查到用户之后判断用户是否为禁用的
	 * @param userId
	 * @return
	 */
	String queryAdminState(@Param("adminId")Integer adminId);
	
	/**
	 *  登录成功之后插入日志
	 * @param log
	 * @return
	 */
	Integer doLog(Log log);

}
