package com.sushant.verma.common.ZProc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.sushant.verma.common.dto.RatingDto;


public class RateThisStoredProcedure extends StoredProcedure {
    
    public RateThisStoredProcedure(DataSource dataSource, String sprocName) {
        super(dataSource, sprocName);
        declareParameter(new SqlParameter(ZProcConstants.IN_MODEL_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_REVIEW_ID,Types.BIGINT));
        declareParameter(new SqlParameter(ZProcConstants.IN_STAR_RATING,Types.FLOAT));
        compile();
    }

	@SuppressWarnings("unchecked")
	public void execute(RatingDto ratingDto) {
		Map inputs=new HashMap();
        inputs.put(ZProcConstants.IN_MODEL_ID, ratingDto.getModelId());
        inputs.put(ZProcConstants.IN_REVIEW_ID, ratingDto.getReviewId());
        inputs.put(ZProcConstants.IN_STAR_RATING, ratingDto.getStarRating());
        super.execute(inputs);
    }
}