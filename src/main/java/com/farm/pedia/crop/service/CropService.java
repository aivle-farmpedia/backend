package com.farm.pedia.crop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.pedia.crop.domain.Crop;
import com.farm.pedia.crop.domain.CropDetails;
import com.farm.pedia.crop.domain.CropProcess;
import com.farm.pedia.crop.domain.PriceEntry;
import com.farm.pedia.crop.domain.Variety;
import com.farm.pedia.crop.mapper.CropMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CropService {

	private final CropMapper cropMapper;

	public CropDetails findCropDetails(Long cropId) {
		Crop crop = cropMapper.findCropDetails(cropId);
		List<Variety> varieties = cropMapper.findVarieties(cropId);
		List<PriceEntry> priceEntries = cropMapper.findPriceEntries(cropId);
		List<CropProcess> cropProcesses = cropMapper.findCropProcesses(cropId);
		return CropDetails.of(crop, varieties, priceEntries, cropProcesses);
	}
}
