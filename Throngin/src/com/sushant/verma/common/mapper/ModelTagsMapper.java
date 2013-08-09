package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.dto.CategoryMstDto;
import com.sushant.verma.common.enums.TableEnums;

public class ModelTagsMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      CategoryMstDto categoryMstDto = new CategoryMstDto();
	      categoryMstDto.setCategoryId(rs.getLong(TableEnums.CategoryMstEnum.CATEGORY_ID.toString()));
	      categoryMstDto.setCategoryName(rs.getString(TableEnums.CategoryMstEnum.CATEGORY_NAME.toString()));
	      categoryMstDto.setCategoryDesc(rs.getString(TableEnums.CategoryMstEnum.CATEGORY_DESC.toString()));
	      categoryMstDto.setDeviceId(rs.getLong(TableEnums.CategoryMstEnum.DEVICE_ID.toString()));
	      return categoryMstDto;
	  }
	}
