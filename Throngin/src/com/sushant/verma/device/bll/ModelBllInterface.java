package com.sushant.verma.device.bll;

import java.util.Map;

public interface ModelBllInterface {

	@SuppressWarnings("unchecked")
	Map fetchModelDetails(Long modelId);
}
