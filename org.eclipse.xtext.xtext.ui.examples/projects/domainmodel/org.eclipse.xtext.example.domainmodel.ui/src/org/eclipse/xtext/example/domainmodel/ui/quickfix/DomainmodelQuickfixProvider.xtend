/*******************************************************************************
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.domainmodel.ui.quickfix

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature
import org.eclipse.xtext.example.domainmodel.validation.IssueCodes
import org.eclipse.xtext.ui.editor.model.IXtextDocument
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.xbase.ui.quickfix.XbaseQuickfixProvider

/**
 * Custom quickfixes.
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class DomainmodelQuickfixProvider extends XbaseQuickfixProvider {
	@Fix(IssueCodes.INVALID_TYPE_NAME) def void fixTypeName(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Capitalize name", '''Capitalize name of '«»«issue.getData().get(0)»'«»''',
			"upcase.png", // exemplary textual modification
			[IModificationContext context|
				var IXtextDocument xtextDocument = context.getXtextDocument()
				var String firstLetter = xtextDocument.get(issue.getOffset(), 1)
				xtextDocument.replace(issue.getOffset(), 1, Strings.toFirstUpper(firstLetter))
			])
	}

	@Fix(IssueCodes.INVALID_FEATURE_NAME) def void fixFeatureName(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Uncapitalize name", '''Uncapitalize name of '«»«issue.getData().get(0)»'«»''',
			"upcase.png", // exemplary semantic modification
			[EObject element, IModificationContext context|
				((element as Feature)).setName(Strings.toFirstLower(issue.getData().get(0)))
			])
	}

	@Fix(IssueCodes.MISSING_TYPE) def void createReferenceType(Issue issue, IssueResolutionAcceptor acceptor) {
		createLinkingIssueResolutions(issue, acceptor)
	}
}
