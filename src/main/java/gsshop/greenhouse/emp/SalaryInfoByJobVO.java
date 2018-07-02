package gsshop.greenhouse.emp;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SalaryInfoByJobVO {
	
	private String job;
	private Long sal;
	private Long comm;
	
	// 첫번재방법
	public SalaryInfoByJobVO(String job, Long sal, Long comm) {
		super();
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	
	// 두번째방법
	public SalaryInfoByJobVO(Object[] columns) {
		this.job = (String)columns[0];
		this.sal = ((BigDecimal)columns[1]).longValue();
		this.comm = ((BigDecimal)columns[2]).longValue();
	}
	
}
