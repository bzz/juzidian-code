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

package org.juzidian.core;

import java.util.Collection;
import java.util.List;

import org.juzidian.pinyin.PinyinSyllable;

/**
 * A searchable Chinese {@link DictionaryEntry} data store.
 */
public interface DictionaryDataStore {

	/**
	 * Add a collection of entries to the datastore.
	 * 
	 * @param entries a collection of {@link DictionaryEntry}.
	 */
	void add(Collection<DictionaryEntry> entries);

	/**
	 * Find dictionary entries in the data store that begin with the given
	 * pinyin syllables.
	 * 
	 * @param pinyin a sequence of {@link PinyinSyllable}.
	 * @param limit the maximum number of entries to return.
	 * @param offset the number of search results to skip.
	 * @param canceller a {@link SearchCanceller} (may be {@code null}).
	 * @return a list of {@link DictionaryEntry}.
	 * @throws IllegalArgumentException if limit or offset are negative.
	 */
	List<DictionaryEntry> findPinyin(List<PinyinSyllable> pinyin, long limit, long offset, SearchCanceller canceller);

	/**
	 * Find dictionary entries in the data store that begin with the given
	 * Chinese characters.
	 * 
	 * @param chineseCharacters Chinese character text to find.
	 * @param limit the maximum number of entries to return.
	 * @param offset the number of search results to skip.
	 * @param canceller a {@link SearchCanceller} (may be {@code null}).
	 * @return a list of {@link DictionaryEntry}.
	 * @throws IllegalArgumentException if limit or offset are negative.
	 */
	List<DictionaryEntry> findChinese(String chineseCharacters, long limit, long offset, SearchCanceller canceller);

	/**
	 * Find dictionary entries in the data store that contain the given English
	 * text.
	 * 
	 * @param englishWords English definition text to find.
	 * @param limit the maximum number of entries to return.
	 * @param offset the number of search results to skip.
	 * @param canceller a {@link SearchCanceller} (may be {@code null}).
	 * @return a list of {@link DictionaryEntry}.
	 * @throws IllegalArgumentException if limit or offset are negative.
	 */
	List<DictionaryEntry> findDefinitions(String englishWords, long limit, long offset, SearchCanceller canceller);

}
