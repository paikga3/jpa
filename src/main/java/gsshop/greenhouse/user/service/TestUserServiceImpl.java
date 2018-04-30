package gsshop.greenhouse.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gsshop.greenhouse.user.repository.TestUser;
import gsshop.greenhouse.user.repository.TestUserRepository;

@Service
public class TestUserServiceImpl implements TestUserService {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private TestUserRepository testUserRepository;
	
	@Override
	public Long getSeq() {
		entityManager.setFlushMode(FlushModeType.COMMIT);
		TestUser user = new TestUser();
		entityManager.persist(user);
		Long seq = user.getNum(); 

		return seq;
	}
	
	@Override
	public void createTestUsers() {
		Set<TestUser> set = new HashSet<>();
		for (int i = 0; i < 100000; i++) {
			TestUser user = new TestUser();
			user.setName("홍길동" + i);
			user.setContents("안녕하세요" + i);
			System.out.println(user.getContents());
			set.add(user);
		}
		testUserRepository.save(set);
	}

	@Override
	public List<TestUser> findByCustomUsernameIn(String names) {
		return testUserRepository.findByCustomUsernameIn(names);
	}
}