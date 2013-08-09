package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class ConfirmUserRegistrationZProc extends StoredProcedure {
    
    public ConfirmUserRegistrationZProc(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_USER_NAME,Types.VARCHAR));
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_USER_EMAIL,Types.VARCHAR));
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_STATUS_ID,Types.BIGINT));
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_IS_REGISTRATION_SUCCESSFUL,Types.CHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_USER_ID,Types.BIGINT));
        compile();
    }
    
	@SuppressWarnings("unchecked")
	public Map execute(Long userId) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_USER_ID, userId);
        System.out.println("inputs=="+inputs);
        return super.execute(inputs);
    }
}