/*
 * Copyright (C) 2014.  sergio
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

package com.dotrow.poo;

/**
  * -
  *
  * @author Sergio Ceron F.
  * @version rev: %I%
  * @date 2/02/14 10:24 PM
  */
 public class HTTPResponse {
	private int status;
	private int version;

	public int getStatus() {
		return status;
	}

	public void setStatus( int status ) {
		this.status = status;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion( int version ) {
		this.version = version;
	}
}
