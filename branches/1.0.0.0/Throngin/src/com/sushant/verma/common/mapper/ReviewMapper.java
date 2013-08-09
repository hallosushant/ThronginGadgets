package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.enums.TableEnums;

public class ReviewMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      ReviewDto reviewDto = new ReviewDto();
	      reviewDto.setReviewId(rs.getLong(TableEnums.ReviewEnum.REVIEW_ID.toString()));
	      reviewDto.setTitle(rs.getString(TableEnums.ReviewEnum.TITLE.toString()));
	      reviewDto.setReview(rs.getString(TableEnums.ReviewEnum.REVIEW.toString()));
	      reviewDto.setAuthor(rs.getString(TableEnums.ReviewEnum.AUTHOR.toString()));
	      reviewDto.setReviewDate(rs.getString(TableEnums.ReviewEnum.REVIEW_DATE.toString()));
	      reviewDto.setRefLink(rs.getString(TableEnums.ReviewEnum.REF_LINK.toString()));
	      reviewDto.setImageLink(rs.getString(TableEnums.ReviewEnum.IMAGE_LINK.toString()));
	      reviewDto.setVideoLink(rs.getString(TableEnums.ReviewEnum.VIDEO_LINK.toString()));
	      reviewDto.setStarRating(rs.getFloat(TableEnums.ReviewEnum.STAR_RATING.toString()));
	      reviewDto.setRatingCount(rs.getLong(TableEnums.ReviewEnum.RATING_COUNT.toString()));
	      return reviewDto;
	  }
	}
