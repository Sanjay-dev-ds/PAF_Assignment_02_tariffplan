package Resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class TariffPlan {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tariffplan", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
// Insert new record to tariff plan
	public String CreateTariffPlan(String ET_ID, String Tarrif_Block, String Unit_Rate, String Fixed_Charge) {
	
		String output = "";
		try {
			 Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into electricity_tariff_plan " + "	(ET_ID,Tarrif_Block, Unit_Rate, Fixed_Charge)"
					+ " values (?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, ET_ID);
			preparedStmt.setString(2, Tarrif_Block);
			preparedStmt.setDouble(3,Double.parseDouble(Unit_Rate));
			preparedStmt.setDouble(4, Double.parseDouble(Fixed_Charge));

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPlans = GetTariffPlan();
			output = "{\"status\":\"success\", \"data\": \"" + newPlans + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the plan\"}";
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return output;

	}
	// Delete a record from tariff plan by it's ET_ID

	public String DeleteTariffPlan(String TariffID) {
		String output = "";
		 
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from electricity_tariff_plan where ET_ID = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, TariffID);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPlans = GetTariffPlan();
			output = "{\"status\":\"success\", \"data\": \"" + newPlans + "\"}";
		
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the plan\"}";
			e.printStackTrace();
		}

		return output;
	}

	// Update Existing record in Tariff plan table
	public String UpdateTariffPlan(String eT_ID, String tariff_Block, String  ur, String  fc) {
		String output = "";
		 
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE electricity_tariff_plan SET Tarrif_Block = ? , Unit_Rate = ?, Fixed_Charge = ? WHERE ET_ID =? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, tariff_Block);
			preparedStmt.setDouble(2,Double.parseDouble(ur));
			preparedStmt.setDouble(3, Double.parseDouble(fc));
			preparedStmt.setString(4, eT_ID);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPlans = GetTariffPlan();
			output = "{\"status\":\"success\", \"data\": \"" + newPlans + "\"}";
		
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the plan\"}";
			e.printStackTrace();
		}

		return output;
	}


	public String GetTariffPlan() {
		String output = "";
		
		try {
			 Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			output = "<table id='Tariff' border='1'><tr><th>Tariff_ID </th>" + "<th> Tariff_Block </th><th>Unite_Rate</th>"
					+ "<th>Fixed_Charge </th>" + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from electricity_tariff_plan ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
			
				String TariffID = rs.getString("ET_ID");
				String TariffBlock = rs.getString("Tarrif_Block");
				String Unit_Rate = Double.toString(rs.getDouble("Unit_Rate"));
				String Fixed_Charge = Double.toString(rs.getDouble("Fixed_Charge"));

				
				// Add into the html table
				output += "<tr><td  ><input id='hidTariffIDUpdate'name='hidTariffIDUpdate'type='hidden' value='" + TariffID
						+ "'>" +  TariffID + "</td>";
				output += "<td >"+ TariffBlock + "</td>";
				output += "<td   >" + Unit_Rate + "</td>";
				output += "<td  >" + Fixed_Charge + "</td>";
				// buttons
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"
						+ TariffID + "'>" + "</td></tr>";

				
			}

			con.close();
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the items...";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
