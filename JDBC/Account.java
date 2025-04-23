package apps.Course.Jdbc.JDBC;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account{
     int id;
     int balance;
     Connection con;
    public Account(int id, Connection con){
        this.id=id;
        this.con=con;
    }
    public String getName() throws SQLException{
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select name from accounts where id="+id+";");
        if(rs.next())
        return rs.getString(1);
        return null;
    }
    public int getBalance() throws SQLException{
        PreparedStatement bps=con.prepareStatement("Select balance from accounts where id=?;");
        bps.setInt(1, id);
        ResultSet rs=bps.executeQuery();
        if(rs.next()){
            balance= rs.getInt(1);
        }
        else balance= -1;
        return balance;
    }
    public void refreshBalance() throws SQLException {
        this.balance = getBalance();
    }
    public void setBalance(int creditId,int amount) throws SQLException{
            int debitId=id;
            refreshBalance();
            System.out.println(balance+"-----balance");
            if(balance>=amount){
            PreparedStatement credit=con.prepareStatement("update accounts set balance=balance+? where id=?;");
            PreparedStatement debit=con.prepareStatement("update accounts set balance=balance-? where id=?;");
            try{
                credit.setInt(1, amount);
                credit.setInt(2, creditId);
                int x=credit.executeUpdate();
                debit.setInt(1, amount);
                debit.setInt(2, debitId);
                int y=debit.executeUpdate();
                System.out.println(x+"  "+y);
                if(x>0&&y>0)
                con.commit();
                else {
                    System.out.println("\n......................Transaction failed!\n");
                    con.rollback();
                }
                }
                catch(Exception e){
                    con.rollback();
                    System.out.println("\n......................Transaction failed!\n");
                }
            }
            else if(balance==-1){
                System.out.println("........................Transaction failed");
                con.rollback();
            }
            else{
                System.out.println(".................Insufficient balance!");
                con.rollback();
            }
            refreshBalance();
            return;
    }
}
