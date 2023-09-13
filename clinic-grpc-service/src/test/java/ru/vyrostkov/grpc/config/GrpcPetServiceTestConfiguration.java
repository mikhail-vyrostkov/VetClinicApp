package ru.vyrostkov.grpc.config;

import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vyrostkov.grpc.serviceGrpc.GrpcPetServiceImpl;


@Configuration
@ImportAutoConfiguration({
        GrpcServerAutoConfiguration.class,
        GrpcServerFactoryAutoConfiguration.class,
        GrpcClientAutoConfiguration.class})
public class GrpcPetServiceTestConfiguration {

    @Bean
    GrpcPetServiceImpl grpcPetService(){
        return new GrpcPetServiceImpl();
    }
}
