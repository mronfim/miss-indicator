package com.missindicator;

import java.awt.Font;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissFontStyle
{
	DEFAULT("Default", Font.PLAIN),
	BOLD("Bold", Font.BOLD),
	ITALIC("Italic", Font.ITALIC),
	BOLD_ITALIC("Bold Italic", Font.BOLD | Font.ITALIC);

	private final String name;
	private final int awtStyle;

	@Override
	public String toString()
	{
		return name;
	}
}
