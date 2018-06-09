package gsshop.greenhouse.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;


public class GreenHouseCommonUtil {
	
	public static String substrb(String target, int pStart, int len) {
		
		// 리턴문자열
		String result = null;
		String encodingType = "UTF-8";
		
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
		
		try {
			source = target.getBytes(encodingType);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getCause());
		}
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
		for (int i = (sPos - 1), j = 0; i < ePos; i++, j++) {
			// 먼저저장되어진 값을 적용 후 index를 증가
			subtractSource[j] = source[i];
		}
		
		// 잘라낸 byte를 문자열로 변환
		try {
			result = new String(subtractSource, encodingType);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getCause());
		}
		return result;
	}
	
	// IN에 들어올 수 있는 파라미터의 갯수가 1000개로 제한되어져 있어
	// 분할쿼리하기 위한 유틸리티
	// RepositorySequentialQuery 에 사용자가 쿼리하려는 레파지토리 호출로직을 구현하여 넘기면
	// paramSet의 갯수가 1000개 이하일 경우는 이 파라미터를 그대로 전달하여 쿼리하고
	// 1000개이상일 경우는 1000개씩 분할하여 추상화된 레파지토리 호출로직을 반복 호출하여 누적된 결과를 리턴한다.
	public static<T,S> List<T> getSequentialQueryList(RepositorySequentialQuery<T,S> repositorySequentialQuery, Set<S> paramSet) {
		
		// 누적결과리스트, 처음엔 null을 할당
		List<T> resultList = null;
		
		// IN조건에 들어오는 파라미터의 총크기(1000개 이상일 가능성이 있는 조건파라미터)
		int size = paramSet.size();
		
		// 파라미터의 크기가 1000개를 초과한다면
		if (size > 1000) {
			// 카운터변수
			int i = 1;
			
			// 이때서야 누적결과리스트의 객체를 생성한다. 메모리잡아먹으니깐 미리 만들어놓지 않음ㅋ
			resultList = new ArrayList<>();
			
			// 실제쿼리에 던질 파라미터셋(제한된 크기 1000개), 이후 템프셋이라 명함
			Set<S> tmpParamSet = new HashSet<>();
			
			// 이터레이터생성
			Iterator<S> paramIter = paramSet.iterator();
			while (paramIter.hasNext()) {
				S param = paramIter.next();
				
				// 템프셋에 파라미터 추가
				tmpParamSet.add(param);
				
				// 템프셋의 크기가 1000개 라면 쿼리하고
				// 쿼리한 결과 결과리스트에 담고
				// 쿼리했으니깐 템프셋은 초기화하고
				// SIZE는 남아있는 파라미터의 개수를 나타내기 위해 업데이트하고(즉 1000개를 뺌, i는 이상태에서 1000이므로 i를 뺌)
				// i는 0으로 초기화, 그래야 다음에 i++되어 1부터 시작
				if (i == 1000) {
					resultList.addAll(repositorySequentialQuery.getResultList(tmpParamSet));
					tmpParamSet = new HashSet<>();
					i = 0;
				}
				i++;
			}
			if (!tmpParamSet.isEmpty()) {
				resultList.addAll(repositorySequentialQuery.getResultList(tmpParamSet));
			}
			
		// 파라미터의 크기가 1000개이면 그냥 바로 넘겨 쿼리하고 리턴
		} else {
			resultList = repositorySequentialQuery.getResultList(paramSet);
		}
		
		return resultList;
	}
}
