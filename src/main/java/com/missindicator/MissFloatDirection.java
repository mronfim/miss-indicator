package com.missindicator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissFloatDirection
{
	UP("Up"),
	DOWN("Down"),
	LEFT("Left"),
	RIGHT("Right"),
	NONE("None (static)");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
