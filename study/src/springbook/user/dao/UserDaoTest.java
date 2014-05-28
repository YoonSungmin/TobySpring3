package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDaoTest {
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		JUnitCore.main("springbook.user.dao.UserDaoTest");
	}
	
	@Test
	public void addAndGet() throws SQLException{
		
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user1= new User("kenshin1", "윤성민1", "1fjqm84");
		User user2= new User("kenshin2", "윤성민2", "2fjqm84");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());		
		assertThat(user1.getName(), is(userget1.getName()));
		assertThat(user1.getPassword(), is(userget1.getPassword()));
		
		User userget2 = dao.get(user2.getId());		
		assertThat(user2.getName(), is(userget2.getName()));
		assertThat(user2.getPassword(), is(userget2.getPassword()));
		
	}
	
	@Test
	public void count() throws SQLException{

		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user1= new User("kenshin1", "윤성민1", "1fjqm84");
		User user2= new User("kenshin2", "윤성민2", "2fjqm84");
		User user3= new User("kenshin3", "윤성민3", "3fjqm84");		
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
		
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException{

		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get("unknown_id");//이 메소드 실행 중에 예외(EmptyResultDataAccessException)가 발생해야 한다. 예외가 발생하지 않으면 테스트가 실패한다
		
	}
	
}
