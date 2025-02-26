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

package org.jetuml.geom;

import static java.lang.Math.max;

/**
 * Represents a pair of width and height.
* @param width The width. >= 0
* @param height The height. >= 0
*/
// public record Dimension(int width, int height)
public class Dimension
{
	private final int width;
	private final int height;

	public static final Dimension NULL = new Dimension(0, 0);

	/**
	 * Constructs a new Dimension object.
	*
	* @param width The width. Must be >= 0.
	* @param height The height. Must be >= 0.
	*/
	public Dimension(int width, int height)
	{
		assert width >= 0 && height >= 0;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return The width of this dimension.
	*/
	public int width()
	{
		return this.width;
	}

	/**
	 * @return The height of this dimension.
	*/
	public int height()
	{
		return this.height;
	}

	/**
	 * Creates a dimension that is the maximum of this dimension
	* and the provided width and height.
	*
	* @param pWidth The minimum width to include. Must be >= 0.
	* @param pHeight The minimum height to include. Must be >= 0.
	* @return A new Dimension object with the maximum width and height.
	* @pre pWidth >= 0 && pHeight >= 0
	*/
	public Dimension include(int pWidth, int pHeight)
	{
		assert pWidth >= 0 && pHeight >= 0;
		return new Dimension(max(width, pWidth), max(height, pHeight));
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		Dimension other = (Dimension) obj;
		return width == other.width && height == other.height;
	}

	@Override
	public int hashCode()
	{
		return java.util.Objects.hash(width, height);
	}

	@Override
	public String toString()
	{
		return "Dimension[width=" + width + ", height=" + height + "]";
	}
}
 