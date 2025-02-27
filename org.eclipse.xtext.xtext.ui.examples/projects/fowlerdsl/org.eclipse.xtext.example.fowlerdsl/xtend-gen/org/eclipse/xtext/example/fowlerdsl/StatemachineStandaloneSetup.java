/**
 * Copyright (c) 2017, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.fowlerdsl;

import org.eclipse.xtext.example.fowlerdsl.StatemachineStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
@SuppressWarnings("all")
public class StatemachineStandaloneSetup extends StatemachineStandaloneSetupGenerated {
  public static void doSetup() {
    new StatemachineStandaloneSetup().createInjectorAndDoEMFRegistration();
  }
}
