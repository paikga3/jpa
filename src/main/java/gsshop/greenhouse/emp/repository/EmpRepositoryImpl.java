package gsshop.greenhouse.emp.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
}
