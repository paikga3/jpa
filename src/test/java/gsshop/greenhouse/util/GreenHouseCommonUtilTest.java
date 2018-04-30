package gsshop.greenhouse.util;

import java.io.UnsupportedEncodingException;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class GreenHouseCommonUtilTest {
	
	@Test
	public void substrb() throws UnsupportedEncodingException {
		
		String target = "홍길동이랑싸워보자";
		
		String result = GreenHouseCommonUtil.substrb(target, 7, 3);
		
		System.out.println("결과문자열 : " + result);
		System.out.println("문자열의 길이 : " + result.length());
	}
	
	
}
