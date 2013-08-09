package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.constants.ZConstants;
import com.sushant.verma.common.dto.ReviewDto;

public class UrlRewriteMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      ReviewDto reviewDto = new ReviewDto();
	      reviewDto.setUrlRewriteRule(rs.getString(ZConstants.URL_REWRITE_RULE));
	      return reviewDto;
	  }
	}
