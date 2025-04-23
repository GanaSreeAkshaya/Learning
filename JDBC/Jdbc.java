package apps.Course.Jdbc.JDBC;
import java.sql.*;
import java.util.Scanner;
public class Jdbc {
    public static void displayAllAccounts(Connection con) throws SQLException{
        Statement st=con.createStatement();
        ResultSet brs=st.executeQuery("Select *from accounts;");
        while(brs.next()){
                System.out.println(brs.getInt(1)+" "+brs.getString(2)+" "+brs.getInt(3));
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try {
            Connection con=DBConnection.getConnection();
            con.setAutoCommit(false);
            System.out.println("Enter your account number: ");
            int debitId=sc.nextInt();sc.nextLine();
            Account ac=new Account(debitId,con);
            while(true){
            System.out.println("\nSelect the action to be done\n1.Display All Accounts\n2.Fetch Balance\n3.Transfer Money\n4.Exit\n");
            int op=sc.nextInt();sc.nextLine();
            switch(op){
                case 1:
                displayAllAccounts(con);
                break;
                case 2:
                System.out.println("Current Balance: "+ac.getBalance());break;
                case 3:
                System.err.println(ac.getName()+" Please Enter the accountid to be credited to: ");
            int creditId=sc.nextInt();sc.nextLine();
            System.out.println("Enter amount: ");
            int amount=sc.nextInt();sc.nextLine();
            ac.setBalance(creditId, amount);
            break;
            case 4:
            System.out.println("Exiting...");
            return;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
