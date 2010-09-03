/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.bracketmatching;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.actions.IActionContributor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class GoToMatchingBracketAction extends Action implements IActionContributor {
	
	@Inject
	private ICharacterPairMatcher matcher;
	private XtextEditor editor;
	
	public GoToMatchingBracketAction() {
		super(Messages.GoToMatchingBracketAction_label);
		setId(GoToMatchingBracketAction.class.getName());
		setActionDefinitionId("org.eclipse.xtext.ui.GotToMatchingBracketAction"); //$NON-NLS-1$
	}
	
	@Override
	public void run() {
		IXtextDocument document = editor.getDocument();
		ISelection selection = editor.getSelectionProvider().getSelection();
		if (selection instanceof TextSelection) {
			TextSelection textSelection = (TextSelection) selection;
			if (textSelection.getLength()==0) {
				IRegion region = matcher.match(document, textSelection.getOffset());
				if (region != null) {
					if (region.getOffset()+1==textSelection.getOffset()) {
						editor.selectAndReveal(region.getOffset()+region.getLength(),0);
					} else {
						editor.selectAndReveal(region.getOffset()+1,0);
					}
				}
			}
		}
	}

	public void contributeActions(XtextEditor editor) {
		this.editor = editor;
		setEnabled(true);
		editor.setAction(getId(), this);
	}
}
