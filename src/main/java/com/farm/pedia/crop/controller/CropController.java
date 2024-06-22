package com.farm.pedia.crop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.pedia.crop.domain.CropDetails;
import com.farm.pedia.crop.service.CropService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/crop")
@RequiredArgsConstructor
public class CropController {

	private final CropService cropService;

	@GetMapping("/{cropId}")
	public CropDetails findCrop(@PathVariable Long cropId) {
		return cropService.findCropDetails(cropId);
	}
}
