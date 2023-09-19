package com.example;

import java.sql.*;

// import javax.annotation.PostConstruct;
// import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class StudentDAO implements InitializingBean, DisposableBean {
    static String driver;
    static Connection con;
    static String url;
    static String userName;
    static String password;

    static void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, userName, password);
    }

    static void closeConnection() throws SQLException {
        con.close();
    }

    void getAllRecords() throws SQLException {
        String sql = "SELECT * FROM week9day2.Student";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }

    // @PostConstruct
    // public void init() throws ClassNotFoundException, SQLException {
    //     System.out.println("Inside init method");
    //     createConnection();
    // }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Inside init method");
        createConnection();
    }

    //@PreDestroy
    @Override
    public void destroy() throws SQLException {
        System.out.println("Inside destroy method");
        closeConnection();
    }

    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        System.out.println("setting driver");
        StudentDAO.driver = driver;
    }

    public static Connection getConnection() {
        return con;
    }
    
    public static void setConnection(Connection connection) {
        StudentDAO.con = connection;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        System.out.println("setting url");
        StudentDAO.url = url;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        System.out.println("setting username");
        StudentDAO.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        System.out.println("setting password");
        StudentDAO.password = password;
    }

}
