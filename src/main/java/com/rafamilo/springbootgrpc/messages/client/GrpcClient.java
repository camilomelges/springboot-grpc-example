package com.rafamilo.springbootgrpc.messages.client;

import com.rafamilo.springbootgrpc.messages.MessageRequest;
import com.rafamilo.springbootgrpc.messages.MessageResponse;
import com.rafamilo.springbootgrpc.messages.MessageServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
	public static MessageResponse run(final String content, final String server, final Integer port) {
		final ManagedChannel channel = ManagedChannelBuilder.forAddress(server == null ? "localhost" : null, port == null ? 8080 : port)
			.usePlaintext()
			.build();

		final MessageServiceGrpc.MessageServiceBlockingStub stub = MessageServiceGrpc.newBlockingStub(channel);

		final MessageResponse messageResponse = stub.hello(MessageRequest.newBuilder().setContent(content).build());
		channel.shutdown();

		return messageResponse;
	}
}
