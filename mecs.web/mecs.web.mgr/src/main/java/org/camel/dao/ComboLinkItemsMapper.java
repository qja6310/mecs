package org.camel.dao;

import org.apache.ibatis.annotations.Param;

public interface ComboLinkItemsMapper {

	/**
	 * 删除套餐-项目对应表的数据
	 * 
	 * @param ComboId
	 * @return
	 */
	Integer delComboLinkItems(@Param("id") String ComboId);
}
