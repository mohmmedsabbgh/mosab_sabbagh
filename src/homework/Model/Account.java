/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class Account {
    private int id;
    private int userid;
    private int accountNumber;
    private String userName;
    private String carrency;
    private double balance;
    private String creationDate;

    public Account( int userid ,int accountNumber, String userName, String carrency, double balance, String creationDate) {        
        this.userid=userid;
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.carrency = carrency;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCarrency() {
        return carrency;
    }

    public void setCarrency(String carrency) {
        this.carrency = carrency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    public int save() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "INSERT INTO accounts (id,user_id,account_number,username,carrency,balance,creationDate) VALUES (?, ?, ?, ?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getUserid());
        ps.setInt(3,this.getAccountNumber());
        ps.setString(4, this.getUserName());
        ps.setString(5, this.getCarrency());
        ps.setDouble(6, this.getBalance());
        ps.setString(7,this.getCreationDate());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUserName()
                    +" Account was added successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    
    public static ArrayList<Account> getAllUsers() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> Accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts ";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            Account account = new Account(rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5)
                    ,rs.getDouble(6),rs.getString(7));
            account.setId(rs.getInt(1));
            Accounts.add(account);            
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return Accounts;
    }
    
    
     public int update() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;

        String sql = "UPDATE accounts SET user_id=?,accountNumber=?, userName=?, carrency=? , balance=?,creationDate=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setInt(1,this.getUserid());
        ps.setInt(2,this.getAccountNumber());
        ps.setString(3, this.getUserName());
        ps.setString(4, this.getCarrency());
        ps.setDouble(5, this.getBalance());
        ps.setString(6,  this.getCreationDate());
        ps.setInt(7, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("accounts with id : "+this.getId()+" was updated successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
     public int delete() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "DELETE FROM accounts WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The Account with id : "+this.getId()+" was deleted successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    
    
}
