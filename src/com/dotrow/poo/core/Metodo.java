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

public class Metodo {
	private Acceso acceso;
	private Modificador modificador;
	private String tipo;
	private String nombre;
	private String parameters;

	public Metodo() {
		this.acceso = Acceso.DEFAULT;
		this.modificador = Modificador.NONE;
	}

	public Metodo( Acceso acceso, Modificador modificador, String nombre, String tipo ) {
		this.acceso = acceso;
		this.modificador = modificador;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public Acceso getAcceso() {
		return acceso;
	}

	public void setAcceso( Acceso acceso ) {
		this.acceso = acceso;
	}

	public Modificador getModificador() {
		return modificador;
	}

	public void setModificador( Modificador modificador ) {
		this.modificador = modificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters( String parameters ) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return String.format("%c %s %s %s(%s):%s",
				getAcceso() == Acceso.PRIVATE ? '-' : '+',
				getAcceso(),
				getModificador(), getNombre(), getParameters(), getTipo());
	}

}
