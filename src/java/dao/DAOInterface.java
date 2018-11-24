/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author slfx7
 */
public interface DAOInterface {

    /**
     * Creates a connection object linked to the DB
     * @return A connection to the DB
     */
    default Connection connect() {
        String driverName = "org.apache.derby.jdbc.ClientDriver";
        String connStr = "jdbc:derby://localhost:1527/LinkedUDB";
        DBHelper.loadDriver(driverName);
        Connection DBConn = DBHelper.connectToDB(connStr, "itkstu", "student");
        return DBConn;
    }
    
    /**
     * Executes an SQL Create, Remove, or Update statement and commits it to the DB
     * @param query The query to execute, with ?s as placeholders
     * @param vars The variables to be inserted in place of the placeholders
     * @return Returns true if update was successful
     */
    default boolean updateDB(String query, ArrayList vars) {
        previewSQL(query, vars); //Debugging
        Connection db = connect();
        boolean success = false;
        try {
            db.setAutoCommit(false);
            PreparedStatement stmt = db.prepareStatement(query);
            setVars(stmt, vars);
            stmt.executeUpdate();
            stmt.close();
            db.commit();
            success = true;
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL update");
            e.printStackTrace();
        }
        try {
            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return success;
    }

    /**
     * Takes a prepared statement initialized with a query string a replaces the placeholders
     * with provided variables
     * @param stmt The PreparedStatement to work on
     * @param vars The variables to be used in the SQL statement
     * @throws SQLException 
     */
    default void setVars(PreparedStatement stmt, ArrayList vars) throws SQLException {
        for (int i = 0; i < vars.size(); i++) {
            stmt.setObject(i + 1, vars.get(i));
        }
    }

    /**
     * Prints approximately the SQL that would be generated by a PreparedStatement
     * @param query The query used for the prepared statement, with ?s as placeholders
     * @param vars  The variables to be used for the placeholders
     */
    default void previewSQL(String query, ArrayList vars) {
        String sql = query;
        for (int i = 0; i < vars.size(); i++) {
            sql = sql.replaceFirst("\\?", String.valueOf(vars.get(i)).replace("'", "''")); //Debugging
        }
        System.out.println(sql);
    }
}
