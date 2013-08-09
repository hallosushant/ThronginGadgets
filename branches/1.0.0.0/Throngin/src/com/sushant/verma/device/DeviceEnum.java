package com.sushant.verma.device;

public class DeviceEnum {

	ModelMenuEnum modelMenuEnum;
	public enum ModelMenuEnum
	{
		SPECIFICATIONS("specifications"),REVIEWS("reviews");
	
		private String menu;

		ModelMenuEnum (String menu)
		{
			this.menu=menu;
		}

		public String getMenu()
		{
			return menu;
		}
	}
}