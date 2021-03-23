package com.sujata.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sujata.bean.Share;

public class ShareDaoImpl implements ShareDao {

	@Override
	public ArrayList<Share> getAllShareRecords() throws ClassNotFoundException,SQLException {
		//JDBC Code
		//1 Connect
		//1.1 Register Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//1.2 Connect to DB : Connection: getConnection(url of mysql,username of mysql, password of my sql)
		//getConnection(url of oracle, username of oracle,password of oracle) :factory design pattern
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cititrainings", "Rutuja", "Rutuja@123");  
		//Connection connection=new OraclerConnection(); , we are not doing it
		//2. Query
		PreparedStatement preparedStatement=connection.prepareStatement("select * from share");
		//executeQuery() is used to execute DQL Query : select
		ResultSet resultSet=preparedStatement.executeQuery();
		
		ArrayList<Share> shareList=new ArrayList<Share>();
		
		//3. Processing the Result
		while(resultSet.next()) {
			Share share=new Share();
			share.setInstrumentId(resultSet.getInt("instrumentId"));
			share.setInstrumentName(resultSet.getString("instrumentName"));
			share.setMarketRate(resultSet.getInt("marketRate"));
			
			shareList.add(share);
			
			
		}
		//4. Close 
		connection.close();
		return shareList;
	}

	@Override
	public boolean insertShareRecord(Share share) throws ClassNotFoundException, SQLException {
				//JDBC Code
				//1 Connect
				//1.1 Register Driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//1.2 Connect to DB
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cititrainings","Rutuja", "Rutuja@123");
				
				//2. Query
				PreparedStatement preparedStatement=connection.prepareStatement("insert into Share values(?,?,?)");
				preparedStatement.setInt(1, share.getInstrumentId());
				preparedStatement.setString(2, share.getInstrumentName());
				preparedStatement.setInt(3, share.getMarketRate());
				
				//executeUpdate() is used to execute DML Query : Data Manipulation Language (Insert, delete, update)
				int rows=preparedStatement.executeUpdate();
				
				connection.close();
				
				if(rows>0)
					return true;
				
				return false;
	}

	@Override
	public Share searchShareById(int shareId) throws ClassNotFoundException, SQLException {
		//JDBC Code
				//1 Connect
				//1.1 Register Driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//1.2 Connect to DB
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cititrainings", "Rutuja", "Rutuja@123");
				
				//2. Query
				PreparedStatement preparedStatement=connection.prepareStatement("select * from share where instrumentId=?");
				preparedStatement.setInt(1, shareId);
				
				//executeQuery() is used to execute DQL Query : select
				ResultSet resultSet=preparedStatement.executeQuery();
				
				Share share=null;
				
				if(resultSet.next()) {
					share=new Share();
					share.setInstrumentId(resultSet.getInt("instrumentId"));
					share.setInstrumentName(resultSet.getString("instrumentName"));
					share.setMarketRate(resultSet.getInt("marketRate"));
				}
					
		return share;
	}

	@Override
	public boolean deleteShareResordById(int shareId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//1.create connection
		//1.1 register driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//1.2 connect to database
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cititrainings", "Rutuja", "Rutuja@123");
		//2.Query
		PreparedStatement preparedStatement = connection.prepareStatement("Delete from share where instrumentId=?");
		preparedStatement.setInt(1, shareId);
		
		//3.execute query 
		int rows = preparedStatement.executeUpdate();
		if(rows>0) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateShareById(int shareId, int marketRate) throws ClassNotFoundException, SQLException {
		//1.create connection
		//1.1 register driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//1.2 connect to database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cititrainings","Rutuja","Rutuja@123");
		//3.write query
		PreparedStatement preparedStatement = connection.prepareStatement("update share set marketRate=? where instrumentId=?");
		preparedStatement.setInt(1, marketRate);
		preparedStatement.setInt(2, shareId);
		
		//4.execute query
		int rows = preparedStatement.executeUpdate();
		if(rows>0)
			return true;
		// TODO Auto-generated method stub
		return false;
	}

}
