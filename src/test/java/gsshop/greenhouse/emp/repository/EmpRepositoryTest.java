package gsshop.greenhouse.emp.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpRepositoryTest {
	@Autowired
	private EmpRepository empRepository;
	
	@Rollback(false)
	@Test
	public void saveAndGet() {
		
		Date currentDate = new Date();
		Emp in = new Emp();
//		in.setEmpno(11L);
		in.setEname("백종현");
		in.setComm(100L);
		in.setDeptno(10L);
		in.setHiredate(currentDate);
		in.setJob("PRO");
		in.setSal(1000L);
		
		Emp saved = empRepository.save(in);
		Emp out = empRepository.getOne(saved.getEmpno());
		
		System.out.println(out.getEname());
		
	}
}