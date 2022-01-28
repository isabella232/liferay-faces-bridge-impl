/**
 * Copyright (c) 2000-2021 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.bridge.tck.factories.filter;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;

import com.liferay.faces.bridge.BridgeConfig;
import com.liferay.faces.bridge.RequestAttributeInspector;
import com.liferay.faces.bridge.RequestAttributeInspectorFactory;


/**
 * @author  Neil Griffin
 */
public class RequestAttributeInspectorFactoryTCKImpl extends RequestAttributeInspectorFactory {

	private RequestAttributeInspectorFactory wrappedFactory;

	public RequestAttributeInspectorFactoryTCKImpl(RequestAttributeInspectorFactory requestAttributeInspectorFactory) {
		this.wrappedFactory = requestAttributeInspectorFactory;
	}

	@Override
	public RequestAttributeInspector getRequestAttributeInspector(PortletRequest portletRequest,
		PortletConfig portletConfig, BridgeConfig bridgeConfig) {
		return wrappedFactory.getRequestAttributeInspector(portletRequest, portletConfig, bridgeConfig);
	}

	@Override
	public RequestAttributeInspectorFactory getWrapped() {
		return wrappedFactory;
	}
}