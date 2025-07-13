package com.mori.api.domain.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GiftShop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mainName;
	private String subName;
	private String description;

	private String roadAddress;
	private String detailAddress;
	private String zipCode;

	private String phoneNumber;
}
