package com.sushant.verma.common.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.opensymphony.xwork2.ActionSupport;
import com.sushant.verma.common.ResourceLoader.ResourceEnums;
import com.sushant.verma.common.constants.FeedConstants;
import com.sushant.verma.common.dto.FeedDto;
import com.sushant.verma.common.utility.CommonUtility;
import com.sushant.verma.common.utility.DateUtility;
import com.sushant.verma.common.utility.StringUtility;



public class BaseAction extends ActionSupport implements ApplicationAware,ServletRequestAware{
	private static Logger log=LogManager.getLogger(BaseAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String,Object> application;
	protected HttpServletRequest request;
	protected String msg;
	protected String msgType;
	protected String searchType;
	protected String remoteAddr;
	protected ImageCaptchaService imageCaptchaService;
	protected String captchaResponse;
	protected String metaDescription;
	protected String pageTitle;
	protected String contextPath;
	@Override
	public String execute() throws Exception{
	return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getApplicationKeyValue(String applicationListName,String key){
		HashMap<String,Object> applicationMap=(HashMap<String,Object>) application.get(applicationListName);
		return (String) applicationMap.get(key);
	}
	
	public void createLocalCopy(String localFileName,InputStream iStream) throws IOException{
		OutputStream outStream = new FileOutputStream(ResourceEnums.ZProps.localFolderPath.getProperty()+localFileName);
        try{
        log.debug("making a local copy="+localFileName);
        byte[] buffer = new byte[1024];
        int len;
        while((len = iStream.read(buffer)) >= 0)
        	outStream.write(buffer, 0, len);
        outStream.flush();
        outStream.close();
        iStream.close();
        }catch(Exception e)
        {
        	outStream.flush();
        	outStream.close();
        	iStream.close();
        log.debug("ERROR IN MAKING LOCAL COPY "+localFileName+", ERROR MSG = "+e.getMessage());
        }
        log.debug("iStream====="+iStream);
	}
	
	
	public String addUrlRules(ArrayList<String> urlRewriteRuleList) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		log.info("addUrlRules | urlRewriteRuleList Size="+urlRewriteRuleList.size());
		ServletContext context = ServletActionContext.getServletContext();
		String filePath=context.getInitParameter("urlrewriteConfPath");
		log.debug("filePath="+filePath);
		String fullPath = context.getRealPath(filePath);
		log.debug("fullPath="+fullPath);
		InputStream stream=new FileInputStream(fullPath);//context.getResourceAsStream(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(stream);
		NodeList urlrewriteNodeList = document.getElementsByTagName("urlrewrite");
		Node urlrewriteNode = urlrewriteNodeList.item(0);
		for(String urlRule:urlRewriteRuleList){
			log.debug("urlRules="+urlRule);
			Document urlDoc=documentBuilder.parse(new InputSource(new StringReader(urlRule)));
			while(urlDoc.hasChildNodes()) {
				Node ruleNode=urlDoc.getFirstChild();
				urlDoc.removeChild(ruleNode);
				ruleNode=document.importNode(ruleNode, true);
				urlrewriteNode.appendChild(ruleNode);
			}
		}
		TransformerFactory tranFactory = TransformerFactory.newInstance(); 
		Transformer aTransformer = tranFactory.newTransformer(); 
		aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		Source src = new DOMSource(document); 
		StreamResult result = new StreamResult(fullPath);
		aTransformer.transform(src, result);
//		Result dest = new StreamResult(System.out); //for SystemOut 
//		aTransformer.transform(src, dest); 
		log.info("Urlrewrite XMl appended with new Url rules.");
		return NONE;
	}
	
	public String removeUrlRules(Long urlForId,String forType) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		log.info("removeUrlRules | urlForId="+urlForId+" | forType="+forType);
		ServletContext context = ServletActionContext.getServletContext();
		String filePath=context.getInitParameter("urlrewriteConfPath");
		log.debug("filePath="+filePath);
		String fullPath = context.getRealPath(filePath);
		log.debug("fullPath="+fullPath);
		InputStream stream=new FileInputStream(fullPath);//context.getResourceAsStream(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(stream);
		NodeList urlrewriteNodeList = document.getElementsByTagName("urlrewrite");
		Node urlrewriteNode = urlrewriteNodeList.item(0);

		NodeList forNodeList = document.getElementsByTagName("for");
		List<Node> toRemoveNodeList=new ArrayList<Node>();
		for(int i=0;i<forNodeList.getLength();i++){
			Node forNode=forNodeList.item(i);
			log.debug("forNode="+forNode.getNodeName());
			if(forNode.hasAttributes()){
				String forAttributeTypeValue=forNode.getAttributes().item(0).getNodeValue();
				log.debug("forAttributeTypeValue="+forAttributeTypeValue);
				if(StringUtility.isNotNullBlank(forAttributeTypeValue) && forAttributeTypeValue.equals(forType)){

					String thisForText=forNode.getTextContent();
					log.debug("thisForText="+thisForText);
					if(StringUtility.isNotNullBlank(thisForText) && urlForId.equals(Long.valueOf(thisForText.trim()))){
						Node thisForNodeParent=forNode.getParentNode();
						log.info("thisForNodeParent.getNodeName()=="+thisForNodeParent.getNodeName());
						log.info("thisForNodeParent.getTextContent()=="+thisForNodeParent.getTextContent());
						toRemoveNodeList.add(thisForNodeParent);
						
					}
				}
			}

		}
		for(Node removeNode:toRemoveNodeList){
			log.info("removeNode.getTextContent()=="+removeNode.getTextContent());
			urlrewriteNode.removeChild(removeNode);
		}
		TransformerFactory tranFactory = TransformerFactory.newInstance(); 
		Transformer aTransformer = tranFactory.newTransformer(); 
		aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		Source src = new DOMSource(document); 
		StreamResult result = new StreamResult(fullPath);
		aTransformer.transform(src, result);
//		Result dest = new StreamResult(System.out); //for SystemOut 
//		aTransformer.transform(src, dest); 
		log.info("Urlrewrite XMl removed Url rules.");
		return NONE;
	}
	
