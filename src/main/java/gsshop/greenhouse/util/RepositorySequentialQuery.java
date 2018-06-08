package gsshop.greenhouse.util;

import java.util.List;
import java.util.Set;

public interface RepositorySequentialQuery<T,S> {
	
	List<T> getResultList(Set<S> paramSet);
}
