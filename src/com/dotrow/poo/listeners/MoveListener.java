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

package com.dotrow.poo.listeners;

import com.dotrow.poo.core.Figure;

import java.awt.*;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 24/05/13 08:41 PM
 * Desc:
 */

public interface MoveListener {
	public void onMove( Figure source, Point from, Point to );
}
