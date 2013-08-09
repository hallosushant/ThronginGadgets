package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.sushant.verma.common.dto.UserDto;


public class RegisterUserZProc extends StoredProcedure {
    
    public RegisterUserZProc(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_USER_EXISTS,Types.INTEGER));
        declareParameter(new SqlOutParameter(ZProcConstants.OUT_USER_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_USER_NAME,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_USER_EMAIL,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_USER_SALT,Types.BLOB));
        declareParameter(new SqlParameter(ZProcConstants.IN_USER_ENC_PWD,Types.BLOB));
        declareParameter(new SqlParameter(ZProcConstants.IN_HINT_QUESTION_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_HINT_ANSWER,Types.VARCHAR));
//        declareParameter(new SqlParameter(ZProcConstants.IN_STATUS_ID,Types.BIGINT));
        compile();
    }
    
	@SuppressWarnings("unchecked")
	public Map execute(UserDto userDto) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_USER_NAME, userDto.getUserName());
        inputs.put(ZProcConstants.IN_USER_EMAIL, userDto.getUserEmail());
        inputs.put(ZProcConstants.IN_USER_SALT, userDto.getSalt());
        inputs.put(ZProcConstants.IN_USER_ENC_PWD, userDto.getEncPwd());
        inputs.put(ZProcConstants.IN_HINT_QUESTION_ID, userDto.getHintQuestionId());
        inputs.put(ZProcConstants.IN_HINT_ANSWER, userDto.getHintAnswer());
//        inputs.put(ZProcConstants.IN_STATUS_ID, userDto.getStatusId());
        System.out.println("inputs=="+inputs);
        return super.execute(inputs);
    }
}