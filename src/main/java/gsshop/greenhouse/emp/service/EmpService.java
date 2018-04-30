package gsshop.greenhouse.emp.service;

import java.util.List;

import gsshop.greenhouse.emp.EmpWithRankVO;

public interface EmpService {
	List<EmpWithRankVO> findByRankOfJobAndSal();
	
	void saveEmp();
}
