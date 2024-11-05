package org.learn.SpringBootWorkAroundBranch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DataSourceConfig {

    @Bean(name = "datasourceprop")
    @ConfigurationProperties(prefix = "spring.security.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "datasourcesecurity")
    @DependsOn("datasourceprop")
    @Primary
    public DataSource dataSourceSecurity(@Qualifier("datasourceprop") DataSourceProperties dataSourceProperties) {
        log.info("DB : {}",dataSourceProperties().getDriverClassName());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
