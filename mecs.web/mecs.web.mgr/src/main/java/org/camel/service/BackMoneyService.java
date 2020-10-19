package org.camel.service;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.dto.ItemsRecordDto;

	/**
	 * 退款
	 * 
     * <p>Title : BackMoneyMapperService.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月14日 下午4:47:04

     * @version : 0.0.1
 */
public interface BackMoneyService {
	
	/**
	 * 通过卡号获取余额
	 * @param cardNum
	 * @return
	 */
	String getBalanceByCard( String cardNum);
	/**
	 * 通过卡号获得用户名
	 * @param cardNum
	 * @return
	 */
	String getUserNameByCard( String cardNum);
    /**
     * 通过卡号获取所有的体检项目，动态查询：通过项目的状态，分页显示
     * @param cardNum
     * @param itemsState
     * @param rb
     * @return
     */
	ArrayList<ItemsRecordDto> getItemsByCard(String cardNum,String itemsState,RowBounds rb);
	/**
	 * 通过卡号获取项目数量，动态查询：通过项目的状态
	 * @param cardNum
	 * @param itemsState
	 * @return
	 */
	Integer getItemsCountByCard(String cardNum,String itemsState);
	/**
	 * 退款，修改卡与项目关系表 的状态，以及余额 通过id
	 * @param cardItemsId 卡与项目关系表id
	 * @return
	 */
	JSONObject modifyItemsStateAndBalanceById(HttpSession session,Integer cardItemsId,String itemsState, 
			String cardNum, String money,RowBounds rb);
	

}
