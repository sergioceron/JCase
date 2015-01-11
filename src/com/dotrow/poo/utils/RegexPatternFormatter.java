/*
 * Copyright (C) 2013.  sergio
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dotrow.poo.utils;

import javax.swing.text.DefaultFormatter;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 21/04/13 12:39 PM
 * Desc:
 */

public class RegexPatternFormatter extends DefaultFormatter {

	protected java.util.regex.Matcher matcher;

	public RegexPatternFormatter( java.util.regex.Pattern regex ) {
		setOverwriteMode(false);
		matcher = regex.matcher(""); // create a Matcher for the regular expression
	}

	public Object stringToValue( String string ) throws java.text.ParseException {
		if( string == null ) return null;
		matcher.reset(string); // set 'string' as the matcher's input

		if( !matcher.matches() ) // Does 'string' match the regular expression?
			throw new java.text.ParseException("does not match regex", 0);

		// If we get this far, then it did match.
		return super.stringToValue(string); // will honor the 'valueClass' property
	}
}