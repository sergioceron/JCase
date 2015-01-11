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

import com.dotrow.poo.ClassManager;
import com.dotrow.poo.Main;
import com.dotrow.poo.enums.ElementType;
import com.dotrow.poo.gui.AttributeForm;
import com.dotrow.poo.gui.MethodForm;
import com.dotrow.poo.listeners.ItemListener;
import com.dotrow.poo.listeners.MoveListener;
import com.dotrow.poo.utils.RegexPatternFormatter;
import com.dotrow.poo.utils.UIList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Clase extends Figure implements PropertyChangeListener, ActionListener, ItemListener {

	private static final long serialVersionUID = -4208854708924194084L;

	private String nombre;
	private List<Atributo> atributos = new ArrayList<Atributo>();
	private List<Metodo> metodos = new ArrayList<Metodo>();
	private List<Relation> relaciones = new ArrayList<Relation>();

	// GUI
	private JFormattedTextField fNombre;
	private JPanel methodsWrapper = new JPanel();
	private JPanel methodsTools = new JPanel();
	private UIList<Metodo> methodsList = new UIList<Metodo>();
	private JPanel attributesWrapper = new JPanel();
	private JPanel attributesTools = new JPanel();
	private UIList<Atributo> attributesList = new UIList<Atributo>();
	private JButton addAttribute = new JButton("+");
	private JButton addMethod = new JButton("+");

	private Border marginBorder = new EmptyBorder(5, 5, 5, 5);

	private Main ui;

	/**
	 * Consutructor por defecto usado en la serializacion
	 */
	public Clase() {
		Pattern noVowels = Pattern.compile("([a-zA-Z_$][a-zA-Z\\d_$]*\\.)*[a-zA-Z_$][a-zA-Z\\d_$]*", Pattern.CASE_INSENSITIVE);
		RegexPatternFormatter noVowelFormatter = new RegexPatternFormatter(noVowels);
		noVowelFormatter.setAllowsInvalid(false);

		fNombre = new JFormattedTextField(noVowelFormatter);
		fNombre.setCaretColor(Color.white);
		fNombre.setCaretPosition(0);
		fNombre.setOpaque(true);

		this.setElementType(ElementType.CLASE);
		this.setDraggable(true);

		addAttribute.setMargin(new Insets(0, 0, 0, 0));
		addAttribute.setBackground(Color.black);
		addAttribute.setForeground(Color.cyan);
		addAttribute.setBorder(new LineBorder(Color.cyan, 1));

		addMethod.setMargin(new Insets(0, 0, 0, 0));
		addMethod.setBackground(Color.black);
		addMethod.setForeground(Color.cyan);
		addMethod.setBorder(new LineBorder(Color.cyan, 1));

		methodsList.addListener(this);
		attributesList.addListener(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos( List<Atributo> atributos ) {
		this.atributos = atributos;
	}

	public void addAtributo( Atributo atributo ) {
		atributos.add(atributo);
	}

	public List<Relation> getRelaciones() {
		return relaciones;
	}

	public List<Metodo> getMetodos() {
		return metodos;
	}

	public void setMetodos( List<Metodo> metodos ) {
		this.metodos = metodos;
	}

	public void setRelaciones( List<Relation> relaciones ) {
		this.relaciones = relaciones;
	}

	public void addRelacion( Relation relation ) {
		this.relaciones.add(relation);
	}

	public void removeRelacion( Relation relation ) {
		this.relaciones.remove(relation);
	}

	public Main getUi() {
		return ui;
	}

	public void setUi( Main ui ) {
		this.ui = ui;
	}

	public void pinta( Graphics g ) {
	}

	public void init() {

		setLayout(new BorderLayout());

		fNombre.setText(nombre);
		fNombre.addPropertyChangeListener("value", this);
		fNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				if( e.getClickCount() == 2 ) {
					fNombre.setEditable(true);
					fNombre.setOpaque(true);
					fNombre.setBackground(new Color(40, 40, 40));
					fNombre.setCaretPosition(0);
					fNombre.setCaretColor(Color.white);
					fNombre.setForeground(Color.white);
				}
			}
		});
		fNombre.setOpaque(false);
		fNombre.setBorder(BorderFactory.createEmptyBorder());
		fNombre.setForeground(Color.cyan);
		fNombre.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.cyan), marginBorder));

		attributesTools.setLayout(new GridLayout(1, 5));
		attributesTools.setOpaque(false);
		attributesTools.add(new JLabel(""));
		attributesTools.add(new JLabel(""));
		attributesTools.add(new JLabel(""));
		attributesTools.add(new JLabel(""));
		attributesTools.add(addAttribute);
		addAttribute.addActionListener(this);

		methodsTools.setLayout(new GridLayout(1, 5));
		methodsTools.setOpaque(false);
		methodsTools.add(new JLabel(""));
		methodsTools.add(new JLabel(""));
		methodsTools.add(new JLabel(""));
		methodsTools.add(new JLabel(""));
		methodsTools.add(addMethod);
		addMethod.addActionListener(this);

		attributesWrapper.setLayout(new BorderLayout());
		attributesWrapper.setOpaque(false);
		attributesWrapper.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.cyan), marginBorder));
		for( Atributo a : atributos ) {
			attributesList.addItem(a);
		}
		attributesWrapper.add(attributesTools, BorderLayout.NORTH);
		attributesWrapper.add(attributesList, BorderLayout.CENTER);


		methodsWrapper.setLayout(new BorderLayout());
		methodsWrapper.setOpaque(false);
		methodsWrapper.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.cyan), marginBorder));
		for( Metodo m : metodos ) {
			methodsList.addItem(m);
		}
		methodsWrapper.add(methodsTools, BorderLayout.NORTH);
		methodsWrapper.add(methodsList, BorderLayout.CENTER);


		add(fNombre, BorderLayout.NORTH);
		add(attributesWrapper, BorderLayout.CENTER);
		add(methodsWrapper, BorderLayout.SOUTH);

		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.cyan));

	}

	public void addMetodo( Metodo metodo ) {
		methodsList.addItem(metodo);
		metodos.add(metodo);
	}

	public void addAttribute( Atributo atributo ) {
		attributesList.addItem(atributo);
		atributos.add(atributo);
	}

	public void updateMetodo( Metodo metodo, int index ) {
		methodsList.updateItem(metodo, index);
		metodos.remove(index);
		metodos.add(metodo);
	}

	public void updateAttribute( Atributo atributo, int index ) {
		attributesList.updateItem(atributo, index);
		atributos.remove(index);
		atributos.add(atributo);
	}

	@Override
	public void propertyChange( PropertyChangeEvent evt ) {
		if( evt.getSource() == fNombre ) {
			nombre = fNombre.getText();
			//fNombre.setEditable(false);
			fNombre.setOpaque(false);
			fNombre.setForeground(Color.cyan);
		}
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource() == addAttribute ) {
			AttributeForm af = new AttributeForm(this, getUi());
			af.setVisible(true);
		} else if( e.getSource() == addMethod ) {
			MethodForm af = new MethodForm(this, getUi());
			af.setVisible(true);
		}
	}

	@Override
	public void onItemDoubleClick( Object source, Object item, int index ) {
		if( source == attributesList ) {
			AttributeForm af = new AttributeForm(this, getUi());
			af.setAtributo((Atributo) item);
			af.setEdit(true);
			af.setEditIndex(index);
			af.setVisible(true);
		} else if( source == methodsList ) {
			MethodForm af = new MethodForm(this, getUi());
			af.setMetodo((Metodo) item);
			af.setEdit(true);
			af.setEditIndex(index);
			af.setVisible(true);
		}
	}

	@Override
	public void onItemRightClick( Object source, Object item, int index ) {
		if( source == attributesList ) {
			atributos.remove(index);
			attributesList.removeItem(index);
		} else if( source == methodsList ) {
			metodos.remove(index);
			methodsList.removeItem(index);
		}
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		if( SwingUtilities.isRightMouseButton(e) ) {
			for( Relation r : relaciones ) {
				getUi().removeFigura(r);
			}
			for( MoveListener ml : getMoveListeners() ) {
				if( ml instanceof Relation ) {
					Relation to = (Relation) ml;
					getUi().removeFigura(to);
				}
			}
			ClassManager.removeClase(this);
			getUi().removeFigura(this);
		}
	}
}
