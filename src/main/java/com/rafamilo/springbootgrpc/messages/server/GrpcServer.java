package com.rafamilo.springbootgrpc.messages.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
	public static void startServer(final Integer port) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(port != null ? port : 8080).addService(new MessageServiceImpl()).build();

		server.start();
		server.awaitTermination();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(8765).addService(new MessageServiceImpl()).build();

		server.start();
		server.awaitTermination();
	}
}
