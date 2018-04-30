package gsshop.greenhouse.user.service;

import java.util.List;

import gsshop.greenhouse.user.repository.TestUser;

public interface TestUserService {
	Long getSeq();
	
	void createTestUsers();
	
	List<TestUser> findByCustomUsernameIn(String names);
}
