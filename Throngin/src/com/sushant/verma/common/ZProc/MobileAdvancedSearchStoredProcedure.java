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


public class MobileAdvancedSearchStoredProcedure extends StoredProcedure {
    
    public MobileAdvancedSearchStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_TOTAL_MODEL_COUNT,Types.BIGINT));
        declareParameter(new SqlReturnResultSet(ZProcConstants.SEARCHED_MODELS_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlParameter(ZProcConstants.IN_DEVICE_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_MOBILE_MANUFACTURER,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_REQ_CATG_ID,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_REQ_CATG_ID_LENGTH,Types.INTEGER));
        declareParameter(new SqlParameter(ZProcConstants.IN_NOT_REQ_CATG_ID,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_PAGE_SIZE,Types.INTEGER));
        declareParameter(new SqlParameter(ZProcConstants.IN_PAGE_NO,Types.INTEGER));
        compile();
    }

	@SuppressWarnings("unchecked")
	public Map execute(String deviceId,Integer pageSize, Integer pageNo,String mobileManufacturer, String requiredCatgId,
			Integer requiredCatgIdLength,String notRequiredCatgId) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_DEVICE_ID, deviceId);
        inputs.put(ZProcConstants.IN_MOBILE_MANUFACTURER, mobileManufacturer);
        inputs.put(ZProcConstants.IN_REQ_CATG_ID, requiredCatgId);
        inputs.put(ZProcConstants.IN_REQ_CATG_ID_LENGTH, requiredCatgIdLength);
        inputs.put(ZProcConstants.IN_NOT_REQ_CATG_ID, notRequiredCatgId);
        inputs.put(ZProcConstants.IN_PAGE_SIZE, pageSize);
        inputs.put(ZProcConstants.IN_PAGE_NO, pageNo);
        return super.execute(inputs);
    }
}