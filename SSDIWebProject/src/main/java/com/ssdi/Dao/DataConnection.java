package com.ssdi.Dao;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by prayas on 4/19/2017.
 */

public class DataConnection {
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();;
        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("application.properties");
            prop.load(input);
            //dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(prop.getProperty("spring.datasource.driverClassName"));
            dataSource.setUrl(prop.getProperty("spring.datasource.url"));
            dataSource.setUsername(prop.getProperty("spring.datasource.username"));
            dataSource.setPassword(prop.getProperty("spring.datasource.password"));

            return dataSource;
        }
        catch (IOException e){
            System.out.println("There is some exception" + e);
        }
        return dataSource;
    }
}
