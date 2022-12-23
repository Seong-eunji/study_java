package study;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class DuplicateFind {

	public static void main(String[] args) {

//	      랜덤함수를 써서 100개의 숫자를 추출해 ArrayList에 담고, 중복된 값은 제거.
//	      이후 중복 제거된 값을 큰 수부터 작은 수로 나열하고, 중복인 값이 몇 개였는지 추출하시오. 

		int number = 0;

		Random r = new Random();
		ArrayList<Integer> randomArrayList = new ArrayList<>();
		ArrayList<Integer> duplicateNumberArrayList = new ArrayList<Integer>();

		for (int i = 0; i < 10; i++) {
			number = r.nextInt(10);
			randomArrayList.add(number);
		}
		System.out.println("기존: " + randomArrayList);

//	      중복 제거하기
		HashSet<Integer> duplicateNumber = new HashSet<>(randomArrayList);

//	      중복인 값 찾기
		for (Integer integer : duplicateNumber) {
			int duplicateCount = 0;
			for (Integer duplicateNum : randomArrayList) {
				if (integer == duplicateNum) {
					duplicateCount++;
				}
			}
			if (duplicateCount > 1) {
				duplicateNumberArrayList.add(integer);
			}
		}

		System.out.println("중복값: " + duplicateNumberArrayList);
		System.out.println("중복개수: " + duplicateNumberArrayList.size());

//	      중복 제거된 것을 다시 ArrayList에 담기
		randomArrayList = new ArrayList<>(duplicateNumber);

//	      중복 제거된 ArrayList 내림차순으로 나열하기
		randomArrayList = (ArrayList<Integer>) randomArrayList.stream().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());

		System.out.println("중복제거: " + randomArrayList);
		System.out.println("중복 제거 후 개수: " + randomArrayList.size());
	}

}
