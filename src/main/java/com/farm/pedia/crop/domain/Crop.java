package com.farm.pedia.crop.domain;

import lombok.Getter;

@Getter
public class Crop {

	private final Long cropId;
	private final String cropName;
	private final Double areaPerYield;
	private final Double timePerYield;
	private final String cultivationUrl;

	private Crop(Long cropId, String cropName, Double areaPerYield, Double timePerYield, String cultivationUrl) {
		this.cropId = cropId;
		this.cropName = cropName;
		this.areaPerYield = areaPerYield;
		this.timePerYield = timePerYield;
		this.cultivationUrl = cultivationUrl;
	}
}
