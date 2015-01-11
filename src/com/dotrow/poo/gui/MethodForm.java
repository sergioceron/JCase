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

package com.dotrow.poo.gui;

import com.dotrow.poo.Main;
import com.dotrow.poo.core.Acceso;
import com.dotrow.poo.core.Clase;
import com.dotrow.poo.core.Metodo;
import com.dotrow.poo.core.Modificador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project Name: ${PROJECT_NAME}
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 21/04/13 06:19 PM
 * Desc:
 */

public class MethodForm extends JFrame implements ActionListener {

	private JComboBox acceso;
	private JComboBox modificador;
	private JTextField tipo;
	private JTextField nombre;
	private JTextField argumentos;
	private JButton submit;
	private Clase clase;
	private Main ui;

	private boolean edit = false;
	private int editIndex = -1;

	public MethodForm( Clase clase, Main ui ) {
		super("Agregar mx.ipn.cic.com.dotrow.poo.core.Metodo");
		setLayout(new GridLayout(6, 1));

		this.clase = clase;
		this.ui = ui;

		acceso = new JComboBox();
		acceso.addItem(Acceso.DEFAULT);
		acceso.addItem(Acceso.PUBLIC);
		acceso.addItem(Acceso.PRIVATE);
		acceso.addItem(Acceso.PROTECTED);

		modificador = new JComboBox();
		modificador.addItem(Modificador.NONE);
		modificador.addItem(Modificador.ABSTRACT);
		modificador.addItem(Modificador.FINAL);
		modificador.addItem(Modificador.STATIC);
		modificador.addItem(Modificador.VOLATILE);

		tipo = new JTextField();
		nombre = new JTextField();
		argumentos = new JTextField();

		submit = new JButton("Agregar");

		add(new JLabel("mx.ipn.cic.com.dotrow.poo.core.Acceso"));
		add(acceso);
		add(new JLabel("mx.ipn.cic.com.dotrow.poo.core.Modificador"));
		add(modificador);
		add(new JLabel("Tipo"));
		add(tipo);
		add(new JLabel("Nombre"));
		add(nombre);
		add(new JLabel("Argumentos"));
		add(argumentos);
		add(new JLabel(""));
		add(submit);

		submit.addActionListener(this);


		setBounds(100, 100, 300, 180);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit( boolean edit ) {
		this.edit = edit;
	}

	public void setEditIndex( int editIndex ) {
		this.editIndex = editIndex;
	}

	public void setMetodo( Metodo metodo ) {
		acceso.setSelectedItem(metodo.getAcceso());
		modificador.setSelectedItem(metodo.getModificador());
		tipo.setText(metodo.getTipo());
		nombre.setText(metodo.getNombre());
		argumentos.setText(metodo.getParameters());
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource() == submit ) {
			Metodo metodo = new Metodo();
			metodo.setAcceso((Acceso) acceso.getSelectedItem());
			metodo.setModificador((Modificador) modificador.getSelectedItem());
			metodo.setTipo(tipo.getText());
			metodo.setNombre(nombre.getText());
			metodo.setParameters(argumentos.getText());

			if( !edit ) {
				clase.addMetodo(metodo);
			} else {
				clase.updateMetodo(metodo, editIndex);
			}

			dispose();
		}
	}
}
