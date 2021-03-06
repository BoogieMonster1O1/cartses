/*
 * Copyright (c) 2020 BoogieMonster1O1
 *
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
 * WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
 * AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

package io.github.boogiemonster1o1.cartses.entity

import com.chocohead.mm.api.ClassTinkerers
import net.minecraft.entity.vehicle.AbstractMinecartEntity

object MinecartTypes {
	val craftingTable: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "CRAFTING_TABLE")
	val barrel: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "BARREL")
	val enderChest: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "ENDER_CHEST")
	val glowstone: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "GLOWSTONE")
	val redstoneLamp: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "REDSTONE_LAMP")
	val noteBlock: AbstractMinecartEntity.Type = ClassTinkerers.getEnum(classOf[AbstractMinecartEntity.Type], "NOTE_BLOCK")
}
