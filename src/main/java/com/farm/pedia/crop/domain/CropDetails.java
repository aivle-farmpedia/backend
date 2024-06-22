package com.farm.pedia.crop.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class CropDetails {
	private final Crop crop;
	private final List<Variety> varieties;
	private final List<PriceEntry> priceEntries;
	private final List<CropProcess> cropProcesses;

	private CropDetails(Crop crop, List<Variety> varieties, List<PriceEntry> priceEntries,
		List<CropProcess> cropProcesses) {
		this.crop = crop;
		this.varieties = varieties;
		this.priceEntries = priceEntries;
		this.cropProcesses = cropProcesses;
	}

	public static CropDetails of(Crop crop, List<Variety> varieties, List<PriceEntry> priceEntries,
		List<CropProcess> cropProcesses) {
		return new CropDetails(crop, varieties, priceEntries, cropProcesses);
	}
}