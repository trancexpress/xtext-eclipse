/**
 * Copyright (c) 2016, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.arithmetics.ui.editor.hierarchy;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.example.arithmetics.ui.editor.hierarchy.ArithmeticsCallHierarchyBuilder;
import org.eclipse.xtext.ide.editor.hierarchy.IHierarchyBuilder;
import org.eclipse.xtext.resource.IGlobalServiceProvider;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.ui.editor.findrefs.EditorResourceAccess;
import org.eclipse.xtext.ui.editor.hierarchy.AbstractOpenHierarchyHandler;
import org.eclipse.xtext.ui.editor.hierarchy.DeferredHierarchyBuilder;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class OpenCallHierarchyHandler extends AbstractOpenHierarchyHandler {
  private static final String HIERARCHY_VIEW_PART_ID = "org.eclipse.xtext.example.arithmetics.ui.ArithmeticsCallHierarchy";
  
  @Inject
  @Extension
  private IGlobalServiceProvider _iGlobalServiceProvider;
  
  @Inject
  private EditorResourceAccess resourceAccess;
  
  @Override
  protected String getHierarchyViewPartID() {
    return OpenCallHierarchyHandler.HIERARCHY_VIEW_PART_ID;
  }
  
  @Override
  protected IHierarchyBuilder createHierarchyBuilder(final EObject target) {
    final ArithmeticsCallHierarchyBuilder xtextCallHierarchyBuilder = this._iGlobalServiceProvider.<ArithmeticsCallHierarchyBuilder>findService(target, ArithmeticsCallHierarchyBuilder.class);
    xtextCallHierarchyBuilder.setResourceAccess(this.resourceAccess);
    xtextCallHierarchyBuilder.setIndexData(this._iGlobalServiceProvider.<IResourceDescriptions>findService(target, IResourceDescriptions.class));
    final DeferredHierarchyBuilder deferredHierarchyBuilder = this._iGlobalServiceProvider.<DeferredHierarchyBuilder>findService(target, DeferredHierarchyBuilder.class);
    deferredHierarchyBuilder.setHierarchyBuilder(xtextCallHierarchyBuilder);
    return deferredHierarchyBuilder;
  }
}
