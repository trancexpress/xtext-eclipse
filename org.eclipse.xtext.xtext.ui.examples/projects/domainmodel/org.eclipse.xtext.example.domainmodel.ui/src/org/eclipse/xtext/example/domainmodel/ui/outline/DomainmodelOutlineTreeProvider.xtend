/*******************************************************************************
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.domainmodel.ui.outline

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.eclipse.xtext.example.domainmodel.domainmodel.Entity
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature
import org.eclipse.xtext.ui.editor.outline.IOutlineNode
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode

/**
 * Customization of the default outline structure.
 *
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#outline
 */
class DomainmodelOutlineTreeProvider extends DefaultOutlineTreeProvider {

	override protected void _createChildren(DocumentRootNode parentNode, EObject rootElement) {
		for (EObject content : rootElement.eContents()) {
			createNode(parentNode, content)
		}
	}

	protected def void _createNode(IOutlineNode parent, JvmParameterizedTypeReference modelElement) {
		// prevent creating outline nodes for JvmParameterizedTypeReference model elements
	}

	def protected boolean _isLeaf(Entity entity) {
		entity.features.isEmpty
	}

	def protected boolean _isLeaf(Feature feature) {
		return true
	}
}
