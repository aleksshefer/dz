package ru.productstar.outcoming;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Application {

    @Bean
    public DataSource dataSource() throws SQLException {

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost:9090/mem:mydb");
        dataSource.setUser("user");
        dataSource.setPassword("password");

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("create table if not exists accounts (" +
                    "id bigint auto_increment primary key," +
                    "name varchar(255), " +
                    "balance int)");
        }
        return dataSource;
    }

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class, args);
    }
}
