package com.farm.pedia.crop.domain;

import lombok.Getter;

@Getter
public class CropProcess {

	private final int processOrder;
	private final String task;
	private final String description;

	private CropProcess(int processOrder, String task, String description) {
		this.processOrder = processOrder;
		this.task = task;
		this.description = description;
	}
}
