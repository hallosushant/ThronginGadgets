package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.sushant.verma.common.mapper.UserMapper;


public class LoginStoredProcedure extends StoredProcedure {
    
    public LoginStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlReturnResultSet(ZProcConstants.RESULT_SET, new UserMapper()));
        declareParameter(new SqlParameter(ZProcConstants.IN_USER_EMAIL,Types.VARCHAR));
//        declareParameter(new SqlParameter(ZProcConstants.IN_USER_PWD,Types.VARCHAR));
        compile();
    }

	@SuppressWarnings("unchecked")
	public Map execute(String userEmail,String userPwd) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_USER_EMAIL, userEmail);
//        inputs.put(ZProcConstants.IN_USER_PWD, userPwd);
        return super.execute(inputs);
    }
}