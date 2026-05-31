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
 * Tracks a single swing the local player made against a target, from the moment
 * the attack animation initiates until we can decide whether it landed.
 *
 * <p>How a hit/miss is decided: we snapshot the player's running damage XP (derived
 * from Hitpoints XP) when the attack starts ({@link #xpAtAttack}). When the attack
 * "resolves" (see {@link #resolveTick}) we compare against the running total. Any
 * damage XP gained in between means damage was dealt — a hit. None gained means the
 * attack dealt a zero — a miss — and the indicator is shown.</p>
 *
 * <p>This mirrors the way Customizable XP Drops infers damage from XP rather than
 * reading hitsplats. Hitpoints XP is used specifically because a magic cast still
 * awards its base XP to the Magic skill on a splash, but never any Hitpoints XP.</p>
 */
@Getter
class PendingAttack
{
	/** The actor that was being attacked when the swing started (NPC, or Player in PvP). */
	private final Actor target;

	/** Game tick on which the attack animation initiated. */
	private final int attackTick;

	/** Running damage-XP total (Hitpoints-derived) captured at the instant of the attack. */
	private final long xpAtAttack;

	/**
	 * Tick on which we evaluate hit-vs-miss. By this tick, a hit's Hitpoints XP has
	 * been delivered (XP is awarded on the attack tick for every style), so if the
	 * damage-XP total has not increased the attack dealt zero — a miss.
	 */
	private final int resolveTick;

	PendingAttack(Actor target, int attackTick, int resolveTick, long xpAtAttack)
	{
		this.target = target;
		this.attackTick = attackTick;
		this.resolveTick = resolveTick;
		this.xpAtAttack = xpAtAttack;
	}
}
