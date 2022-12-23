package cheerUp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

// 용기를 북돋아주기 위한 코딩테스트 1번 문제
public class WeCanDoIt {
	// '0'과 '1'로만 이루어진 문자열이 있습니다.
	// 이 문자열에서 '1'이 최대 몇 번 연속해서 나타나는지 구하려 합니다.
	// s의 길이는 3 이상 5000이하
	// s는 '0'과 '1'로만 구성되어 있습니다.

	public int solution(String s) {
//		int count = 0, max = 0;
//		for (int i = 0; i < s.length(); i++) {
//			if(s.charAt(i) == '0') {count = 0;}
//			else {
//				count++;
//				if(max < count) {max = count;}
//			}
//		}
//		return max;

		ArrayList<String> str = new ArrayList<String>(Arrays.asList(s.split("0")));
		ArrayList<Integer> length = new ArrayList<Integer>();

		str.stream().map(n -> n.length()).forEach(length::add);
		length = (ArrayList) length.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());

		return length.size() == 0 ? 0 : length.get(0);

		// 강사님 코드
//		// 0을 기준으로 문자열 분리
//		ArrayList<String> datas = new ArrayList<String>(Arrays.asList(s.split("0")));
//		// 각 세트의 "1" 개수를 담아줄 컬렉션
//		ArrayList<Integer> lengths = new ArrayList<Integer>();
//		// 내림차순 결과를 담아줄 컬렉션
//		List<Integer> result = null;
//
//		// 기존의 값을 길이로 변경 후 lengths에 저장
//		datas.stream().map(data -> data.length()).forEach(lengths::add);
//		// 내림차순 후 결과를 List로 변환
//		result = lengths.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
//		
//		// 전달받은 값이 0밖에 없으면 size()는 0이므로 0 리턴
//		return result.size() == 0 ? 0 : result.get(0);
	}

	public static void main(String[] args) {
		WeCanDoIt can = new WeCanDoIt();

		can.solution("1100111");

		System.out.println(can.solution("110111")); // 3
		System.out.println(can.solution("11001111")); // 4
		System.out.println(can.solution("11011111")); // 5
		System.out.println(can.solution("101")); // 1
		System.out.println(can.solution("0000000")); // 0

	}
}
