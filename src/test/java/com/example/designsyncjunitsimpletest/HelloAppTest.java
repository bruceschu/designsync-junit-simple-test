package com.example.designsyncjunitsimpletest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.WebClient;


// This will be the main starting class.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloAppTest {

    @Test
    public void T000_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    assertTrue("testing title text", page.getTitleText().contains("ENOVIA Synchronicity 3DEXPERIENCE"));
	    
	}
    }
    
    @Test
    public void T001_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    
	    final String pageAsXml = page.asXml();
	    assertTrue("testing SyncNotesTop xml", pageAsXml.contains("<body class=\"SyncNotesTop\">"));
    }
    }

    @Test
    public void T002_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    
	    final String pageAsText = page.asText();
	    assertTrue("testing banner text", pageAsText.contains("3DEXPERIENCE R2018x"));
	    
	}
	}

    @Test
    public void T003_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    
 	    // start snooping at the various iframes on the landing page
        final List<FrameWindow> window = page.getFrames();
        assertNotNull("could not get iframes", window);
	}
	}
    
}
