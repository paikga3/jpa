package gsshop.greenhouse.dept.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "DEPT")
public class Dept {
	@Id
	private Long deptno;
	private String dname;
	private String loc;
}
