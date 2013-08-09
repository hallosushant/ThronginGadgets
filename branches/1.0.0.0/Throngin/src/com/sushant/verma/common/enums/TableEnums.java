package com.sushant.verma.common.enums;

public class TableEnums {

	CategoryMstEnum categoryMst;
	public enum CategoryMstEnum
	{
		CATEGORY_ID,categoryId(20),categoryId_Type("Long"),
		CATEGORY_NAME,categoryName(500),categoryName_Type("String"),
		CATEGORY_DESC,categoryDesc(500),categoryDesc_Type("String"),
		PARENT_CATEGORY_ID,parentCategoryId(20),parentCategoryId_Type("Long"),
		DEVICE_ID,deviceId(20),deviceId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		CategoryMstEnum (){}
		CategoryMstEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		CategoryMstEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	DetailNameMstEnum detailNameMst;
	public enum DetailNameMstEnum
	{
		DETAIL_NAME_ID,detailNameId(20),detailNameId_Type("Long"),
		DETAIL_NAME,detailName(100),detailName_Type("String"),
		CATEGORY_ID,categoryId(20),categoryId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		DetailNameMstEnum (){}
		DetailNameMstEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		DetailNameMstEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	DeviceMstEnum deviceMst;
	public enum DeviceMstEnum
	{
		DEVICE_MST_ID,deviceId(20),deviceId_Type("Long"),
		DEVICE_NAME,deviceName(100),deviceName_Type("String"),
		DEVICE_IMG_PATH,deviceImgPath(2000),deviceImgPath_Type("String"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		DeviceMstEnum (){}
		DeviceMstEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		DeviceMstEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	FileEnum file;
	public enum FileEnum
	{
		FILE_ID,fileId(20),fileId_Type("Long"),
		FILE_NAME,fileName(500),fileName_Type("String"),
		FILE,file(65535),file_Type("[B"),
		FILE_DESC,fileDesc(500),fileDesc_Type("String"),
		FILE_TYPE,fileType(100),fileType_Type("String"),
		FILE_EXTENSION,fileExtension(10),fileExtension_Type("String"),
		FILE_PATH,filePath(2000),filePath_Type("String"),
		CATEGORY_ID,categoryId(20),categoryId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		MODIFIED_DATE,modifiedDate(19),modifiedDate_Type("Timestamp"),
		MODIFIED_BY,modifiedBy(20),modifiedBy_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		FileEnum (){}
		FileEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		FileEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	FileCommentEnum fileComment;
	public enum FileCommentEnum
	{
		FILE_COMMENT_ID,fileCommentId(20),fileCommentId_Type("Long"),
		FILE_ID,fileId(20),fileId_Type("Long"),
		USER_ID,userId(20),userId_Type("Long"),
		USER_NAME,userName(45),userName_Type("String"),
		STATUS_ID,statusId(20),statusId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		COMMENT,comment(1000),comment_Type("String");
	
		private int columnLength;
		private String columnType;
		FileCommentEnum (){}
		FileCommentEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		FileCommentEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	FileDetailEnum fileDetail;
	public enum FileDetailEnum
	{
		FILE_DETAIL_ID,fileDetailId(20),fileDetailId_Type("Long"),
		FILE_ID,fileId(20),fileId_Type("Long"),
		DETAIL_NAME,detailName(500),detailName_Type("String"),
		DETAIL_VALUE,detailValue(1000),detailValue_Type("String"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		MODIFIED_DATE,modifiedDate(19),modifiedDate_Type("Timestamp"),
		MODIFIED_BY,modifiedBy(20),modifiedBy_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		FileDetailEnum (){}
		FileDetailEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		FileDetailEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	FileTagsMappingEnum fileTagsMapping;
	public enum FileTagsMappingEnum
	{
		FILE_TAGS_MAPPING_ID,fileTagsMappingId(20),fileTagsMappingId_Type("Long"),
		FILE_ID,fileId(20),fileId_Type("Long"),
		TAG_ID,tagId(20),tagId_Type("Long"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		FileTagsMappingEnum (){}
		FileTagsMappingEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		FileTagsMappingEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	HintQuestionEnum hintQuestion;
	public enum HintQuestionEnum
	{
		HINT_QUESTION_ID,hintQuestionId(20),hintQuestionId_Type("Long"),
		HINT_QUESTION,hintQuestion(200),hintQuestion_Type("String"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		HintQuestionEnum (){}
		HintQuestionEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		HintQuestionEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ManufacturerEnum manufacturer;
	public enum ManufacturerEnum
	{
		MANUFACTURER_ID,manufacturerId(20),manufacturerId_Type("Long"),
		MANUFACTURER_NAME,manufacturerName(500),manufacturerName_Type("String"),
		DEVICE_MST_ID,deviceMstId(20),deviceMstId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		ManufacturerEnum (){}
		ManufacturerEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ManufacturerEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ModelEnum model;
	public enum ModelEnum
	{
		MODEL_ID,modelId(20),modelId_Type("Long"),
		DEVICE_MST_ID,deviceMstId(20),deviceMstId_Type("Long"),
		MANUFACTURER_ID,manufacturerId(20),manufacturerId_Type("Long"),
		MODEL_NAME,modelName(500),modelName_Type("String"),
		MODEL_DESC,modelDesc(4000),modelDesc_Type("String"),
		PRICE,price(11),price_Type("Integer"),
		LAUNCH_DATE,launchDate(10),launchDate_Type("Date"),
		MODEL_IMAGE,modelImage(65535),modelImage_Type("[B"),
		MODEL_IMAGE_URL,modelImageUrl(4000),modelImageUrl_Type("String"),
		MODEL_VIDEO_LINK,modelVideoLink(4000),modelVideoLink_Type("String"),
		STAR_RATING,starRating(12),starRating_Type("Float"),
		RATING_COUNT,ratingCount(20),ratingCount_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long"),
		MODIFIED_BY,modifiedBy(20),modifiedBy_Type("Long"),
		MODIFIED_DATE,modifiedDate(19),modifiedDate_Type("Timestamp"),
		MODEL_LINK,modelLink(4000),modelLink_Type("String");
		private int columnLength;
		private String columnType;
		ModelEnum (){}
		ModelEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ModelEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ModelCategoryMappingEnum modelCategoryMapping;
	public enum ModelCategoryMappingEnum
	{
		MODEL_CATEGORY_MAPPING_ID,modelCategoryMappingId(20),modelCategoryMappingId_Type("Long"),
		CATEGORY_ID,categoryId(20),categoryId_Type("Long"),
		MODEL_ID,modelId(20),modelId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		MODIFIED_DATE,modifiedDate(19),modifiedDate_Type("Timestamp"),
		MODIFIED_BY,modifiedBy(20),modifiedBy_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		ModelCategoryMappingEnum (){}
		ModelCategoryMappingEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ModelCategoryMappingEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ModelCommentEnum modelComment;
	public enum ModelCommentEnum
	{
		MODEL_COMMENT_ID,modelCommentId(20),modelCommentId_Type("Long"),
		MODEL_ID,modelId(20),modelId_Type("Long"),
		USER_ID,userId(20),userId_Type("Long"),
		USER_NAME,userName(45),userName_Type("String"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long"),
		COMMENT,comment(1000),comment_Type("String");
	
		private int columnLength;
		private String columnType;
		ModelCommentEnum (){}
		ModelCommentEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ModelCommentEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ModelDetailEnum modelDetail;
	public enum ModelDetailEnum
	{
		MODEL_DETAIL_ID,modelDetailId(20),modelDetailId_Type("Long"),
		MODEL_ID,modelId(20),modelId_Type("Long"),
		DETAIL_NAME_ID,detailNameId(20),detailNameId_Type("Long"),
		DETAIL_NAME,detailName(100),detailName_Type("String"),
		DETAIL_VALUE,detailValue(1000),detailValue_Type("String"),
		CATEGORY_ID,categoryId(20),categoryId_Type("Long"),
		CATEGORY_NAME,categoryName(500),categoryName_Type("String"),
		MODIFIED_DATE,modifiedDate(19),modifiedDate_Type("Timestamp"),
		MODIFIED_BY,modifiedBy(20),modifiedBy_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		ModelDetailEnum (){}
		ModelDetailEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ModelDetailEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ModelFileMappingEnum modelFileMapping;
	public enum ModelFileMappingEnum
	{
		MODEL_FILE_MAPPING_ID,modelFileMappingId(20),modelFileMappingId_Type("Long"),
		MODEL_ID,modelId(20),modelId_Type("Long"),
		FILE_ID,fileId(20),fileId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		MODIFIED_DATE,modifiedDate(19),modifiedDate_Type("Timestamp"),
		MODIFIED_BY,modifiedBy(20),modifiedBy_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		ModelFileMappingEnum (){}
		ModelFileMappingEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ModelFileMappingEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ModelTagsMappingEnum modelTagsMapping;
	public enum ModelTagsMappingEnum
	{
		MODEL_TAG_MAPPING_ID,modelTagMappingId(20),modelTagMappingId_Type("Long"),
		MODEL_ID,modelId(20),modelId_Type("Long"),
		TAG_ID,tagId(20),tagId_Type("Long"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		ModelTagsMappingEnum (){}
		ModelTagsMappingEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ModelTagsMappingEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	PopularityEnum popularity;
	public enum PopularityEnum
	{
		POPULARITY_ID,popularityId(20),popularityId_Type("Long"),
		DEVICE_ID,deviceId(20),deviceId_Type("Long"),
		MANUFACTURER_ID,manufacturerId(20),manufacturerId_Type("Long"),
		CATEGORY_ID,categoryId(20),categoryId_Type("Long"),
		MODEL_ID,modelId(20),modelId_Type("Long"),
		POPULARITY_INDEX,popularityIndex(11),popularityIndex_Type("Integer");
	
		private int columnLength;
		private String columnType;
		PopularityEnum (){}
		PopularityEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		PopularityEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	ReviewEnum review;
	public enum ReviewEnum
	{
		REVIEW_ID,reviewId(20),reviewId_Type("Long"),
		MODEL_ID,modelId(20),modelId_Type("Long"),
		FILE_ID,fileId(20),fileId_Type("Long"),
		TITLE,title(4000),title_Type("String"),
		REVIEW,review(65535),review_Type("String"),
		AUTHOR,author(100),author_Type("String"),
		REVIEW_DATE,reviewDate(10),reviewDate_Type("Date"),
		REF_LINK,refLink(2000),refLink_Type("String"),
		IMAGE_LINK,imageLink(2000),imageLink_Type("String"),
		VIDEO_LINK,videoLink(2000),videoLink_Type("String"),
		STAR_RATING,starRating(12),starRating_Type("Float"),
		RATING_COUNT,ratingCount(20),ratingCount_Type("Long"),
		MODIFIED_DATE,modifiedDate(19),modifiedDate_Type("Timestamp"),
		MODIFIED_BY,modifiedBy(20),modifiedBy_Type("Long"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		ReviewEnum (){}
		ReviewEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		ReviewEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	StatusMstEnum statusMst;
	public enum StatusMstEnum
	{
		STATUS_ID,statusId(20),statusId_Type("Long"),
		STATUS,status(100),status_Type("String"),
		STATUS_DESC,statusDesc(200),statusDesc_Type("String"),
		ACTIVE,active(1),active_Type("String");
	
		private int columnLength;
		private String columnType;
		StatusMstEnum (){}
		StatusMstEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		StatusMstEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	TagEnum tag;
	public enum TagEnum
	{
		TAG_ID,tagId(20),tagId_Type("Long"),
		TAG_NAME,tagName(100),tagName_Type("String"),
		TAG_DESC,tagDesc(2000),tagDesc_Type("String"),
		CREATED_BY,createdBy(20),createdBy_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		TagEnum (){}
		TagEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		TagEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	UserEnum user;
	public enum UserEnum
	{
		USER_ID,userId(20),userId_Type("Long"),
		USER_NAME,userName(45),userName_Type("String"),
		USER_EMAIL,userEmail(200),userEmail_Type("String"),
		SALT,salt,salt_Type("byte[]"),
		USER_PWD,userPwd(200),userPwd_Type("byte[]"),
		HINT_QUESTION_ID,hintQuestionId(20),hintQuestionId_Type("Long"),
		HINT_ANSWER,hintAnswer(200),hintAnswer_Type("String"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		CREATED_BY,createdBy(200),createdBy_Type("String"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		UserEnum (){}
		UserEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		UserEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	UserRoleMappingEnum userRoleMapping;
	public enum UserRoleMappingEnum
	{
		USER_ROLE_MAPPING_ID,userRoleMappingId(20),userRoleMappingId_Type("Long"),
		USER_ROLE_ID,userRoleId(20),userRoleId_Type("Long"),
		USER_ID,userId(20),userId_Type("Long"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		UserRoleMappingEnum (){}
		UserRoleMappingEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		UserRoleMappingEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	UserRoleMstEnum userRoleMst;
	public enum UserRoleMstEnum
	{
		USER_ROLE_ID,userRoleId(20),userRoleId_Type("Long"),
		USER_ROLE,userRole(100),userRole_Type("String"),
		USER_ROLE_DESC,userRoleDesc(100),userRoleDesc_Type("String"),
		CREATED_DATE,createdDate(19),createdDate_Type("Timestamp"),
		STATUS_ID,statusId(20),statusId_Type("Long");
	
		private int columnLength;
		private String columnType;
		UserRoleMstEnum (){}
		UserRoleMstEnum (int columnLength)
		{
			this.columnLength=columnLength;
		}
		UserRoleMstEnum (String columnType)
		{
			this.columnType=columnType;
		}
		public int getColumnLength()
		{
			return columnLength;
		}
		public String getColumnType()
		{
			return columnType;
		}
	}

	public enum  TableEnum
	{
		CATEGORY_MST,
		DETAIL_NAME_MST,
		DEVICE_MST,
		FILE,
		FILE_COMMENT,
		FILE_DETAIL,
		FILE_TAGS_MAPPING,
		HINT_QUESTION,
		MANUFACTURER,
		MODEL,
		MODEL_CATEGORY_MAPPING,
		MODEL_COMMENT,
		MODEL_DETAIL,
		MODEL_FILE_MAPPING,
		MODEL_TAGS_MAPPING,
		POPULARITY,
		REVIEW,
		STATUS_MST,
		TAG,
		USER,
		USER_ROLE_MAPPING,
		USER_ROLE_MST;
		TableEnum(){}
	}
}