/**
 * Copyright (c) 2013, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.fowlerdsl.ui.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.example.fowlerdsl.statemachine.State;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#label-provider
 */
@SuppressWarnings("all")
public class StatemachineLabelProvider extends DefaultEObjectLabelProvider {
  @Inject
  public StatemachineLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final State state) {
    String _name = state.getName();
    return ("state " + _name);
  }
}
