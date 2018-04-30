package gsshop.greenhouse.rank.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TEST_RANK")
@Data
public class TestRank {
	@Id
	private Long empno;
	private Long deptno;
	private Long point;
}