package gsshop.greenhouse.emp.repository;

import java.util.List;
import java.util.Set;

import gsshop.greenhouse.emp.SalaryInfoByJobVO;

public interface EmpRepositoryCustom {
	List<Emp> findByEnameIn(String param);
	
	List<Emp> findByPairDeptnoAndJob(Set<String> queryString);
	
	List<SalaryInfoByJobVO> findBySalaryInfoByJob(Long deptNo);
}
