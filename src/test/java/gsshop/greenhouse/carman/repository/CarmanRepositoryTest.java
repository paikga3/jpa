package gsshop.greenhouse.carman.repository;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gsshop.greenhouse.carman.repository.Carman;
import gsshop.greenhouse.carman.repository.CarmanRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarmanRepositoryTest {
	
	@Autowired
	private CarmanRepository carmanRepository;
	
	@Test
	public void assertSave() {
		Date inDate = new Date();
		Carman in = new Carman();
		in.setModseq(1L);
		in.setUserid("TEST_USER");
		in.setCarnumnam("제네시스");
		in.setInsdat(inDate);
		in.setDelyn("N");
		
		Carman saved = carmanRepository.save(in);
		Carman out = carmanRepository.getOne(saved.getModseq());
		
		Assert.assertEquals(saved.getUserid(), out.getUserid());
	}
}