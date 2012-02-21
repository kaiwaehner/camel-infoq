package infoq.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelStarter {

	public static void main(String[] args) throws Exception {
		
		// Create a CamelContext (which is Camel's runtime system)
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new IntegrationRoute());
		
		// After starting, you have 30 seconds to copy files to the inbox.
		// They are automatically processed by the route.
		context.start();
		
		Thread.sleep(30000);
		
		context.stop();

	}

}
