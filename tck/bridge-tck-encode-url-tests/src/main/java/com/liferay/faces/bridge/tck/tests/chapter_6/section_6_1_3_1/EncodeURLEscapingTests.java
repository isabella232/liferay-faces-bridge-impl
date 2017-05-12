/**
 * Copyright (c) 2000-2017 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.bridge.tck.tests.chapter_6.section_6_1_3_1;

import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.liferay.faces.bridge.tck.annotation.BridgeTest;
import com.liferay.faces.bridge.tck.beans.TestBean;
import com.liferay.faces.bridge.tck.common.Constants;


/**
 * @author  Neil Griffin
 */
public class EncodeURLEscapingTests {

	// Test is SingleRequest -- tests whether parameters encoded in the defaultViewId's
	// queryString are exposed as request parameters.
	// Test #6.99
	@BridgeTest(test = "encodeURLEscapingTest")
	public String encodeURLEscapingTest(TestBean testBean) {
		testBean.setTestComplete(true);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		// test encodeActionURL preserves the xml escape encoding in the url it returns
		if (EncodeURLTestUtil.isStrictXhtmlEncoded(
					externalContext.encodeActionURL(
						externalContext.getRequestContextPath() + "/tests/SingleRequestTest.jsf?parm1=a&param2=b"))) {
			testBean.setTestResult(false,
				"EncodeActionURL incorrectly returned an url including xml escaping when the input url wasn't escaped.");

			return Constants.TEST_FAILED;
		}

		if (
			!EncodeURLTestUtil.isStrictXhtmlEncoded(
					externalContext.encodeActionURL(
						externalContext.getRequestContextPath() + "/tests/SingleRequestTest.jsf?parm1=a&amp;param2=b"))) {
			testBean.setTestResult(false,
				"EncodeActionURL incorrectly returned an url without xml escaping when the input url was escaped.");

			return Constants.TEST_FAILED;
		}

		if (EncodeURLTestUtil.isStrictXhtmlEncoded(
					externalContext.encodeActionURL(
						externalContext.getRequestContextPath() + "/tests/SingleRequestTest.jsf"))) {
			testBean.setTestResult(false,
				"EncodeActionURL incorrectly returned an url including xml escaping when the input url contained no indication (no query string).");

			return Constants.TEST_FAILED;
		}

		ViewHandler viewHandler = facesContext.getApplication().getViewHandler();

		if (EncodeURLTestUtil.isStrictXhtmlEncoded(
					externalContext.encodeResourceURL(
						viewHandler.getResourceURL(facesContext, "/tests/SingleRequestTest.jsf?parm1=a&param2=b")))) {
			testBean.setTestResult(false,
				"EncodeResourceURL incorrectly returned an url including xml escaping when the input url wasn't escaped.");

			return Constants.TEST_FAILED;
		}

		if (
			!EncodeURLTestUtil.isStrictXhtmlEncoded(
					externalContext.encodeResourceURL(
						viewHandler.getResourceURL(facesContext,
							"/tests/SingleRequestTest.jsf?parm1=a&amp;param2=b")))) {
			testBean.setTestResult(false,
				"EncodeResourceURL incorrectly returned an url without xml escaping when the input url was escaped.");

			return Constants.TEST_FAILED;
		}

		if (EncodeURLTestUtil.isStrictXhtmlEncoded(
					externalContext.encodeResourceURL(
						viewHandler.getResourceURL(facesContext, "/tests/SingleRequestTest.jsf")))) {
			testBean.setTestResult(false,
				"EncodeResourceURL incorrectly returned an url including xml escaping when the input url contained no indication (no query string).");

			return Constants.TEST_FAILED;
		}

		// Otherwise all was good.

		testBean.setTestResult(true,
			"encodeActionURL and encodeResourceURL both correctly xml escaped urls it should and didn't xml escape urls it shouldn't");

		return Constants.TEST_SUCCESS;

	}
}
