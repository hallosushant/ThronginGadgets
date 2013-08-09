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


public class QuickSearchStoredProcedure extends StoredProcedure {
    
    public QuickSearchStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_TOTAL_MODEL_COUNT,Types.BIGINT));
        declareParameter(new SqlReturnResultSet(ZProcConstants.SEARCHED_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlParameter(ZProcConstants.IN_DEVICE_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_SEARCH_STR,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_PAGE_SIZE,Types.INTEGER));
        declareParameter(new SqlParameter(ZProcConstants.IN_PAGE_NO,Types.INTEGER));
        compile();
    }

	@SuppressWarnings("unchecked")
	public Map execute(Long deviceId,String searchStr,Integer pageSize, Integer pageNo) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_DEVICE_ID, deviceId);
        inputs.put(ZProcConstants.IN_SEARCH_STR, searchStr);
        inputs.put(ZProcConstants.IN_PAGE_SIZE, pageSize);
        inputs.put(ZProcConstants.IN_PAGE_NO, pageNo);
        return super.execute(inputs);
    }
}