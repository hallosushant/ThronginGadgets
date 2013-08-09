package com.sushant.verma.device.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.constants.ZConstants.TitleEnum;
import com.sushant.verma.common.dto.ModelDetailDto;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.RatingDto;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.dto.SearchResultDto;
import com.sushant.verma.common.mapper.DeviceReviewMapper;
import com.sushant.verma.common.mapper.ModelDetailMapper;
import com.sushant.verma.common.mapper.ModelMapper;
import com.sushant.verma.common.mapper.ModelSearchMapper;
import com.sushant.verma.common.mapper.ReviewMapper;

public class DeviceDao extends JdbcDaoSupport {

	private static Logger log = LogManager.getLogger(DeviceDao.class);
/*
	@SuppressWarnings("unchecked")
	public List<ModelDto> fetchManufacturerModels(Long deviceId,Long manufacturerId){
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.ALL.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", MODEL_ID, DEVICE_MST_ID," );
		sql.append(" MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, MODEL_IMAGE_URL, MODEL_VIDEO_LINK, MODIFIED_DATE ");
		sql.append(" FROM model  ");
		sql.append(" where DEVICE_MST_ID=? and MANUFACTURER_ID=? and STATUS_ID=? order by MODEL_NAME LIMIT 5 ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelDto> fetchManufacturerModelsPopular(Long deviceId,Long manufacturerId){
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK, model.MODIFIED_DATE  ");
		sql.append(" FROM model  inner join popularity ");
		sql.append(" on model.MODEL_ID=popularity.MODEL_ID "); 
		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID ");
		sql.append(" and model.MANUFACTURER_ID=popularity.MANUFACTURER_ID ");
		sql.append(" where model.DEVICE_MST_ID=? and model.MANUFACTURER_ID=? and model.STATUS_ID=? order by popularity.POPULARITY_INDEX  LIMIT 5 ");
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
	}
	@SuppressWarnings("unchecked")
	public List<ModelDto> fetchManufacturerModelsNew(Long deviceId,Long manufacturerId,String limitDate){
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, MODEL_IMAGE_URL, MODEL_VIDEO_LINK, MODIFIED_DATE ");
		sql.append(" FROM model  ");
		sql.append(" where DEVICE_MST_ID=? and MANUFACTURER_ID=? and STATUS_ID=? and LAUNCH_DATE between ? and CURRENT_DATE order by LAUNCH_DATE desc  LIMIT 5 ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(limitDate);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
	}
	@SuppressWarnings("unchecked")
	public List<ModelDto> fetchManufacturerModelsUpComing(Long deviceId,Long manufacturerId){
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, MODEL_IMAGE_URL, MODEL_VIDEO_LINK, MODIFIED_DATE ");
		sql.append(" FROM model  ");
		sql.append(" where DEVICE_MST_ID=? and MANUFACTURER_ID=? and STATUS_ID=? and LAUNCH_DATE > CURRENT_DATE order by LAUNCH_DATE LIMIT 5 ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
	}
*/
	@SuppressWarnings("unchecked")
	public List<ModelDto> morePopularModels(Long deviceId,Long manufacturerId, Integer pageSize,Integer pageNo) {
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
		sql.append(" FROM model  inner join popularity ");
		sql.append(" on model.MODEL_ID=popularity.MODEL_ID "); 
		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID ");
		sql.append(" and model.MANUFACTURER_ID=popularity.MANUFACTURER_ID ");
		sql.append(" where model.DEVICE_MST_ID=? and model.MANUFACTURER_ID=? and model.STATUS_ID=? ");
		sql.append(" order by popularity.POPULARITY_INDEX  LIMIT "+startLimit+","+pageSize);
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreNewModels(Long deviceId,Long manufacturerId,String limitDate, Integer pageSize,Integer pageNo){
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", MODEL_ID, DEVICE_MST_ID, ");
		sql.append(" MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, MODIFIED_DATE, MODEL_LINK ");
		sql.append(" FROM model  ");
		sql.append(" where DEVICE_MST_ID=? and MANUFACTURER_ID=? and STATUS_ID=? and LAUNCH_DATE between ? and CURRENT_DATE ");
		sql.append(" order by LAUNCH_DATE desc  LIMIT "+startLimit+","+pageSize);
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(limitDate);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreUpcomingModels(Long deviceId,Long manufacturerId, Integer pageSize,Integer pageNo){
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", MODEL_ID, DEVICE_MST_ID, ");
		sql.append(" MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, MODIFIED_DATE , MODEL_LINK ");
		sql.append(" FROM model  ");
		sql.append(" where DEVICE_MST_ID=? and MANUFACTURER_ID=? and STATUS_ID=? and LAUNCH_DATE > CURRENT_DATE ");
		sql.append(" order by LAUNCH_DATE desc  LIMIT "+startLimit+","+pageSize);
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreModels(Long deviceId,Long manufacturerId, Integer pageSize,Integer pageNo){
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.ALL.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+",MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, MODIFIED_DATE, MODEL_LINK ");
		sql.append(" FROM model  ");
		sql.append(" where DEVICE_MST_ID=? and MANUFACTURER_ID=? and STATUS_ID=? order by LAUNCH_DATE desc  LIMIT "+startLimit+","+pageSize);
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(manufacturerId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}





	
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreCatgPopularModels(Long deviceId,Long categoryId, Integer pageSize,Integer pageNo) {
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT distinct '"+TitleEnum.POPULAR.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, ");
		sql.append(" model.MODEL_NAME, model.PRICE, model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
		sql.append(" FROM model  inner join popularity  ");
		sql.append(" on model.MODEL_ID=popularity.MODEL_ID  ");
		sql.append(" and model.DEVICE_MST_ID=popularity.DEVICE_ID ");
		sql.append(" inner join model_category_mapping mcp  ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
		sql.append(" and mcp.CATEGORY_ID=popularity.CATEGORY_ID ");
		sql.append(" where model.DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=? ");
		sql.append(" order by popularity.POPULARITY_INDEX  LIMIT "+startLimit+","+pageSize );
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreCatgNewModels(Long deviceId,Long categoryId,String limitDate, Integer pageSize,Integer pageNo){
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.NEW.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=? ");
		sql.append(" and LAUNCH_DATE between ? and CURRENT_DATE ");
		sql.append(" order by LAUNCH_DATE desc  LIMIT "+startLimit+","+pageSize );
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(limitDate);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreCatgUpcomingModels(Long deviceId,Long categoryId, Integer pageSize,Integer pageNo){
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.UPCOMING.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC,  ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=? ");
		sql.append(" and LAUNCH_DATE > CURRENT_DATE ");
		sql.append(" order by LAUNCH_DATE LIMIT "+startLimit+","+pageSize );
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}
	@SuppressWarnings("unchecked")
	public List<ModelDto> moreCatgModels(Long deviceId,Long categoryId, Integer pageSize,Integer pageNo){
		Integer startLimit=(pageNo-1)*pageSize;
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT '"+TitleEnum.ALL.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", model.MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK ");
		sql.append(" FROM model inner join model_category_mapping mcp ");
		sql.append(" on model.MODEL_ID=mcp.MODEL_ID ");
		sql.append(" where DEVICE_MST_ID=? and mcp.CATEGORY_ID=? and model.STATUS_ID=? ");
		sql.append(" order by MODEL_NAME LIMIT "+startLimit+","+pageSize );
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(categoryId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}

	@SuppressWarnings("unchecked")
	public List<ReviewDto> fetchModelReviews(Long modelId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT REVIEW_ID, TITLE, REVIEW, AUTHOR, DATE_FORMAT(REVIEW_DATE,'%D %M %Y') as REVIEW_DATE, REF_LINK, IMAGE_LINK, VIDEO_LINK, ");
		sql.append(" STAR_RATING, RATING_COUNT ");
		sql.append(" FROM review ");
		sql.append(" WHERE ");
		sql.append(" MODEL_ID = ? AND STATUS_ID = ? ");
		sql.append(" ORDER BY REVIEW_DATE ASC ");
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(), args.toArray(),new ReviewMapper());
		
	}

	@SuppressWarnings("unchecked")
	public List<ReviewDto> fetchDeviceLatestReviews(Long deviceId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT model.DEVICE_MST_ID,model.MANUFACTURER_ID,model.MODEL_ID,model.MODEL_NAME,model.MODEL_IMAGE_URL,review.REVIEW_ID,review.TITLE,substring(review.REVIEW,1,300) REVIEW,review.AUTHOR,  ");
		sql.append(" DATE_FORMAT(review.REVIEW_DATE,'%D %M %Y') as REVIEW_DATE,review.STAR_RATING,review.RATING_COUNT, review.REF_LINK, review.STATUS_ID ");
		sql.append(" FROM model Inner Join review ON model.MODEL_ID = review.MODEL_ID ");
		sql.append(" WHERE  ");
		sql.append(" model.DEVICE_MST_ID=? and review.STATUS_ID =  ? ");
		sql.append(" ORDER BY review.REVIEW_DATE DESC ");
		sql.append(" LIMIT 0, 6 ");
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(), args.toArray(),new DeviceReviewMapper());
		
	}

	@SuppressWarnings("unchecked")
	public void rateThis(RatingDto ratingDto) {
		getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
    	
    	Float finalRating;
    	if(null!=ratingDto.getModelId() && 0!=ratingDto.getModelId()){
        	List<ListOrderedMap> modelRatingList=getJdbcTemplate().queryForList("SELECT RATING_COUNT,STAR_RATING FROM model where MODEL_ID=?",new Object[]{ratingDto.getModelId()});
        	log.info("modelRatingList="+modelRatingList);    	
        	Long ratingCount=(Long) modelRatingList.get(0).get("RATING_COUNT");
        	Float starRating=(Float) modelRatingList.get(0).get("STAR_RATING");
        	finalRating=((starRating*ratingCount)+ratingDto.getStarRating())/(ratingCount+1);
        	
        	String sql=" Update model set RATING_COUNT=RATING_COUNT+1,STAR_RATING=? where MODEL_ID=? ";
    		ArrayList<Object> args=new ArrayList<Object>();
    		args.add(finalRating);
    		args.add(ratingDto.getModelId());
    		int update=getJdbcTemplate().update(sql.toString(), args.toArray());
    		log.info("UPDATE rating count ="+update);
    	}else if(null!=ratingDto.getReviewId() && 0!=ratingDto.getReviewId()){
    		List<ListOrderedMap> reviewRatingList=getJdbcTemplate().queryForList("SELECT RATING_COUNT,STAR_RATING FROM review where REVIEW_ID=?",new Object[]{ratingDto.getReviewId()});
        	log.info("reviewRatingList="+reviewRatingList);    	
        	Long ratingCount=(Long) reviewRatingList.get(0).get("RATING_COUNT");
        	Float starRating=(Float) reviewRatingList.get(0).get("STAR_RATING");
        	finalRating=((starRating*ratingCount)+ratingDto.getStarRating())/(ratingCount+1);
        	
        	String sql=" Update review set RATING_COUNT=RATING_COUNT+1,STAR_RATING=? where REVIEW_ID=? ";
    		ArrayList<Object> args=new ArrayList<Object>();
    		args.add(finalRating);
    		args.add(ratingDto.getReviewId());
    		int update=getJdbcTemplate().update(sql.toString(), args.toArray());
    		log.info("UPDATE rating count ="+update);
    	}

		/*try {
    		RateThisStoredProcedure proc = new RateThisStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_RATE_THIS);
            proc.execute(ratingDto);
    	} catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
	}

	public Integer saveModelReview(ReviewDto reviewDto) {

		String sql=" INSERT INTO review ( MODEL_ID, TITLE, REVIEW, AUTHOR, REVIEW_DATE, MODIFIED_BY, STATUS_ID ) VALUES ( ?,?,?,?,CURRENT_DATE,?,? ) ";
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(reviewDto.getModelId());
		args.add(reviewDto.getTitle());
		args.add(reviewDto.getReview());
		args.add(reviewDto.getAuthor());
		args.add(reviewDto.getModifiedBy());
		args.add(DbConstants.INACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().update(sql.toString().toLowerCase(), args.toArray());
	}

	@SuppressWarnings("unchecked")
	public List<ModelDetailDto> fetchModelPhotos(Long modelId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT CATEGORY_NAME, DETAIL_NAME, DETAIL_VALUE ");
		sql.append(" FROM model_detail ");
		sql.append(" WHERE ");
		sql.append(" MODEL_ID = ? AND DETAIL_NAME_ID=? AND STATUS_ID = ? ");
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.MODEL_GALLERY_PHOTO_ID);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelDetailMapper());
	}

	@SuppressWarnings("unchecked")
	public List<SearchResultDto> zModelSearch(String searchStr,Long deviceId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT  MODEL_ID, MODEL_NAME, MODEL_IMAGE_URL" );
		sql.append(" FROM model  ");
		sql.append(" where MODEL_NAME like ? and DEVICE_MST_ID=? and STATUS_ID=? order by MODIFIED_DATE desc LIMIT 5 ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add("%"+searchStr+"%");
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelSearchMapper());

	}

	@SuppressWarnings("unchecked")
	public List<ModelDto> fetchRelatedCatgModels(Long deviceId,Long modelId, String tagsCatg) {
		log.debug("deviceId="+deviceId+" | modelId="+modelId+" | tagsCatg="+tagsCatg);
		String sql1="select PRICE from MODEL where model_id=? ";
		Long price=getJdbcTemplate().queryForLong(sql1.toLowerCase(),new Object[]{modelId});
		log.debug("price="+price);
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT distinct m.MODEL_ID,'"+TitleEnum.RELATED_MODELS.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", m.DEVICE_MST_ID," );
		sql.append(" m.MANUFACTURER_ID, m.MODEL_NAME, m.PRICE,m.LAUNCH_DATE, m.MODEL_DESC, m.MODEL_IMAGE_URL, m.MODEL_VIDEO_LINK, " );
		sql.append(" m.STAR_RATING, m.RATING_COUNT, m.MODIFIED_DATE, m.MODEL_LINK ");
		sql.append(" FROM MODEL m inner join MODEL_CATEGORY_MAPPING mcm on m.MODEL_ID=mcm.MODEL_ID ");
		sql.append(" where m.DEVICE_MST_ID=? and m.STATUS_ID=? and MCM.CATEGORY_ID in ("+tagsCatg+")  ");
		sql.append(" and m.PRICE between ? and ? and m.MODEL_ID <> ? ");
		sql.append(" order by m.MODIFIED_DATE desc LIMIT 8 ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(price-price/2);
		args.add(price+price/2);
		args.add(modelId);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}

	/**
	 * Should be used with modified date within 3months
	 * @param deviceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ModelDto> fetchTopRatedModels(Long deviceId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT distinct m.MODEL_ID,'"+TitleEnum.TOP_RATED_MODELS.getTitle()+"' as "+TitleEnum.TITLE.getTitle()+", m.DEVICE_MST_ID," );
		sql.append(" m.MANUFACTURER_ID, m.MODEL_NAME, m.PRICE,m.LAUNCH_DATE, m.MODEL_DESC, m.MODEL_IMAGE_URL, m.MODEL_VIDEO_LINK, " );
		sql.append(" m.STAR_RATING, m.RATING_COUNT, m.MODIFIED_DATE, m.MODEL_LINK ");
		sql.append(" FROM MODEL m ");
		sql.append(" where m.DEVICE_MST_ID=? and m.STATUS_ID=? order by m.STAR_RATING desc LIMIT 5 ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString().toLowerCase(), args.toArray(),new ModelMapper());
	}
}
