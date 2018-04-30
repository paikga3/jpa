package gsshop.greenhouse.user.repository;

import java.util.List;

public interface TestUserRepositoryCustom {
	List<TestUser> findByCustomUsernameIn(String names);
	
	List<TestUser> findByLimitUser(int pageIdx, int rowsPerPage);
}
