package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import springbook.user.domain.User;

public class UserDao {
	private ConnectionMaker connectionMaker;

//	public UserDao(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
	
	public void setConnectionMaker(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}

	public void del() throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("delete from users");

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		PreparedStatement ps = c
				.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public ArrayList<User> get(String id) throws ClassNotFoundException,
			SQLException {
		Connection c = this.connectionMaker.makeConnection();
		PreparedStatement ps = c
				.prepareStatement("select id, name, password from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		ArrayList<User> userList = new ArrayList<User>();

		while (rs.next()) {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			userList.add(user);
		}

		rs.close();
		ps.close();
		c.close();

		return userList;
	}
}
