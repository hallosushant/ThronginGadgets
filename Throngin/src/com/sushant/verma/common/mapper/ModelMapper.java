package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.constants.ZConstants.TitleEnum;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.enums.TableEnums;

public class ModelMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		  
	      ModelDto model = new ModelDto();
	      model.setTitle(rs.getString(TitleEnum.TITLE.getTitle()));
	      model.setModelId(rs.getLong(TableEnums.ModelEnum.MODEL_ID.toString()));
	      model.setDeviceId(rs.getLong(TableEnums.ModelEnum.DEVICE_MST_ID.toString()));
	      model.setManufacturerId(rs.getLong(TableEnums.ModelEnum.MANUFACTURER_ID.toString()));
	      model.setModelName(rs.getString(TableEnums.ModelEnum.MODEL_NAME.toString()));
	      model.setPrice(rs.getInt(TableEnums.ModelEnum.PRICE.toString()));
	      model.setLaunchDate(rs.getDate(TableEnums.ModelEnum.LAUNCH_DATE.toString()));
	      model.setModelDesc(rs.getString(TableEnums.ModelEnum.MODEL_DESC.toString()));
	      model.setModelImageUrl(rs.getString(TableEnums.ModelEnum.MODEL_IMAGE_URL.toString()));
	      model.setModelVideoLink(rs.getString(TableEnums.ModelEnum.MODEL_VIDEO_LINK.toString()));
	      model.setStarRating(rs.getFloat(TableEnums.ModelEnum.STAR_RATING.toString()));
	      model.setRatingCount(rs.getLong(TableEnums.ModelEnum.RATING_COUNT.toString()));
	      model.setModifiedDate(rs.getTimestamp(TableEnums.ModelEnum.MODIFIED_DATE.toString()));
	      model.setModelLink(rs.getString(TableEnums.ModelEnum.MODEL_LINK.toString()));
	      return model;
	  }
	}
