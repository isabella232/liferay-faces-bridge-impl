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
package com.liferay.faces.bridge.tck.tests.chapter7.section_7_2;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.EventRequest;
import javax.portlet.annotations.PortletRequestScoped;


/**
 * @author  Neil Griffin
 */
@Named
@PortletRequestScoped
public class EventBean {

	@Inject
	private EventRequest eventRequest;

	public String getInjectedEventRequestFQCN() {
		return eventRequest.getClass().getName();
	}
}