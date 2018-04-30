package gsshop.greenhouse.user.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TEST_USER")
public class TestUser {
	@Id
	@SequenceGenerator(
		name="TEST_USER_SEQ",
		sequenceName="TEST_USER_SEQ",
		allocationSize=1
	)
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEST_USER_SEQ")
	private Long num;
	private String name;
	private String contents;
}