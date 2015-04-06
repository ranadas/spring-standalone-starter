package com.rdas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rdas on 06/04/2015.
 */
@Service
public class DatabaseService {

    @Autowired
    @Qualifier("embeddedDS")
    private DataSource dataSource;

    public boolean isTableCreated() throws SQLException {
        String sql = "SELECT count(*) FROM PING";
        boolean tableCreated = false;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet  resultSet = preparedStatement.executeQuery()
            ) {                
                if (resultSet.next()) {
                    tableCreated = true;
                } else {
                    System.out.println("noo nooo>?????");
                }
        } catch (SQLException e ) {
            tableCreated = false;
            throw e;
        } finally {
            return tableCreated;
        }
    }
}
