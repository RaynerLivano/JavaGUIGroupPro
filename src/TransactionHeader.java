import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class TransactionHeader {
	String transactionID;
	
	public TransactionHeader(String transactionID2) {
		// TODO Auto-generated constructor stub
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	String userID;
	
	public void insert() {
		Connect connect = Connect.getInstance();
		String query = "INSERT INTO transaction (transactionID, userID) VALUES (?, ?)";
		PreparedStatement s = connect.prepareStatement(query);
		try {
			s.setString(1, this.transactionID);
			s.setString(2, this.userID);
			
			
			
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}