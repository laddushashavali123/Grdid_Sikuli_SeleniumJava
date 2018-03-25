package tests.example;

import utils.database.MySQLConnUtils;

import java.sql.*;


public class JDBC_MySQL {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Get connection ... ");
        Connection conn = MySQLConnUtils.getMySQLConnection();
        System.out.println("Connected to " + conn);

    }
}
