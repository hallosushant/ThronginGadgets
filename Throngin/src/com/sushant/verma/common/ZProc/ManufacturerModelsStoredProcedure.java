package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.sushant.verma.common.mapper.ModelMapper;


public class ManufacturerModelsStoredProcedure extends StoredProcedure {
    
    public ManufacturerModelsStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_POPULAR_MODEL_COUNT,Types.BIGINT));
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_NEW_MODEL_COUNT,Types.BIGINT));
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_UPCOMING_MODEL_COUNT,Types.BIGINT));
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_ALL_MODEL_COUNT,Types.BIGINT));
        declareParameter(new SqlReturnResultSet(ZProcConstants.POPULAR_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.NEW_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.UPCOMING_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.ALL_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlParameter(ZProcConstants.IN_DEVICE_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_MANUFACTURER_ID,Types.BIGINT));
        compile();
    }

	@SuppressWarnings("unchecked")
	public Map execute(Long deviceId,Long manufacturerId) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_DEVICE_ID, deviceId);
        inputs.put(ZProcConstants.IN_MANUFACTURER_ID, manufacturerId);
        return super.execute(inputs);
    }
}