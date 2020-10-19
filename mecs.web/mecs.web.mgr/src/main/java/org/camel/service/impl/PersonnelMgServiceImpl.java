package org.camel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.LogMapper;
import org.camel.dao.PersonnelMgMapper;
import org.camel.service.PersonnelMgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.utils.IsNullUtil;
import mecs.camel.utils.Md5Util;

@Service
public class PersonnelMgServiceImpl implements PersonnelMgService {
	@Autowired
	private PersonnelMgMapper personnelMgMapper;
	@Autowired
	private LogMapper logMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject queryAdminTable(String adName, String adAcc, String adDepId, String state, String beginDate,
			String endDate, RowBounds rb) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 后台人员动态查询
		List<Admin> queryAdminTable = personnelMgMapper.queryAdminTable(adName, adAcc, adDepId, state, beginDate,
				endDate, rb);
		Integer adminCount = personnelMgMapper.getAdminCount(adName, adAcc, adDepId, state, beginDate, endDate);
		obj.put("table", queryAdminTable);
		obj.put("count", adminCount);
		return obj;
		 
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addAdmin(String doId,String adName, String adAcc, String adPswd, String adDepId, ArrayList<String> roleId) {

		JSONObject obj = new JSONObject();
		ArrayList<String> isNullList = new ArrayList<>();
		isNullList.add(adName);
		isNullList.add(adAcc);
		isNullList.add(adPswd);
		isNullList.add(adDepId);
		if (IsNullUtil.isNull(isNullList)) {
			// 判断用户账号是否存在
			String queryAccIsExist = personnelMgMapper.queryAccIsExist(adAcc);
			if (queryAccIsExist == null) { // 如果账号不存在
				String md5 = Md5Util.encryption(adPswd);
				
				Admin am = new Admin(null, adAcc, md5, adName, adDepId, null, null, null, null);
				// 增加用户
				Integer addAdmin = personnelMgMapper.addAdmin(am);
				// 判断是否选了角色
				if (roleId != null) {
					// 增加用户角色
					Integer addAdminRole = personnelMgMapper.addAdminRole(am.getAdminId(), roleId);
					if (addAdmin > 0 && addAdminRole > 0) {
						obj.put("res", "增加成功");
						 //插入日志
						logMapper.addLog(Integer.valueOf(doId),"新增人员", "新增账号为："+adAcc+"的人员");
					} else {
						obj.put("res", "增加失败");
					}
				} else {
					if (addAdmin > 0) {
						obj.put("res", "增加成功");
						 //插入日志
							logMapper.addLog(Integer.valueOf(doId),"新增人员", "新增账号为："+adAcc+"的人员");
					} else {
						obj.put("res", "增加失败");
					}
				}

			} else {
				obj.put("res", "该账号已存在");
			}
		} else {
			obj.put("res", "数据为空");
		}
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delAdmin(String doId,String adId) { // TODO Auto-generated method stub
		ArrayList<String> isNullList = new ArrayList<>();
		JSONObject obj = new JSONObject();
		isNullList.add(adId);
		// 如果没有空
		if (IsNullUtil.isNull(isNullList)) {
			Admin admin = personnelMgMapper.getAdmin(adId);
			// 删除用户
			Integer delAdmin = personnelMgMapper.delAdmin(adId);
			// 删除对应表
			Integer delAdminRole = personnelMgMapper.delAdminRole(adId);
			// 对应表可能在删除角色的时候被删除
			if (delAdmin > 0) {
				obj.put("res", "删除成功");
				 //插入日志
				
				logMapper.addLog(Integer.valueOf(doId),"删除人员", "删除了账号为："+admin.getAdminAcc()+"的人员 ");
				
			} else {
				obj.put("res", "删除失败");
			}

		} else {
			obj.put("res", "数据为空");
		}
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject initAdmSelect() {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("res", personnelMgMapper.getDepSelect());

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject initAddAdmSelect() {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("dep", personnelMgMapper.getDepByState());
		obj.put("role", personnelMgMapper.getAllRole());

		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject toAdminState(String doId,String adId, String state) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		String msg = "";
		if ("启用".equals(state)) {
			msg = state;
			state = "1";

		} else {
			msg = state;
			state = "0";
		}
		Integer updateState = personnelMgMapper.updateState(adId, state);

		if (updateState > 0) {

			obj.put("res", msg + "成功！");
			 //插入日志
			Admin admin = personnelMgMapper.getAdmin(adId);
			logMapper.addLog(Integer.valueOf(doId),"禁用/启用人员", msg+"了账号为："+admin.getAdminAcc()+"的人员");
		} else {
			obj.put("res", msg + "失败！");
		}
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject rePswd(String doId,String adId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		String newPswd = "123456";
		String md5 = Md5Util.encryption(newPswd);
		Integer reAdminPsw = personnelMgMapper.reAdminPsw(adId, md5);
		if (reAdminPsw > 0) {
			obj.put("res", "重置成功,重置密码为：" + newPswd);
			 //插入日志
			Admin admin = personnelMgMapper.getAdmin(adId);
			logMapper.addLog(Integer.valueOf(doId),"重置密码", "重置了账号为:"+admin.getAdminAcc()+"人员的密码");
		} else {
			obj.put("res", "重置失败");
		}
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject showUpdatePanel(String adId) {
		// TODO Auto-generated method stub
		// 根据人员id查到这个人员的信息
		JSONObject obj = new JSONObject();
		Admin admin = personnelMgMapper.getAdmin(adId);
		// 查到他拥有的角色
		ArrayList<String> adminRole = personnelMgMapper.getAdminRole(adId);
		obj.put("admin", admin);
		obj.put("myRole", adminRole);
		obj.put("dep", personnelMgMapper.getDepByState());
		obj.put("role", personnelMgMapper.getAllRole());
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject toUpdateAdmin(String doId,String adName, String adAcc, String adDepId, String adId,
			ArrayList<String> roleId) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		// 判断用户账号是否存在
		String queryAccIsExist = personnelMgMapper.queryAccIsExist(adAcc);
		if (queryAccIsExist == null) {

			// 修改人员信息
			Integer updateAdmin = personnelMgMapper.toUpdateAdmin(adName, adAcc, adDepId, adId);
			// 删除原本人员角色
			Integer delAdminRole = personnelMgMapper.delAdminRole(adId); // 可能一开始就没有对应关系
			if (roleId != null) {
				// 增加新角色
				Integer addAdminRole = personnelMgMapper.addAdminRole(adId, roleId);
				if (updateAdmin > 0 && addAdminRole > 0) {
					obj.put("res", "修改成功");
					//插入日志
					Admin admin = personnelMgMapper.getAdmin(adId);
					logMapper.addLog(Integer.valueOf(doId),"编辑人员", "修改了账号为:"+admin.getAdminAcc()+"的人员");
				} else {
					obj.put("res", "修改失败");
				}
			} else {
				if (updateAdmin > 0) {
					obj.put("res", "修改成功");
					//插入日志
					Admin admin = personnelMgMapper.getAdmin(adId);
					logMapper.addLog(Integer.valueOf(doId),"编辑人员", "修改了账号为:"+admin.getAdminAcc()+"的人员");
				} else {
					obj.put("res", "修改失败");
				}

			}
		} else {
			// 判断账号是否和原来一样
			Admin admin = personnelMgMapper.getAdmin(adId);
			if (admin.getAdminAcc().equals(adAcc)) {
				// 修改人员信息
				Integer updateAdmin = personnelMgMapper.toUpdateAdmin(adName, adAcc, adDepId, adId);
				// 删除原本人员角色
				Integer delAdminRole = personnelMgMapper.delAdminRole(adId); // 可能一开始就没有对应关系
				if (roleId != null) {
					// 增加新角色
					Integer addAdminRole = personnelMgMapper.addAdminRole(adId, roleId);
					if (updateAdmin > 0 && addAdminRole > 0) {
						obj.put("res", "修改成功");
						//插入日志
						logMapper.addLog(Integer.valueOf(doId),"编辑人员", "修改了账号为:"+admin.getAdminAcc()+"的人员");
					} else {
						obj.put("res", "修改失败");
					}
				} else {
					if (updateAdmin > 0) {
						obj.put("res", "修改成功");
						//插入日志
						logMapper.addLog(Integer.valueOf(doId),"编辑人员", "修改了账号为:"+admin.getAdminAcc()+"的人员");
					} else {
						obj.put("res", "修改失败");
					}

				}
			} else {

				obj.put("res", "账号已存在");
			}

		}

		return obj;

	}
}
