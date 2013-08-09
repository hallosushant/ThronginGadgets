package com.sushant.verma.common.bll;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;


public interface CommonBllInterface {
	
	List<ListOrderedMap> getDeviceList();

	@SuppressWarnings("unchecked")
	Map getModelList(Long dId);

}
