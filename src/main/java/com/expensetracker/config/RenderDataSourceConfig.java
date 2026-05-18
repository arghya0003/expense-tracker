package com.expensetracker.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
@Profile("prod")
public class RenderDataSourceConfig {

    @Bean
    @ConditionalOnProperty(name = "DATABASE_URL")
    public DataSource dataSource() throws Exception {
        String dbUrl = System.getenv("DATABASE_URL");
        URI uri = new URI(dbUrl);

        String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();
        String username = uri.getUserInfo().split(":")[0];
        String password = uri.getUserInfo().split(":")[1];

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }
}
