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

package io.github.boogiemonster1o1.cartses.screen

import io.github.boogiemonster1o1.cartses.screen.Note.ids
import net.minecraft.text.{Text, TranslatableText}
import net.minecraft.util.collection.IdList
import net.minecraft.util.math.MathHelper

object Note {
	private val ids: IdList[Note] = new IdList[Note](25)
	val fSharpLower: Note = new Note(0, new TranslatableText("cartses.note.lower_f_sharp"))
	val gLower: Note = new Note(1, new TranslatableText("cartses.note.lower_f_sharp"))
	val gSharpLower: Note = new Note(2, new TranslatableText("cartses.note.lower_f_sharp"))
	val aLower: Note = new Note(3, new TranslatableText("cartses.note.lower_f_sharp"))
	val aSharpLower: Note = new Note(4, new TranslatableText("cartses.note.lower_f_sharp"))
	val bLower: Note = new Note(5, new TranslatableText("cartses.note.lower_f_sharp"))
	val cMiddle: Note = new Note(6, new TranslatableText("cartses.note.lower_f_sharp"))
	val cSharpMiddle: Note = new Note(7, new TranslatableText("cartses.note.lower_f_sharp"))
	val dMiddle: Note = new Note(8, new TranslatableText("cartses.note.lower_f_sharp"))
	val dSharpMiddle: Note = new Note(9, new TranslatableText("cartses.note.lower_f_sharp"))
	val eMiddle: Note = new Note(10, new TranslatableText("cartses.note.lower_f_sharp"))
	val fMiddle: Note = new Note(11, new TranslatableText("cartses.note.lower_f_sharp"))
	val fSharpMiddle: Note = new Note(12, new TranslatableText("cartses.note.lower_f_sharp"))
	val gMiddle: Note = new Note(13, new TranslatableText("cartses.note.lower_f_sharp"))
	val gSharpMiddle: Note = new Note(14, new TranslatableText("cartses.note.lower_f_sharp"))
	val aMiddle: Note = new Note(15, new TranslatableText("cartses.note.lower_f_sharp"))
	val aSharpMiddle: Note = new Note(16, new TranslatableText("cartses.note.lower_f_sharp"))
	val bMiddle: Note = new Note(17, new TranslatableText("cartses.note.lower_f_sharp"))
	val cHigher: Note = new Note(18, new TranslatableText("cartses.note.lower_f_sharp"))
	val cSharpHigher: Note = new Note(19, new TranslatableText("cartses.note.lower_f_sharp"))
	val dHigher: Note = new Note(20, new TranslatableText("cartses.note.lower_f_sharp"))
	val dSharpHigher: Note = new Note(21, new TranslatableText("cartses.note.lower_f_sharp"))
	val eHigher: Note = new Note(22, new TranslatableText("cartses.note.lower_f_sharp"))
	val fHigher: Note = new Note(23, new TranslatableText("cartses.note.lower_f_sharp"))
	val fSharpHigher: Note = new Note(24, new TranslatableText("cartses.note.lower_f_sharp"))

	def byId(int: Int): Note = {
		ids.get(MathHelper.clamp(int, 0, 24))
	}
}

class Note(val value: Int, val title: Text) extends Iterator[Note] {
	ids.add(this)

	def asFloat: Float = {
		Math.pow(2, (value - 12) / 12.0).asInstanceOf[Float]
	}

	override def hasNext: Boolean = true

	override def next: Note = {
		if (value == 3) return ids.get(0)
		ids.get(value)
	}
}
