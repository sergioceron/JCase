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

package com.dotrow.poo.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 26/05/13 02:06 PM
 * Desc:
 */

public class Arrow {
	private int x1, y1, x2, y2;
	private int size = 4;
	private boolean fill = false;
	private boolean bidirectional = false;

	public Arrow( Point p1, Point p2, int size, boolean fill ) {
		this.x1 = p1.x;
		this.x2 = p2.x;
		this.y1 = p1.y;
		this.y2 = p2.y;
		this.size = size;
		this.fill = fill;

	}

	public boolean isBidirectional() {
		return bidirectional;
	}

	public void setBidirectional( boolean bidirectional ) {
		this.bidirectional = bidirectional;
	}

	public void draw( Graphics g1 ) {
		Graphics2D g = (Graphics2D) g1;

		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx * dx + dy * dy);
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g.transform(at);

		Polygon arrow = new Polygon(new int[]{ len, len - size, len - 2 * size, len - size, len },
				new int[]{ 0, -size + 5, 0, size - 5, 0 }, 5);

		if( !isBidirectional() ) {
			arrow = new Polygon(new int[]{ len, len - size, len - size, len },
					new int[]{ 0, -size + 5, size - 5, 0 }, 4);
		}

		// Draw horizontal arrow starting in (0, 0)
		g.drawLine(0, 0, len, 0);
		if( fill ) {
			g.fillPolygon(arrow);
		} else {
			g.drawPolygon(arrow);
		}
	}
}
