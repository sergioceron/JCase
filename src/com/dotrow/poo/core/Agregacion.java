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
 * Date: 26/05/13 02:06 PM
 * Desc:
 */

import com.dotrow.poo.enums.RelationType;
import com.dotrow.poo.utils.Arrow;

import java.awt.*;

/**
 * The type Agregacion.
 */
public class Agregacion extends Relation {

	/**
	 * Instantiates a new Agregacion.
	 */
	public Agregacion() {
		super.setColor("ffff00");
		super.setType(RelationType.AGGREGATION);
	}

	@Override
	public void pinta( Graphics g ) {
		super.pinta(g);
		Graphics2D g2 = (Graphics2D) g;

		Point p1 = getFrom().getBounds().getLocation();
		Point p2 = getTo().getBounds().getLocation();

		Arrow arrow;
		if( p1.y > p2.y ) {
			arrow = new Arrow(getA(), getB(), 15, false);
		} else {
			arrow = new Arrow(getB(), getA(), 15, false);
		}
		arrow.setBidirectional(true);
		arrow.draw(g2);
	}

	@Override
	public void init() {
		super.init();
	}

}
