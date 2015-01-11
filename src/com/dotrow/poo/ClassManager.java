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

package com.dotrow.poo;

import com.dotrow.poo.core.Clase;
import com.dotrow.poo.core.Figure;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Class manager.
 */
public class ClassManager {
	private static final List<Clase> CLASES = new ArrayList<Clase>();

	/**
	 * Add clases.
	 *
	 * @param figures the figures
	 */
	public static void addClases( List<Figure> figures ) {
		for( Figure f : figures ) {
			if( f instanceof Clase ) {
				CLASES.add((Clase) f);
			}
		}
	}

	/**
	 * Add clase.
	 *
	 * @param clase the clase
	 */
	public static void addClase( Clase clase ) {
		CLASES.add(clase);
	}

	/**
	 * Remove clase.
	 *
	 * @param clase the clase
	 */
	public static void removeClase( Clase clase ) {
		CLASES.remove(clase);
	}

	/**
	 * Clear all.
	 */
	public static void clearAll() {
		CLASES.clear();
	}

	/**
	 * Gets clase by name.
	 *
	 * @param name the name
	 * @return the clase by name
	 */
	public static Clase getClaseByName( String name ) {
		for( Clase clase : CLASES ) {
			if( clase.getNombre() != null ) {
				if( clase.getNombre().equals(name) ) {
					return clase;
				}
			}
		}
		return null;
	}

	/**
	 * Gets clases.
	 *
	 * @return the clases
	 */
	public static List<Clase> getClases() {
		return CLASES;
	}
}
