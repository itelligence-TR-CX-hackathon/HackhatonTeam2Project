package com.team2.dao;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao
{

    DataSource dataSource ;

    public DataSource getDataSource()
    {
        return this.dataSource;
    }

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isValidUser(String name, String password) throws SQLException
    {
        String query = "Select count(1) from user where name = ? and password = ?";
        PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, password);
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next())
            return (resultSet.getInt(1) > 0);
        else
            return false;
    }

}