package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.sushant.verma.common.mapper.ModelDetailMapper;
import com.sushant.verma.common.mapper.ModelMapper;
import com.sushant.verma.common.mapper.ModelPictureMapper;
import com.sushant.verma.common.mapper.ModelTagsMapper;


public class ModelDetailsStoredProcedure extends StoredProcedure {
    
    public ModelDetailsStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlReturnResultSet(ZProcConstants.MODEL_BASIC_INFO_RESULT_SET, new ModelMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.MODEL_PICTURES_RESULT_SET, new ModelPictureMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.MODEL_DETAILS_RESULT_SET, new ModelDetailMapper()));
        declareParameter(new SqlReturnResultSet(ZProcConstants.MODEL_TAGS_RESULT_SET, new ModelTagsMapper()));
        declareParameter(new SqlParameter(ZProcConstants.IN_MODEL_ID,Types.BIGINT));
        compile();
    }

	@SuppressWarnings("unchecked")
	public Map execute(Long modelId) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_MODEL_ID, modelId);
        return super.execute(inputs);
    }
}