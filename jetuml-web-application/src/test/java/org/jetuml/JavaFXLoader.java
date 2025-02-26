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
package org.jetuml;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

/**
 * Loading this class into the JVM ensures the JavaFX toolkit
 * is available.
 */
@SuppressWarnings("unused")
public final class JavaFXLoader 
{
	private static JFXPanel aJFXPanel = new JFXPanel();
	
	private JavaFXLoader() {}
	
	public static void load() {
		// This prevents the JavaFX Application Thread from exiting after the execution of the
		// first test file using it.
		Platform.setImplicitExit(false);
	}
}
