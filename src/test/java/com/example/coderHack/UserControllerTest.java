package com.example.coderHack;

import com.example.coderHack.controller.UserController;
import com.example.coderHack.model.User;
import com.example.coderHack.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	public void testGetAllUsers() {
		// Arrange
		when(userService.getAllUsers()).thenReturn(Arrays.asList(new User(), new User()));

		// Act
		ResponseEntity<List<User>> response = userController.getAllUsers();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, ((Iterable<User>) Objects.requireNonNull(response.getBody())).spliterator().getExactSizeIfKnown());
	}

	@Test
	public void testGetUserById() {
		// Arrange
		String userId = "123";
		User user = new User();
		when(userService.getUserById(userId)).thenReturn(Optional.of(user));

		// Act
		ResponseEntity<User> response = userController.getUserById(userId);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	public void testCreateUser() {
		// Arrange
		User user = new User("123" , "Ram");
		when(userService.registerUser(any(User.class))).thenReturn(user);

		// Act
		ResponseEntity<User> response = userController.registerUser(user);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	public void testUpdateUser() {
		// Arrange
		String userId = "123";
		User user = new User();
		when(userService.updateScore(eq(userId))).thenReturn(Optional.of(user));

		// Act
		ResponseEntity<User> response = userController.updateScore(userId, user.getScore());

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

}

