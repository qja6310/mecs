package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Item;

public interface ItemsLinkItemMapper {

	/**
	 * 删除项目相关细项
	 * 
	 * @param itemsId
	 * @return
	 */
	Integer delItemLink(@Param("id") String itemId);

	/**
	 * 删除项目相关细项
	 * 
	 * @param itemsId
	 * @return
	 */
	Integer delItemsLink(@Param("id") String itemsId);

	/**
	 * 增加项目相关细项
	 * 
	 * @param itemsId
	 * @param itemList
	 * @return
	 */
	Integer addItemsLink(@Param("id") String itemsId, @Param("itemList") List<String> itemList);

	/**
	 * 获得项目中的细项
	 * 
	 * @param itemsId
	 * @return
	 */
	List<String> getItemByItemsId(@Param("itemsId") String itemsId);
	
	/**
	 * 查看项目的细项
	 * @param itemsId
	 * @return
	 */
	List<Item> lookItemById(@Param("itemsId") String itemsId);
}
