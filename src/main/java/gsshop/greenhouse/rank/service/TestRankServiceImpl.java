package gsshop.greenhouse.rank.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gsshop.greenhouse.rank.RankInfoInlineVO;
import gsshop.greenhouse.rank.RankInfoVO;
import gsshop.greenhouse.rank.repository.TestRank;
import gsshop.greenhouse.rank.repository.TestRankRepository;

@Service
public class TestRankServiceImpl implements TestRankService {
	
	@Autowired
	private TestRankRepository testRankRepository;
	
	@Override
	public List<RankInfoVO> getRankInfo() {
		
		List<TestRank> rankList = testRankRepository.findAll();
		
		List<RankInfoInlineVO> inlineList = new ArrayList<>();
		
		List<RankInfoVO> resultList = new ArrayList<>();
		
		for (TestRank rank : rankList) {
			Long point = rank.getPoint();
			List<TestRank> underPointList = rankList.stream().filter(a -> point < a.getPoint()).collect(Collectors.toList());
			
			if (underPointList.isEmpty()) {
				RankInfoInlineVO vo = new RankInfoInlineVO();
				vo.setAEmpno(rank.getEmpno());
				vo.setADeptno(rank.getDeptno());
				vo.setAPoint(rank.getPoint());
				inlineList.add(vo);
			} else {
				for (Iterator<TestRank> iter = underPointList.iterator(); iter.hasNext();) {
					TestRank cRank = iter.next();
					
					RankInfoInlineVO vo = new RankInfoInlineVO();
					vo.setAEmpno(rank.getEmpno());
					vo.setADeptno(rank.getDeptno());
					vo.setAPoint(rank.getPoint());
					vo.setBEmpno(cRank.getEmpno());
					vo.setBDeptno(cRank.getDeptno());
					
					inlineList.add(vo);
				}
			}
		}
		
		Map<String, List<RankInfoInlineVO>> groupingMap = inlineList.stream()
				.collect(Collectors.groupingBy(a -> a.getAEmpno() + "," + a.getADeptno() + "," + a.getAPoint()));
		
		Iterator<Map.Entry<String, List<RankInfoInlineVO>>> entryIter = groupingMap.entrySet().iterator();
		while(entryIter.hasNext()) {
			Map.Entry<String, List<RankInfoInlineVO>> entry = entryIter.next();
			String[] groupKey = entry.getKey().split(",");
			Long empnoGroupKey = Long.parseLong(groupKey[0]);
			Long deptnoGroupKey = Long.parseLong(groupKey[1]);
			Long pointGroupKey = Long.parseLong(groupKey[2]);
			
			List<RankInfoInlineVO> groupList = entry.getValue();
			long rkAll = groupList.stream().map(a -> a.getBEmpno()).filter(a -> a != null).count() + 1L;
			long rkDept = groupList.stream().filter(a -> a.getADeptno() != null && a.getBDeptno() != null)
					.mapToLong(a -> a.getADeptno().longValue() == a.getBDeptno().longValue() ? 1L : 0L).sum() + 1L;
			
			RankInfoVO vo = new RankInfoVO();
			vo.setEmpno(empnoGroupKey);
			vo.setDeptno(deptnoGroupKey);
			vo.setPoint(pointGroupKey);
			vo.setRkAll(rkAll);
			vo.setRkDept(rkDept);
			
			resultList.add(vo);
		}
		
		resultList = resultList.stream().sorted( (a,b) -> {
			if (a.getDeptno().compareTo(b.getDeptno()) == 0) {
				if (a.getRkDept().compareTo(b.getRkDept()) == 0) {
					return a.getEmpno().compareTo(b.getEmpno());
				} else {
					return a.getRkDept().compareTo(b.getRkDept());
				}
			} else {
				return a.getDeptno().compareTo(b.getDeptno());
			}
		}).collect(Collectors.toList());
		return resultList;
	}
}