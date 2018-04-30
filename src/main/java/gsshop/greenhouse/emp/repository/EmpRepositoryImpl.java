package gsshop.greenhouse.emp.repository;

import java.util.List;

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
}
