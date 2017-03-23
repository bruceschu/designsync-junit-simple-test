package com.example.javamavenjunithelloworld;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

//import static org.mockito.Mockito.*;
//import static org.powermock.api.mockito.PowerMockito.whenNew;


// This will be the main starting class.
public class HelloAppTest {

    @Test
    public void homePage() throws Exception {
	try (final WebClient webClient = new WebClient()) {
	    final HtmlPage page = webClient.getPage("http://192.168.0.7:30080/scripts/isynch.dll");
	    assertTrue(page.getTitleText().contains("ENOVIA Synchronicity 3DEXPERIENCE"));
	    
	    final String pageAsXml = page.asXml();
	    assertTrue(pageAsXml.contains("<body class=\"SyncNotesTop\">"));
	    
	    final String pageAsText = page.asText();
	    assertTrue(pageAsText.contains("3DEXPERIENCE R2018x"));
	    
	    final HtmlAnchor anchor = page.getAnchorByText("DesignSync Menu");
	    assertNotNull("DS Menu entry", anchor);
	}
    }

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
