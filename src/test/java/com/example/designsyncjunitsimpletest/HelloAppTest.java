package com.example.designsyncjunitsimpletest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.WebClient;

//import static org.mockito.Mockito.*;
//import static org.powermock.api.mockito.PowerMockito.whenNew;


// This will be the main starting class.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloAppTest {

    @Test
    public void T000_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    assertTrue(page.getTitleText().contains("ENOVIA Synchronicity 3DEXPERIENCE"));
	    
	}
    }
    
    @Test
    public void T001_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    
	    final String pageAsXml = page.asXml();
	    assertTrue(pageAsXml.contains("<body class=\"SyncNotesTop\">"));
    }}

    @Test
    public void T002_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    
	    final String pageAsText = page.asText();
	    assertTrue(pageAsText.contains("3DEXPERIENCE foo R2018x"));
	    
	    }}

    @Test
    public void T003_homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://" + System.getProperty("testServer") + "/scripts/isynch.dll");
	    
 	    // start snooping at the various iframes on the landing page
        final List<FrameWindow> window = page.getFrames();
        assertNotNull("Could not get iframes", window);
	   }}

	    	    
	    
}


//    /**
//     * Unit test for HelloApp.
//     * <p/>
//     * A unit test aims to test all code and code paths of a specific class.
//     * <p/>
//     * This test uses PowerMock and Mockito to mock objects.
//     */
//    @RunWith(PowerMockRunner.class)
//    @PrepareForTest({System.class, HelloApp.class})
//    public class HelloAppTest {
//    
//        @Test
//        public void testMain() {
//            String[] args = {"1"};
//            HelloApp.main(args);
//        }
//    
//        @Test
//        public void testWrongArgument() {
//            PowerMockito.mockStatic(System.class);
//    
//            String[] args = {"bicycle"};
//            HelloApp.main(args);
//    
//            // Did the program exit with the expected error code?
//            PowerMockito.verifyStatic(only());
//            System.exit(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD);
//        }
//    
//        @Test
//        public void testHelloError() throws Exception {
//            PowerMockito.mockStatic(System.class);
//    
//            // Mock Hello used by HelloApp to throw the expected exception when invoked with setTimes(5).
//            Hello hi = mock(Hello.class);
//            doThrow(new IllegalArgumentException("Nope.")).when(hi).setTimes(5);
//            // Sneakily insert our fake Hello class when it is created.
//            whenNew(Hello.class).withNoArguments().thenReturn(hi);
//    
//            // We know this will raise the expected exception, because we mocked Hello.
//            String[] args = {"5"};
//            HelloApp.main(args);
//    
//            // Did the program exit with the expected error code?
//            PowerMockito.verifyStatic(only());
//            System.exit(HelloApp.EXIT_STATUS_HELLO_FAILED);
//        }
//    
//        @Test
//        public void testDefaultArgument() {
//            // Passing no arguments should work.
//            String[] args = {};
//            HelloApp.main(args);
//        }
//    
//        @Test
//        public void classInstanceForCodeCoverageTest() {
//            // Strictly speaking this test doesn't achieve anything, because HelloApp contains only a single static
//            // method, but for purposes of full code coverage it is included. In general,
//            // it is easier to aim for full code coverage and be done with it, than to remember why class X is stuck at
//            // 95% code coverage.
//            new HelloApp();
//        }
//    }
