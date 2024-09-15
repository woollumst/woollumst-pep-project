package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    //register
    public Account registerAccount (Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO Account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.execute();

            String sql2 = "SELECT * FROM Account WHERE username = ?;";
            PreparedStatement p2 = connection.prepareStatement(sql2);
            p2.setString(1, account.getUsername());
            ResultSet rs = p2.executeQuery();
            rs.next();
            account.setAccount_id(rs.getInt("account_id"));
            return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //user logins
    public Account accountLogin (Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            account.setAccount_id(rs.getInt("account_id"));
            return account;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<String> getAllUsernames (){
        Connection connection = ConnectionUtil.getConnection();
        List<String> usernameList = new ArrayList<>();
        try{
            String sql = "SELECT username FROM Account;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                usernameList.add(rs.getString("username"));
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return usernameList;
    }
        
        
    public List<Account> getAllAccounts (){
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Account;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                accounts.add(account);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }
    
}
