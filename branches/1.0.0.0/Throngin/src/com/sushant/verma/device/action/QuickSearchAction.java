package com.sushant.verma.device.action;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.ModelDto;
import com.sushant.verma.common.utility.StringUtility;
import com.sushant.verma.device.DeviceConstants;
import com.sushant.verma.device.bll.DeviceBllInterface;
@Validations
public class QuickSearchAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(QuickSearchAction.class);

	private List<ModelDto> modelList;
	private Integer p=1;
	private Long tc;
	private boolean showMore;
	private Long dId;
	private Long mId;
	private String searchStr;
	private String searchType="quick";
	private DeviceBllInterface deviceBllImpl;
	
	public String execute() throws Exception{
		log.info("execute");
		if(mId==null || mId.longValue()<1){
			search();
			return SUCCESS;
		}
		else{
		String modelName=getApplicationKeyValue("modelList", mId.toString());
		if(StringUtility.isNotNullBlank(modelName))
			return "modelDetail";
		}
		return SUCCESS;
	}
	
	public String search(){
		log.debug("search");

		log.debug("deviceId="+dId+"\n searchStr="+searchStr+"\n pageNo="+p);
		if(StringUtility.isNullBlank(searchStr)){
			msg="Please enter a search word!";
			msgType=MsgType.ERROR.getMsgType();
			return NONE;
		}
		modelList=deviceBllImpl.quickSearch(dId,searchStr,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p);
		if(modelList.isEmpty()){
			msg="No Records Found";
			msgType=MsgType.INFO.getMsgType();
		}
		else{
			tc=modelList.get(0).getModelCount();
			showMore=true;
			log.debug("1 modelList Size="+modelList.size()+" | tc="+tc+" | p++"+p+" | showMore="+showMore);

			if(p*DeviceConstants.MODEL_GALLERY_PAGE_SIZE>=tc.intValue())
				showMore=false;
			p+=1;//increase pageNo

			log.debug("2 modelList Size="+modelList.size()+" | tc="+tc+" | p++"+p+" | showMore="+showMore);
		}
		
				
		return SUCCESS;
	}
	
	
	/*
	 * Getters & Setters
	 */
	
	public DeviceBllInterface getDeviceBllImpl() {
		return deviceBllImpl;
	}

	public void setDeviceBllImpl(DeviceBllInterface deviceBllImpl) {
		this.deviceBllImpl = deviceBllImpl;
	}

	public List<ModelDto> getModelList() {
		return modelList;
	}

	public void setModelList(List<ModelDto> modelList) {
		this.modelList = modelList;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Long getTc() {
		return tc;
	}

	public void setTc(Long tc) {
		this.tc = tc;
	}

	public boolean isShowMore() {
		return showMore;
	}

	public void setShowMore(boolean showMore) {
		this.showMore = showMore;
	}

	public Long getDId() {
		return dId;
	}

	public void setDId(Long dId) {
		this.dId = dId;
	}

	public Long getMId() {
		return mId;
	}

	public void setMId(Long mId) {
		this.mId = mId;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

}
