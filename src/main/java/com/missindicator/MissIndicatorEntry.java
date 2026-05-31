/*
 * Copyright (c) 2024, Miss Indicator Plugin
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.missindicator;

import lombok.Getter;
import net.runelite.api.Actor;

/**
 * Immutable snapshot of a single miss event.
 * Config values are captured at creation time so that live config changes do not
 * affect indicators that are already in flight — the same pattern used by
 * Customizable XP Drops for its predicted-hit entries.
 */
@Getter
public class MissIndicatorEntry
{
	private final Actor actor;
	private final int spawnTick;

	/**
	 * Wall-clock spawn time in milliseconds. The overlay drives its float/fade
	 * animation off this rather than {@link #spawnTick}, so motion updates every
	 * rendered frame instead of once per 600ms game tick (which looked choppy).
	 */
	private final long spawnMillis;

	// Snapshot of config values at the moment the miss occurred
	private final String displayText;
	private final MissDisplayMode displayMode;
	private final int durationTicks;
	private final MissFloatDirection floatDirection;
	private final int floatDistance;
	private final boolean fadeOut;

	MissIndicatorEntry(Actor actor, int spawnTick, MissIndicatorConfig config)
	{
		this.actor        = actor;
		this.spawnTick    = spawnTick;
		this.spawnMillis  = System.currentTimeMillis();
		this.displayText  = config.missText().isEmpty() ? "MISS" : config.missText();
		this.displayMode  = config.displayMode();
		this.durationTicks = config.displayDurationTicks();
		this.floatDirection = config.floatDirection();
		this.floatDistance  = config.floatDistance();
		this.fadeOut        = config.fadeOut();
	}
}
