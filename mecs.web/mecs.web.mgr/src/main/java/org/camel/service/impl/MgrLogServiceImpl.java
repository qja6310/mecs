package org.camel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.camel.dao.MgrLogMapper;
import org.camel.service.MgrLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.Log;
@Service
public class MgrLogServiceImpl implements MgrLogService {
	
	@Autowired
	private MgrLogMapper mgrLogMapper;
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<Log> getLog(String beginTime, String endTime, String name) {
		ArrayList<Log> mgrLogList = mgrLogMapper.getLog(beginTime, endTime, name);
		return mgrLogList;
	}


	@Transactional(rollbackFor = Exception.class)
	public Integer getAllCount(String beginTime, String endTime, String name) {
		Integer count = mgrLogMapper.getAllCount(beginTime, endTime, name);
		return count;
	}


	@Transactional(rollbackFor = Exception.class)
	public boolean delLog(List<String> logIdList) {
		Integer del = mgrLogMapper.delLog(logIdList);
		if(del>0) {
			return true;
		}else {
		}
		return false;
	}

	


}
