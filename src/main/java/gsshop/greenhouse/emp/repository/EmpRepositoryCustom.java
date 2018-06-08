package gsshop.greenhouse.emp.repository;

import java.util.List;
import java.util.Set;

public interface EmpRepositoryCustom {
	List<Emp> findByEnameIn(String param);
	
	List<Emp> findByPairDeptnoAndJob(Set<String> queryString);
}
