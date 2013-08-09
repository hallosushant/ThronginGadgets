package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.dto.ModelDetailDto;
import com.sushant.verma.common.enums.TableEnums;

public class ModelDetailMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      ModelDetailDto modelDetail = new ModelDetailDto();
	      modelDetail.setCategoryName(rs.getString(TableEnums.ModelDetailEnum.CATEGORY_NAME.toString()));
	      modelDetail.setDetailName(rs.getString(TableEnums.ModelDetailEnum.DETAIL_NAME.toString()));
	      modelDetail.setDetailValue(rs.getString(TableEnums.ModelDetailEnum.DETAIL_VALUE.toString()));
	      return modelDetail;
	  }
	}
