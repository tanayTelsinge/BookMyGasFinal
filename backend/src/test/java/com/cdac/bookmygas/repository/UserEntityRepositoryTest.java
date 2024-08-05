package com.cdac.bookmygas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.bookmygas.entity.User;
import com.app.bookmygas.repository.UserRepository;
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserEntityRepositoryTest {

	// dep
		@Autowired
		private UserRepository userRepo;

		@Autowired
		private PasswordEncoder enc;

		@Test
	void testAddUsers() {
			List<User> list = List.of(
					new User("a1", "a1@gmail.com", enc.encode("12345"), "admin"),
					new User("a2", "a2@gmail.com", enc.encode("2345"), "user"));
			List<User> list2 = userRepo.saveAll(list);
			assertEquals(3, list2.size());

		}

}
