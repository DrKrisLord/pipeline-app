package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.AccountInfo;
import com.revature.pojos.User;
import com.revature.pojos.UserInformation;
import com.revature.util.ConnectionFactory;  

public class UserDao {
	
	public List<User> findAll(){
		List<User> Users = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Bank_User");
			while(rs.next()) {
				User a = new User();
				a.setId(rs.getInt(1));
				a.setFirstName(rs.getString(2));
				a.setLastName(rs.getString(3));
				a.setEmail(rs.getString(4));
				a.setUsername(rs.getString(5));
				a.setPassword(rs.getString(6));
				Users.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Users;
	}
	
	public User getUserByUserName(String user) {
		User b =  null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM bank_user WHERE lower(loginname)=?");
	        ps.setString(1, user.toLowerCase());
	        ResultSet rs = ps.executeQuery();
	        if(rs.next())
	        {
	        	b = new User();
	        	b.setId(rs.getInt(1));
				b.setFirstName(rs.getString(2));
				b.setLastName(rs.getString(3));
				b.setEmail(rs.getString(4));
				b.setUsername(rs.getString(5));
				b.setPassword(rs.getString(6));
	            return b;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return b;
	}
	
	public UserInformation getUserInfo(User u) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select acc.accountid, acc.balance, type.name " + 
					"from bank_account_new acc " + 
					"inner join bank_account_type type " + 
					"on acc.ACCOUNTTYPE = type.typeid " + 
					"where acc.USERID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());

			UserInformation info = new UserInformation();
			info.setUser(u);
			List<AccountInfo> accounts = new ArrayList<AccountInfo>();

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				AccountInfo temp = new AccountInfo();
				temp.setId(rs.getInt(1));
				temp.setBalance(rs.getDouble(2));
				temp.setType(rs.getString(3));
				accounts.add(temp);
			}
			info.setAccounts(accounts);
			return info;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
