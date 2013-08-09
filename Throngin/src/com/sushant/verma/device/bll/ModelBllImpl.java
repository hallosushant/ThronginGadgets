package com.sushant.verma.device.bll;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sushant.verma.device.dao.ModelDetailsSPDao;

public class ModelBllImpl implements ModelBllInterface {
	
	private static Logger log=LogManager.getLogger(ModelBllImpl.class);
	private ModelDetailsSPDao modelDetailsSPDao;
	
	@SuppressWarnings("unchecked")
	public Map fetchModelDetails(Long modelId){
		log.debug("|___modelId=="+modelId);
		return modelDetailsSPDao.fetchModelDetails(modelId);
	}

	
	
	/*
	 * Getters & Setters
	 */

	public ModelDetailsSPDao getModelDetailsSPDao() {
		return modelDetailsSPDao;
	}

	public void setModelDetailsSPDao(ModelDetailsSPDao modelDetailsSPDao) {
		this.modelDetailsSPDao = modelDetailsSPDao;
	}



}
