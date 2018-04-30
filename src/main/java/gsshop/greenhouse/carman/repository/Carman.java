package gsshop.greenhouse.carman.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "CARMAN")
public class Carman {
	@Id
	private Long modseq;
	private String userid;
	private String carnumnam;
	@Temporal(TemporalType.TIMESTAMP)
	private Date insdat;
	private String delyn;
	@Temporal(TemporalType.TIMESTAMP)
	private Date deldat;
}