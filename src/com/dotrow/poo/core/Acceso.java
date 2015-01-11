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

package com.dotrow.poo.core;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 26/05/13 02:06 PM
 * Desc:
 */

public enum Acceso {
	/**
	 * The PUBLIC ACCESS.
	 */
	PUBLIC("public"),
	/**
	 * The PRIVATE ACCESS.
	 */
	PRIVATE("private"),
	/**
	 * The PROTECTED ACCESS.
	 */
	PROTECTED("protected"),
	/**
	 * The DEFAULT ACCESS.
	 */
	DEFAULT("");

	private String label;

	private Acceso( String label ) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}
