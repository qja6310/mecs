package org.camel.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Admin;
import mecs.camel.model.Department;
import mecs.camel.model.Role;

public interface PersonnelMgMapper {
	/**
	 * 人员管理动态查询
	 * 
	 * @param adName    名字
	 * @param adAcc     账号
	 * @param adDepId   科室ID
	 * @param state     状态
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @param rb
	 * @return
	 */
	List<Admin> queryAdminTable(@Param("adName") String adName, @Param("adAcc") String adAcc,
			@Param("adDepId") String adDepId, @Param("state") String state, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate, RowBounds rb);

	/**
	 * 新增后台人员
	 * 
	 * @param ad 后台人员bean
	 * @return
	 */
	Integer addAdmin(Admin ad);

	/**
	 * 查询后台用户是否存在
	 * 
	 * @param adAcc 账号
	 * @return
	 */
	String queryAccIsExist(@Param("adAcc") String adAcc);

	/**
	 * 删除后台用户
	 * 
	 * @param adId 后台用户id
	 * @return
	 */
	Integer delAdmin(@Param("adId") String adId);

	/**
	 * 
	 * @param adName    姓名
	 * @param adAcc     账号
	 * @param adDepId   科室id
	 * @param state     状态
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @return
	 */
	Integer getAdminCount(@Param("adName") String adName, @Param("adAcc") String adAcc,
			@Param("adDepId") String adDepId, @Param("state") String state, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate);

	/**
	 * 增加用户角色
	 * 
	 * @param adId   用户id
	 * @param roleId 角色id
	 * @return
	 */

	Integer addAdminRole(@Param("adId") String adId, @Param("roleId") ArrayList<String> roleId);

	/**
	 * 根据用户id删除对应角色
	 * 
	 * @param roleId
	 * @return
	 */
	Integer delAdminRole(@Param("adminId") String adminId);

	/**
	 * 人员管理获取所有科室下拉框，包括删除的
	 * 
	 * @return
	 */
	List<Department> getDepSelect();

	/**
	 * 获取全部角色
	 * 
	 * @return
	 */
	List<Role> getAllRole();

	/**
	 * 修改后台人员状态
	 * 
	 * @param adId  人员id
	 * @param state 状态
	 * @return
	 */
	Integer updateState(@Param("adId") String adId, @Param("state") String state);

	/**
	 * 重置密码
	 * 
	 * @param adId 员工id
	 * @param pswd 新密码
	 * @return
	 */
	Integer reAdminPsw(@Param("adId") String adId, @Param("pswd") String pswd);

	/**
	 * 获得人员信息
	 * 
	 * @param adId 人员id
	 * @return
	 */
	Admin getAdmin(@Param("adId") String adId);

	/**
	 * 获得该人员已有角色
	 * 
	 * @param adId 人员id
	 * @return
	 */
	ArrayList<String> getAdminRole(@Param("adId") String adId);

	/**
	 * 修改人员
	 * 
	 * @param adName  姓名
	 * @param adAcc   账号
	 * @param adDepId 科室id
	 * @param adId    人员id
	 * @return
	 */
	Integer toUpdateAdmin(@Param("adName") String adName, @Param("adAcc") String adAcc,
			@Param("adDepId") String adDepId, @Param("adId") String adId);

	/**
	 * 查询科室下拉框，没有包括已删除的
	 * @return
	 */
	List<Department> getDepByState();
}
