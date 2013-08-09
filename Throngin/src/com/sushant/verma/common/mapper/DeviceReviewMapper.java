package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.enums.TableEnums;

public class DeviceReviewMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      ReviewDto reviewDto = new ReviewDto();
	      reviewDto.setDeviceId(rs.getLong(TableEnums.DeviceMstEnum.DEVICE_MST_ID.toString()));
	      reviewDto.setManufacturerId(rs.getLong(TableEnums.ManufacturerEnum.MANUFACTURER_ID.toString()));
	      reviewDto.setModelId(rs.getLong(TableEnums.ReviewEnum.MODEL_ID.toString()));
	      reviewDto.setModelName(rs.getString(TableEnums.ModelEnum.MODEL_NAME.toString()));
	      reviewDto.setModelImageUrl(rs.getString(TableEnums.ModelEnum.MODEL_IMAGE_URL.toString()));
	      reviewDto.setReviewId(rs.getLong(TableEnums.ReviewEnum.REVIEW_ID.toString()));
	      reviewDto.setTitle(rs.getString(TableEnums.ReviewEnum.TITLE.toString()));
	      reviewDto.setReview(rs.getString(TableEnums.ReviewEnum.REVIEW.toString()));
	      reviewDto.setAuthor(rs.getString(TableEnums.ReviewEnum.AUTHOR.toString()));
	      reviewDto.setReviewDate(rs.getString(TableEnums.ReviewEnum.REVIEW_DATE.toString()));
	      reviewDto.setStarRating(rs.getFloat(TableEnums.ReviewEnum.STAR_RATING.toString()));
	      reviewDto.setRatingCount(rs.getLong(TableEnums.ReviewEnum.RATING_COUNT.toString()));
	      reviewDto.setRefLink(rs.getString(TableEnums.ReviewEnum.REF_LINK.toString()));
	      reviewDto.setStatusId(rs.getLong(TableEnums.ReviewEnum.STATUS_ID.toString()));
	      return reviewDto;
	  }
	}
