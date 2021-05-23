/**
 * General Crud Dao abstract class
 * */
package dao;

import com.mysql.cj.QueryResult;
import utils.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * General Crud Dao abstract class. CRUD actions to connect to database and retrieve results
 * */
public abstract class GeneralCrudDaoImpl<T>{

    /**
     * Method to for finding data of a single item from database.
     *
     * @param table name
     * @param column name
     * @param target conditional where
     * @return results database results found
     * */
    protected ResultSet find(String table, String column, String target) {
        String sqlStatement = "SELECT * FROM " + table + " WHERE " + column + " = '" + target + "'";
        Query.makeQuery(sqlStatement);
        return Query.getResult();
    }

    /**
     * Method to for finding all data of specific table from database.
     *
     * @param table name
     * @return results database results found
     * */
    protected ResultSet findAll(String table){
        String sqlStatement = "SELECT * FROM " + table;
        Query.makeQuery(sqlStatement);
        return Query.getResult();
    }

    /**
     * Method to for finding all data of specific table from database. On Inner is added
     *
     * @param table name
     * @param table2 name
     * @param column name
     * @return results database results found
     * */
    protected ResultSet findAllOnInner(String table, String table2, String column){
        String sqlStatement = "SELECT * FROM " + table;
        String innerStatement = " INNER JOIN " + table2 + " ON " + table + "." + column +
                " = " + table2 + "." + column;
        Query.makeQuery(sqlStatement + innerStatement);
        return Query.getResult();
    }

    /**
     * Method to for data from database.
     *
     * @param sqlStatement custom sql statement
     * @return results database results found
     * */
    protected ResultSet findCustom(String sqlStatement){
        Query.makeQuery(sqlStatement);
        return Query.getResult();
    }

    /**
     * Method to for adding data to database.
     *
     * @param table custom sql statement
     * @param values database results found
     * */
    protected void create(String table, String values) {
        String sqlStatement = "INSERT INTO " + table + " VALUES (" + values +
                ")";
        Query.makeQuery(sqlStatement);
    }

    /**
     * Method to for updating data to database.
     *
     * @param table name
     * @param setValues values to be change
     * @param column name
     * @param target where condition
     * */
    protected void update(String table, String setValues, String column,
                          String target){
        String sqlStatement = "UPDATE " + table + " SET " + setValues + " " +
                "WHERE " + column + " = " + target;
        Query.makeQuery(sqlStatement);
    }

    /**
     * Method to for delete data from database.
     *
     * @param table name
     * @param column name
     * @param target is where condition
     *
     * @return boolean if delete succeed
     * */
    protected boolean delete(String table, String column, String target){
        String sqlStatement = "DELETE FROM " + table + " WHERE " + column +
                " = " + target;
        return Query.makeQuery(sqlStatement);
    }

    protected int count(String table) throws SQLException {
        String sqlStatement = "SELECT count(*) FROM " + table;
        Query.makeQuery(sqlStatement);
        ResultSet results = Query.getResult();
        results.next();
        return results.getInt(1);
    }
}
