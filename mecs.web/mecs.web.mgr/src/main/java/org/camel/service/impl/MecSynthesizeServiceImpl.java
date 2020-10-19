package org.camel.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.MecSynthesizeMapper;
import org.camel.service.MecSynthesizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.MecSynthesize;
import mecs.camel.utils.LimitUtil;

@Service
public class MecSynthesizeServiceImpl implements MecSynthesizeService{

	@Autowired
	private MecSynthesizeMapper mecSynthesizeMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getMecSynthesize(String nowPage, String sTime, String eTime, String userName, String userTel,
			String mecCode) {
		JSONObject obj = new JSONObject();
		/*
		 * 判断当前页是否为空,为空的默认赋值为1
		 */
		if(nowPage == null || "".equals(nowPage)) {
			nowPage = "1";
		}
		int limit = LimitUtil.MECSYNTHESIZELIMIT;//获取每页显示的条数
		int begin = (Integer.parseInt(nowPage) - 1) * limit;//开始显示的地方
		RowBounds rb = new RowBounds(begin, limit);//分页器
		
		/*
		 * 获取综合
		 */
		List<MecSynthesize> mecSynthesizeList = mecSynthesizeMapper.getMecSynthesize(sTime, eTime, userName, userTel, mecCode, rb);
		obj.put("mecSynthesizeList", mecSynthesizeList);
		
		/*
		 *获取数量 
		 */
		Integer count = mecSynthesizeMapper.getMecSynthesizeCount(sTime, eTime, userName, userTel, mecCode);
		obj.put("count", count);
		obj.put("limit", limit);
		
		return obj;
	}

}
