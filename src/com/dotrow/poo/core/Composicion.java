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
import com.dotrow.poo.utils.Arrow;

import java.awt.*;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 28/04/13 10:50 AM
 * Desc:
 */public class Composicion extends Relation {

	public Composicion() {
		super.setColor("ffff00");
		super.setType(RelationType.COMPOSITION);
	}

	@Override
	public void pinta( Graphics g ) {
		super.pinta(g);
		Graphics2D g2 = (Graphics2D) g;

		Point p1 = getFrom().getBounds().getLocation();
		Point p2 = getTo().getBounds().getLocation();

		Arrow arrow;
		if( p1.y > p2.y ) {
			arrow = new Arrow(getA(), getB(), 15, true);
		} else {
			arrow = new Arrow(getB(), getA(), 15, true);
		}
		arrow.setBidirectional(true);
		arrow.draw(g2);
	}

	@Override
	public void init() {
		super.init();
	}

}
