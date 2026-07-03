package com.nicico.copper.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EGrantType {
	View(1),
	New(2),
	Edit(3),
	Delete(4),
	Print(5),
	Owner(6);

	// ------------------------------

	private final Integer id;
}