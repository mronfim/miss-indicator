package com.missindicator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissDisplayMode
{
	ABOVE_TARGET("Above target"),
	ABOVE_PLAYER("Above player"),
	SCREEN_CENTER("Screen center");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
