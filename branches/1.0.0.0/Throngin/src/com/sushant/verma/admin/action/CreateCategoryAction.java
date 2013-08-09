package com.sushant.verma.admin.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.sushant.verma.admin.bll.AdminBllInterface;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.exception.BaseRuntimeException;
import com.sushant.verma.common.utility.StringUtility;

@SuppressWarnings("unchecked")
public class CreateCategoryAction extends BaseAction implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(CreateCategoryAction.class);
	private Map session;
	private AdminBllInterface adminBllInterface;
	private List<ListOrderedMap> categoryList;
	private String categoryName;
	private Long parentCategory;
	private List<ListOrderedMap> subCategoryList;
	private Long categoryNameSearch;
	
	@SkipValidation
	public String execute(){
		categoryList=adminBllInterface.getCategoryList();
		log.debug("|__categoryList=="+categoryList);
		session.put("CATEGORY_LIST", categoryList);
		return SUCCESS;
	}
	
	public String createCategory(){
		log.debug("|__categoryName=="+categoryName+
				"\n|__parentCategory=="+parentCategory);
		if(StringUtility.areNotNullBlank(categoryName,parentCategory.toString())){
			try{
				categoryList=adminBllInterface.createCategory(categoryName,parentCategory);
				log.debug("|__categoryList=="+categoryList);
				session.put("PARENT_CATEGORY_LIST", categoryList);
				addActionMessage("Category \""+categoryName+"\" created Successfully!");
				return SUCCESS;
			}
			catch (DataIntegrityViolationException e) {
				log.error("|__Exception Message=="+e.getMessage()+
						"\n|__Exception Cause=="+e.getCause());
				addActionError("Error: Unable to process request, \""+categoryName+"\" already exists!");
				return INPUT;
			}
			catch (Exception e) {
				throw new BaseRuntimeException(e);
			}
		}
		else{
			addActionError("Unable to Process your Request, Please Try Again!");
			return INPUT;
		}
		
	}
	
	@SkipValidation
	public String searchSubCategoryList(){
		log.debug("|__categoryNameSearch=="+categoryNameSearch);
		if(StringUtility.isNotNullBlank(categoryNameSearch.toString())){
			 subCategoryList=adminBllInterface.searchSubCategoryList(categoryNameSearch);
			 log.debug("|__subCategoryList=="+subCategoryList);
			 return SUCCESS;
		}
		else{
			addActionError("Unable to Process your Request, Please Try Again!");
			return INPUT;
		}
	}
	
	
	/* 
	 * Getters & Setters
	 */

	public AdminBllInterface getAdminBllInterface() {
		return adminBllInterface;
	}

	public void setAdminBllInterface(AdminBllInterface adminBllInterface) {
		this.adminBllInterface = adminBllInterface;
	}

	public List<ListOrderedMap> getcategoryList() {
		return categoryList;
	}

	public void setcategoryList(List<ListOrderedMap> categoryList) {
		this.categoryList = categoryList;
	}

	@RequiredStringValidator(fieldName="categoryName",key="key.categoryName.mandatory",message="Category Name is Mandatory",trim=true)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@RequiredFieldValidator(fieldName="parentCategory",key="key.parentCategory.mandatory",message="Parent Category is Mandatory")
	public Long getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Long parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	public Long getCategoryNameSearch() {
		return categoryNameSearch;
	}

	public void setCategoryNameSearch(Long categoryNameSearch) {
		this.categoryNameSearch = categoryNameSearch;
	}

	public List<ListOrderedMap> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<ListOrderedMap> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}



	

}


