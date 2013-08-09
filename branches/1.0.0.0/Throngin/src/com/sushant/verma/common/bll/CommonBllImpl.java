package com.sushant.verma.common.bll;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.common.dao.CommonDao;

public class CommonBllImpl implements CommonBllInterface {
	
	private static Logger log=LogManager.getLogger(CommonBllImpl.class);
	private CommonDao commonDao;
	
	public List<ListOrderedMap> getDeviceList() {
		log.info("Inside getDeviceList");
		return commonDao.getDeviceList();
	}

	@SuppressWarnings("unchecked")
	public Map getModelList(Long deviceId) {

		log.info("@ getModelList | deviceId="+deviceId);
		return commonDao.getModelList(deviceId);
/*		Map modelsDBResult=commonDao.getModelList(deviceId);
		
        Map modelsMap=new HashMap();           

        List<ModelDto> popularModels=(List) modelsDBResult.get(ZProcConstants.POPULAR_MODELS_RESULT_SET);
		log.debug("popularModels="+popularModels);
		modelsMap.put(DeviceConstants.POPULAR_MODELS,popularModels);
		
		List<ModelDto> newModels=(List) modelsDBResult.get(ZProcConstants.NEW_MODELS_RESULT_SET);
		log.debug("newModels="+newModels);
		modelsMap.put(DeviceConstants.NEW_MODELS,newModels);
		
		List<ModelDto> upComingModels=(List) modelsDBResult.get(ZProcConstants.UPCOMING_MODELS_RESULT_SET);
		log.debug("upComingModels="+upComingModels);
		modelsMap.put(DeviceConstants.UPCOMING_MODELS,upComingModels);

		return modelsMap;
*/	}
	

	
	
	/*
	 * Getters & Setters
	 */
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	
}
