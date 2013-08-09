package com.sushant.verma.admin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sushant.verma.admin.AdminConstants;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.constants.ZConstants.TitleEnum;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.mapper.DeviceReviewMapper;
import com.sushant.verma.common.mapper.ModelMapper;
import com.sushant.verma.common.mapper.UrlRewriteMapper;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.common.utility.UserDataPasser;
import com.sushant.verma.device.DeviceConstants;

@SuppressWarnings("unchecked")
public class AdminDao extends JdbcDaoSupport{
	private static Logger log=LogManager.getLogger(AdminDao.class);

	public List<ListOrderedMap> getModelDetails(Long modelId) {
		String sql=" select CATEGORY_ID,CATEGORY_NAME,DETAIL_NAME_ID,DETAIL_NAME,DETAIL_VALUE from model_detail where STATUS_ID=? and MODEL_ID=? ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(DbConstants.ACTIVE_STATUS_ID);
		args.add(modelId);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().queryForList(sql, args.toArray());
	}

	public void assignModelDetail(final Long modelId,final List<String> detailName,final List<String> detailValue,final List<String> detailCategory) {
		String sql=" insert into model_detail (MODEL_ID,DETAIL_NAME_ID,DETAIL_NAME,DETAIL_VALUE,CATEGORY_ID,CATEGORY_NAME,MODIFIED_BY,STATUS_ID) " +
				"values (" +
				"?," +
				"?," +
				"(select DETAIL_NAME from detail_name_mst where DETAIL_NAME_ID=? and CATEGORY_ID=? and STATUS_ID = 1)," +
				"?," +
				"?," +
				"(select CATEGORY_NAME from category_mst where STATUS_ID = 1 and CATEGORY_ID=?)," +
				"?," +
				"?) ";

		log.debug("|__query="+sql+"\n|__detailName="+detailName+"\n|__detailCategory="+detailCategory+"\n|__detailValue="+detailValue);

		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			 
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, modelId);
				ps.setString(2, detailName.get(i));
				ps.setString(3, detailName.get(i));
				ps.setString(4, detailCategory.get(i) );
				ps.setString(5, detailValue.get(i) );
				ps.setString(6, detailCategory.get(i) );
				ps.setString(7, detailCategory.get(i) );
				ps.setLong(8, UserDataPasser.getUserID() );
				ps.setLong(9, DbConstants.ACTIVE_STATUS_ID );
			}
		 
			public int getBatchSize() {
				return detailName.size();
			}
		  });
	}
	
	public void deleteModelDetail(Long modelId){
		String sql=" delete from model_detail where MODEL_ID=? ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(modelId);
		log.debug("|__query="+sql+"\n|__modelId="+modelId);
		getJdbcTemplate().update(sql, args.toArray());
	}

	public List<ListOrderedMap> getModelList(int state) {
		String sql=null;
		if(state==1)
			sql=" select MODEL_ID,MODEL_NAME from model where STATUS_ID=1 ";
		else if(state==0)
			sql=" select m.MODEL_ID,m.MODEL_NAME from model m left  join model_detail md on m.model_id=md.model_id where m.STATUS_ID=1 and  md.model_id is null ";
		log.debug("|__query="+sql);
		return getJdbcTemplate().queryForList(sql);
	}
	
	public List<ListOrderedMap> getModelList(Long deviceId, Long manufacturerId) {
		
		String sql=" select m.MODEL_ID,m.MODEL_NAME from model m where m.STATUS_ID=1 and DEVICE_MST_ID=? and MANUFACTURER_ID=? ";
		log.debug("|__deviceId="+deviceId+"|__manufacturerId="+manufacturerId+"\n|__query="+sql);
		Object[] args=new Object[2];
		args[0]=deviceId;
		args[1]=manufacturerId;
		return getJdbcTemplate().queryForList(sql,args);
	}
	public List<ListOrderedMap> getUserList(String userEmail,Long userRoleId) {
		log.debug("|__userEmail=="+userEmail+
				"\n|__userRoleSearch=="+userRoleId);
		
		StringBuilder sql=new StringBuilder();
		ArrayList<Object> args=new ArrayList<Object>();		
		if(userRoleId.longValue()!=AdminConstants.USER_ROLE_ALL.longValue()){
			sql.append(" SELECT U.USER_ID, U.USER_EMAIL, U.MODIFIED_DATE, U.CREATED_DATE, U.CREATED_BY, U.MODIFIED_BY, U.STATUS as ACTIVE, U.IS_BLOCKED, " );
			sql.append(" URMP.USER_ROLE_MASTER_ID,URMP.STATUS as APPROVED, URMP.IS_REJECTED, URMS.ROLE_TYPE ");
			sql.append(" FROM user as U Left Join user_role_mapping as URMP ");
			sql.append(" on U.USER_ID=URMP.USER_ID ");
			sql.append(" Left Join user_role_master as URMS ");
			sql.append(" on URMP.USER_ROLE_MASTER_ID=URMS.USER_ROLE_MASTER_ID ");

			if(StringUtility.isNotNullBlank(userEmail) && userRoleId.longValue()==0)
				sql.append(" where U.USER_EMAIL = ? ");
			if(StringUtility.isNotNullBlank(userEmail) && userRoleId.longValue()!=0)
				sql.append(" where U.USER_EMAIL = ? and URMP.USER_ROLE_MASTER_ID = ? ");
			if(StringUtility.isNullBlank(userEmail) && userRoleId.longValue()!=0)
				sql.append(" where URMP.USER_ROLE_MASTER_ID = ? ");

			if(StringUtility.isNotNullBlank(userEmail) && userRoleId.longValue()==0)
				args.add(userEmail);
			if(StringUtility.isNotNullBlank(userEmail) && userRoleId.longValue()!=0){
				args.add(userEmail);
				args.add(userRoleId);
			}
			if(StringUtility.isNullBlank(userEmail) && userRoleId.longValue()!=0)
				args.add(userRoleId);
		}
		else if(userRoleId.longValue()==AdminConstants.USER_ROLE_ALL.longValue()){
			sql.append(" SELECT U.USER_ID, U.USER_EMAIL, U.MODIFIED_DATE, U.CREATED_DATE, U.CREATED_BY, U.MODIFIED_BY, U.STATUS as ACTIVE, U.IS_BLOCKED, " );
			sql.append(" URMP.USER_ROLE_MASTER_ID,URMP.STATUS as APPROVED, URMP.IS_REJECTED, URMS.ROLE_TYPE ");
			sql.append(" FROM user as U inner Join user_role_mapping as URMP ");
			sql.append(" on U.USER_ID=URMP.USER_ID ");
			sql.append(" inner Join user_role_master as URMS ");
			sql.append(" on URMP.USER_ROLE_MASTER_ID=URMS.USER_ROLE_MASTER_ID ");

			if(StringUtility.isNotNullBlank(userEmail))
				sql.append(" where U.USER_EMAIL = ? ");
			
			if(StringUtility.isNotNullBlank(userEmail))
				args.add(userEmail);
			}
		log.debug("|__sql query=="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return (List<ListOrderedMap>)getJdbcTemplate().queryForList(sql.toString(), args.toArray());
	}

	public void removeCurrentRole(Long userId, Long currentRoleId) {
		String sql=" delete from user_role_mapping where USER_ID=? and USER_ROLE_MASTER_ID= ? ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(userId);
		args.add(currentRoleId);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		getJdbcTemplate().update(sql, args.toArray());
	}

	public void assignUserRole(Long userId, Long selectedUserRole) {
		String sql=" insert into user_role_mapping (USER_ID,USER_ROLE_MASTER_ID,STATUS) values (?,?,?) ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(userId);
		args.add(selectedUserRole);
		args.add(DbConstants.Y_STATUS);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		getJdbcTemplate().update(sql, args.toArray());
	}


	public List<ListOrderedMap> getCategoryList() {
		String sql=" select CATEGORY_ID,CATEGORY_NAME from category_mst where STATUS_ID = ? order by CATEGORY_NAME ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__sql query=="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return (List<ListOrderedMap>)getJdbcTemplate().queryForList(sql.toString(), args.toArray());
	}

	public void createCategory(String categoryName, Long parentCategory) {
		String sql=" insert into category_mst (CATEGORY_NAME,PARENT_CATEGORY_ID,STATUS) values (?,?,?) ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(categoryName);
		args.add(parentCategory);
		args.add(DbConstants.Y_STATUS);
		log.debug("|__sql query=="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);	
		int rowsEffected=getJdbcTemplate().update(sql, args.toArray());
		log.debug("|__rowsEffected"+rowsEffected);
	}

	public List<ListOrderedMap> searchSubCategoryList(Long categoryNameSearch) {
		StringBuilder sql=new StringBuilder();
		sql.append(" select GIVEN.CATEGORY_NAME as SEARCHED_CATEGORY, PARENT.CATEGORY_NAME as PARENT_CATEGORY,'-' as SUB_CATEGORY ");
		sql.append(" from category_mst as PARENT ");
		sql.append(" inner join category_mst as GIVEN ");
		sql.append(" on PARENT.CATEGORY_MST_ID=GIVEN.PARENT_CATEGORY_ID ");
		sql.append(" and GIVEN.CATEGORY_MST_ID=?  and given.STATUS=? ");
		sql.append(" union  ");
		sql.append(" select GIVEN.CATEGORY_NAME as SEARCHED_CATEGORY,'-' as PARENT_CATEGORY,CHILD.CATEGORY_NAME as SUB_CATEGORY ");
		sql.append(" from category_mst as CHILD ");
		sql.append(" inner join category_mst as GIVEN ");
		sql.append(" on GIVEN.CATEGORY_MST_ID=CHILD.PARENT_CATEGORY_ID ");
		sql.append(" and GIVEN.CATEGORY_MST_ID=?  and given.STATUS=? ");

		ArrayList<Object> args=new ArrayList<Object>();
		args.add(categoryNameSearch);
		args.add(DbConstants.Y_STATUS);
		args.add(categoryNameSearch);
		args.add(DbConstants.Y_STATUS);
		log.debug("|__sql query=="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);	
		return (List<ListOrderedMap>)getJdbcTemplate().queryForList(sql.toString(), args.toArray());
	}


	public void createAttribute(String attributeName, String attributeDesc) {
		String sql=" INSERT INTO attribute_mst(ATTRIBUTE_NAME,ATTRIBUTE_DESC,STATUS) VALUES (?,?,?) ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(attributeName);
		args.add(attributeDesc);
		args.add(DbConstants.Y_STATUS);
		log.debug("|__sql query=="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);	
		int rowsEffected=getJdbcTemplate().update(sql, args.toArray());
		log.debug("|__rowsEffected"+rowsEffected);		
	}

	/*
	 * NEW METHODS
	 */
	public List<ListOrderedMap> getManufacturerList(Long deviceId) {
		String sql=" select MANUFACTURER_ID, MANUFACTURER_NAME from manufacturer where STATUS_ID=1 and DEVICE_MST_ID = ? order by MANUFACTURER_NAME ";
		Object[] args=new Object[1];
		args[0]=deviceId;
		log.debug("|__sql query=="+sql);
		List<ListOrderedMap> manufacturerList=getJdbcTemplate().queryForList(sql, args);
		log.debug("|__manufacturerList="+manufacturerList);
		return manufacturerList;
	}

	public Long getModelId(String modelName) {
		String sql=" select MODEL_ID from model where STATUS_ID=1 and MODEL_NAME = ? ";
		Object[] args=new Object[1];
		args[0]=modelName;
		log.debug("|__sql query=="+sql);
		Long modelId=getJdbcTemplate().queryForLong(sql, args);
		log.debug("|__modelId="+modelId);
		return modelId;
	}

	public int addNewModel(ModelDto modelDto) {
		StringBuilder sql=new StringBuilder();
		ArrayList<Object> args=new ArrayList<Object>();
		
		sql.append(" INSERT INTO model ( ");
		sql.append(" DEVICE_MST_ID,");
		sql.append(" MANUFACTURER_ID,");
		sql.append(" MODEL_NAME, ");
		sql.append(" MODEL_DESC, ");
		if(modelDto.getPrice()!=null)
			sql.append(" PRICE, ");
		if(modelDto.getModelLaunchDate()!=null)
			sql.append(" LAUNCH_DATE, ");
		if(modelDto.getModelImageBytes()!=null)
			sql.append(" MODEL_IMAGE,");
		if(StringUtility.isNotNullBlank(modelDto.getModelImageUrl()))
			sql.append(" MODEL_IMAGE_URL,");
		sql.append(" MODIFIED_BY,");
		sql.append(" STATUS_ID ");
		sql.append(" ) ");
		sql.append(" VALUES (");
		
		sql.append(" ?, ");
		args.add(modelDto.getDeviceId());
		
		sql.append(" ?, ");
		args.add(modelDto.getManufacturerId());
		
		sql.append(" ?, ");
		args.add(modelDto.getModelName());
		
		sql.append(" ?, ");
		args.add(modelDto.getModelDesc());
		
		if(modelDto.getPrice()!=null){
			sql.append(" ?, ");
			args.add(modelDto.getPrice());
		}
		if(modelDto.getModelLaunchDate()!=null){
			sql.append(" ?, ");
			args.add(modelDto.getModelLaunchDate());
		}
		if(modelDto.getModelImageBytes()!=null){
			sql.append(" ?, ");
			args.add(modelDto.getModelImageBytes());
		}
		if(StringUtility.isNotNullBlank(modelDto.getModelImageUrl())){
			sql.append(" ?, ");
			args.add(modelDto.getModelImageUrl());
		}
			
		sql.append(" ?, ");
		args.add(modelDto.getModifiedBy());
				
		sql.append(" ? ");
		args.add(modelDto.getStatusId());
		
		sql.append(" ) ");
		log.debug("|__query="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().update(sql.toString(), args.toArray());
		
	}

	public void addModelCategory(final ModelDto modelDto) {
		
		StringBuilder sql=new StringBuilder();
		sql.append(" INSERT INTO model_category_mapping (");
		sql.append(" CATEGORY_ID,");
		sql.append(" MODEL_ID, ");
		sql.append(" MODIFIED_DATE,");
		sql.append(" MODIFIED_BY,");
		sql.append(" STATUS_ID ");
		sql.append(" ) ");
		sql.append(" VALUES (");
		sql.append(" ?, ");//CATEGORY_ID
		sql.append(" ?, ");//MODEL_ID
		sql.append(" now(), ");//MODIFIED_DATE
		sql.append(" ?, ");//MODIFIED_BY
		sql.append(" ?  ");//STATUS_ID
		sql.append(" )  ");
		
		log.debug("|__query="+sql.toString());

		getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			 
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, modelDto.getModelCategoryId().get(i));
				ps.setLong(2, modelDto.getModelId());
				ps.setLong(3, modelDto.getModifiedBy() );
				ps.setLong(4, DbConstants.ACTIVE_STATUS_ID );
			}
		 
			public int getBatchSize() {
				return modelDto.getModelCategoryId().size();
			}
		  });
		
	}

	public List<ListOrderedMap> getDetailNameList(String detailCategory) {
		String sql=" select DETAIL_NAME_ID,DETAIL_NAME from detail_name_mst where STATUS_ID=1 and CATEGORY_ID = ? ";
		Object[] args=new Object[1];
		args[0]=detailCategory;
		log.debug("|__sql query=="+sql);
		List<ListOrderedMap> detailNameList=getJdbcTemplate().queryForList(sql, args);
		log.debug("|__detailNameList="+detailNameList);
		return detailNameList;
	}



	public List<ReviewDto> fetchDeviceInactiveReviews(Long deviceId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT model.DEVICE_MST_ID,model.MANUFACTURER_ID,model.MODEL_ID,model.MODEL_NAME,model.MODEL_IMAGE_URL,review.REVIEW_ID,review.TITLE,review.REVIEW,review.AUTHOR,  ");
		sql.append(" DATE_FORMAT(review.REVIEW_DATE,'%D %M %Y') as REVIEW_DATE,review.STAR_RATING,review.RATING_COUNT, review.REF_LINK, review.STATUS_ID ");
		sql.append(" FROM model Inner Join review ON model.MODEL_ID = review.MODEL_ID ");
		sql.append(" WHERE  ");
		sql.append(" model.DEVICE_MST_ID=? and review.STATUS_ID =  ? ");
		sql.append(" ORDER BY review.REVIEW_DATE DESC ");
		sql.append(" LIMIT 0, 1 ");
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(deviceId);
		args.add(DbConstants.INACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(), args.toArray(),new DeviceReviewMapper());
		
	}

	public List<ReviewDto> approveReview(ReviewDto reviewDto) {
		
		getJdbcTemplate().execute("SET session TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");

		/*
    	 * Approve Review
    	 */
    	StringBuilder sql=new StringBuilder();
    	sql.append(" Update review 	 ");
    	sql.append(" inner join ");
    	sql.append(" ( ");
    	sql.append(" 	SELECT ");
    	sql.append(" 	review.REVIEW_ID, ");
    	sql.append(" 	concat( ");
    	sql.append(" 		'/',device_mst.DEVICE_NAME,'/',replace(model.MODEL_NAME,' ','-'),'/Review/', ");
    	sql.append(" 		(case ");
    	sql.append(" 			when (locate('-',replace(review.TITLE,' ','-'),30)-1) >0 ");
    	sql.append(" 				then substr(replace(review.TITLE,' ','-'),1,locate('-',replace(review.TITLE,' ','-'),30)-1) ");
    	sql.append(" 			else substr(replace(review.TITLE,' ','-'),1,30) ");
    	sql.append(" 		end), ");
    	sql.append(" 		'.html' ");
    	sql.append(" 	) as REF_LINK  FROM   review ");
    	sql.append(" 	Inner Join model ON review.MODEL_ID = model.MODEL_ID ");
    	sql.append(" 	Inner Join device_mst ON model.DEVICE_MST_ID = device_mst.DEVICE_ID ");
    	sql.append(" 	where REVIEW_ID=? ");
    	sql.append(" ) t ");
    	sql.append(" on review.REVIEW_ID=t.REVIEW_ID ");
    	sql.append(" set review.REF_LINK=t.REF_LINK, review.TITLE=? , review.REVIEW=? , review.STATUS_ID=? ");
    	sql.append(" where review.REVIEW_ID=? ");
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(reviewDto.getReviewId());
		args.add(reviewDto.getTitle());
		args.add(reviewDto.getReview());
		args.add(reviewDto.getStatusId());
		args.add(reviewDto.getReviewId());

		log.debug("|__Approve Review query="+sql);
		int approveCount=getJdbcTemplate().update(sql.toString(), args.toArray());
		log.debug("|__Approve Review count="+approveCount);
		
		/*
		 * Getting the list of UrlRewriteList
		 */
		sql=new StringBuilder();
		sql.append(" select concat('<rule><from>^',review.REF_LINK,'$</from><to>', ");
		sql.append(" 	concat('/modelDetail!review.html\\?dId=',model.DEVICE_MST_ID,'&amp;bId=',model.MANUFACTURER_ID,'&amp;mId=',model.MODEL_ID,'&amp;rId=',review.REVIEW_ID), ");
		sql.append(" 	'</to><for type=\"reviewId\">',review.REVIEW_ID,'</for></rule>') urlRewriteRule ");
		sql.append(" from review ");
		sql.append(" 	Inner Join model ON review.MODEL_ID = model.MODEL_ID ");
		sql.append(" where review.REVIEW_ID=? and review.STATUS_ID=? ");
		sql.append(" union ");
		sql.append(" select concat('<outbound-rule><from>', ");
		sql.append(" 	concat('/modelDetail!review.html\\\\?dId=',model.DEVICE_MST_ID,'&amp;bId=',model.MANUFACTURER_ID,'&amp;mId=',model.MODEL_ID,'&amp;rId=',review.REVIEW_ID,'#',review.REVIEW_ID), ");
		sql.append(" 	'</from><to>',review.REF_LINK,'</to><for type=\"reviewId\">',review.REVIEW_ID,'</for></outbound-rule>') urlRewriteRule ");
		sql.append(" FROM review Inner Join model ON review.MODEL_ID = model.MODEL_ID ");
		sql.append(" where review.REVIEW_ID=?  and review.STATUS_ID=? ");
		args=new ArrayList<Object>();
		args.add(reviewDto.getReviewId());
		args.add(DeviceConstants.ACTIVE_STATUS_ID);
		args.add(reviewDto.getReviewId());
		args.add(DeviceConstants.ACTIVE_STATUS_ID);
		log.debug("|__Approve Review  urlrewrite query="+sql);
		List<ReviewDto> urlRewriteRuleList=getJdbcTemplate().query(sql.toString(), args.toArray(),new UrlRewriteMapper());
		return urlRewriteRuleList;
		
		/*try {
			ApproveReviewStoredProcedure proc = new ApproveReviewStoredProcedure(getJdbcTemplate().getDataSource(), ZProcConstants.SP_APPROVE_REVIEW);
			Map results = proc.execute(reviewDto);
			log.debug("|__results="+results);
			return (List<ReviewDto>) results.get(ZProcConstants.REVIEW_URL_RULES_RESULT_SET);

		} catch (DataAccessException ex) {
			throw new BaseRuntimeException(ex);
		}*/
	}

	public void updateModelLink(ModelDto modelDto) {
		StringBuilder sql=new StringBuilder();
		ArrayList<Object> args=new ArrayList<Object>();
		sql.append(" update model set MODEL_LINK=(concat('/',(select d.DEVICE_NAME from device_mst d  ");
		sql.append(" where d.DEVICE_ID=DEVICE_MST_ID),'/Specification/',replace(MODEL_NAME,' ','-'),'.html'))  ");
		sql.append(" where MODEL_ID=? ");
		args.add(modelDto.getModelId());
		log.debug("|__query="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		getJdbcTemplate().update(sql.toString(), args.toArray());
		
	}
	
	public void updateCategoryModelLink(ModelDto modelDto) {
		StringBuilder sql=new StringBuilder();
		ArrayList<Object> args=new ArrayList<Object>();
		
		sql.append(" update model_category_mapping AS MCM ");
		sql.append(" inner JOIN  ");
		sql.append(" ( ");
		sql.append(" 	select m1.MODEL_CATEGORY_MAPPING_ID,( ");
		sql.append(" 	concat( ");
		sql.append(" 	'/',d.DEVICE_NAME,'/',replace(replace(replace(c.CATEGORY_NAME,',',''),'+',''),' ','-')," );
		sql.append("	'/Specification/',replace(m.MODEL_NAME,' ','-'), ");
		sql.append(" 	'.html') ");
		sql.append(" 	) as MODEL_LINK ");
		sql.append(" 	from  model_category_mapping m1, model m, category_mst c,device_mst d ");
		sql.append(" 	where d.DEVICE_ID=m.DEVICE_MST_ID ");
		sql.append(" 	and c.CATEGORY_ID=m1.CATEGORY_ID ");
		sql.append(" 	and m.MODEL_ID=m1.MODEL_ID  ");
		sql.append(" 	and m1.MODEL_ID=? ");
		sql.append(" ) AS t ");
		sql.append(" on t.MODEL_CATEGORY_MAPPING_ID=MCM.MODEL_CATEGORY_MAPPING_ID ");
		sql.append(" set MCM.CATEGORY_MODEL_LINK = t.MODEL_LINK ");
		
		args.add(modelDto.getModelId());
		log.debug("|__query="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		getJdbcTemplate().update(sql.toString(), args.toArray());
	}

	public List<ListOrderedMap> fetchModelLink(ModelDto modelDto) {
		StringBuilder sql=new StringBuilder();
		sql.append(" select MODEL_LINK Rule ");
		sql.append(" from model where MODEL_ID=? ");
		sql.append(" union  ");
		sql.append(" select concat('<rule><from>^',MODEL_LINK,'$</from><to>', ");
		sql.append(" 	concat('/modelDetail.html\\\\?dId=',DEVICE_MST_ID,'&amp;bId=',MANUFACTURER_ID,'&amp;cId=&amp;mId=',MODEL_ID), ");
		sql.append(" 	'</to><for type=\"modelId\">',MODEL_ID,'</for></rule>') Rule ");
		sql.append(" from model where MODEL_ID=? ");
		sql.append(" union  ");
		sql.append(" select concat('<outbound-rule><from>', ");
		sql.append(" 	concat('/modelDetail.html\\\\?dId=',DEVICE_MST_ID,'&amp;bId=([0-9]*)&amp;cId=&amp;mId=',MODEL_ID,'$'), ");
		sql.append(" 	'</from><to>',MODEL_LINK,'</to><for type=\"modelId\">',MODEL_ID,'</for></outbound-rule>') outboundRule ");
		sql.append(" from model where MODEL_ID=? ");
		sql.append(" union  ");
		sql.append(" select concat('<rule><from>^',mcm.CATEGORY_MODEL_LINK,'$</from><to>', ");
		sql.append(" 	concat('/modelDetail.html\\\\?dId=',m.DEVICE_MST_ID,'&amp;bId=&amp;cId=',mcm.CATEGORY_ID,'&amp;mId=',mcm.MODEL_ID), ");
		sql.append(" 	'</to><for type=\"modelId\">',mcm.MODEL_ID,'</for></rule>') Rule ");
		sql.append(" from model m ");
		sql.append(" inner join model_category_mapping mcm ");
		sql.append(" on m.MODEL_ID=mcm.MODEL_ID where m.MODEL_ID=? ");
		sql.append(" union  ");
		sql.append(" select concat('<outbound-rule><from>', ");
		sql.append(" 	concat('/modelDetail.html\\\\?dId=',m.DEVICE_MST_ID,'&amp;bId=&amp;cId=',mcm.CATEGORY_ID,'&amp;mId=',m.MODEL_ID,'$'), ");
		sql.append(" 	'</from><to>',mcm.CATEGORY_MODEL_LINK,'</to><for type=\"modelId\">',m.MODEL_ID,'</for></outbound-rule>') outboundRule ");
		sql.append(" from model m inner join model_category_mapping mcm ");
		sql.append(" on m.MODEL_ID=mcm.MODEL_ID where m.MODEL_ID=?  ");
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(modelDto.getModelId());
		args.add(modelDto.getModelId());
		args.add(modelDto.getModelId());
		args.add(modelDto.getModelId());
		args.add(modelDto.getModelId());
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().queryForList(sql.toString(), args.toArray());
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelDto> allModels() {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT model.MODEL_NAME as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
		sql.append(" FROM model  ");
		sql.append(" order by model.MODIFIED_DATE ");
		
		return getJdbcTemplate().query(sql.toString().toLowerCase(),new ModelMapper());
	}

	public List<ModelDto> getModelBasicDetails(Long deviceId, Long manufacturerId,Long modelId) {
		log.debug("|__deviceId="+deviceId+" |__manufacturerId="+manufacturerId+" |__modelId="+modelId);
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT model.MODEL_NAME as "+TitleEnum.TITLE.getTitle()+" , model.MODEL_ID, model.DEVICE_MST_ID, model.MANUFACTURER_ID, model.MODEL_NAME, model.PRICE, ");
		sql.append(" model.LAUNCH_DATE, model.MODEL_DESC, model.MODEL_IMAGE_URL, model.MODEL_VIDEO_LINK,STAR_RATING, RATING_COUNT, model.MODIFIED_DATE, model.MODEL_LINK  ");
		sql.append(" FROM model  ");
		sql.append(" where model.MODEL_ID=?  and model.STATUS_ID=?  ");

		ArrayList<Object> args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().query(sql.toString(),args.toArray(),new ModelMapper());
	
		
	}

	public List getModelCatgoryList(Long deviceId, Long manufacturerId,Long modelId) {
		log.debug("|__deviceId="+deviceId+" |__manufacturerId="+manufacturerId+" |__modelId="+modelId);		
		StringBuilder sql=new StringBuilder();
/*		sql.append(" SELECT  model_category_mapping.CATEGORY_ID, category_mst.CATEGORY_NAME ");
		sql.append(" FROM model_category_mapping Inner Join category_mst  ");
		sql.append(" on model_category_mapping.CATEGORY_ID=category_mst.CATEGORY_ID ");
		sql.append(" WHERE model_category_mapping.MODEL_ID = ? and model_category_mapping.STATUS_ID=? and category_mst.STATUS_ID=? ");
	*/	
		sql.append(" SELECT  model_category_mapping.CATEGORY_ID ");
		sql.append(" FROM model_category_mapping  ");
		sql.append(" WHERE model_category_mapping.MODEL_ID = ? and model_category_mapping.STATUS_ID=? ");
		
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(modelId);
		args.add(DbConstants.ACTIVE_STATUS_ID);
		log.debug("|__query="+sql);
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().queryForList(sql.toString(), args.toArray(),Long.class);
//		return getJdbcTemplate().queryForList(sql.toString(), args.toArray());
		
	}
	
	
	public int updateModel(ModelDto modelDto) {
		StringBuilder sql=new StringBuilder();
		ArrayList<Object> args=new ArrayList<Object>();
		
		sql.append(" UPDATE model set ");
		sql.append(" MODEL_NAME=?, ");
		args.add(modelDto.getModelName());
		sql.append(" MODEL_DESC=?, ");
		args.add(modelDto.getModelDesc());
		
		if(modelDto.getPrice()!=null){
			sql.append(" PRICE=?, ");
			args.add(modelDto.getPrice());
		}

		if(modelDto.getModelLaunchDate()!=null){
			sql.append(" LAUNCH_DATE=?, ");
			args.add(modelDto.getModelLaunchDate());
		}
		if(modelDto.getModelImageBytes()!=null){
			sql.append(" MODEL_IMAGE=?,");
			args.add(modelDto.getModelImageBytes());
		}
		if(StringUtility.isNotNullBlank(modelDto.getModelImageUrl())){
			sql.append(" MODEL_IMAGE_URL=?,");
			args.add(modelDto.getModelImageUrl());
		}
		sql.append(" MODIFIED_BY=?,");
		sql.append(" STATUS_ID=? ");
		sql.append(" WHERE MODEL_ID=? ");
		args.add(modelDto.getModifiedBy());
		args.add(modelDto.getStatusId());
		args.add(modelDto.getModelId());
		
		log.debug("|__query="+sql.toString());
		for(Object i:args)
			log.debug("|__args["+args.indexOf(i)+"]=="+i);
		return getJdbcTemplate().update(sql.toString(), args.toArray());
		
	}

	public void deleteModelCategories(Long modelId){
		String sql=" delete from model_category_mapping where MODEL_ID=? ";
		ArrayList<Object> args=new ArrayList<Object>();
		args.add(modelId);
		log.debug("|__query="+sql+"\n|__modelId="+modelId);
		getJdbcTemplate().update(sql, args.toArray());
	}

	/* 
	 * Getters & Setters
	 */


	

}
