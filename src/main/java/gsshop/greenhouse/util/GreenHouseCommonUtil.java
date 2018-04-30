package gsshop.greenhouse.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;


public class GreenHouseCommonUtil {
	
	public static String substrb(String target, int pStart, int len) throws UnsupportedEncodingException {
		
		// 리턴문자열
		String result = null;
		
		// 대상문자열이 null이거나 빈문자열일 경우와 자를 길이(len)이 0보다 작은수가 주어지면 null를 리턴한다. 
		if (StringUtils.isEmpty(target) || len <= 0) {
			return result;
		}
		
		// 시작인덱스부터 len길이만큼 잘라낸 byte를 저장할 byte배열
		byte[] subtractSource;
		// target를 UTF-8인코딩 byte로 표현하여 담을 배열
		byte[] source;
		
		// byte추출의 시작위치
		int sPos;
		// byte추출의 종료위치
		int ePos;
		// source의 길이, 즉 바이트배열의 길이
		int sourceLen;
		
		source = target.getBytes("UTF-8");
		sourceLen = source.length;
		
		// 시작위치의 절대값이 source의 길이보다 크다면 null를 리턴
		if (Math.abs(pStart) > sourceLen) {
			return null;
		}
		
		// 0은 1로 치환
		if (pStart == 0) {
			sPos = 1;
		// 0보다 작은경우 뒤에서부터 자르기위한 계산
		} else if (pStart < 0) {
			sPos = sourceLen + pStart + 1;
		// 그외는 그냥 대입
		} else {
			sPos = pStart;
		}
		
		// 종료위치 계산
		ePos = (sPos + len - 1) > sourceLen ? sourceLen : (sPos + len - 1);
		
		// 잘라서 저장할 배열의 길이계산하여 생성
		subtractSource = new byte[ePos - sPos + 1];
		
		// 잘라내기작업
		int index = 0;
		for (int i = (sPos - 1); i < ePos; i++) {
			// 먼저저장되어진 값을 적용 후 index를 증가
			subtractSource[index++] = source[i];
		}
		
		// 잘라낸 byte를 문자열로 변환
		result = new String(subtractSource);
		return result;
	}
	
}
