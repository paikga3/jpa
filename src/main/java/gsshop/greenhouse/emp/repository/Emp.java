package gsshop.greenhouse.emp.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "EMP")
public class Emp {
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMP_SEQ_NAME")
	@SequenceGenerator(allocationSize=1, sequenceName="EMP_SEQ", name="EMP_SEQ_NAME")
	private Long empno;
	private String ename;
	private String job;
	
	private Long mgr;
	@Temporal(TemporalType.TIMESTAMP)
	private Date hiredate;
	private Long sal;
	private Long comm;
	private Long deptno;
	public Long getEmpno() {
		this.setEname("홍길동");
		return empno;
	}

//	@OneToOne
//	@JoinColumn(name="MGR", insertable=false, updatable=false)
//	private Emp mgrEmp;
//	
//	@OneToMany
//	@JoinColumn(name="MGR")
//	private List<Emp> descendants;
}