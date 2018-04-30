package gsshop.greenhouse.user.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class TestUserRepositoryImpl implements TestUserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TestUser> findByCustomUsernameIn(String names) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select tu ")
		  .append(" from TestUser tu ")
		  .append(" where ('1',tu.name) in ")
		  .append(names);
		
		TypedQuery<TestUser> query = entityManager.createQuery(sb.toString(), TestUser.class);
		
		return query.getResultList();
	}

	@Override
	public List<TestUser> findByLimitUser(int pageIdx, int rowsPerPage) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT tu ")
		  .append(" FROM   TestUser tu ");
		
		TypedQuery<TestUser> query = entityManager.createQuery(sb.toString(), TestUser.class);
		
		int from = (pageIdx - 1) * rowsPerPage;
		
		query.setFirstResult(from);
		query.setMaxResults(rowsPerPage);
		
		
		return query.getResultList();
	}

}
