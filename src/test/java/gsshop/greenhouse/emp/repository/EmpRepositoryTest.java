package gsshop.greenhouse.emp.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gsshop.greenhouse.util.GreenHouseCommonUtil;
import gsshop.greenhouse.util.RepositorySequentialQuery;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpRepositoryTest {
	@Autowired
	private EmpRepository empRepository;
	
//	@Rollback(false)
//	@Test
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
	
//	@Test
	public void countCommByDeptno() {
		Long deptno = 20L;
		Long cnt = empRepository.countCommByDeptno(deptno);
		
		System.out.println(cnt);
	}
	
//	@Test
	public void findByPairDeptnoAndJob() {
		
//		String queryString = "('10','PRESIDENT'),('30','SALESMAN'),('40','CLERK')";
		Set<String> queryString = new HashSet<>();
		queryString.add("\\(\\'10\\'\\, \\'PRESIDENT\\'\\)");
//		queryString.add("('30', 'SALESMAN')");
//		queryString.add("('40', 'CLERK')");
		List<Emp> list = empRepository.findByPairDeptnoAndJob(queryString);
		System.out.println(list);
	}
	
	@Test
	public void findByEmpnoIn() {
		String ename = "BLAKE";
		Set<Long> paramSet2 = new HashSet<>();
		for (long i=0; i < 8001; i++) {
			paramSet2.add(i);
		}
//		paramSet.add(7698L);
		List<Emp> list = GreenHouseCommonUtil.getSequentialQueryList(new RepositorySequentialQuery<Emp, Long>() {
			@Override
			public List<Emp> getResultList(Set<Long> paramSet) {
				return empRepository.findByEmpnoInAndEname(paramSet, ename);
			}
			
		}, paramSet2);
		
		System.out.println(list);
	}
}