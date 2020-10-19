package org.camel.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSONObject;

public interface PersonnelMgService {

	/**
	 * 查询后台人员列表
	 * 
	 * @param adName    名字
	 * @param adAcc     账号
	 * @param adDepId   科室id
	 * @param state     状态
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @param rb
	 * @return
	 */
	JSONObject queryAdminTable(@Param("adName") String adName, @Param("adAcc") String adAcc,
			@Param("adDepId") String adDepId, @Param("state") String state, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate, RowBounds rb);

	/**
	 * 新增后台人员
	 * 
	 * @param adNam   姓名
	 * @param adAcc   账号
	 * @param adPswd  密码
	 * @param adDepId 科室id
	 * @param state   状态
	 * @return
	 */
	JSONObject addAdmin(String doId,String adName, String adAcc, String adPswd, String adDepId, ArrayList<String> roleId);

	/**
	 * 删除后台用户
	 * 
	 * @param adId 用户id
	 * @return
	 */
	JSONObject delAdmin(String doId,String adId);

	/**
	 * 初始化后台人员下拉框
	 * 
	 * @return
	 */
	JSONObject initAdmSelect();

	/**
	 * 初始化增加人员界面选择项
	 * 
	 * @return
	 */
	JSONObject initAddAdmSelect();

	/**
	 * 修改后台人员状态
	 * 
	 * @param adId 员工id
	 * @return
	 */
	JSONObject toAdminState(String doId,String adId, String state);

	/**
	 * 重置密码
	 * 
	 * @param adId 员工id
	 * @return
	 */
	JSONObject rePswd(String doId,String adId);

	/**
	 * 初始化编辑界面
	 * 
	 * @return
	 */
	JSONObject showUpdatePanel(String adId);
	/**
	 * 修改人员
	 * @param adName
	 * @param adAcc
	 * @param adDepId
	 * @param adId
	 * @return
	 */
	JSONObject toUpdateAdmin(String doId,String adName, String adAcc, String adDepId, String adId,ArrayList<String>roleId);

}
