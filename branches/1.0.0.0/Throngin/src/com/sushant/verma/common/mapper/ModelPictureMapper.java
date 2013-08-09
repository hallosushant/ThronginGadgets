package com.sushant.verma.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.enums.TableEnums;

public class ModelPictureMapper implements RowMapper {
	  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	      ModelDto model = new ModelDto();
	      model.setModelImageUrl(rs.getString(TableEnums.ModelEnum.MODEL_IMAGE_URL.toString()));
	      return model;
	  }
	}
