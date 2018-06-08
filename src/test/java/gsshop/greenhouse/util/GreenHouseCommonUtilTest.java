package gsshop.greenhouse.util;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class GreenHouseCommonUtilTest {
	
//	@Test
	public void substrb() {
		
		String target = "홍길동이랑싸워보자";
		
		String result = GreenHouseCommonUtil.substrb(target, 4, 6);
		
		System.out.println("결과문자열 : " + result);
		System.out.println("문자열의 길이 : " + result.length());
	}
	
	@Test
	public void salaryTotal() {
		int salaryTotal = 0;
		double retrieveTax = 0;
		
		
		int[] sList = new int[12];
		
		sList[11] = 1550000; // 3.3
		sList[10] = 3100000; // 3.3
		sList[9] = 3100000; // 3.3
		sList[8] = 2400000; // 3.3
		sList[7] = 2800000; // 3.3
		sList[6] = 2200000; // 3.3
		sList[5] = 1672690;
		sList[4] = 1973120;
		sList[3] = 748072;
		sList[2] = 1150910;
		sList[1] = 1317080;
		sList[0] = 1079977;
		
		for (int i = 0; i < sList.length; i++) {
			
			
			if (i > 5) {
				salaryTotal += sList[i];
				retrieveTax += sList[i] * 3.3d / 100;
			}
			
		}
		
		System.out.println(salaryTotal);
		System.out.println(retrieveTax);
	}
	
	
}
