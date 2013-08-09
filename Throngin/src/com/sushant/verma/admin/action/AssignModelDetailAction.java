package com.sushant.verma.admin.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.admin.bll.AdminBllInterface;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.enums.TableEnums.ModelEnum;
@Validations
public class AssignModelDetailAction extends BaseAction implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(AssignModelDetailAction.class);
	
	private AdminBllInterface adminBllInterface;
	private Long modelId;
	private String modelName;
	private List<String> detailCategory;
	private List<String> detailName;
	private List<String> detailValue;
	private boolean assign=false;
	private List<ListOrderedMap> modelDetailList;
//	private List<ListOrderedMap> modelList;
	private String assignType;
	private Map<String,Object> session;
	private final String MODEL_LIST="modelList";
	@SkipValidation
	public String execute() throws Exception{
		log.debug("|__ modelId="+modelId+
				"\n|__detailName="+detailName+
				"\n|__detailValue="+detailValue);
		if("edit".equalsIgnoreCase(assignType))
				return editModelDetail();
		session.remove(MODEL_LIST);
		List<ListOrderedMap> modelList=adminBllInterface.getModelList(0);
		session.put(MODEL_LIST, modelList);
		log.debug("|__session.get('modelList')=="+session.get(MODEL_LIST));
		setAssign(false);
		assignType="add";
		return SUCCESS;
	}
	@SkipValidation
	public String editModelDetail() throws Exception{
		log.debug("|__ modelId="+modelId+
				"\n|__detailName="+detailName+
				"\n|__detailValue="+detailValue);
		session.remove(MODEL_LIST);
		List<ListOrderedMap> modelList=adminBllInterface.getModelList(1);
		session.put(MODEL_LIST, modelList);
		log.debug("|__session.get('modelList')=="+session.get(MODEL_LIST));
		setAssign(false);
		assignType="edit";
		return SUCCESS;
	}
	
	public String openAssignBlock() throws Exception{
		log.debug("|__ modelId="+modelId+" | __assignType="+assignType); 
		modelDetailList=adminBllInterface.getModelDetails(modelId);
		if(modelDetailList==null || modelDetailList.size()<1){
			modelDetailList=new ArrayList<ListOrderedMap>();
			ListOrderedMap modelDetailMap=new ListOrderedMap();
			modelDetailMap.put("DETAIL_NAME", "");
			modelDetailMap.put("DETAIL_VALUE", "");
			modelDetailList.add(modelDetailMap);
		}
		modelName=getSessionKeyValue(MODEL_LIST, modelId.toString(),ModelEnum.MODEL_ID.toString(),ModelEnum.MODEL_NAME.toString());
		setAssign(true);
		return SUCCESS;
	}
	
	public String assignModelDetail(){
		log.debug("|__ modelId="+modelId+
				"\n|__detailName="+detailName+
				"\n|__detailCategory="+detailCategory+
				"\n|__detailValue="+detailValue+
				"\n|__assignType="+assignType);
		String modelName=getSessionKeyValue(MODEL_LIST, modelId.toString(),ModelEnum.MODEL_ID.toString(),ModelEnum.MODEL_NAME.toString());
		adminBllInterface.assignModelDetail(modelName,modelId, detailName, detailValue,detailCategory,assignType);
		msg="Model Details Assigned Successfully for "+modelName;
		msgType=MsgType.SUCCESS.getMsgType();
		setAssign(false);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getSessionKeyValue(String sessionListName,String key, String mapKey, String mapValue){
		System.out.println("session.get(sessionListName)>>>>>>>>>>>"+session.get(sessionListName));
		List<ListOrderedMap> sessionList= (List<ListOrderedMap>) session.get(sessionListName);
		for(int i=0;i<sessionList.size();i++){
			Map map=sessionList.get(i);
			log.debug("map=="+map+"\nmap.get(mapKey)="+map.get(mapKey)+"\nkey="+key+"\nmap.get(mapValue)="+map.get(mapValue));
			if(key.equals(map.get(mapKey).toString())){
				return (String) map.get(mapValue);
			}
		}
		return "-";
	}

	@Override
	public void validate() {
		String methodName=ActionContext.getContext().getActionInvocation().getProxy().getMethod();
		log.debug("|__methodName="+methodName);
/*		if(StringUtility.isNotBlank(methodName)){
			if(methodName.equals("assignModelDetail")){
				for(int i=0;i<detailName.size();i++){
					String detName=detailName.get(i);
					if(StringUtility.isBlank(detName)){
						addFieldError("detailName", "Please enter Detail Name");
					}
				}
			}
		}
*/	}
 
	/*
	 *Getters & Setters 
	 *
	 */
	

	
	public AdminBllInterface getAdminBllInterface() {
		return adminBllInterface;
	}

	public void setAdminBllInterface(AdminBllInterface adminBllInterface) {
		this.adminBllInterface = adminBllInterface;
	}

	@RequiredFieldValidator(fieldName="modelId",key="modelId.mandatory",message="Please select a Model",shortCircuit=true)
	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public List<String> getDetailName() {
		return detailName;
	}

	public void setDetailName(List<String> detailName) {
		this.detailName = detailName;
	}

	public List<String> getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(List<String> detailValue) {
		this.detailValue = detailValue;
	}

	public boolean isAssign() {
		return assign;
	}

	public void setAssign(boolean assign) {
		this.assign = assign;
	}

	public List<ListOrderedMap> getModelDetailList() {
		return modelDetailList;
	}

	public void setModelDetailList(List<ListOrderedMap> modelDetailList) {
		this.modelDetailList = modelDetailList;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

/*	public List<ListOrderedMap> getModelList() {
		return modelList;
	}

	public void setModelList(List<ListOrderedMap> modelList) {
		this.modelList = modelList;
	}
*/
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<String> getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(List<String> detailCategory) {
		this.detailCategory = detailCategory;
	}
	public String getAssignType() {
		return assignType;
	}
	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}



	
	
}
