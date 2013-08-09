package com.sushant.verma.device.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sushant.verma.common.ZProc.ZProcConstants;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.mapper.ModelDetailMapper;
import com.sushant.verma.common.mapper.ModelMapper;
import com.sushant.verma.common.mapper.ModelPictureMapper;
import com.sushant.verma.common.mapper.ModelTagsMapper;
 

public class ModelDetailsSPDao extends JdbcDaoSupport{
 
	private static final Logger log=LogManager.getLogger(ModelDetailsSPDao.class);
  
    @SuppressWarnings("unchecked")
	public Map fetchModelDetails(Long modelId) {
    	log.debug("@ fetchModelDetails |___modelId=="+modelId);
    	getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
    	Map results = new HashMap();
		StringBuilder sql;
		ArrayList<Object> args;
		
		sql=new StringBuilder();
		/*MODEL_BASIC_INFO_RESULT_SET*/
		sql.append(" SELECT MODEL_NAME as TITLE, MODEL_ID, DEVICE_MST_ID, MANUFACTURER_ID, MODEL_NAME,PRICE,LAUNCH_DATE, MODEL_DESC, ");
		sql.append(" MODEL_IMAGE_URL, MODEL_VIDEO_LINK, STAR_RATING, RATING_COUNT, MODIFIED_DATE  , MODEL_LINK  ");
		sql.append(" FROM model    ");
		sql.append(" where MODEL_ID =  ? and STATUS_ID=? ");
		args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__MODEL_BASIC_INFO_RESULT_SET query="+sql);
		List<ModelDto> modelBasicInfoResultSet=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelMapper());
		results.put(ZProcConstants.MODEL_BASIC_INFO_RESULT_SET,modelBasicInfoResultSet);
		
		
		sql=new StringBuilder();
		 /*MODEL_PICTURES_RESULT_SET*/
		sql.append(" SELECT DETAIL_VALUE as MODEL_IMAGE_URL ");
		sql.append(" FROM model_detail ");
		sql.append(" where  MODEL_ID=? and DETAIL_NAME_ID=? and STATUS_ID=? LIMIT 5  ");
		args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.MODEL_PROFILE_PICTURE);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__MODEL_PICTURES_RESULT_SET query="+sql);
		List<ModelDto> modelPicturesResultSet=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelPictureMapper());
		results.put(ZProcConstants.MODEL_PICTURES_RESULT_SET,modelPicturesResultSet);
		
		sql=new StringBuilder();
		 /*MODEL_DETAILS_RESULT_SET*/
		sql.append(" SELECT CATEGORY_NAME, DETAIL_NAME, DETAIL_VALUE ");
		sql.append(" FROM model_detail ");
		sql.append(" where MODEL_ID=? and STATUS_ID=? and CATEGORY_ID!=? ");
		sql.append(" order by CATEGORY_ID,DETAIL_NAME_ID  ");
		args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(DbConstants.MODEL_PICTURES);
		log.debug("|__MODEL_DETAILS_RESULT_SET query="+sql);
		List<ModelDto> modeldetailsResultSet=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelDetailMapper());
		results.put(ZProcConstants.MODEL_DETAILS_RESULT_SET,modeldetailsResultSet);
		
		sql=new StringBuilder();
		 /*MODEL_TAGS_RESULT_SET*/
		sql.append(" SELECT category_mst.CATEGORY_ID,category_mst.CATEGORY_NAME,category_mst.CATEGORY_DESC,category_mst.DEVICE_ID ");
		sql.append(" FROM category_mst  ");
		sql.append(" Inner Join model ON category_mst.DEVICE_ID = model.DEVICE_MST_ID  ");
		sql.append(" AND model.MODEL_ID =  ?  ");
		sql.append(" Inner Join model_category_mapping ON category_mst.CATEGORY_ID = model_category_mapping.CATEGORY_ID ");
		sql.append(" and model.MODEL_ID=model_category_mapping.MODEL_ID ");
		sql.append(" WHERE ");
		sql.append(" model_category_mapping.CATEGORY_ID IN   (4,5,6,8,10,11,23,24,25,27,28,29,30,31,32,36,38,40,41,42,43,44,59,60,67 ) AND ");
		sql.append(" model_category_mapping.STATUS_ID =  ? ");
		sql.append(" ORDER BY ");
		sql.append(" model_category_mapping.MODEL_CATEGORY_MAPPING_ID ASC ");
		sql.append(" LIMIT 5  ");		
		args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__MODEL_TAGS_RESULT_SET query="+sql);
		List<ModelDto> modelTagsResultSet=getJdbcTemplate().query(sql.toString(), args.toArray(),new ModelTagsMapper());
		results.put(ZProcConstants.MODEL_TAGS_RESULT_SET,modelTagsResultSet);
		
		return results;
    	/*try {
    		ModelDetailsStoredProcedure proc = new ModelDetailsStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_MODEL_DETAILS);
            Map results = proc.execute(modelId);
            log.debug("|__results="+results);
            return results;

    	} catch (DataAccessException ex) {
            throw new BaseRuntimeException(ex);
        }*/
    }
	
}