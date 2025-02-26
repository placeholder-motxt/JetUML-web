/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2020 by McGill University.
 *     
 * See: https://github.com/prmr/JetUML
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 *******************************************************************************/
package org.jetuml.persistence;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.jetuml.JavaFXLoader;
import org.jetuml.persistence.json.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestJsonDecoder
{
	@BeforeAll
	public static void setupClass()
	{
		JavaFXLoader.load();
	}
	
	/*
	 * Try to decode a valid but empty
	 * JSON object.
	 */
	@Test
	public void testEmptyJSONObject()
	{
		JsonObject object = new JsonObject();
		assertThrows(DeserializationException.class, () -> new JsonDecoder(object).decode());
	}
	
	/*
	 * Try to decode a valid JSON object missing
	 * the nodes and edges.
	 */
	@Test
	public void testIncompleteJSONObject()
	{
		JsonObject object = new JsonObject();
		object.put("version", "1.2");
		object.put("diagram", "StateDiagram");
		assertThrows(DeserializationException.class, () -> new JsonDecoder(object).decode());
	}
}
