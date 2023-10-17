package ru.vyrostkov.grpc.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ContextConfiguration(initializers = PgContainerTest.PgContainerInitializer.class)
public class PgContainerTest {
    private static final String DATABASE_NAME = "test_pet_clinic_db";

    protected static final PostgreSQLContainer<?> pgContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName(DATABASE_NAME)
            .withUsername("postgres")
            .withPassword("test");

    public static class PgContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            pgContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + pgContainer.getJdbcUrl(),
                    "spring.datasource.username=" + pgContainer.getUsername(),
                    "spring.datasource.password=" + pgContainer.getPassword()
            ).applyTo(applicationContext);
        }
    }
}
