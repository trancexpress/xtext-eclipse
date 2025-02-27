/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.xtext.builder.JavaProjectBasedBuilderParticipant;
import org.junit.Assert;

/**
 * @author Christian Dietrich - Initial contribution and API
 */
public class JarPackageFragmentRootTestUtil {
	
	public static JarPackageFragmentRoot getJarPackageFragmentRoot(IResource resource, JavaProject project) {
		try {
			Constructor<JarPackageFragmentRoot> constructor = JarPackageFragmentRoot.class.getDeclaredConstructor(IResource.class, JavaProject.class);
			constructor.setAccessible(true);
			return constructor.newInstance(resource, project);
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new AssertionError("construction failed", e);
		} catch (NoSuchMethodException e) {
			// OK
		}
		try {
			Constructor<JarPackageFragmentRoot> constructor = JarPackageFragmentRoot.class.getDeclaredConstructor(IResource.class, IPath.class, JavaProject.class, IClasspathAttribute[].class);
			return constructor.newInstance(resource, resource.getFullPath(), project, null);
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new AssertionError("construction failed", e);
		} catch (NoSuchMethodException e) {
			// OK
		}
		throw new IllegalStateException("no suitable constructor found");
	}

}
