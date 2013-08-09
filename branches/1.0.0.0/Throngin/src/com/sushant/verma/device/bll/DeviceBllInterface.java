package com.sushant.verma.device.bll;

import java.util.List;

import com.sushant.verma.common.dto.AdvancedSearchDto;
import com.sushant.verma.common.dto.ModelDetailDto;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.RatingDto;
import com.sushant.verma.common.dto.ReviewDto;
import com.sushant.verma.common.dto.SearchResultDto;



public interface DeviceBllInterface {

	public List<List<ModelDto>> fetchManufacturerModels(Long deviceId,Long manufacturerId);

	public List<ModelDto> moreManufacturerModelsWithCount(Long deviceId, Long manufacturerId,String galleryType, Integer pageSize, Integer pageNo);
	
	public List<ModelDto> moreManufacturerModels(Long deviceId, Long manufacturerId,String galleryType, Integer pageSize, Integer pageNo);
		
	public List<List<ModelDto>> fetchCategoryModels(Long deviceId, Long categoryId);

	public List<ModelDto> moreCategoryModels(Long deviceId, Long categoryId,String galleryType, Integer pageSize, Integer pageNo);

	public List<ModelDto> moreCategoryModelsWithCount(Long deviceId, Long categoryId,String galleryType, Integer pageSize, Integer pageNo);

	public List<ReviewDto> fetchModelReviews(Long modelId);
	
	public List<ReviewDto> fetchDeviceLatestReviews(Long deviceId);
	
	public void rateThis(RatingDto ratingDto);

	public Integer saveModelReview(ReviewDto reviewDto);

	public List<ModelDetailDto> fetchModelPhotos(Long modelId);

	public List<SearchResultDto> zModelSearch(String searchStr,Long deviceDao);

	public List<ModelDto> mobileAdvancedSearch(AdvancedSearchDto advancedSearchDto,Integer pageSize, Integer pageNo);

	public List<ModelDto> quickSearch(Long deviceId, String searchStr,Integer pageSize, Integer pageNo);

	public List<ModelDto> fetchRelatedCatgModels(Long deviceId,Long modelId, String tagsCatg);
	
	public List<ModelDto> fetchTopRatedModels(Long deviceId);
}
