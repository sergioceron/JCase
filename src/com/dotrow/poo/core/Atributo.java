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
 *     along with this program.  If not, see <http://www.gnu.org/licens
 */

package com.dotrow.poo.core;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 16/04/13 10:15 PM
 * Desc:
 */

public class Atributo {
	private Acceso acceso;
	private Modificador modificador;
	private String tipo;
	private String nombre;
	private boolean array;

	/**
	 * Instantiates a new Atributo.
	 */
	public Atributo() {
		this.acceso = Acceso.DEFAULT;
		this.modificador = Modificador.NONE;
	}

	/**
	 * Instantiates a new Atributo.
	 *
	 * @param acceso the acceso
	 * @param modificador the modificador
	 * @param nombre the nombre
	 * @param tipo the tipo
	 */
	public Atributo( Acceso acceso, Modificador modificador, String nombre, String tipo ) {
		this.acceso = acceso;
		this.modificador = modificador;
		this.nombre = nombre;
		this.tipo = tipo;
		this.array = false;
	}

	/**
	 * Gets acceso.
	 *
	 * @return the acceso
	 */
	public Acceso getAcceso() {
		return acceso;
	}

	/**
	 * Sets acceso.
	 *
	 * @param acceso the acceso
	 */
	public void setAcceso( Acceso acceso ) {
		this.acceso = acceso;
	}

	/**
	 * Gets modificador.
	 *
	 * @return the modificador
	 */
	public Modificador getModificador() {
		return modificador;
	}

	/**
	 * Sets modificador.
	 *
	 * @param modificador the modificador
	 */
	public void setModificador( Modificador modificador ) {
		this.modificador = modificador;
	}

	/**
	 * Gets nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets nombre.
	 *
	 * @param nombre the nombre
	 */
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	/**
	 * Gets tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets tipo.
	 *
	 * @param tipo the tipo
	 */
	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	/**
	 * Is array.
	 *
	 * @return the boolean
	 */
	public boolean isArray() {
		return array;
	}

	/**
	 * Sets array.
	 *
	 * @param array the array
	 */
	public void setArray( boolean array ) {
		this.array = array;
	}

	@Override
	public String toString() {
		return String.format("%c %s %s %s %s",
				getAcceso() == Acceso.PRIVATE ? '-' : '+',
				getAcceso(),
				getModificador(), getTipo(), getNombre());
	}

}
