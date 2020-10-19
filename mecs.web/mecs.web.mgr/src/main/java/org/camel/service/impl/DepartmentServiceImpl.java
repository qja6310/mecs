package org.camel.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.DepartmentMapper;
import org.camel.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired  DepartmentMapper departmentMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<Department> getDepartment(String beginTime, String endTime, String name, RowBounds rb) {
		
		ArrayList<Department> depList = departmentMapper.getDepartment(beginTime, endTime, name, rb);
		
		return depList;
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer getCount(String beginTime, String endTime, String name) {

		Integer count = departmentMapper.getAllCount(beginTime, endTime, name);
		return count;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean delDep(String depId, String depName) {

		Integer del = departmentMapper.delDep(depId, depName);
		if(del>0) {
			return true;
		}else {
			
		}
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public String getDepName(String depId) {
		String depName = departmentMapper.getDepName(depId);
		return depName;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean addDep(String depName, String depDescribe) {

		Integer add = departmentMapper.addDep(depName, depDescribe);
		if(add>0) {
			return true;
		}else {
			
		}
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public String checkdepName(String depName) {

		String newdepName = departmentMapper.checkdepName(depName);
		return newdepName;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean update(String depId, String depName, String depDescribe) {

		Integer update = departmentMapper.updateDep(depName, depDescribe, depId);
		if(update>0) {
			return true;
		}else {
			
		}
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public String getdepDescribe(String depId) {

		String depDescribe = departmentMapper.getDepDescribe(depId);
		return depDescribe;
	}

	@Transactional(rollbackFor = Exception.class)
	public String checkEditDepName(String depId, String depName) {
		String newdepName = departmentMapper.checkEditDepName(depId, depName);
		return newdepName;
	}

	

}

