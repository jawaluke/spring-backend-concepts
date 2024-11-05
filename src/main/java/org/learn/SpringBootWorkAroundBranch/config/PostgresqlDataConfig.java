package org.learn.SpringBootWorkAroundBranch.config;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerPostgresql",
        basePackages = "org.learn.SpringBootWorkAroundBranch.repository",
        transactionManagerRef = "transactionManagerPostgresql"
)
@Slf4j
public class PostgresqlDataConfig {

    @Bean(name = "transactionManagerPostgresql")
    @DependsOn("entityManagerPostgresql")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerPostgresql") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) throws SQLException
    {
        log.info("Transaction manager : ");
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

}
