import java.sql.*;
import javax.swing.*;
import java.util.*;
import net.proteanit.sql.DbUtils;
public class ConnCode
{
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
     String JDBC_DRIVER="com.mysql.jdbc.Driver";
    String DB_URL="jdbc:mysql://localhost/royals";
    String USER="root";
    String PASS="12345";
    
    public ConnCode()
    {
        
    }
    public String getDBURL()
    {
        return DB_URL;
    }
    public String getFromDb(String sql,String column)
    {
        String n="";
        try
        {
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                n=rs.getString(column);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return n;
    }
    public int insertIntoDatabase(String sql ,String pri)
    {
        int x=0;
        try
        {
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
            stmt.executeUpdate(sql);
            x=1;
        }
        catch(Exception ex){
            x=2;
            String error="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '"+pri+"' for key 'PRIMARY'";
            String err=ex.toString();
            if(err.equals(error))
            {
               
            }
            else
            JOptionPane.showMessageDialog(null,ex);
            
        }
        return x;
    }
    public void populateComboBox(String sql,String column,JComboBox combo)
    {
        try
        {
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                combo.addItem(rs.getString(column));
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void popTable(String sql,JTable table)
    {
        try
        {
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void getFromDatabase(String sql, ArrayList<String> Columns,ArrayList<String> results)
    {
        try
        {
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
            for(int a=0;a<Columns.size();a++){
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                results.add(a,rs.getString(Columns.get(a)));
            }
           }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }

    }
    public boolean testConnection(){
       boolean t=false;
        try{
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
            String sql="show tables;";
            rs=stmt.executeQuery(sql);
            t=true;
        }
        catch(Exception ex){
            String err= "any packets from the server.";
            String ex1=ex.toString().substring(186);
            if(err.equals(ex1)){
                JOptionPane.showMessageDialog(null,"Your app Was unable o reach the server. "
                        + "\nPlease check your network Connection. Then try again","Connection to Server Failure",JOptionPane.ERROR_MESSAGE);
            }else{
            JOptionPane.showMessageDialog(null,ex);
            }
        }
         return t;
    }

}

/*
try
        {
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }



*/