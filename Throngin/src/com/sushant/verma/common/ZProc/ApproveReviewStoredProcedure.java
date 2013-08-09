package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.mapper.UrlRewriteMapper;


public class ApproveReviewStoredProcedure extends StoredProcedure {
    
    public ApproveReviewStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlParameter(ZProcConstants.IN_REVIEW_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_STATUS_ID,Types.FLOAT));
        declareParameter(new SqlParameter(ZProcConstants.IN_REVIEW_TITLE,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_REVIEW_DESC,Types.VARCHAR));
        declareParameter(new SqlParameter(ZProcConstants.IN_USER_ID,Types.FLOAT));
        declareParameter(new SqlReturnResultSet(ZProcConstants.REVIEW_URL_RULES_RESULT_SET, new UrlRewriteMapper()));
        compile();
    }

	@SuppressWarnings("unchecked")
	public Map execute(ReviewDto reviewDto) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_REVIEW_ID, reviewDto.getReviewId());
        inputs.put(ZProcConstants.IN_STATUS_ID, reviewDto.getStatusId());
        inputs.put(ZProcConstants.IN_REVIEW_TITLE, reviewDto.getTitle());
        inputs.put(ZProcConstants.IN_REVIEW_DESC, reviewDto.getReview());
        inputs.put(ZProcConstants.IN_USER_ID, reviewDto.getModifiedBy());
        return super.execute(inputs);
    }
}