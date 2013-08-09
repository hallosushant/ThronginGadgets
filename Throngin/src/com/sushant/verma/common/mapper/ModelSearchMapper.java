package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.dto.SearchResultDto;
import com.sushant.verma.common.enums.TableEnums;

public class ModelSearchMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      SearchResultDto searchResult = new SearchResultDto();
	      searchResult.setId(rs.getLong(TableEnums.ModelEnum.MODEL_ID.toString()));
	      searchResult.setText(rs.getString(TableEnums.ModelEnum.MODEL_NAME.toString()));
	      searchResult.setImage(rs.getString(TableEnums.ModelEnum.MODEL_IMAGE_URL.toString()));
	      return searchResult;
	  }
	}
