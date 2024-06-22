package com.farm.pedia.crop.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class PriceEntry {

	private final LocalDate priceDate;
	private final Double price;

	private PriceEntry(LocalDate priceDate, Double price) {
		this.priceDate = priceDate;
		this.price = price;
	}
}