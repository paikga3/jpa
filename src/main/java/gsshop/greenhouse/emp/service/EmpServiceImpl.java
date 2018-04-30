package gsshop.greenhouse.emp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import gsshop.greenhouse.emp.EmpWithRankVO;
import gsshop.greenhouse.emp.repository.Emp;
import gsshop.greenhouse.emp.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpRepository empRepository;
	
	@Autowired
	private org.springframework.transaction.PlatformTransactionManager platformTransactionManager;
	
	@Override
	public List<EmpWithRankVO> findByRankOfJobAndSal() {
		
		List<EmpWithRankVO> resultList = new ArrayList<>();
		List<EmpWithRankVO> empList = empRepository.findAll().stream().map(a -> {
			EmpWithRankVO vo = new EmpWithRankVO();
			vo.setEmpno(a.getEmpno());
			vo.setEname(a.getEname());
			vo.setJob(a.getJob());
			vo.setSal(a.getSal());
			return vo;
		}).collect(Collectors.toList());
		
		
		Map<String, List<EmpWithRankVO>> map = empList.stream().collect(Collectors.groupingBy(a -> a.getJob() + "," + a.getSal() ));
		
		List<String> keyList = map.keySet().stream().sorted((a,b) -> a.compareTo(b)).collect(Collectors.toList());
		
		long rank = 1L;
		long beforeSize = 0L;
		
		for (String key : keyList) {
			List<EmpWithRankVO> groupList = map.get(key);
			rank += beforeSize;
			
			for (EmpWithRankVO vo : groupList) {
				vo.setRk(rank);
			}
			beforeSize = groupList.size();
			
			resultList.addAll(groupList);
		}
		
		resultList = resultList.stream().sorted( (a,b) -> {
			if (a.getJob().compareTo(b.getJob()) == 0) {
				return a.getSal().compareTo(b.getSal());
			} else {
				return a.getJob().compareTo(b.getJob());
			}
		}).collect(Collectors.toList());
		
		return resultList;
	}

	@Transactional(readOnly = false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRES_NEW)
	@Override
	public void saveEmp() {
		
		TransactionSynchronizationManager.setCurrentTransactionName("abc");
		
		Date currentDate = new Date();
		
		Emp emp = new Emp();
		emp.setEmpno(1000L);
		emp.setEname("백종현");
		emp.setJob("BOSS");
		emp.setHiredate(currentDate);
		emp.setSal(430L);
		emp.setComm(30L);
		emp.setDeptno(10L);
		
		empRepository.saveAndFlush(emp);
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		platformTransactionManager.commit(platformTransactionManager.getTransaction(def));
		
		Map<String,String> pMap = new HashMap<>();
		pMap.put("empno", String.valueOf(emp.getEmpno()));
		
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		System.out.println("transaction 1 : " + platformTransactionManager.getTransaction(def));
		
		System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
		
		this.confirmEmp(pMap);
		
	}
	
	@Transactional(readOnly = false)
	private void confirmEmp(Map<String,String> map) {
		
		
		
		Date currentDate = new Date();
		Emp emp = new Emp();
		emp.setEmpno(1001L);
		emp.setEname("백종현");
		emp.setJob("BOSS");
		emp.setHiredate(currentDate);
		emp.setSal(430L);
		emp.setComm(30L);
		emp.setDeptno(10L);
		
		empRepository.saveAndFlush(emp);
		
		System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
		
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		System.out.println("transaction 2 : " + platformTransactionManager.getTransaction(def));
//		System.out.println(emp);
		throw new RuntimeException("hi");
	}

}
