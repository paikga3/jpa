package gsshop.greenhouse.dept.repository;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gsshop.greenhouse.emp.repository.Emp;
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
