package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.sushant.verma.common.mapper.ModelMapper;


public class ModelsListStoredProcedure extends StoredProcedure {
    
    public ModelsListStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlReturnResultSet(ZProcConstants.POPULAR_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.NEW_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.UPCOMING_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlParameter(ZProcConstants.IN_DEVICE_ID,Types.BIGINT));
        compile();
    }

	@SuppressWarnings("unchecked")
	public Map execute(Long deviceId) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_DEVICE_ID, deviceId);
        return super.execute(inputs);
    }
}