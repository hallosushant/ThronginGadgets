package com.sushant.verma.device.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sushant.verma.common.ZProc.ZProcConstants;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.constants.ZConstants.TitleEnum;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.exception.BaseRuntimeException;
import com.sushant.verma.common.mapper.ModelMapper;
import com.sushant.verma.common.utility.StringUtility;
 

public class ModelGallerySPDao extends JdbcDaoSupport{
 
	private static final Logger log=LogManager.getLogger(ModelGallerySPDao.class);
  
    @SuppressWarnings("unchecked")
	public Map fetchManufacturerModels(Long deviceId,Long manufacturerId) {
    	log.debug("|___ deviceId=="+deviceId+"\t|___ manufacturerId=="+manufacturerId);
    	getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
    	/*String procName;
    	if(null==manufacturerId){
    		procName=ZProcConstants.SP_DEVICE_MODELS_COUNT;
    		manufacturerId=new Long(0);
    	}
    	else
    		procName=ZProcConstants.SP_MANUFACTURER_MODELS_COUNT;*/
    	try {
    		/*ManufacturerModelsStoredProcedure proc = new ManufacturerModelsStoredProcedure(getJdbcTemplate().getDataSource(), procName);
            Map results = proc.execute(deviceId,manufacturerId);
            log.debug("|__results="+results);*/
    		Map results = new HashMap();
            /*
        	 * POPULAR
        	 */
        	StringBuilder sql=new StringBuilder();
    		sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
    		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
    		sql.append(" FROM model  inner join popularity ");
    		sql.append(" on model.MODEL_ID=popularity.MODEL_ID "); 
    		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID ");
    		sql.append(" and model.MANUFACTURER_ID=popularity.MANUFACTURER_ID ");
    		sql.append(" where model.DEVICE_MST_ID=? and model.STATUS_ID=?  ");
    		if(null!=manufacturerId)
    			sql.append(" and model.MANUFACTURER_ID=? ");
    		sql.append(" order by popularity.POPULARITY_INDEX  LIMIT 5 ");
    		ArrayList<Object> args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__POPULAR query="+sql);
    		List<ModelDto> popularModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.POPULAR_MODELS_RESULT_SET,popularModels);
    		
    		/*
        	 * POPULAR COUNT
        	 */
        	sql=new StringBuilder();
        	sql.append(" SELECT count(distinct model.MODEL_ID) count");
        	sql.append(" FROM model  inner join popularity  ");
        	sql.append(" 	on model.MODEL_ID=popularity.MODEL_ID   ");
        	sql.append(" 	and model.DEVICE_MST_ID=popularity.DEVICE_ID  ");
        	sql.append(" 	and model.MANUFACTURER_ID=popularity.MANUFACTURER_ID  ");
        	sql.append(" where model.DEVICE_MST_ID=? ");
        	sql.append(" 	and model.STATUS_ID=? ");
        	if(null!=manufacturerId)
    			sql.append(" and model.MANUFACTURER_ID=? ");
        	
        	args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__POPULAR count query="+sql);
    		List<ListOrderedMap> popularModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_POPULAR_MODEL_COUNT,popularModelsCount.get(0).get("count"));
    		
