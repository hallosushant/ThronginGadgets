package com.sushant.verma.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.constants.ZConstants.TitleEnum;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.mapper.ModelMapper;
import com.sushant.verma.common.utility.DateUtility;
import com.sushant.verma.device.DeviceConstants;

public class CommonDao extends JdbcDaoSupport {
	
	

	private static Logger log = LogManager.getLogger(CommonDao.class);

	@SuppressWarnings("unchecked")
	public List<ListOrderedMap> getDeviceList(){
		log.info("@Start of deviceList");		
		String sql=" select DEVICE_ID,DEVICE_NAME,DEVICE_IMG_PATH from DEVICE_MST where STATUS_ID=1 ";
		List<ListOrderedMap> deviceList=(List<ListOrderedMap>) getJdbcTemplate().queryForList(sql.toLowerCase());
		log.debug("|__deviceList="+deviceList);
		return deviceList;
	}

	@SuppressWarnings("unchecked")
	public Map getModelList(Long deviceId) {
    	log.debug("|___ deviceId=="+deviceId);
    	
    	getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
    	
    	Map modelsMap=new HashMap();
    	String currentDate=DateUtility.getDateAfter(new Date(),DateUtility.ZERO_DAYS_BACK, DateUtility.YYYY_MM_DD_FORMAT);
    	/*
    	 * FEATURED
    	 */
    	StringBuilder sql=new StringBuilder();
		sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
		sql.append(" FROM model  inner join popularity ");
		sql.append(" on model.MODEL_ID=popularity.MODEL_ID "); 
		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID ");
		sql.append(" where model.DEVICE_MST_ID=? and model.STATUS_ID=? ");
		sql.append(" and popularity.IS_FEATURED =? LIMIT 6 ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(DbConstants.Y_STATUS);
		log.debug("|__POPULAR query="+sql);
		List<ModelDto> featuredModels=getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
		modelsMap.put(DeviceConstants.FEATURED_MODELS,featuredModels);
    	/*
    	 * POPULAR
    	 */
    	sql=new StringBuilder();
		sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
		sql.append(" FROM model  inner join popularity ");
		sql.append(" on model.MODEL_ID=popularity.MODEL_ID "); 
		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID ");
		sql.append(" where model.DEVICE_MST_ID=? and model.STATUS_ID=? ");
		sql.append(" order by popularity.POPULARITY_INDEX  LIMIT 10 ");
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__POPULAR query="+sql);
		List<ModelDto> popularModels=getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
		modelsMap.put(DeviceConstants.POPULAR_MODELS,popularModels);
		
		/*
		 * UPCOMING
		 */
		sql=new StringBuilder();
		sql.append(" select '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc, ");
		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date  , model_link   ");
		sql.append(" from model    ");
		sql.append(" where device_mst_id=?  ");
		sql.append(" and status_id=? and launch_date > ?  ");
		sql.append(" order by launch_date limit 5  ");
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(currentDate);
		log.debug("|__UPCOMING query="+sql);
		List<ModelDto> upcomingModels=getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
		modelsMap.put(DeviceConstants.UPCOMING_MODELS,upcomingModels);
		
		
		/*
		 * NEW
		 */
		String fromDate=DateUtility.getDateAfter(new Date(),DateUtility.NINTY_DAYS_BACK, DateUtility.YYYY_MM_DD_FORMAT);
		
		sql=new StringBuilder();
		sql.append(" select '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model_id, device_mst_id, manufacturer_id, model_name,price,launch_date, model_desc,  ");
		sql.append(" model_image_url, model_video_link,star_rating, rating_count, modified_date , model_link  ");
		sql.append(" from model    ");
		sql.append(" where device_mst_id=?  ");
		sql.append(" and status_id=?  ");
		sql.append(" and launch_date between ? and ?  ");
		sql.append(" order by launch_date desc  limit 5  ");
		args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(fromDate);
		args.add(currentDate);
		log.debug("|__NEW query="+sql);
		List<ModelDto> newModels=getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
		modelsMap.put(DeviceConstants.NEW_MODELS,newModels);
		
		return modelsMap;
    	/*try {
    		ModelsListStoredProcedure proc = new ModelsListStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_MODELS_LIST);
            Map results = proc.execute(deviceId);
            log.debug("|__results="+results);
            return results;

    	} catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }
*/    }
	

}
