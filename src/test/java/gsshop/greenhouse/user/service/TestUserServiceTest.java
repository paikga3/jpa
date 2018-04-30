package gsshop.greenhouse.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gsshop.greenhouse.user.repository.TestUser;
import gsshop.greenhouse.user.repository.TestUserRepository;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserServiceTest {
	
	@Autowired
	private TestUserRepository testUserRepository;
	
	@Autowired
	private TestUserService testUserService;
	
//	@Test
	public void assertTest() {
		Long seq = testUserService.getSeq();
		Assert.assertEquals(3L, seq.longValue() );
	}
	
//	@Test
//	@Rollback(false)
	public void create() {
		testUserService.createTestUsers();
	}
	
//	@Test
	public void assertFindAll() {
		long start1 = System.currentTimeMillis();
		List<TestUser> list = testUserRepository.findAll();
		long end1 = System.currentTimeMillis();
		
		long start2 = System.currentTimeMillis();
//		for (long i = 4L; i <= 10003L; i++) {
//			TestUser user = testUserRepository.findOne(i);
//		}
		long end2 = System.currentTimeMillis();
		
		long time1 = end1 - start1;
		long time2 = end2 - start2;
		
		System.out.println("첫번째 전체조회 시간 : " + time1);
		System.out.println("건별 전체조회 시간 : " + time2);

		Assert.assertEquals(true, time2 > time1 );
	}
	
//	@Test
	public void assertFindByCustomNameIn() {
		String names = "(" + IntStream.rangeClosed(1, 10000).mapToObj(a -> "('1','성지아" + a + "')").collect(Collectors.joining(",")) + ")";
		Pattern pattern = Pattern.compile("\\('1','[^,]+'\\)");
		Matcher m = pattern.matcher(names);
		
		List<String> nameList = new ArrayList<>();
		int i = 0;
		int bdex = 1;
		while(m.find() ) {
			i++;
			if (i == 1000) {
				nameList.add(names.substring(bdex, m.end()));
				bdex = m.end() + 1;
				i = 0;
			}
		}
		
		List<TestUser> list = new ArrayList<>();
		for (String name : nameList) {
			list.addAll(testUserService.findByCustomUsernameIn("(" + name + ")"));
		}

		Assert.assertEquals(10000, list.size());
	}
	
	@Test
	public void assertLimitJPQL() {
		int pageIdx = 4;
		int rowsPerPage = 25480;
		List<TestUser> list = testUserRepository.findByLimitUser(pageIdx, rowsPerPage);
		
		Assert.assertEquals(23560, list.size());
	}
	
	
}