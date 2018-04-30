package gsshop.greenhouse.rank.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestRankServiceMockTest {
	
	@Test
	public void assertTest() throws UnsupportedEncodingException {
		String hangul = "테스트 문의내역입니다";
		String result = substrb(hangul, 25, 25);
		Assert.assertEquals(3, getLength(result) );
	}
	
	public String substrb(String target, int start, int len) throws UnsupportedEncodingException {
		byte[] byteArr = target.getBytes("UTF-8");
		List<Byte> byteList = new ArrayList<>();
		
		int length = byteArr.length >= start + len ? start + len : byteArr.length;
		
		for (int i = start - 1; i < length; i++ ) {
			byteList.add(new Byte(byteArr[i]));
		}
		
		byte[] newByteArr = new byte[byteList.size()];
		for (int i = 0; i < byteList.size(); i++ ) {
			newByteArr[i] = byteList.get(i);
		}
		
		return new String(newByteArr);
	}
	
	public int getLength(String target) throws UnsupportedEncodingException {
		Pattern patt = Pattern.compile("[[가-힣]|[A-Za-z0-9+&@#/%=~_|$?!:..`\\[\\]]|\\s]");
		int resultLength = 0;
		int brokenCharCount = 0;
		for (int i = 0 ; i < target.length(); i++ ) {
			String ch = String.valueOf( target.charAt(i) );
			Matcher nonBrokenCharMatcher = patt.matcher(ch);
			
			if (nonBrokenCharMatcher.matches()) {
				resultLength++;
				if (brokenCharCount > 0) {
					resultLength += brokenCharCount;
					brokenCharCount = 0;
				}
			} else {
				brokenCharCount++;
			}
		}
		return resultLength;
	}
	
	@Test
	public void assertVector() {
		double a = 5d;
		double b = Math.sqrt(13d);
		double c = Math.sqrt(26d);
		
		double s = (a+b+c) / 2;
		
//		System.out.println(s);
		
		double area = Math.sqrt( s * (s-a) * (s-b) * (s-c) );
		
//		System.out.println(area);
		
		Assert.assertTrue( (17d/2d == area) );
	}
	
	@Test
	public void assertParseLong() {
		String target = "0010111";
		long n = Long.parseLong(target, 2);
	}
	
	@Test
	public void nanoTime() {
//		DateTime dateTime1 = new DateTime();
//		DateTime dateTime2 = new DateTime();
//		DateTime dateTime3 = new DateTime();
//		String s1 = dateTime1.toString("yyyyMMddHHmmss.SSS");
//		String s2 = dateTime2.toString("yyyyMMddHHmmss.SSS");
//		String s3 = dateTime3.toString("yyyyMMddHHmmss.SSS");
//		
//		System.out.println(s1);
//		System.out.println(s2);
//		System.out.println(s3);
		
		DateTimeFormatter formatter = new DateTimeFormatterBuilder ()
		        .appendPattern ( "yyyyMMddHHmmss" )
		        .appendFraction ( ChronoField.NANO_OF_SECOND , 0 , 9 , true ) // Nanoseconds = 0-9 digits of fractional second.
		        .toFormatter ();
		
		LocalDateTime ldt = LocalDateTime.now();
		
		String date1 = getSysdate(ldt);
		String date2 = getSysdate(LocalDateTime.parse(date1, formatter));
		String date3 = getSysdate(LocalDateTime.parse(date2, formatter));
		
		System.out.println(date1);
		System.out.println(date2);
		System.out.println(date3);
		
	}
	
	public String getSysdate(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder ()
		        .appendPattern ( "yyyyMMddHHmmss" )
		        .appendFraction ( ChronoField.NANO_OF_SECOND , 0 , 9 , true ) // Nanoseconds = 0-9 digits of fractional second.
		        .toFormatter ();
		LocalDateTime now = LocalDateTime.now();
		
		String time1 = now.format(formatter);
		String time2 = localDateTime.format(formatter);
		if (time1.equals(time2)) {
			return getSysdate(now);
		} else {
			return time1;
		}
		
	}
	
}