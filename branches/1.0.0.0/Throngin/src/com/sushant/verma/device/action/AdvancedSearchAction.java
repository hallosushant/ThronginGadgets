package com.sushant.verma.device.action;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.sushant.verma.common.action.BaseAction;
import com.sushant.verma.common.constants.ZConstants.MsgType;
import com.sushant.verma.common.dto.AdvancedSearchDto;
import com.sushant.verma.device.DeviceConstants;
import com.sushant.verma.device.bll.DeviceBllInterface;
@Validations
public class AdvancedSearchAction extends BaseAction implements ModelDriven<AdvancedSearchDto>{

	private static final long serialVersionUID = 1L;
	private static Logger log=LogManager.getLogger(AdvancedSearchAction.class);

	private AdvancedSearchDto advancedSearchDto=new AdvancedSearchDto();
	private DeviceBllInterface deviceBllImp;
	
	public String execute() throws Exception{
		log.info("execute"+advancedSearchDto.getSearchType());
		advancedSearchDto.setP(1);
		advancedSearchDto.setShowMore(true);
		return SUCCESS;
	}
	
	public String mobileSearch(){
		log.debug(advancedSearchDto.getSearchType()+
				"\ndId="+advancedSearchDto.getDId()+
				"\nshowMore="+advancedSearchDto.isShowMore()+
				"\np="+advancedSearchDto.getP()+
				"\nmarketTrend="+advancedSearchDto.getMarketTrend()+
				"\nmobileManufacturer="+advancedSearchDto.getMobileManufacturer()+
				"\nnetwork="+advancedSearchDto.getNetwork()+
				"\nmobilePriceBand="+advancedSearchDto.getMobilePriceBand()+
				"\nmobileBody="+advancedSearchDto.getMobileBody()+
				"\ntouchscreen="+advancedSearchDto.getTouchscreen()+
				"\ntouchscreenType="+advancedSearchDto.getTouchscreenType()+
				"\nmobileOS="+advancedSearchDto.getMobileOS()+
				"\ndataConnectivity="+advancedSearchDto.getDataConnectivity()+
				"\ncamera="+advancedSearchDto.getCamera()+
				"\ncameraType="+advancedSearchDto.getCameraType()+
				"\nprimaryCameraType="+advancedSearchDto.getPrimaryCameraType()+
				"\nminDisplaySize="+advancedSearchDto.getScreenDisplaySize()+
				"\nmusic="+advancedSearchDto.getMusic()+
				"\nmisc="+advancedSearchDto.getMisc());

		advancedSearchDto.setShowMore(true);
		Integer p=advancedSearchDto.getP();
		Long tc=0l;

		advancedSearchDto.setModelList(deviceBllImp.mobileAdvancedSearch(advancedSearchDto,DeviceConstants.MODEL_GALLERY_PAGE_SIZE,p));
		if(advancedSearchDto.getModelList()!=null && advancedSearchDto.getModelList().size()>0)
			tc=advancedSearchDto.getModelList().get(0).getModelCount();

		log.debug("1 modelList Size="+advancedSearchDto.getModelList().size()+" | tc="+tc+" | p++"+p+" | showMore="+advancedSearchDto.isShowMore());

		if(p*DeviceConstants.MODEL_GALLERY_PAGE_SIZE>=tc.intValue())
			advancedSearchDto.setShowMore(false);
		p+=1;//increase pageNo

		log.debug("2 modelList Size="+advancedSearchDto.getModelList().size()+" | tc="+tc+" | p++"+p+" | showMore="+advancedSearchDto.isShowMore());
		
		if(advancedSearchDto.getModelList().isEmpty()){
			msg="No Records Found";
			msgType=MsgType.INFO.getMsgType();
		}
		
		log.debug("3 modelList Size="+advancedSearchDto.getModelList().size()+" | tc="+tc+" | p++"+p+" | showMore="+advancedSearchDto.isShowMore());
		
		advancedSearchDto.setP(p);
		advancedSearchDto.setTc(tc);
		advancedSearchDto.setSearchType("advanced");
		return SUCCESS;
	}
	
	
	/*
	 * Getters & Setters
	 */
	


	public AdvancedSearchDto getModel() {
		
		return advancedSearchDto;
	}

	public AdvancedSearchDto getAdvancedSearchDto() {
		return advancedSearchDto;
	}

	public void setAdvancedSearchDto(AdvancedSearchDto advancedSearchDto) {
		this.advancedSearchDto = advancedSearchDto;
	}

	public DeviceBllInterface getDeviceBllImp() {
		return deviceBllImp;
	}

	public void setDeviceBllImp(DeviceBllInterface deviceBllImp) {
		this.deviceBllImp = deviceBllImp;
	}

}
