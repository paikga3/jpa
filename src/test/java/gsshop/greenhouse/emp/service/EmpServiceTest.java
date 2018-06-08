package gsshop.greenhouse.emp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gsshop.greenhouse.emp.EmpWithRankVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpServiceTest {
	@Autowired
	private EmpService empService;
	
//	@Test
	public void assertEmpWithRank() {
		List<EmpWithRankVO> list = empService.findByRankOfJobAndSal();
		
		Assert.assertEquals(11L, list.get(10).getRk().longValue() );
		Assert.assertEquals(11L, list.get(11).getRk().longValue() );
		Assert.assertEquals(13L, list.get(12).getRk().longValue() );
		
		//Assert.assertEquals(7902L, list.get(0).getEmpno().longValue() );
	}
	
//	@Test
	public void saveEmp() {
		empService.saveEmp();
	}
}
