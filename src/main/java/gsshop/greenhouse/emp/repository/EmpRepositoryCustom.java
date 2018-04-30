package gsshop.greenhouse.emp.repository;

import java.util.List;

public interface EmpRepositoryCustom {
	List<Emp> findByEnameIn(String param);
}
