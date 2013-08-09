package com.sushant.verma.common.ResourceLoader;

public class ResourceEnums {

	public enum WordList
	{
		wordCount("WORD_COUNT"),wordList("WORD_LIST");

		private String property;
		private String resourceFile="/Resources/WordList.properties";;

		WordList(String property){
			ResourceFactory rf=new ResourceFactory(resourceFile);
			this.property=rf.getProperty(property);
		}

		public String getProperty() {
			return property;
		}
	}
	
	public enum ZProps
	{
		localFolderPath("LOCAL_FOLDER_PATH"),modelGalleryPageSize("MODEL_GALLERY_PAGE_SIZE")
		;
		
		private String property;
		private String resourceFile="/Resources/z.properties";;

		ZProps(String property){
			ResourceFactory rf=new ResourceFactory(resourceFile);
			this.property=rf.getProperty(property);
		}

		public String getProperty() {
			return property;
		}
	}

}
