package infoq.camel;

import infoq.camel.IntegrationRoute;

import java.io.File;


import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest extends CamelTestSupport {

	@Before
	public void setup() throws Exception {
		// set up the CamelContext (runtime system of Camel)
		super.setUp();
		
		// add all required routes
		context.addRoutes(new IntegrationRoute());
		
		// do some clean up to avoid unexpected results
		deleteDirectory("orders/inbox");
		deleteDirectory("orders/outbox");
	}
	
	@Test
	public void testFileToFile() throws Exception {
		
		// Body contains two CDs, one DVD and one BOOK.
		String bodyOfMessage = 
				"eminem / cd,harry potter and the deathly hollows / dvd,"
				+ "Claus Ibsen - Camel in Action / book,"
				+ "Xzibit / cd";
		
		// The TemplateProducer is part of CamelTestSupport. It is used to send messages to Camel endpoints.
		template.sendBodyAndHeader("file://orders/inbox", bodyOfMessage, Exchange.FILE_NAME, "order.csv");
		
		// Mock is included implicitly.
		MockEndpoint mock = context.getEndpoint("mock:others", MockEndpoint.class);
		// The Mock expects only one message, because it only receives the BOOK order:
		mock.expectedMessageCount(1);
		mock.setResultWaitTime(1000);
		
		Thread.sleep(3000);
		
		String dvdBody = " harry potter and the deathly hollows / dvd";
		
		File target = new File("orders/outbox/dvd/order.csv");
		String content = context.getTypeConverter().convertTo(String.class, target);
		
		// Assertions
		mock.assertIsSatisfied();
		assertEquals(dvdBody.toUpperCase(), content);

		
		
	}
}
