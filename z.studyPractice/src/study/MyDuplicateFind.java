package study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;

public class MyDuplicateFind {
//	문제 1 (3점).
//	랜덤함수를 써서 100개의 숫자를 추출해 ArrayList에 담고, 중복된 값은 제거.
//	이후 중복 제거된 값을 큰 수부터 작은 수로 나열하고, 중복인 값이 몇 개였는지 추출하시오. 
	
	public static void main(String[] args) {
		Random r = new Random();
		ArrayList<Integer> arData = new ArrayList<Integer>();
		ArrayList<Integer> resultData = new ArrayList<Integer>();
		HashSet<Integer> hashData = new HashSet<Integer>();
		int count = 0;
		
		for (int i = 0; i < 10; i++) {
			arData.add(r.nextInt(10));	
		}
		
		arData.stream().forEach(hashData::add);
		hashData.stream().sorted(Comparator.reverseOrder());
		
		System.out.println("arData : ");
		for (Integer integer : arData) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
		System.out.println("hashData : ");
		for (Integer integer : hashData) {
			System.out.print(integer + " ");
		}
		System.out.println();

		System.out.println("================");
		
		System.out.println("arData 사이즈 " + arData.size());
		System.out.println("hashData 사이즈 " + hashData.size());
		System.out.println(arData.size() - hashData.size());
		
		System.out.println("================");
	
		hashData.stream().forEach(arData::remove);
		
		System.out.println("arData의 사이즈 " + arData.size());
		System.out.println("arData : ");
		for (Integer integer : arData) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
		System.out.println("================");
		
		arData.stream().sorted().filter(n -> Collections.frequency(arData, n) > 1).forEach(arData::remove);
		
		System.out.println("arData의 사이즈 " + arData.size());
		System.out.println("arData : ");
		for (Integer integer : arData) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
	}
}
