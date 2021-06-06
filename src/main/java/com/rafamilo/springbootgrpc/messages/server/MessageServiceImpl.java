package com.rafamilo.springbootgrpc.messages.server;

import com.rafamilo.springbootgrpc.messages.MessageRequest;
import com.rafamilo.springbootgrpc.messages.MessageResponse;
import com.rafamilo.springbootgrpc.messages.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;

public class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {

	@Override public void hello(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
		final String content = "New Content, " + request.getContent();

		final MessageResponse response = MessageResponse.newBuilder().setContent(content).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
