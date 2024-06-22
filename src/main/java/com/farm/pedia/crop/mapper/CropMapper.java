package com.farm.pedia.crop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.farm.pedia.crop.domain.Crop;
import com.farm.pedia.crop.domain.CropProcess;
import com.farm.pedia.crop.domain.PriceEntry;
import com.farm.pedia.crop.domain.Variety;

@Mapper
public interface CropMapper {

	Crop findCropDetails(@Param("cropId") Long cropId);

	List<Variety> findVarieties(@Param("cropId") Long cropId);

	List<PriceEntry> findPriceEntries(@Param("cropId") Long cropId);

	List<CropProcess> findCropProcesses(@Param("cropId") Long cropId);
}
