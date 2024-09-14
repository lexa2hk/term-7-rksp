package org.example;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://minio1:9000")
                .credentials("minioadmin", "minioadmin123")
                .build();
    }

    @Bean
    public MinioClient minioClient2() {
        return MinioClient.builder()
                .endpoint("http://minio2:9001")
                .credentials("minioadmin", "minioadmin123")
                .build();
    }
}
