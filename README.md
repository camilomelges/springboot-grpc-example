# springboot-grpc-example

#### Just simple example with grpc

# Requirements
    - Install protobuf-compiler: sudo apt install -y protobuf-compiler
    - Read the src files and test files
    - Run tests: mvn test

# For development
    - Compile proto: protoc --plugin=protoc-gen-grpc-java=/home/soft005089/projects/springbootgrpc/target/protoc-plugins/protoc-gen-grpc-java-1.4.0-linux-x86_64.exe -I=src --java_out=src/main/java/ --grpc-java_out=src/main/java/ src/main/proto/MessageService.proto