	public String addRssFeedItem(FeedDto feedDto) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		log.info("addRssFeedItem | feedDto="+CommonUtility.getDTOasXML(feedDto));
		ServletContext context = ServletActionContext.getServletContext();
		String filePath=context.getInitParameter("rssFeedPath");
		log.debug("filePath="+filePath);
		String fullPath = context.getRealPath(filePath);
		log.debug("fullPath="+fullPath);
		InputStream stream=new FileInputStream(fullPath);//context.getResourceAsStream(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(stream);
		Node lastBuildDate = document.getElementsByTagName("lastBuildDate").item(0);
		lastBuildDate.setTextContent(DateUtility.getRFC822Date(new Date()));
		NodeList channelList = document.getElementsByTagName("channel");
		Node channelNode = channelList.item(0);
		NodeList itemList = document.getElementsByTagName("item");
		String itemXml=feedDto.getItemXmlNode();
		if(StringUtility.isNotNullBlank(itemXml)){
			log.debug("itemXml="+itemXml);
			Document itemDoc=documentBuilder.parse(new InputSource(new StringReader(itemXml)));
			while(itemDoc.hasChildNodes()) {
				Node itemNode=itemDoc.getFirstChild();
				itemDoc.removeChild(itemNode);
				itemNode=document.importNode(itemNode, true);
				if(itemList.getLength()==0)
					channelNode.appendChild(itemNode);//adds the item node at the end
				else
					channelNode.insertBefore(itemNode, itemList.item(0));//adds the item node at the start before other item Nodes
			}
		}
		TransformerFactory tranFactory = TransformerFactory.newInstance(); 
		Transformer aTransformer = tranFactory.newTransformer(); 
		aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		Source src = new DOMSource(document); 
		StreamResult result = new StreamResult(fullPath);
		aTransformer.transform(src, result);
//		Result dest = new StreamResult(System.out); //for SystemOut 
//		aTransformer.transform(src, dest); 
		log.info("Urlrewrite XMl appended with new Url rules.");
		return NONE;
	}
	
	public boolean isCaptchaResponseValid(String captchaResponse){
		HttpServletRequest request = ServletActionContext.getRequest();
		Boolean isCaptchaResponseValid =Boolean.FALSE;
        String captchaId = request.getSession().getId();
        try {
        	isCaptchaResponseValid = imageCaptchaService.validateResponseForID(captchaId,captchaResponse);
        } catch (CaptchaServiceException e) {
             //should not happen, may be thrown if the id is not valid
        	log.debug("Captcha ID Not Valid");
        }
        log.debug("|__isCaptchaResponseValid=="+isCaptchaResponseValid);
		return isCaptchaResponseValid;
	}
	
	public String getServerContext(final HttpServletRequest request) {
        // Get the base url.
        final StringBuilder serverPath = new StringBuilder();
        
        serverPath.append(request.getScheme() + "://");
        serverPath.append(request.getServerName());

        if (request.getServerPort() != 80) {
            serverPath.append(":" + request.getServerPort());
        }
        serverPath.append(request.getContextPath());
        log.info("serverPath="+serverPath.toString());
        return serverPath.toString();
    }
	
	/*
	 * Getters & Setters
	 */
	public Map<String, Object> getApplication() {
		return application;
	}
	public void setApplication(Map<String, Object> application) {
		contextPath=ServletActionContext.getRequest().getContextPath();
		this.application = application;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getRemoteAddr() {
		HttpServletRequest request = ServletActionContext.getRequest();
		remoteAddr = (String) request.getRemoteAddr();
		log.info("remoteAddr="+remoteAddr);
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		log.info("remoteAddr="+remoteAddr);
		this.remoteAddr = remoteAddr;
	}
	public ImageCaptchaService getImageCaptchaService() {
		return imageCaptchaService;
	}
	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}
	public String getCaptchaResponse() {
		return captchaResponse;
	}
	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public String getContextPath() {
		return contextPath;
	}


	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
}



