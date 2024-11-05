package org.learn.SpringBootWorkAroundBranch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Slf4j
public class PostgresqlEntityManager {

    @Primary
    @Bean(name = "entityManagerPostgresql")
    @DependsOn("datasourcesecurity")
    public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder managerFactoryBuilder,
                                                                @Qualifier("datasourcesecurity") DataSource dataSource
    ) throws SQLException {
        log.info("entity manager: {}",dataSource.getConnection().getClientInfo().toString());
        return managerFactoryBuilder.dataSource(dataSource)
                .packages("org.learn.SpringBootWorkAroundBranch.entity")
                .build();
    }
}
