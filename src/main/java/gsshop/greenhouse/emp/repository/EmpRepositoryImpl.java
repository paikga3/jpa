package gsshop.greenhouse.emp.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.TypedQuery;

import org.hibernate.transform.Transformers;

import gsshop.greenhouse.emp.SalaryInfoByJobVO;

public class EmpRepositoryImpl implements EmpRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Emp> findByEnameIn(String param) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select e ")
		  .append(" from Emp e ")
		  .append(" where (1, e.ename) in ")
		  .append(param);
		
		TypedQuery<Emp> query = entityManager.createQuery(sb.toString(), Emp.class);
		
		return query.getResultList();
	}

	@Override
	public List<Emp> findByPairDeptnoAndJob(Set<String> queryString) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT E ")
		  .append(" FROM   Emp E ")
		  .append(" WHERE  (E.deptno, E.job) IN (:queryString) ");
		
		TypedQuery<Emp> query = entityManager.createQuery(sb.toString(), Emp.class);
		query.setParameter("queryString", queryString);
		
		return query.getResultList();
	}

	@Override
	public List<SalaryInfoByJobVO> findBySalaryInfoByJob(Long deptNo) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ")
		  .append("        A.JOB AS JOB ")
		  .append("       ,SUM(A.SAL) AS SAL")
		  .append("       ,SUM(A.COMM) AS COMM ")
		  .append(" FROM ")
		  .append("     ( SELECT ")
		  .append("             E.JOB ")
		  .append("            ,E.ENAME ")
		  .append("            ,MAX(E.SAL) AS SAL ")
		  .append("            ,MAX(NVL(E.COMM,0)) AS COMM ")
		  .append("        FROM EMP E ")
		  .append("       WHERE E.DEPTNO = :deptNo ")
		  .append("       CONNECT BY PRIOR E.EMPNO = E.MGR ")
		  .append("       START WITH E.ENAME = 'KING' ")
		  .append("       GROUP BY E.JOB,E.ENAME ) A ")
		  .append(" GROUP BY A.JOB ")
		  .append(" ORDER BY A.JOB ");
		
		// 1. 첫번째 방법 @SqlResultSetMapping 사용하기
//		Query query = entityManager.createNativeQuery(sb.toString(), "SalaryInfoByJobMapping");
//		query.setParameter("deptNo", deptNo);
//		List<SalaryInfoByJobVO> resultList = query.getResultList();
		// 2. 두번째 방법
		List<SalaryInfoByJobVO> resultList = new ArrayList<>();
		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter("deptNo", deptNo);
		List<Object[]> queryList = query.getResultList();
		for (Object[] obj : queryList) {
			resultList.add(new SalaryInfoByJobVO(obj));
		}
		
		return resultList;
	}
}
