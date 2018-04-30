package gsshop.greenhouse.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import gsshop.greenhouse.emp.UserInfoVo;

public interface EmpRepository extends JpaRepository<Emp, Long>, EmpRepositoryCustom {
	
	Emp findByEmpno(Long empno);
	
}