/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2020, 2021 by McGill University.
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
package org.jetuml.diagram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestProperty
{
	@Test
	public void testProperty()
	{
		class Stub { String aValue = "value"; }
		Stub stub = new Stub();
		Property property = new Property(PropertyName.NAME, () -> stub.aValue, newval -> stub.aValue = (String) newval);
		assertEquals(PropertyName.NAME, property.name());
		assertEquals("value", property.get());
		
		property.set("foo");
		assertEquals("foo", property.get());
	}
}
