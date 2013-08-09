package com.sushant.verma.admin.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.dto.ReviewDto;

public interface AdminBllInterface {
	
	List<ReviewDto> fetchDeviceInactiveReviews(Long deviceId);
	
	void assignModelDetail(String modelName,Long modelId, List<String> detailName, List<String> detailValue, List<String> detailCategory,String assignType);

	List<ListOrderedMap> getModelDetails(Long modelId);

	List<ListOrderedMap> getModelList(int state);
	
	List<ListOrderedMap> getUserList(String userEmail,Long userRoleId);

	void removeCurrentRole(Long userId, Long currentRoleId);

	void assignUserRole(Long userId, Long selectedUserRole);

	List<ListOrderedMap> getCategoryList();

	List<ListOrderedMap> createCategory(String categoryName, Long parentCategory) throws Exception;

	List<ListOrderedMap> searchSubCategoryList(Long categoryNameSearch);


	void createAttribute(String attributeName, String attributeDesc) throws Exception;

	List<ListOrderedMap> getManufacturerList(Long deviceId);

	List<ListOrderedMap> addNewModel(ModelDto modelDto);

	List<ListOrderedMap> getDetailNameList(String detailCategory);

	List<ReviewDto> approveReview(ReviewDto reviewDto);

	List<ModelDto> fetchAllModels();

	List<ListOrderedMap> getModelList(Long deviceId, Long manufacturerId);

	HashMap<String, Object> getModelBasicDetails(Long deviceId, Long manufacturerId, Long modelId);

	Map<String, List<ListOrderedMap>> editModel(ModelDto modelDto);


}
