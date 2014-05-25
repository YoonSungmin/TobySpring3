package springbook.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{

		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		dao.del();
		
		System.out.println("users테이블 삭제");
		
		User user = new User();
		user.setId("onlylovu");
		user.setName("윤성민");
		user.setPassword("fjqm84");
		
		dao.add(user);
		
		System.out.println("등록성공\n" + "ID  : " + user.getId() + "\n" + "NAME  : " + user.getName() + "\n" +  "PASSWORD  : " + user.getPassword());
		
		ArrayList<User> userList = dao.get(user.getId());
		Iterator<User> userEIterator = userList.iterator();
		
		while(userEIterator.hasNext()){
			User resultUser = (User)userEIterator.next();
			System.out.println("조회성공\n" + "ID  : " + resultUser.getId() + "\n" + "NAME  : " + resultUser.getName() + "\n" +  "PASSWORD  : " + resultUser.getPassword());
		}
	}
}
