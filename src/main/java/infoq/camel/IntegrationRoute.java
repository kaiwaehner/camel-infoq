package infoq.camel;

import org.apache.camel.builder.RouteBuilder;

public class IntegrationRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		// Consumer Endpoint
		from("file:orders/inbox")
			// if tracing is turned on, you get very detailed information about processing within the route.
			.tracing()
			// a processor does custom integration logic
			.process(new LoggingProcessor())
			// a bean also does custom integration logic
			.bean(new TransformationBean(), "makeUpperCase")
			// Translator EIP (using the camel-csv component)
			.unmarshal().csv()
			// Splitter EIP
			.split().body()
			// Content-based Router EIP
			.log("Kai: ${body}")
			.choice()
				.when(body().contains("DVD"))
					// Producer Endpoint
					.to("file:orders/outbox/dvd")
				.when(body().contains("CD"))
					.to("file:orders/outbox/cd")
				.otherwise()
					.to("file:orders/mock");
		
	}

}
