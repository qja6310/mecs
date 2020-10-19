package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Item;

public interface ItemMapper {

	/**
	 * 获取所有细项
	 * 
	 * @param itemName
	 * @param rb
	 * @return
	 */
	List<Item> getAllItem(@Param("name") String itemName, RowBounds rb);

	/**
	 * 获取细项数量
	 * 
	 * @param itemName
	 * @return
	 */
	Integer getAllItemCount(@Param("name") String itemName);

	/**
	 * 修改细项的名称
	 * 
	 * @param itemName
	 * @param itemId
	 * @return
	 */
	Integer alterItemName(@Param("name") String itemName, @Param("id") String itemId);

	/**
	 * 判断细项名是否重复
	 * 
	 * @param itemName
	 * @return
	 */
	String selectItemName(@Param("name") String itemName);

	/**
	 * 新增细项
	 * 
	 * @param itemName
	 * @param itemPrice
	 * @return
	 */
	Integer addItem(@Param("name") String itemName, @Param("unit") String itemUnit);

	/**
	 * 删除细项
	 * 
	 * @param itemId
	 * @return
	 */
	Integer delItem(@Param("id") String itemId);
	
	/**
	 * 根据id得到细项
	 * @param itemId
	 * @return
	 */
	Item getIemById(@Param("itemId")String itemId);
}
