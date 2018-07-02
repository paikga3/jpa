package gsshop.greenhouse.rank.service;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gsshop.greenhouse.rank.RankInfoVO;
import gsshop.greenhouse.rank.repository.TestRankRepository;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRankServiceTest {
	@Autowired
	private TestRankRepository testRankRepository;
	
	@Autowired
	private TestRankService testRankService;
	
	@Test
	public void assertTest() {
		List<RankInfoVO> list = testRankService.getRankInfo();
		
		Assert.assertEquals(9, list.size() );
		
		RankInfoVO vo_01 = list.get(0);
		RankInfoVO vo_02 = list.get(1);
		RankInfoVO vo_03 = list.get(2);
		RankInfoVO vo_04 = list.get(3);
		RankInfoVO vo_05 = list.get(4);
		RankInfoVO vo_06 = list.get(5);
		RankInfoVO vo_07 = list.get(6);
		RankInfoVO vo_08 = list.get(7);
		RankInfoVO vo_09 = list.get(8);
		
		Assert.assertEquals(1L, vo_01.getEmpno().longValue() );
		Assert.assertEquals(10L, vo_01.getDeptno().longValue() );
		Assert.assertEquals(100L, vo_01.getPoint().longValue() );
		Assert.assertEquals(1L, vo_01.getRkAll().longValue() );
		Assert.assertEquals(1L, vo_01.getRkDept().longValue() );
		
		Assert.assertEquals(2L, vo_02.getEmpno().longValue() );
		Assert.assertEquals(10L, vo_02.getDeptno().longValue() );
		Assert.assertEquals(90L, vo_02.getPoint().longValue() );
		Assert.assertEquals(5L, vo_02.getRkAll().longValue() );
		Assert.assertEquals(2L, vo_02.getRkDept().longValue() );
		
		Assert.assertEquals(3L, vo_03.getEmpno().longValue() );
		Assert.assertEquals(10L, vo_03.getDeptno().longValue() );
		Assert.assertEquals(80L, vo_03.getPoint().longValue() );
		Assert.assertEquals(8L, vo_03.getRkAll().longValue() );
		Assert.assertEquals(3L, vo_03.getRkDept().longValue() );
		
		Assert.assertEquals(4L, vo_04.getEmpno().longValue() );
		Assert.assertEquals(20L, vo_04.getDeptno().longValue() );
		Assert.assertEquals(100L, vo_04.getPoint().longValue() );
		Assert.assertEquals(1L, vo_04.getRkAll().longValue() );
		Assert.assertEquals(1L, vo_04.getRkDept().longValue() );
		
		Assert.assertEquals(5L, vo_05.getEmpno().longValue() );
		Assert.assertEquals(20L, vo_05.getDeptno().longValue() );
		Assert.assertEquals(90L, vo_05.getPoint().longValue() );
		Assert.assertEquals(5L, vo_05.getRkAll().longValue() );
		Assert.assertEquals(2L, vo_05.getRkDept().longValue() );
		
		Assert.assertEquals(6L, vo_06.getEmpno().longValue() );
		Assert.assertEquals(20L, vo_06.getDeptno().longValue() );
		Assert.assertEquals(80L, vo_06.getPoint().longValue() );
		Assert.assertEquals(8L, vo_06.getRkAll().longValue() );
		Assert.assertEquals(3L, vo_06.getRkDept().longValue() );
		
		Assert.assertEquals(7L, vo_07.getEmpno().longValue() );
		Assert.assertEquals(30L, vo_07.getDeptno().longValue() );
		Assert.assertEquals(95L, vo_07.getPoint().longValue() );
		Assert.assertEquals(3L, vo_07.getRkAll().longValue() );
		Assert.assertEquals(1L, vo_07.getRkDept().longValue() );
		
		Assert.assertEquals(9L, vo_08.getEmpno().longValue() );
		Assert.assertEquals(30L, vo_08.getDeptno().longValue() );
		Assert.assertEquals(95L, vo_08.getPoint().longValue() );
		Assert.assertEquals(3L, vo_08.getRkAll().longValue() );
		Assert.assertEquals(1L, vo_08.getRkDept().longValue() );
		
		Assert.assertEquals(8L, vo_09.getEmpno().longValue() );
		Assert.assertEquals(30L, vo_09.getDeptno().longValue() );
		Assert.assertEquals(85L, vo_09.getPoint().longValue() );
		Assert.assertEquals(7L, vo_09.getRkAll().longValue() );
		Assert.assertEquals(3L, vo_09.getRkDept().longValue() );
	}
}
