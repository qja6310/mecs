package org.camel.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.ParameterMapper;
import org.camel.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.Dict;

@Service
public class ParameterServiceImpl implements ParameterService {

	@Autowired ParameterMapper parameterMapper;
	

	@Transactional(rollbackFor = Exception.class)
	public Integer getCount(String beginTime, String endTime, String name) {
		Integer count = parameterMapper.getAllCount(beginTime, endTime, name);
		return count;
	}


	@Transactional(rollbackFor = Exception.class)
	public ArrayList<Dict> getDict(String beginTime, String endTime, String name, RowBounds rb) {

		ArrayList<Dict> dictList = parameterMapper.getDict(beginTime, endTime, name, rb);
		return dictList;
	}




	@Transactional(rollbackFor = Exception.class)
	public boolean delDict(String dictId) {
		Integer del = parameterMapper.delDict(dictId);
		if(del>0) {
			return true;
		}else {
			
		}
		return false;
	}


	@Transactional(rollbackFor = Exception.class)
	public String getDictName(String dictId) {
		String dictName = parameterMapper.getDictName(dictId);
		return dictName;
	}


	@Transactional(rollbackFor = Exception.class)
	public String getDictDescribe(String dictId) {
		String dictDescribe = parameterMapper.getDictDescribe(dictId);
		return dictDescribe;
	}


	@Transactional(rollbackFor = Exception.class)
	public boolean updateDict(String dictName, String dictDescribe, String dictId) {
		Integer update = parameterMapper.updateDict(dictName, dictDescribe, dictId);
		if(update>0) {
			return true;
		}else {
			
		}
		return false;
	}


	@Transactional(rollbackFor = Exception.class)
	public boolean addDict(String dictCode, String dictName, String dictType, String dictDescribe) {

		Integer add = parameterMapper.addDict(dictCode, dictName, dictType, dictDescribe);
		if(add>0) {
			return true;
		}else {
			
		}
		return false;
	}


	
	

}
