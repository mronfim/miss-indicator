package com.missindicator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissBackgroundStyle
{
	NONE("None"),
	SHADOW("Shadow"),
	OUTLINE("Outline"),
	BOX("Box");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
