package com.sushant.verma.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.sushant.verma.admin.AdminConstants;
import com.sushant.verma.admin.bll.AdminBllInterface;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.DbConstants;
import com.sushant.verma.common.enums.TableEnums.UserRoleMappingEnum;
import com.sushant.verma.common.enums.TableEnums.UserRoleMstEnum;
import com.sushant.verma.common.utility.StringUtility;
@SuppressWarnings("unchecked")
public class AssignUserRoleAction extends BaseAction implements ApplicationAware{

	private static final long serialVersionUID = 4559281981822201368L;
	private static Logger log=LogManager.getLogger(AssignUserRoleAction.class);
	private AdminBllInterface adminBllInterface;
	private String userEmail;
	private String userId;
	private String currentRole;
	private List<String> currentRoleList;
	private String currentRoleId;
	private Long userRoleSearch;
	private Long selectedUserRole;
	private List<ListOrderedMap> userList;
	private Map application;
	private int modal;
	private Integer assignRole;
	private Integer selectedUserListIndex;
	private String[] userIdArray;
	private String[] userEmailArray;
	private String[] currentRoleArray;
	private String[] currentRoleIdArray;
	private String[] activeArray;
	private String[] blockedArray;
	private String[] approvedArray;
	private String[] rejectedArray;
	
	public String execute(){
		return SUCCESS;
	}

	public String assignRole(){
		log.debug("|__userId=="+userId+"\n|__selectedUserRole=="+selectedUserRole+"\n|__userEmail=="+userEmail);
		if(StringUtility.isNotNullBlank(userEmail)){
		List<ListOrderedMap> currentUserRoleList=adminBllInterface.getUserList(userEmail, AdminConstants.USER_ROLE_ALL);
		if(null!=currentUserRoleList && currentUserRoleList.size()>0)
			for(int i=0;i<currentUserRoleList.size();i++){
				Long currentRole=(Long) currentUserRoleList.get(i).get(UserRoleMappingEnum.USER_ROLE_ID);
				if(null!=currentRole && null!=selectedUserRole && currentRole.longValue()==selectedUserRole.longValue()){
					addActionError(userEmail+ " already has the selected "+getUserRole(selectedUserRole)+" Role!");
					return INPUT;
				}
			}
		if(null!=userId && null!=selectedUserRole){
			adminBllInterface.assignUserRole(Long.valueOf(userId),selectedUserRole);
			addActionMessage(getUserRole(selectedUserRole)+" Role Assigned Successfully to "+userEmail);
			return SUCCESS;
		}
		else {
			addActionError("Unable to Process the Request, Please try again!");
			return INPUT;
		}
		}
		else{
			addActionError("Unable to Process the Request, Please try again!");
			return INPUT;
		}
	}

	public String searchUser(){
		log.debug("|__userEmail=="+userEmail+
				"\n|__userRoleSearch=="+userRoleSearch);
		userList=adminBllInterface.getUserList(userEmail,userRoleSearch);
		log.debug("|__userList=="+userList);
		if(userList==null || userList.size()==0)
			addActionMessage("No Records Found!!!");
		return SUCCESS;
	}

	public String openAssignBlock(){
	
		if(selectedUserListIndex!=null){
			log.debug("getting value from userList for selected radio button");
			
			userId=userIdArray[selectedUserListIndex];
			userEmail=userEmailArray[selectedUserListIndex];
			currentRole=currentRoleArray[selectedUserListIndex];
			currentRoleId=currentRoleIdArray[selectedUserListIndex];
			
			String isActive=activeArray[selectedUserListIndex];
			if(StringUtility.isNotNullBlank(isActive) && isActive.equals(DbConstants.N_STATUS)){
				addActionError(userEmail+" is NOT active, can not assign Role!");
				return INPUT;
			}
				
			String isBlocked=blockedArray[selectedUserListIndex];
			if(StringUtility.isNotNullBlank(isBlocked) && isBlocked.equals(DbConstants.Y_STATUS)){
				addActionError(userEmail+" has been blocked, can not assign Role!");
				return INPUT;
			}

			String isApproved=approvedArray[selectedUserListIndex];
			if(StringUtility.isNotNullBlank(isApproved) && isApproved.equals(DbConstants.N_STATUS)){
				addActionError(userEmail+" has NOT been Approved yet, can not assign Role!");
				return INPUT;
			}

			String isRejected=rejectedArray[selectedUserListIndex];
			if(StringUtility.isNotNullBlank(isRejected) && isRejected.equals(DbConstants.Y_STATUS)){
				addActionError(userEmail+" role has been rejected, can not assign Role!");
				return INPUT;
			}

 
		}
		
		log.debug("|__selectedUserListIndex=="+selectedUserListIndex+
				"\n|__userId=="+userId+
				"\n|__userEmail=="+userEmail+
				"\n|__currentRole=="+currentRole+
				"\n|__currentRoleId=="+currentRoleId+
				"\n|__assignRole=="+assignRole);		

		if(StringUtility.isNotNullBlank(userEmail) 
				&& assignRole.intValue()==AdminConstants.ASSIGN_ROLE.intValue()){
			log.debug("getting role list for the selected user for assign new role");
			List<ListOrderedMap> currentUserRoleList=adminBllInterface.getUserList(userEmail, AdminConstants.USER_ROLE_ALL);
			currentRoleList=new ArrayList<String>();
			for(int i=0;i<currentUserRoleList.size();i++){
				currentRole=(String) currentUserRoleList.get(i).get(UserRoleMstEnum.USER_ROLE);
				currentRoleList.add(currentRole);
				log.debug(i+"] |__currentRole=="+currentRole);
			}
		}

		modal=1;
		return SUCCESS;
	}

