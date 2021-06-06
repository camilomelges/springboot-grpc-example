package com.rafamilo.springbootgrpc.messages.client;

import java.io.IOException;

import com.rafamilo.springbootgrpc.messages.MessageResponse;
import com.rafamilo.springbootgrpc.messages.server.GrpcServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GrpcClientTest {

	final Logger logger = LoggerFactory.getLogger(getClass());

	private static final Integer port = 8765;

	final Thread server = mountServer();

	@Test
	void shouldBeMakeACall() {
		server.start();

		Assertions.assertDoesNotThrow(() -> {
			final TestObject testObject = new TestObject();
			testObject.content = "teste";
			testObject.port = port;

			GrpcClient.run(testObject.content, testObject.server, testObject.port);

			server.interrupt();
		});
	}

	@Test
	void shouldBeReturnCorrectResponse() throws InterruptedException {
		server.start();
		final TestObject testObject = new TestObject();
		testObject.port = port;
		for (int i = 0; i < 1000; i++) {
			testObject.content = "teste: ".concat(String.valueOf(i));
			final MessageResponse response = GrpcClient.run(testObject.content, testObject.server, testObject.port);

			assertEquals("New Content, ".concat(testObject.content), response.getContent());
			logger.info(response.getContent());
		}

		server.interrupt();
	}

	private static class TestObject {
		String content;
		String server;
		Integer port;
	}

	private Thread mountServer() {
		return new Thread(() -> {
			try {
				GrpcServer.startServer(port);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
