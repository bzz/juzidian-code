/*
 * Copyright Nathan Jones 2012
 * 
 * This file is part of Juzidian.
 *
 * Juzidian is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Juzidian is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Juzidian.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.juzidian.core.build;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.juzidian.cedict.CedictEntry;
import org.juzidian.cedict.CedictLoadHandler;
import org.juzidian.cedict.CedictPinyinSyllable;

/**
 * A {@link CedictLoadHandler} that builds up a complete set of valid Pinyin
 * syllables according to the contents of the CEDict data file.
 * <p>
 * Known invalid CEDict Pinyin syllables, such as "xx" (no pronunciation), "r"
 * (Erhua suffix) and "m" (the interjection "呣"), are ignored. Instances of "u:"
 * are replaced with "ü".
 */
final class PinyinSyllableCaptor implements CedictLoadHandler {

	private final Set<String> uniquePinyin = new TreeSet<String>();

	@Override
	public void entryLoaded(final CedictEntry cedictEntry) {
		for (final CedictPinyinSyllable pinyinSyllable : cedictEntry.getPinyinSyllables()) {
			final String letters = pinyinSyllable.getLetters();
			if (isKnownInvalidCedictSyllable(letters)) {
				continue;
			}
			this.uniquePinyin.add(letters.toLowerCase().replace("u:", "ü"));
		}
	}

	private boolean isKnownInvalidCedictSyllable(final String letters) {
		return "xx".equals(letters) || "r".equals(letters) || "m".equals(letters);
	}

	public Set<String> getUniquePinyinSyllables() {
		return Collections.unmodifiableSet(this.uniquePinyin);
	}

	@Override
	public void loadingStarted() {
	}

	@Override
	public void loadingFinished() {
	}

}
