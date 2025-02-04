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
package com.liferay.faces.bridge.test.integration.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.faces.bridge.test.integration.BridgeTestUtil;
import com.liferay.faces.test.selenium.browser.BrowserDriver;
import com.liferay.faces.test.selenium.browser.BrowserDriverManagingTesterBase;
import com.liferay.faces.test.selenium.browser.WaitingAsserter;
import com.liferay.faces.test.selenium.util.ClosableUtil;


/**
 * @author  Kyle Stiemann
 */
public class JSFExportPDFPortletTester extends BrowserDriverManagingTesterBase {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(JSFExportPDFPortletTester.class);

	@Test
	public void runJSFExportPDFPortletTest() throws IOException {

		// Test that the view contains links to all three pdfs.
		BrowserDriver browserDriver = getBrowserDriver();
		browserDriver.navigateWindowTo(BridgeTestUtil.getDemoPageURL("jsf-pdf"));

		WaitingAsserter waitingAsserter = getWaitingAsserter();
		waitingAsserter.assertElementDisplayed(
			"//td[contains(text(),'Green')]/preceding-sibling::td/a[contains(text(),'Export')]");
		waitingAsserter.assertElementDisplayed(
			"//td[contains(text(),'Kessler')]/preceding-sibling::td/a[contains(text(),'Export')]");

		String shearerPDFLinkXpath =
			"//td[contains(text(),'Shearer')]/preceding-sibling::td/a[contains(text(),'Export')]";

		waitingAsserter.assertElementDisplayed(shearerPDFLinkXpath);

		// Test that the "Rich Shearer" link generates a PDF with the correct test. Note: since different browsers
		// and WebDriver implementations handle downloading files differently, download the file using a Java URL
		// connection.
		WebElement shearerPDFLinkElement = browserDriver.findElementByXpath(shearerPDFLinkXpath);
		String shearerPDFLink = shearerPDFLinkElement.getAttribute("href");
		URL shearerPDFURL = new URL(shearerPDFLink);
		HttpURLConnection httpURLConnection = (HttpURLConnection) shearerPDFURL.openConnection();
		httpURLConnection.setRequestMethod("GET");

		Set<Cookie> cookies = browserDriver.getBrowserCookies();
		String cookieString = "";

		for (Cookie cookie : cookies) {
			cookieString += cookie.getName() + "=" + cookie.getValue() + ";";
		}

		httpURLConnection.addRequestProperty("Cookie", cookieString);

		InputStream inputStream = httpURLConnection.getInputStream();

		// Compare the text of the PDFs rather than the files (via a hash such as md5) becuase the portlet generates
		// slightly different PDFs each time the link is clicked (CreationDate, ModDate, and Info 7 0 R/ID are
		// different each time).
		String shearerRichPDFText = getPDFText(inputStream);
		inputStream = JSFExportPDFPortletTester.class.getResourceAsStream("/Shearer-Rich.pdf");

		String expectedShearerRichPDFText = getPDFText(inputStream);
		logger.info("Expected Shearer-Rich.pdf text:\n\n{}\nDownloaded Shearer-Rich.pdf text:\n\n{}",
			expectedShearerRichPDFText, shearerRichPDFText);
		Assert.assertEquals(
			"The downloaded Shearer-Rich.pdf file's text does not match the expected Shearer-Rich.pdf file's text.",
			expectedShearerRichPDFText, shearerRichPDFText);
	}

	private String getPDFText(InputStream inputStream) throws IOException {

		String text = "";
		PDDocument pdDocument = null;

		try {

			pdDocument = PDDocument.load(inputStream);

			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			text = pdfTextStripper.getText(pdDocument);
		}
		finally {

			ClosableUtil.close(pdDocument);
			ClosableUtil.close(inputStream);
		}

		return text;
	}
}
