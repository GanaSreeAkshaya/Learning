package apps.Course.Jdbc;
import java.sql.*;
import java.util.Scanner;

public class Jdbc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try {
            String url = "jdbc:mysql://localhost:3306/testDb";
            String userName = "root";
            String password = "Akshaya@888";
            Connection con = DriverManager.getConnection(url, userName, password);
            con.setAutoCommit(false); // Start transaction

            PreparedStatement balance=con.prepareStatement("select balance from accounts where id=?;");
            PreparedStatement credit=con.prepareStatement("update accounts set balance=balance+? where id=?;");
            PreparedStatement debit=con.prepareStatement("update accounts set balance=balance-? where id=?;");
            Statement st=con.createStatement();
            ResultSet brs=st.executeQuery("Select *from accounts;");
            System.out.println("before");
            while(brs.next()){
                System.out.println(brs.getInt(1)+" "+brs.getString(2)+" "+brs.getInt(3));
            }
            System.err.println("Enter the accountid to be debited from: ");
            int debitId=sc.nextInt();sc.nextLine();
            System.err.println("Enter the accountid to be credited to: ");
            int creditId=sc.nextInt();sc.nextLine();
            System.out.println("Enter amount: ");
            int amount=sc.nextInt();sc.nextLine();
            balance.setInt(1,debitId);
            ResultSet rs=balance.executeQuery();
            if(rs.next()){
                if(rs.getInt(1)>=amount){
                    try{
                    credit.setInt(1, amount);
                    credit.setInt(2, creditId);
                    credit.executeUpdate();
                    debit.setInt(1, amount);
                    debit.setInt(2, debitId);
                    debit.executeUpdate();
                    con.commit();
                    }
                    catch(Exception e){
                        con.rollback();
                        System.out.println("Transaction failed");
                    }
                }
                else{
                    System.out.println("Insufficient balance..!");
                    con.rollback();
                }
            }
            else{
                System.out.println("..................transaction failed");
            }
            
            ResultSet prs=st.executeQuery("Select *from accounts;");
            while(prs.next()){
                System.out.println(prs.getInt(1)+" "+prs.getString(2)+" "+prs.getInt(3));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hi to JDBC!");
    }
}
