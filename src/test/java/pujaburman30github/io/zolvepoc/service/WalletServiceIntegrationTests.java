package pujaburman30github.io.zolvepoc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.service.WalletService;

@SpringBootTest
class WalletServiceIntegrationTests {

	@Autowired
	private WalletService service;

	@Test
	void happyPathForAddUser(){
		User user = User.builder().firstName("PK").balance(100).lastName("Kumar").build();
		User savedUser = service.createUser(user);
		Assertions.assertNotNull(savedUser);
		Assertions.assertNotNull(savedUser.getId());
	}

}
