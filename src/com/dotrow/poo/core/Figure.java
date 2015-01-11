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

import com.dotrow.poo.enums.ElementType;
import com.dotrow.poo.listeners.MoveListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 26/05/13 02:06 PM
 * Desc: Componente que puede ser serializado por la api de json.
 *       mx.ipn.cic.com.dotrow.poo.core.Clase padre de los elementos a dibujar
 *       A su vez extiende de la clase swing JPanel
 */

public abstract class Figure extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 6401254218116245370L;

	private Dimension dimension;
	private ElementType elementType;
	private Point posicion;
	private String color;
	private boolean draggable = false;

	private Border oldBorder = BorderFactory.createEmptyBorder();

	private MouseEvent aux;

	// Listeners
	private List<MoveListener> moveListeners = new ArrayList<MoveListener>();

	/**
	 * Constructor principal de la clase mx.ipn.cic.com.dotrow.poo.core.Figure
	 *
	 * @param dimension
	 * 		Es el ancho y alto del componente, este esta definido en pixeles
	 * @param posicion
	 * 		Es la ubicacion (x,y) del componente en el mapa
	 */
	public Figure( Dimension dimension, Point posicion ) {
		this.dimension = dimension;
		this.posicion = posicion;
		this.setSize(new Dimension(dimension.width + 1, dimension.height + 1));
		this.setOpaque(false);
	}

	/**
	 * Constructor por defecto usado por la api de json para serializar
	 */
	public Figure() {
		this.setOpaque(false);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension( Dimension dimension ) {
		this.dimension = dimension;
	}

	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType( ElementType elementType ) {
		this.elementType = elementType;
	}

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion( Point posicion ) {
		this.posicion = posicion;
	}

	public String getColor() {
		return color;
	}

	public void setColor( String color ) {
		this.color = color;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable( boolean draggable ) {
		this.draggable = draggable;
	}

	public List<MoveListener> getMoveListeners() {
		return moveListeners;
	}

	public void addMoveListener( MoveListener moveListener ) {
		moveListeners.add(moveListener);
	}

	public void removeMoveListener( MoveListener moveListener ) {
		moveListeners.remove(moveListener);
	}

	public void mousePressed( MouseEvent e ) {
		aux = e;
	}

	public void mouseEntered( MouseEvent e ) {
		if( draggable ) {
			this.oldBorder = this.getBorder();
			this.setBorder(BorderFactory.createLineBorder(Color.yellow));
			this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
	}

	public void mouseExited( MouseEvent e ) {
		if( draggable ) {
			this.setBorder(oldBorder);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			this.repaint();
		}
	}

	public void mouseDragged( MouseEvent e ) {
		if( draggable ) {
			Point pPoint = getLocation();
			int x = pPoint.x - aux.getX() + e.getX();
			int y = pPoint.y - aux.getY() + e.getY();
			Point nPoint = new Point(x, y);
			setLocation(x, y);
			setPosicion(nPoint);
			setDimension(getBounds().getSize());
			for( MoveListener moveListener : moveListeners ) {
				moveListener.onMove(this, pPoint, nPoint);
			}
		}
	}

	public void mouseReleased( MouseEvent e ) {
	}

	public void mouseMoved( MouseEvent e ) {
	}

	public void mouseClicked( MouseEvent e ) {
	}

	/**
	 * mx.ipn.cic.com.dotrow.poo.core.Metodo que sobreescribe la funcion paintComponent de la clase JPanel y
	 * se encarga de pintar el componente en la pantalla
	 */
	public void paintComponent( Graphics g ) {

		super.paintComponent(g);
		Color old = g.getColor();
		try {
			g.setColor(parseColor(color));
		} catch( Exception e ) {
		}
		this.pinta(g);
		g.setColor(old);
	}

	public Color parseColor( String color ) {
		String a = color.substring(0, 2);
		String b = color.substring(2, 4);
		String c = color.substring(4, 6);
		return new Color(Integer.parseInt(a, 16), Integer.parseInt(b, 16), Integer.parseInt(c, 16));
	}

	public abstract void pinta( Graphics g );

	public abstract void init();
}