	public String removeCurrentRole(){
		if(userId!=null && currentRoleId!=null && StringUtility.areNotNullBlank(userEmail,currentRole)){
			adminBllInterface.removeCurrentRole(Long.valueOf(userId),Long.valueOf(currentRoleId));
			addActionMessage(currentRole+" Role Removed for "+userEmail);
			return SUCCESS;
		}

		else{
			addActionError("Unable to Process the Request, Please try again!");
			return INPUT;
		}

	}

	public String getUserRole(Long userRoleId){
		HashMap<String,Object> userRoleList=(HashMap<String,Object>) application.get("userRoleList");
		return (String) userRoleList.get(userRoleId.toString());
	}

	public AdminBllInterface getAdminBllInterface() {
		return adminBllInterface;
	}

	public void setAdminBllInterface(AdminBllInterface adminBllInterface) {
		this.adminBllInterface = adminBllInterface;
	}

	@EmailValidator(fieldName="userEmail",key="key.userEmail.emailValidation",message="Please enter valid email")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

	public List<String> getCurrentRoleList() {
		return currentRoleList;
	}

	public void setCurrentRoleList(List<String> currentRoleList) {
		this.currentRoleList = currentRoleList;
	}

	public String getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(String currentRoleId) {
		this.currentRoleId = currentRoleId;
	}

	public Long getUserRoleSearch() {
		return userRoleSearch;
	}

	public void setUserRoleSearch(Long userRoleSearch) {
		this.userRoleSearch = userRoleSearch;
	}

	public Long getSelectedUserRole() {
		return selectedUserRole;
	}

	public void setSelectedUserRole(Long selectedUserRole) {
		this.selectedUserRole = selectedUserRole;
	}

	public List<ListOrderedMap> getUserList() {
		return userList;
	}

	public void setUserList(List<ListOrderedMap> userList) {
		this.userList = userList;
	}

	public Map getApplication() {
		return application;
	}

	public void setApplication(Map application) {
		this.application = application;
	}

	public int getModal() {
		return modal;
	}

	public void setModal(int modal) {
		this.modal = modal;
	}

	public Integer getAssignRole() {
		return assignRole;
	}

	public void setAssignRole(Integer assignRole) {
		this.assignRole = assignRole;
	}

	public Integer getSelectedUserListIndex() {
		return selectedUserListIndex;
	}

	public void setSelectedUserListIndex(Integer selectedUserListIndex) {
		this.selectedUserListIndex = selectedUserListIndex;
	}

	public String[] getUserIdArray() {
		return userIdArray;
	}

	public void setUserIdArray(String[] userIdArray) {
		this.userIdArray = userIdArray;
	}

	public String[] getUserEmailArray() {
		return userEmailArray;
	}

	public void setUserEmailArray(String[] userEmailArray) {
		this.userEmailArray = userEmailArray;
	}

	public String[] getCurrentRoleArray() {
		return currentRoleArray;
	}

	public void setCurrentRoleArray(String[] currentRoleArray) {
		this.currentRoleArray = currentRoleArray;
	}

	public String[] getCurrentRoleIdArray() {
		return currentRoleIdArray;
	}

	public void setCurrentRoleIdArray(String[] currentRoleIdArray) {
		this.currentRoleIdArray = currentRoleIdArray;
	}

	public String[] getActiveArray() {
		return activeArray;
	}

	public void setActiveArray(String[] activeArray) {
		this.activeArray = activeArray;
	}

	public String[] getBlockedArray() {
		return blockedArray;
	}

	public void setBlockedArray(String[] blockedArray) {
		this.blockedArray = blockedArray;
	}

	public String[] getApprovedArray() {
		return approvedArray;
	}

	public void setApprovedArray(String[] approvedArray) {
		this.approvedArray = approvedArray;
	}

	public String[] getRejectedArray() {
		return rejectedArray;
	}

	public void setRejectedArray(String[] rejectedArray) {
		this.rejectedArray = rejectedArray;
	}
	

	
}
