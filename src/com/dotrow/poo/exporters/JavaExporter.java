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

package com.dotrow.poo.exporters;

import com.dotrow.poo.core.*;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 2/06/13 11:30 AM
 * Desc:
 */

public class JavaExporter {

	private static final String CLASS_TEMPLATE =
			"public class %s %s{\n" +
					"%s\n" +
					"%s\n" +
					"%s\n" +
					"}\n";

	private static final String METHOD_TEMPLATE =
			"%s %s %s %s(%s) {\n" +
					"\n" +
					"}\n";

	private static final String PROPERTY_TEMPLATE =
			"%s %s %s %s;\n";

	private List<Clase> clases = new ArrayList<Clase>();
	private String code = "";

	/**
	 * Instantiates a new Java exporter.
	 *
	 * @param clases the clases
	 */
	public JavaExporter( List<Clase> clases ) {
		this.clases = clases;
	}

	/**
	 * Generate void.
	 */
	public void generate() {
		for( Clase clase : clases ) {
			String claseStr = String.format(CLASS_TEMPLATE, clase.getNombre(), inheritance(clase),
					constructor(clase), properties(clase), methods(clase));
			try {
				DataOutputStream fw = new DataOutputStream(new FileOutputStream(new File(clase.getNombre() + ".java")));
				fw.writeBytes(claseStr);
				fw.close();
			} catch( IOException e ) {
				e.printStackTrace();
			}

			code += claseStr + "\n\n";
		}
	}

	private String inheritance( Clase clase ) {
		String str = "";
		for( Relation relation : clase.getRelaciones() ) {
			if( relation instanceof Herencia ) {
				str = "extends " + relation.getToClass();
			}
		}
		return str;
	}

	private String constructor( Clase clase ) {
		String tpl = "public %s() { \n" +
				"}\n";
		return String.format(tpl, clase.getNombre());
	}

	private String properties( Clase clase ) {
		StringBuilder sb = new StringBuilder();
		for( Atributo atributo : clase.getAtributos() ) {
			sb.append(String.format(PROPERTY_TEMPLATE,
					atributo.getAcceso(), atributo.getModificador(),
					atributo.getTipo(), atributo.getNombre()));
		}
		return sb.toString();
	}

	private String methods( Clase clase ) {
		StringBuilder sb = new StringBuilder();
		for( Metodo metodo : clase.getMetodos() ) {
			sb.append(String.format(METHOD_TEMPLATE,
					metodo.getAcceso(), metodo.getModificador(),
					metodo.getTipo(), metodo.getNombre(), metodo.getParameters()));
		}
		return sb.toString();
	}

	/**
	 * Gets text code.
	 *
	 * @return the text raw code
	 */
	public String getCode() {
		return code;
	}
}
