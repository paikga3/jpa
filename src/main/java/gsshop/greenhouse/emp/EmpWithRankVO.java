package gsshop.greenhouse.emp;

import lombok.Data;

@Data
public class EmpWithRankVO {
	private Long seq;
	private Long rk;
	private Long empno;
	private String ename;
	private String job;
	private Long sal;
}
