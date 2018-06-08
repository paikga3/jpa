package gsshop.greenhouse.emp.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpRepository extends JpaRepository<Emp, Long>, EmpRepositoryCustom {
	
	Emp findByEmpno(Long empno);
	
	
	@Query(" SELECT COALESCE(SUM(CASE WHEN E.comm IS NULL THEN 0L ELSE 1L END)) FROM Emp E WHERE E.deptno = :deptno ")
	Long countCommByDeptno(@Param("deptno") Long deptno);
	
	List<Emp> findByEmpnoInAndEname(Set<Long> empnoSet, String ename);
}