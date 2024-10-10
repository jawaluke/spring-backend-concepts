package org.learn.SpringBootWorkAroundBranch.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PgDataSourceConfig {

    @Bean
    @ConfigurationProperties(
            prefix = "spring.datasource.pg"
    )
    public DataSourceProperties getDataSourceProperty() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource() {
        return getDataSourceProperty().initializeDataSourceBuilder().build();
    }

}
