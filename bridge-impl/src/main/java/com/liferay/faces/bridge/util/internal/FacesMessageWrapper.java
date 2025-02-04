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
package com.liferay.faces.bridge.util.internal;

import javax.faces.application.FacesMessage;


/**
 * @author  Neil Griffin
 */
public class FacesMessageWrapper {

	private String clientId;
	private FacesMessage facesMessage;

	public FacesMessageWrapper(String clientId, FacesMessage facesMessage) {
		this.clientId = clientId;
		this.facesMessage = facesMessage;
	}

	public String getClientId() {
		return clientId;
	}

	public FacesMessage getFacesMessage() {
		return facesMessage;
	}
}
