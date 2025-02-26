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
package org.jetuml.gui.tips;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.jetuml.gui.tips.TipLoader.Tip;
import org.jetuml.persistence.json.JsonObject;
import org.jetuml.persistence.json.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestTipLoader 
{
	private static final String TIP_TITLE_FIELD = TipFieldName.TITLE.asString();
	private static final String TIP_CONTENT_FIELD = TipFieldName.CONTENT.asString();
	private static final String TIP_CONTENT_TEXT_FIELD = Media.TEXT.name().toLowerCase();
	private static final String TIP_CONTENT_IMAGE_FIELD = Media.IMAGE.name().toLowerCase();
	
	private static JsonObject WELL_FORMATTED_TIP;
	private final static String WELL_FORMATTED_TIP_STRING = 
			  "{"
			+ " \"" + TIP_TITLE_FIELD + "\": \"First Tip\","
			+ " \"" + TIP_CONTENT_FIELD + "\": [{ \"" + TIP_CONTENT_TEXT_FIELD + "\": \"sample text\"},"
			+ 				 				   "{ \"" + TIP_CONTENT_IMAGE_FIELD + "\": \"image.png\"}] "
			+ "}";
	
	
	@BeforeAll
	public static void setupClass()
	{
		WELL_FORMATTED_TIP = JsonParser.parse(WELL_FORMATTED_TIP_STRING);
	}
	
	@Test
	void testTipLoader_loadTipCanLoadTipFromCorrectId()
	{
		Tip tip = TipLoader.loadTip(1);
		assertTrue(tip != null);
	}
	
	@Test
	void testTipLoader_loadTipCreatesTipsWithRightId()
	{
		Tip tip1 = TipLoader.loadTip(1);
		assertEquals(tip1.getId(), 1);
		Tip tip2 = TipLoader.loadTip(2);
		assertEquals(tip2.getId(), 2);
	}
	
	@Test
	void testTipConvertJSONObjectToTipElements_listHasRightSize()
	{
		List<TipElement> tipElements = convertJSONObjectToTipElements(WELL_FORMATTED_TIP);
		assertEquals(tipElements.size(), 2);
	}
	
	@Test
	void testTipConvertJSONObjectToTipElements_elementsHaveRightMedia()
	{
		List<TipElement> tipElements = convertJSONObjectToTipElements(WELL_FORMATTED_TIP);
		TipElement tipElement1 = tipElements.get(0);
		TipElement tipElement2 = tipElements.get(1);
		
		assertEquals(tipElement1.getMedia(), Media.TEXT);
		assertEquals(tipElement2.getMedia(), Media.IMAGE);
	}
	
	@Test
	void testTipConvertJSONObjectToTipElements_elementsHaveRightContent()
	{
		List<TipElement> tipElements = convertJSONObjectToTipElements(WELL_FORMATTED_TIP);
		TipElement tipElement1 = tipElements.get(0);
		TipElement tipElement2 = tipElements.get(1);
		
		assertEquals(tipElement1.getContent(), "sample text");
		assertEquals(tipElement2.getContent(), "image.png");
	}
	
	@Test
	void testinputStreamToString()
	{
		try(InputStream stream = new FileInputStream("testdata/streamtest.txt"))
		{
			String result = inputStreamToString(stream);
			assertEquals("ABCDE", result);
		}
		catch( IOException e )
		{
			fail();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static List<TipElement> convertJSONObjectToTipElements(JsonObject pTip)
	{
		try
		{
			Method method = TipLoader.Tip.class.getDeclaredMethod("convertJsonObjectToTipElements", JsonObject.class);
			method.setAccessible(true);
			return (List<TipElement>) method.invoke(null, pTip);
		}
		catch(ReflectiveOperationException e)
		{
			fail();
			return null;
		}
	}
	
	static String inputStreamToString(InputStream pStream)
	{
		try
		{
			Method method = TipLoader.class.getDeclaredMethod("inputStreamToString", InputStream.class);
			method.setAccessible(true);
			return (String) method.invoke(null, pStream);
		}
		catch(ReflectiveOperationException e)
		{
			fail();
			return null;
		}
	}
}
