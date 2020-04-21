package com.semantyca.yatt.test;

import com.semantyca.yatt.dao.IUserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class YattApplicationTests {

	@Autowired
	IUserDAO userDAO;

	@Test
	void contextLoads() {
		List users = userDAO.findAllUnrestricted(100, 0);
		assertNotNull(users);
	}

}
