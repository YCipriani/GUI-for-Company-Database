import java.sql.*;
import java.util.*;
import java.lang.*;
import java.io.*;

/*
 * Author: Yonatan Cipriani
 * G Number: G0093773
 */

public class Company {
	private Connection conn = null; 
	private Statement stmt = null;
	private ResultSet r = null;

	public boolean initiateConnection() throws SQLException, IOException {
	
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException x) {
			System.out.println("Driver could not be loaded");
			return false;
		}
		this.conn = DriverManager.getConnection("jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", "yciprian", "eersef");
		return true;
	}
	
	public ResultSet executeQuery(String query)
	{
		if(this.conn== null) {
			System.out.println("Not connected to Oracle, can't process query\n");
			return null;
		}
		try {
		stmt = this.conn.createStatement();
		this.r = stmt.executeQuery(query);
		if(r == null) {
			System.out.println("Query result is empty");
			return null;
		}
		return r;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void printResearchEmployeesQuery(ResultSet rset, String query) throws SQLException
	{
		System.out.println("SQL Query: " + query + "\n");
		System.out.println("LNAME\t\tSSN");
		System.out.println("_________________________");
		while (rset.next()) {
			System.out.println(rset.getString(1) + "\t\t" + rset.getString(2));
			}
		System.out.print("\n");
	}
	
	public void printHoustonEmployeesQuery(ResultSet rset, String query) throws SQLException
	{
		System.out.println("SQL Query: " + query + "\n");
		System.out.println("LNAME\t\tSSN\t\tPROJECT_HOURS");
		System.out.println("_______________________________________________");
		while (rset.next()) {
			System.out.println(rset.getString(1) + "\t\t" + rset.getString(2) + "\t\t" +rset.getString(3));
			}
		System.out.print("\n");
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		Company c = new Company();
		ResultSet result;
		if(c.initiateConnection() == false)
		{
			return;
		}
		System.out.println("Successfully connected to Oracle\n");
		String research_query = "select lname, ssn from  employee, department where dno=dnumber AND dname='Research'";
		result = c.executeQuery(research_query);
		c.printResearchEmployeesQuery(result, research_query);
		String houston_productz = "select Lname , ssn , hours from EMPLOYEE e, PROJECT p, WORKS_ON w where (e.ssn = w.essn AND w.pno = p.pnumber AND p.pname = 'ProductZ')";
		result = c.executeQuery(houston_productz);
		c.printHoustonEmployeesQuery(result, houston_productz);
		
	}
	

}
