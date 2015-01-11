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

import com.dotrow.poo.enums.RelationType;
import com.dotrow.poo.listeners.MoveListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 23/04/13 10:33 PM
 * Desc:
 */

public abstract class Relation extends Figure implements MoveListener {

	private RelationType type;
	private String toClass;
	private Clase from;
	private Clase to;
	private Point a;
	private Point b;

	public Relation() {
	}

	public String getToClass() {
		if( to != null ) {
			toClass = to.getNombre();
		}
		return toClass;
	}

	public void setToClass( String toClass ) {
		this.toClass = toClass;
	}

	public Clase getFrom() {
		return from;
	}

	public void setFrom( Clase from ) {
		this.from = from;
	}

	public Clase getTo() {
		return to;
	}

	public void setTo( Clase to ) {
		this.to = to;
	}

	public RelationType getType() {
		return type;
	}

	public void setType( RelationType type ) {
		this.type = type;
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	@Override
	public void init() {
		Point p1 = getFrom().getBounds().getLocation();
		Point p2 = getTo().getBounds().getLocation();
		int w = getFrom().getBounds().width;
		int h = getFrom().getBounds().height;

		Point a = new Point(p1.x < p2.x ? p1.x : p2.x, p1.y < p2.y ? p1.y : p2.y);
		Point b = new Point(p1.x >= p2.x ? p1.x : p2.x, p1.y >= p2.y ? p1.y : p2.y);

		Dimension dimension = new Dimension(Math.abs(a.x - b.x) + w, Math.abs(a.y - b.y) + h);

		Rectangle r = new Rectangle();
		r.setSize(dimension);
		r.setLocation(a);
		setBounds(r);
	}

	@Override
	public void onMove( Figure source, Point from, Point to ) {
		init();
		repaint();
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		if( SwingUtilities.isRightMouseButton(e) ) {
			from.removeRelacion(this);
			from.removeMoveListener(this);
			from.getUi().removeFigura(this);
		}
	}

	@Override
	public void pinta( Graphics g ) {
		Graphics2D g2 = (Graphics2D) g;
		Dimension dimension = getBounds().getSize();

		Point p1 = getFrom().getBounds().getLocation();
		Point p2 = getTo().getBounds().getLocation();
		int w = getFrom().getBounds().width;
		int h = getFrom().getBounds().height;

		Point2D[] aux1 = new Point2D[4];
		Point2D[] aux2 = new Point2D[4];
		if( ( p2.x < p1.x && p2.y >= p1.y ) || p2.y < p1.y && p2.x >= p1.x ) {
			aux1[0] = new Point(dimension.width - w / 2, 0);    // top
			aux1[1] = new Point(dimension.width - w / 2, h);    // bottom
			aux1[2] = new Point(dimension.width, h / 2);        // right
			aux1[3] = new Point(dimension.width - w, h / 2);    // left

			aux2[0] = new Point(w / 2, dimension.height - h);
			aux2[1] = new Point(w / 2, dimension.height);
			aux2[2] = new Point(w, dimension.height - h / 2);
			aux2[3] = new Point(0, dimension.height - h / 2);
		} else {
			aux1[0] = new Point(w / 2, 0);
			aux1[1] = new Point(w / 2, h);
			aux1[2] = new Point(0, h / 2);
			aux1[3] = new Point(w, h / 2);

			aux2[0] = new Point(dimension.width - w / 2, dimension.height - h);
			aux2[1] = new Point(dimension.width - w / 2, dimension.height);
			aux2[2] = new Point(dimension.width - w, dimension.height - h / 2);
			aux2[3] = new Point(dimension.width, dimension.height - h / 2);
		}

		double min = 100000;
		for( Point2D a2d : aux1 ) {
			for( Point2D b2d : aux2 ) {
				double dis = b2d.distance(a2d);
				if( dis < min ) {
					min = dis;
					a = (Point) a2d;
					b = (Point) b2d;
				}
			}
		}

		g2.setColor(Color.CYAN);

		float dash1[] = { 10.0f };
		Stroke oldStroke = g2.getStroke();
		BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

		g2.setStroke(dashed);
		g2.drawLine(a.x, a.y, b.x, b.y);
		g2.setStroke(oldStroke);
	}
}
