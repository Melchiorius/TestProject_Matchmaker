package com.boost.test_project.lasta_games.matchmaker.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan("com.boost.test_project.lasta_games.matchmaker")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:matchmaker.properties")
public class Config {
/*
    @Value("db.jdbc_url")
    String JdbcUrl;
    @Value("db.user")
    String user;
    @Value("db.password")
    String password;

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl(JdbcUrl);
            dataSource.setUser(user);
            dataSource.setPassword(password);
        }
        catch(PropertyVetoException e){
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.boost.test_project.lasta_games.matchmaker");

            Properties hibernateProperties = new Properties();
            hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
            hibernateProperties.setProperty("hibernate.show_sql","true");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
    */
}
