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

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.locators.TypeLocator;
import flexjson.test.mock.superhero.Hero;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * -
 *
 * @author Sergio Ceron F.
 * @version rev: %I%
 * @date 3/02/14 12:06 AM
 */
public class Test {
	public static void main( String[] args ) {
		String codigo = "";
		String line = "";
		try {
			DataInputStream fr = new DataInputStream( new FileInputStream( "cards.json" ) );
			while( ( line = fr.readLine() ) != null )
				codigo += line + "\n";
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
		}

		JSONDeserializer<CardHTTPResponse> deserializer = new JSONDeserializer<CardHTTPResponse>();
		CardHTTPResponse response = deserializer.use( null, CardHTTPResponse.class ).use( "cards.values", ContactCard.class ).deserialize( codigo );

		JSONSerializer serializer = new JSONSerializer();
		String out = serializer.exclude("*.class").prettyPrint( response.getCards() );
		System.out.println(out);


		JSONDeserializer<List<CardHTTPResponse>> deserializer2 = new JSONDeserializer<List<CardHTTPResponse>>();
		List<CardHTTPResponse> response2 = deserializer2.use( "values", ContactCard.class ).deserialize( out );

		int a = 6;
	}
}
