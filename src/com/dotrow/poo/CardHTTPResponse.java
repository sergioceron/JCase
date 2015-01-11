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

import java.util.List;

/**
 * -
 *
 * @author Sergio Ceron F.
 * @version rev: %I%
 * @date 2/02/14 10:23 PM
 */
public class CardHTTPResponse extends HTTPResponse {
	private List<ContactCard> cards;

	public List<ContactCard> getCards() {
		return cards;
	}

	public void setCards( List<ContactCard> cards ) {
		this.cards = cards;
	}
}