    		/*
    		 * NEW
    		 */
    		sql=new StringBuilder();
    		sql.append(" select '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc,  ");
    		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date , model_link  ");
    		sql.append(" from model    ");
    		sql.append(" where device_mst_id=?  ");
    		sql.append(" and status_id=? ");
    		if(null!=manufacturerId)
    			sql.append(" and model.MANUFACTURER_ID=? ");
    		sql.append(" and launch_date between (select date_sub(curdate(), interval 90 day)) and current_date  ");
    		sql.append(" order by launch_date desc  limit 5  ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__NEW query="+sql);
    		List<ModelDto> newModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.NEW_MODELS_RESULT_SET,newModels);
    		
    		/*
    		 * NEW COUNT
    		 */
    		sql=new StringBuilder();
    		sql.append(" SELECT count(1) count ");
    		sql.append(" FROM model   ");
    		sql.append(" where DEVICE_MST_ID=? ");
    		sql.append(" 	and STATUS_ID=? ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" 	and LAUNCH_DATE between (select date_sub(curdate(), interval 90 day)) and CURRENT_DATE ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__NEW count query="+sql);
    		List<ListOrderedMap> newModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_NEW_MODEL_COUNT,newModelsCount.get(0).get("count"));
    		
    		/*
    		 * UPCOMING
    		 */
    		sql=new StringBuilder();
    		sql.append(" select '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc, ");
    		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date  , model_link   ");
    		sql.append(" from model    ");
    		sql.append(" where device_mst_id=? ");
    		sql.append(" and status_id=?  and LAUNCH_DATE > CURRENT_DATE ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" order by MODEL_NAME limit 5  ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__UPCOMING query="+sql);
    		List<ModelDto> upcomingModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.UPCOMING_MODELS_RESULT_SET,upcomingModels);
    		
    		/*
    		 * UPCOMING COUNT
    		 */
    		sql=new StringBuilder();
    		sql.append(" SELECT count(1) count");
    		sql.append(" FROM model   ");
    		sql.append(" where DEVICE_MST_ID=? ");
    		sql.append(" and STATUS_ID=? ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" and LAUNCH_DATE > CURRENT_DATE ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__UPCOMING COUNT query="+sql);
    		List<ListOrderedMap> upcomingModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_UPCOMING_MODEL_COUNT,upcomingModelsCount.get(0).get("count"));
    		
    		/*
    		 * ALL
    		 */
    		sql=new StringBuilder();
    		sql.append(" select '"+TitleEnum.ALL.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc, ");
    		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date  , model_link   ");
    		sql.append(" from model    ");
    		sql.append(" where device_mst_id=? ");
    		sql.append(" and status_id=? ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" order by launch_date limit 5  ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__ALL query="+sql);
    		List<ModelDto> allModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.ALL_MODELS_RESULT_SET,allModels);
    		
    		/*
    		 * ALL COUNT
    		 */
    		sql=new StringBuilder();
    		sql.append(" SELECT count(1) count ");
    		sql.append(" FROM model    ");
    		sql.append(" where DEVICE_MST_ID=?  ");
    		sql.append(" 	and STATUS_ID=?  ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__ALL COUNT query="+sql);
    		List<ListOrderedMap> allModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_ALL_MODEL_COUNT,allModelsCount.get(0).get("count"));
    		
            return results;

    	} catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }
    }

	@SuppressWarnings("unchecked")
	public List<List<ModelDto>> fetchCategoryModels(Long deviceId,Long categoryId) {
    	log.debug("|___ deviceId=="+deviceId+"\t|___ categoryId=="+categoryId);
    	getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
    	 List<List<ModelDto>> categoryModelsList=new ArrayList<List<ModelDto>>();
        /*
    	 * POPULAR
    	 */
    	StringBuilder sql=new StringBuilder();
		sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE,  ");
		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK     ");
		sql.append(" FROM model  inner join popularity   ");
		sql.append(" on model.MODEL_ID=popularity.MODEL_ID   "); 
		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID   ");
		sql.append(" inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
		sql.append(" and mcp.CATEGORY_ID=popularity.CATEGORY_ID  ");
		sql.append(" where model.DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
		sql.append(" order by popularity.POPULARITY_INDEX  LIMIT 5  ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__POPULAR query="+sql);
		List<ModelDto> popularModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
		
		/*
    	 * POPULAR COUNT
    	 */
    	sql=new StringBuilder();
    	sql.append(" SELECT count(distinct model.MODEL_ID) count "); 
    	sql.append(" FROM model  inner join popularity   ");
    	sql.append(" on model.MODEL_ID=popularity.MODEL_ID   "); 
    	sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID   "); 
    	sql.append(" inner join model_category_mapping mcp ");
    	sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
    	sql.append(" and mcp.CATEGORY_ID=popularity.CATEGORY_ID  ");
    	sql.append(" where model.DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");

    	args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__POPULAR count query="+sql);
		Long popularModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
		if(!popularModels.isEmpty()){
			popularModels.get(0).setModelCount(popularModelsCount);
			log.debug("popularModelsCount="+popularModelsCount+" | dtoVal="+popularModels.get(0).getModelCount());
			categoryModelsList.add(popularModels);
		}
		/*
		 * NEW
		 */
		sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE   ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK  ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
		sql.append(" and LAUNCH_DATE between (SELECT DATE_SUB(curdate(), INTERVAL 90 day)) and CURRENT_DATE  ");
		sql.append(" order by LAUNCH_DATE desc  LIMIT 5   ");

		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__NEW query="+sql);
		List<ModelDto> newModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
		
		/*
		 * NEW COUNT
		 */
		sql=new StringBuilder();
		sql.append(" SELECT count(1) count ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
		sql.append(" and LAUNCH_DATE between (SELECT DATE_SUB(curdate(), INTERVAL 90 day)) and CURRENT_DATE  ");

		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__NEW count query="+sql);
		Long newModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
		if(!newModels.isEmpty()){
			newModels.get(0).setModelCount(newModelsCount);
			log.debug("newModelsCount="+newModelsCount+" | dtoVal="+newModels.get(0).getModelCount());
			categoryModelsList.add(newModels);
		}
		/*
		 * UPCOMING
		 */
		sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK    "); 
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
		sql.append(" and LAUNCH_DATE > CURRENT_DATE  ");
		sql.append(" order by LAUNCH_DATE LIMIT 5   ");

		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__UPCOMING query="+sql);
		List<ModelDto> upComingModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
		
		/*
		 * UPCOMING COUNT
		 */
		sql=new StringBuilder();
		sql.append(" SELECT count(1) count ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
		sql.append(" and LAUNCH_DATE > CURRENT_DATE  ");
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__UPCOMING COUNT query="+sql);
		Long upComingModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
		if(!upComingModels.isEmpty()){
			upComingModels.get(0).setModelCount(upComingModelsCount);
			log.debug("upComingModelsCount="+upComingModelsCount+" | dtoVal="+upComingModels.get(0).getModelCount());
			categoryModelsList.add(upComingModels);
		}
		/*
		 * ALL
		 */
		sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.ALL.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK  ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
		sql.append(" order by MODEL_NAME LIMIT 5   ");
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__ALL query="+sql);
		List<ModelDto> allModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
		
		/*
		 * ALL COUNT
		 */
		sql=new StringBuilder();
		sql.append(" SELECT count(1) count ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__ALL COUNT query="+sql);
		Long allModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");

		if(!allModels.isEmpty()){
			allModels.get(0).setModelCount(allModelsCount);
			log.debug("allModelsCount="+allModelsCount+" | dtoVal="+allModels.get(0).getModelCount());
			categoryModelsList.add(allModels);
		}
		
		return categoryModelsList;
		
    	/*try {
    		CategoryModelsStoredProcedure proc = new CategoryModelsStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_CATEGORY_MODELS_COUNT);
            Map results = proc.execute(deviceId,categoryId);
            log.debug("|__results="+results);
            
            List<List<ModelDto>> categoryModelsList=new ArrayList<List<ModelDto>>();            
            
            List<ModelDto> popularModels=(List) results.get(ZProcConstants.POPULAR_MODELS_RESULT_SET);
    		log.debug("popularModels="+popularModels);
    		if(!popularModels.isEmpty()){
    			Long popularModelsCount=(Long) results.get(ZProcConstants.OUT_POPULAR_MODEL_COUNT);
    			popularModels.get(0).setModelCount(popularModelsCount);
    			log.debug("popularModelsCount="+popularModelsCount+" | dtoVal="+popularModels.get(0).getModelCount());
    			categoryModelsList.add(popularModels);
    		}

    		
    		List<ModelDto> newModels=(List) results.get(ZProcConstants.NEW_MODELS_RESULT_SET);
    		log.debug("newModels="+newModels);
    		if(!newModels.isEmpty()){
    			Long newModelsCount=(Long) results.get(ZProcConstants.OUT_NEW_MODEL_COUNT);
    			newModels.get(0).setModelCount(newModelsCount);
    			log.debug("newModelsCount="+newModelsCount+" | dtoVal="+newModels.get(0).getModelCount());
    			categoryModelsList.add(newModels);
    		}

    		List<ModelDto> upComingModels=(List) results.get(ZProcConstants.UPCOMING_MODELS_RESULT_SET);
    		log.debug("upComingModels="+upComingModels);
    		if(!upComingModels.isEmpty()){
    			Long upComingModelsCount=(Long) results.get(ZProcConstants.OUT_UPCOMING_MODEL_COUNT);
    			upComingModels.get(0).setModelCount(upComingModelsCount);
    			log.debug("upComingModelsCount="+upComingModelsCount+" | dtoVal="+upComingModels.get(0).getModelCount());
    			categoryModelsList.add(upComingModels);
    		}
    		
    		List<ModelDto> allModels=(List) results.get(ZProcConstants.ALL_MODELS_RESULT_SET);
    		log.debug("allModels="+allModels);
    		if(!allModels.isEmpty()){
    			Long allModelsCount=(Long) results.get(ZProcConstants.OUT_ALL_MODEL_COUNT);
    			allModels.get(0).setModelCount(allModelsCount);
    			log.debug("allModelsCount="+allModelsCount+" | dtoVal="+allModels.get(0).getModelCount());
    			categoryModelsList.add(allModels);
    		}
    			
    		
    		return categoryModelsList;

    	} catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
    }

	@SuppressWarnings("unchecked")
	public Map moreManufacturerModelsWithCount(Long deviceId,Long manufacturerId, String galleryType, Integer pageSize,Integer pageNo) {
		log.info("@ moreManufacturerModelsWithCount | deviceId="+deviceId+" | manufacturerId="+manufacturerId+" | galleryType="+galleryType+" | pageSize="+pageSize+" | pageNo="+pageNo);
		getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
		
		Map results = new HashMap();
		StringBuilder sql=new StringBuilder();
		ArrayList<Object> args=new ArrayList<Object>();
		if(galleryType.equals(TitleEnum.POPULAR.getTitle())){
			
            /*
        	 * POPULAR
        	 */
        	
    		sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
    		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
    		sql.append(" FROM model  inner join popularity ");
    		sql.append(" on model.MODEL_ID=popularity.MODEL_ID "); 
    		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID ");
    		sql.append(" and model.MANUFACTURER_ID=popularity.MANUFACTURER_ID ");
    		sql.append(" where model.DEVICE_MST_ID=? and model.STATUS_ID=?  ");
    		if(null!=manufacturerId)
    			sql.append(" and model.MANUFACTURER_ID=? ");
    		sql.append(" order by popularity.POPULARITY_INDEX  LIMIT "+(pageNo-1)*pageSize+","+pageSize);
    		
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__POPULAR query="+sql);
    		List<ModelDto> popularModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.RESULT_SET,popularModels);
    		
    		/*
        	 * POPULAR COUNT
        	 */
        	sql=new StringBuilder();
        	sql.append(" SELECT count(distinct model.MODEL_ID) count");
        	sql.append(" FROM model  inner join popularity  ");
        	sql.append(" 	on model.MODEL_ID=popularity.MODEL_ID   ");
        	sql.append(" 	and model.DEVICE_MST_ID=popularity.DEVICE_ID  ");
        	sql.append(" 	and model.MANUFACTURER_ID=popularity.MANUFACTURER_ID  ");
        	sql.append(" where model.DEVICE_MST_ID=? ");
        	sql.append(" 	and model.STATUS_ID=? ");
        	if(null!=manufacturerId)
    			sql.append(" and model.MANUFACTURER_ID=? ");
        	
        	args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__POPULAR count query="+sql);
    		List<ListOrderedMap> popularModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,popularModelsCount.get(0).get("count"));
    		
		}else if(galleryType.equals(TitleEnum.NEW.getTitle())){
			
			/*
    		 * NEW
    		 */
    		sql=new StringBuilder();
    		sql.append(" select '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc,  ");
    		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date , model_link  ");
    		sql.append(" from model    ");
    		sql.append(" where device_mst_id=?  ");
    		sql.append(" and status_id=? ");
    		if(null!=manufacturerId)
    			sql.append(" and model.MANUFACTURER_ID=? ");
    		sql.append(" and launch_date between (select date_sub(curdate(), interval 90 day)) and current_date  ");
    		sql.append(" order by launch_date desc  limit "+(pageNo-1)*pageSize+","+pageSize);
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__NEW query="+sql);
    		List<ModelDto> newModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.RESULT_SET,newModels);
    		
    		/*
    		 * NEW COUNT
    		 */
    		sql=new StringBuilder();
    		sql.append(" SELECT count(1) count ");
    		sql.append(" FROM model   ");
    		sql.append(" where DEVICE_MST_ID=? ");
    		sql.append(" 	and STATUS_ID=? ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" 	and LAUNCH_DATE between (select date_sub(curdate(), interval 90 day)) and CURRENT_DATE ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__NEW count query="+sql);
    		List<ListOrderedMap> newModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,newModelsCount.get(0).get("count"));
		
		}else if(galleryType.equals(TitleEnum.UPCOMING.getTitle())){
			
			/*
    		 * UPCOMING
    		 */
    		sql=new StringBuilder();
    		sql.append(" select '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc, ");
    		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date  , model_link   ");
    		sql.append(" from model    ");
    		sql.append(" where device_mst_id=? ");
    		sql.append(" and status_id=?  and LAUNCH_DATE > CURRENT_DATE ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" order by MODEL_NAME limit "+(pageNo-1)*pageSize+","+pageSize);
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__UPCOMING query="+sql);
    		List<ModelDto> upcomingModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.RESULT_SET,upcomingModels);
    		
    		/*
    		 * UPCOMING COUNT
    		 */
    		sql=new StringBuilder();
    		sql.append(" SELECT count(1) count");
    		sql.append(" FROM model   ");
    		sql.append(" where DEVICE_MST_ID=? ");
    		sql.append(" and STATUS_ID=? ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" and LAUNCH_DATE > CURRENT_DATE ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__UPCOMING COUNT query="+sql);
    		List<ListOrderedMap> upcomingModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,upcomingModelsCount.get(0).get("count"));
    		
		
		}else if(galleryType.equals(TitleEnum.ALL.getTitle())){
			
			/*
    		 * ALL
    		 */
    		sql=new StringBuilder();
    		sql.append(" select '"+TitleEnum.ALL.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc, ");
    		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date  , model_link   ");
    		sql.append(" from model    ");
    		sql.append(" where device_mst_id=? ");
    		sql.append(" and status_id=? ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		sql.append(" order by launch_date limit "+(pageNo-1)*pageSize+","+pageSize);
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__ALL query="+sql);
    		List<ModelDto> allModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
    		results.put(ZProcConstants.RESULT_SET,allModels);
    		
    		/*
    		 * ALL COUNT
    		 */
    		sql=new StringBuilder();
    		sql.append(" SELECT count(1) count ");
    		sql.append(" FROM model    ");
    		sql.append(" where DEVICE_MST_ID=?  ");
    		sql.append(" 	and STATUS_ID=?  ");
    		if(null!=manufacturerId)
    			sql.append(" 	and MANUFACTURER_ID=? ");
    		args=new ArrayList<Object>();
    		args.add(deviceId);
    		args.add(DbConstants.ACTIVE_STATUS_ID);
    		if(null!=manufacturerId)
    			args.add(manufacturerId);
    		log.debug("|__ALL COUNT query="+sql);
    		List<ListOrderedMap> allModelsCount=getJdbcTemplate().queryForList(sql.toString(), args.toArray());
    		results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,allModelsCount.get(0).get("count"));
    		
    		
		}
		log.debug("|__results="+results);
        return results;
		
    	/*String procName;
    	if(null==manufacturerId){
    		procName=ZProcConstants.SP_MORE_DEVICE_MODELS_COUNT;
    		manufacturerId=new Long(0);
    	}
    	else
    		procName=ZProcConstants.SP_MORE_MANUFACTURER_MODELS_COUNT;

		try {
    		MoreManufacturerModelsStoredProcedure proc = new MoreManufacturerModelsStoredProcedure(getJdbcTemplate().getDataSource(), procName);
            Map results = proc.execute(deviceId,manufacturerId,galleryType,pageSize,pageNo);
            log.debug("|__results="+results);
            return results;

    	} catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
	}

	@SuppressWarnings("unchecked")
	public Map moreCategoryModelsWithCount(Long deviceId,Long categoryId, String galleryType, Integer pageSize,Integer pageNo) {
		log.info("@ moreCategoryModelsWithCount | deviceId="+deviceId+" | categoryId="+categoryId+" | galleryType="+galleryType+" | pageSize="+pageSize+" | pageNo="+pageNo);
		getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
		Map results = new HashMap();
		StringBuilder sql;
		ArrayList<Object> args;

		if(galleryType.equals(TitleEnum.POPULAR.getTitle())){
			/*
			 * POPULAR
			 */
			sql=new StringBuilder();
			sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE,  ");
			sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK     ");
			sql.append(" FROM model  inner join popularity   ");
			sql.append(" on model.MODEL_ID=popularity.MODEL_ID   "); 
			sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID   ");
			sql.append(" inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
			sql.append(" and mcp.CATEGORY_ID=popularity.CATEGORY_ID  ");
			sql.append(" where model.DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
			sql.append(" order by popularity.POPULARITY_INDEX  LIMIT "+(pageNo-1)*pageSize+","+pageSize);
			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__POPULAR query="+sql);
			List<ModelDto> popularModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
			results.put(ZProcConstants.RESULT_SET,popularModels);
			/*
			 * POPULAR COUNT
			 */
			sql=new StringBuilder();
			sql.append(" SELECT count(distinct model.MODEL_ID) count "); 
			sql.append(" FROM model  inner join popularity   ");
			sql.append(" on model.MODEL_ID=popularity.MODEL_ID   "); 
			sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID   "); 
			sql.append(" inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
			sql.append(" and mcp.CATEGORY_ID=popularity.CATEGORY_ID  ");
			sql.append(" where model.DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");

			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__POPULAR count query="+sql);
			Long popularModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
			results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,popularModelsCount);

		}else if(galleryType.equals(TitleEnum.NEW.getTitle())){
			/*
			 * NEW
			 */
			sql=new StringBuilder();
			sql.append(" SELECT '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
			sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE   ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK  ");
			sql.append(" FROM model inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
			sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
			sql.append(" and LAUNCH_DATE between (SELECT DATE_SUB(curdate(), INTERVAL 90 day)) and CURRENT_DATE  ");
			sql.append(" order by LAUNCH_DATE desc  LIMIT "+(pageNo-1)*pageSize+","+pageSize);

			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__NEW query="+sql);
			List<ModelDto> newModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
			results.put(ZProcConstants.RESULT_SET,newModels);
			/*
			 * NEW COUNT
			 */
			sql=new StringBuilder();
			sql.append(" SELECT count(1) count ");
			sql.append(" FROM model inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
			sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
			sql.append(" and LAUNCH_DATE between (SELECT DATE_SUB(curdate(), INTERVAL 90 day)) and CURRENT_DATE  ");

			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__NEW count query="+sql);
			Long newModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
			results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,newModelsCount);

		}else if(galleryType.equals(TitleEnum.UPCOMING.getTitle())){
			/*
			 * UPCOMING
			 */
			sql=new StringBuilder();
			sql.append(" SELECT '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
			sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK    "); 
			sql.append(" FROM model inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
			sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
			sql.append(" and LAUNCH_DATE > CURRENT_DATE  ");
			sql.append(" order by LAUNCH_DATE LIMIT "+(pageNo-1)*pageSize+","+pageSize);

			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__UPCOMING query="+sql);
			List<ModelDto> upComingModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
			results.put(ZProcConstants.RESULT_SET,upComingModels);
			/*
			 * UPCOMING COUNT
			 */
			sql=new StringBuilder();
			sql.append(" SELECT count(1) count ");
			sql.append(" FROM model inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID  ");
			sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
			sql.append(" and LAUNCH_DATE > CURRENT_DATE  ");
			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__UPCOMING COUNT query="+sql);
			Long upComingModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
			results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,upComingModelsCount);

		}else if(galleryType.equals(TitleEnum.ALL.getTitle())){
			/*
			 * ALL
			 */
			sql=new StringBuilder();
			sql.append(" SELECT '"+TitleEnum.ALL.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
			sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE ,mcp.CATEGORY_MODEL_LINK as MODEL_LINK  ");
			sql.append(" FROM model inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
			sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
			sql.append(" order by MODEL_NAME LIMIT "+(pageNo-1)*pageSize+","+pageSize);
			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__ALL query="+sql);
			List<ModelDto> allModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
			results.put(ZProcConstants.RESULT_SET,allModels);
			/*
			 * ALL COUNT
			 */
			sql=new StringBuilder();
			sql.append(" SELECT count(1) count ");
			sql.append(" FROM model inner join model_category_mapping mcp ");
			sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
			sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=?  ");
			args=new ArrayList<Object>();
			args.add(deviceId);
			args.add(categoryId);
			args.add(DbConstants.ACTIVE_STATUS_ID);
			log.debug("|__ALL COUNT query="+sql);
			Long allModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
			results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,allModelsCount);
		}
		return results;

		/*try {
			MoreCategoryModelsStoredProcedure proc = new MoreCategoryModelsStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_MORE_CATEGORY_MODELS_COUNT);
			Map results = proc.execute(deviceId,categoryId,galleryType,pageSize,pageNo);
			log.debug("|__results="+results);
			return results;

		} catch (DataAccessException ex) {
			throw new BaseRuntimeException(ex);
		}*/
	}


	@SuppressWarnings("unchecked")
	public Map mobileAdvancedSearch(String deviceId,Integer pageSize, Integer pageNo,
			String mobileManufacturer, String requiredCatgId,Integer requiredCatgIdLength,
			String notRequiredCatgId) {
		
		Map results = new HashMap();
		ArrayList<Object> args;
		int startLimit= ((pageNo-1)*pageSize) ;
		/*
		 * Search Result
		 */
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT 'Search Result' as TITLE, model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE  , model.MODEL_LINK  ");
		sql.append(" FROM ( SELECT MODEL_ID, category_id FROM model_category_mapping   ");
		if(StringUtility.isNotNullBlank(requiredCatgId) || StringUtility.isNotNullBlank(notRequiredCatgId))
			sql.append(" where ");
		if(StringUtility.isNotNullBlank(requiredCatgId)) 
			sql.append(" CATEGORY_ID IN  ("+requiredCatgId+" )  ");
		if(StringUtility.isNotNullBlank(requiredCatgId) && StringUtility.isNotNullBlank(notRequiredCatgId))
			sql.append(" and ");
		if(StringUtility.isNotNullBlank(notRequiredCatgId))
			sql.append(" Model_Id not in(select model_id from model_category_mapping where category_id  in ("+notRequiredCatgId+")) ");
		sql.append(" GROUP BY MODEL_ID,category_id ) T  ");
		sql.append(" inner join model model on T.MODEL_ID=model.MODEL_ID where DEVICE_MST_ID= ? ");
		if(StringUtility.isNotNullBlank(mobileManufacturer)) 
			sql.append(" and MANUFACTURER_ID in ("+mobileManufacturer+" ) ");
		sql.append(" and STATUS_ID=? group by model.model_id " );
		if(StringUtility.isNotNullBlank(requiredCatgId)) 
			sql.append(" having count(model.model_id )  = "+requiredCatgIdLength);
		sql.append(" order by LAUNCH_DATE desc  LIMIT "+startLimit+" , "+pageSize );
		log.debug("|__AdvancedSearch query="+sql);
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		List<ModelDto> advancedSearchModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
		results.put(ZProcConstants.SEARCHED_MODELS_RESULT_SET,advancedSearchModels);
		
		/*
		 * Search Result Count
		 */
		sql=new StringBuilder();
		sql.append("SELECT count(1) count from ( ");
		sql.append("Select * FROM ( SELECT MODEL_ID M_ID, category_id FROM model_category_mapping  ");
		if(StringUtility.isNotNullBlank(requiredCatgId) || StringUtility.isNotNullBlank(notRequiredCatgId))
			sql.append(" where ");
		if(StringUtility.isNotNullBlank(requiredCatgId)) 
			sql.append(" CATEGORY_ID IN  ("+requiredCatgId+" )  ");
		if(StringUtility.isNotNullBlank(requiredCatgId) && StringUtility.isNotNullBlank(notRequiredCatgId))
			sql.append(" and ");
		if(StringUtility.isNotNullBlank(notRequiredCatgId))
			sql.append(" Model_Id not in(select model_id from model_category_mapping where category_id  in ("+notRequiredCatgId+")) ");
		sql.append("GROUP BY MODEL_ID,category_id ) T  ");
		sql.append("inner join model model on T.M_ID=model.MODEL_ID where DEVICE_MST_ID=? ");
		if(StringUtility.isNotNullBlank(mobileManufacturer)) 
			sql.append(" and MANUFACTURER_ID in ("+mobileManufacturer+" ) ");
		sql.append("and STATUS_ID=? group by model.model_id ");
		if(StringUtility.isNotNullBlank(requiredCatgId)) 
			sql.append(" having count(model.model_id )  = "+requiredCatgIdLength);
		sql.append(" ) C  ");
		log.debug("|__AdvancedSearch COUNT query="+sql);
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		Long advancedSearchModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
		results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,advancedSearchModelsCount);
		
		return results;

		/*try {
			MobileAdvancedSearchStoredProcedure proc = new MobileAdvancedSearchStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_MOBILE_ADVANCED_SEARCH);
			Map results = proc.execute(deviceId,pageSize, pageNo,mobileManufacturer,requiredCatgId,requiredCatgIdLength,notRequiredCatgId);
			log.debug("|__results="+results);
			return results;

		} catch (DataAccessException ex) {
			throw new BaseRuntimeException(ex);
		}*/
	}
	
	@SuppressWarnings("unchecked")
	public Map quickSearch(Long deviceId,String searchStr,Integer pageSize, Integer pageNo) {
		log.debug("@quickSearch | deviceId="+deviceId+"\n searchStr="+searchStr+"\n pageSize="+pageSize+"\n pageNo="+pageNo);
		getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
		Map results = new HashMap();
		StringBuilder sql;
		ArrayList<Object> args;

		/*
		 * quickSearch
		 */
		sql=new StringBuilder();
		sql.append(" SELECT 'Search Result :' as TITLE, MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, MODIFIED_DATE, MODEL_LINK  ");
		sql.append(" FROM model  ");
		sql.append(" where   ");
		sql.append(" DEVICE_MST_ID=? ");
		sql.append(" and STATUS_ID=? ");
		sql.append(" and MODEL_NAME like '%"+searchStr+"%' ");
		sql.append(" order by MODIFIED_DATE desc LIMIT "+(pageNo-1)*pageSize+","+pageSize );
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__quickSearch query="+sql);
		List<ModelDto> quickSearchModels=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
		results.put(ZProcConstants.SEARCHED_MODELS_RESULT_SET,quickSearchModels);
		/*
		 * quickSearch COUNT
		 */
		sql=new StringBuilder();
		sql.append(" SELECT count(1) count   ");
		sql.append(" FROM model  ");
		sql.append(" where MODEL_NAME like '%"+searchStr+"%'");
		sql.append(" and DEVICE_MST_ID=? ");
		sql.append(" and STATUS_ID=? ");
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__quickSearch COUNT query="+sql);
		Long quickSearchModelsCount=(Long)((ListOrderedMap)getJdbcTemplate().queryForList(sql.toString(), args.toArray()).get(0)).get("count");
		results.put(ZProcConstants.OUT_TOTAL_MODEL_COUNT,quickSearchModelsCount);
		
		return results;
		/*try {
    		QuickSearchStoredProcedure proc = new QuickSearchStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_QUICK_SEARCH);
            Map results = proc.execute(deviceId,searchStr,pageSize, pageNo);
            log.debug("|__results="+results);
            return results;

    	} catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
	}

}