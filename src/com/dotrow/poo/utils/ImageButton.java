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

import com.dotrow.poo.core.Figure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 5/05/13 10:53 AM
 * Desc:
 */

public class ImageButton extends Figure {

	private BufferedImage imageNormal;
	private BufferedImage imageHover;
	private BufferedImage image;

	private URL urlImageNormal;
	private URL urlImageHover;

	public ImageButton( URL urlImageNormal, URL urlImageHover ) {
		this.urlImageNormal = urlImageNormal;
		this.urlImageHover = urlImageHover;
	}

	@Override
	public void pinta( Graphics g ) {
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public void init() {
		try {
			imageNormal = ImageIO.read(urlImageNormal);
			imageHover = ImageIO.read(urlImageHover);
			int width = imageNormal.getWidth();
			int height = imageNormal.getHeight();
			setPreferredSize(new Dimension(width + 20, height));
			image = imageNormal;
		} catch( IOException ex ) {
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
		image = imageHover;
		repaint();
	}

	@Override
	public void mouseExited( MouseEvent e ) {
		image = imageNormal;
		repaint();
	}

}
