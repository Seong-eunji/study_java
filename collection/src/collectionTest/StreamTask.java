package collectionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTask {
	public static void main(String[] args) {
		
		// 1~10까지 ArrayList에 담고 출력
		ArrayList<Integer> arData = new ArrayList<Integer>();		
		
		IntStream.rangeClosed(1, 10).forEach(arData::add);
		System.out.println(arData);
		
		
		// 강사님 코드
//		ArrayList<Integer> datas = new ArrayList<Integer>();
//		IntStream.range(1, 11).forEach(datas::add);
//		System.out.println(datas);
		
		
		
		// 1~100까지 중 홀수만 ArrayList에 담고 출력
		arData = new ArrayList<Integer>();
		
		IntStream.rangeClosed(1, 100).filter(number -> number % 2 != 0).forEach(arData::add);
		System.out.println(arData);
		
		
		// 강사님 코드
//		ArrayList<Integer> datas = new ArrayList<Integer>();
//		IntStream.range(1, 51).map(v -> v * 2 - 1).forEach(datas::add);
//		System.out.println(datas);
		
		
		
		// ABCDEF를 각 문자별로 출력
		IntStream.range(65, 71).forEach(number -> System.out.print((char)number));
		System.out.println();
		
		
		// 강사님 코드
//		String data = "ABCDEF";
//		data.chars().forEach(v -> System.out.println((char)v));
//		IntStream.rangeClosed('A', 'F').forEach(c -> System.out.println((char)c));
		
		
		
		// A~F까지 ArrayList에 담고 출력
		ArrayList<Character> arChar = new ArrayList<Character>();
		
		IntStream.range(65, 71).forEach(number -> arChar.add((char)number));
		System.out.println(arChar);
		
		
		// 강사님 코드
//		ArrayList<Character> datas = new ArrayList<Character>();
//		IntStream.rangeClosed('A', 'F').forEach(c -> datas.add((char)c));
//		System.out.println(datas);

		
		
		// A~F까지 중 D 제외하고 ArrayList에 담고  출력
		// map() 사용
		// filter() 사용하면 D일 때 건너뛰면서 반복을 6번 도니까
		ArrayList<Character> arChar2 = new ArrayList<Character>();
		
		IntStream.range(65, 71).filter(n -> n != 68).forEach(number -> arChar2.add((char)number));
		System.out.println(arChar2);
		
		IntStream.range(65, 70).map( n -> n < 68? n : n + 1).forEach( n -> arChar2.add((char)n));

		
		// 강사님 코드
//		ArrayList<Character> datas = new ArrayList<Character>();
//		IntStream.rangeClosed('A', 'E').map(v -> v > 67 ? v + 1 : v).forEach(v -> datas.add((char)v));
//		System.out.println(datas);
		
		
		
		// 5개의 문자열을 입력받은 후 모두 소문자로 변경(String.toLowerCase())
		ArrayList<String> str = new ArrayList<String>(Arrays.asList("Hello", "STRING", "INT", "HI", "BanaNa"));
				
		str.stream().map(String::toLowerCase).forEach(System.out::println);
		
		
		// 강사님 코드
//		ArrayList<String> datas = new ArrayList<String>(Arrays.asList("Black","WHITE","reD","yeLLow","PINK"));
//		datas.stream().map(String::toLowerCase).forEach(System.out::println);
		
		
		
		// Apple, banana, Melon 중 첫번째 문자가 대문자인 문자열 출력
		ArrayList<String> arFruit = new ArrayList<String>(Arrays.asList("Apple", "banana", "Melon"));
		
		arFruit.stream().filter(n -> n.charAt(0) > 64 && n.charAt(0) < 91).forEach(System.out::println);
		
		
		// 강사님 코드
//		ArrayList<String> datas = new ArrayList<String>(Arrays.asList("Apple", "banana", "Melon"));
//		datas.stream().filter(fruit -> fruit.charAt(0) >= 'A').filter(fruit -> fruit.charAt(0) <= 'Z').forEach(System.out::println);
		
		
		
		// 한글을 정수로 변경
		// chars(), map(), forEach() 사용
		String hangle = "공일이삼사오육칠팔구";
		String ex = "삼오일사";
		
		hangle.chars().map( n -> hangle.indexOf(n)).forEach(System.out::print);
		System.out.println();
		
		ex.chars().map( n -> hangle.indexOf(n)).forEach(System.out::print);
		System.out.println();
		
		
		// 강사님 코드
//		String hangle = "공일이삼사오육칠팔구";
//		String data = "일공이사";
//		data.chars().map(hangle::indexOf).forEach(System.out::print);
		
		
		
		// 정수를 한글로 변경
		String hangle2 = "공일이삼사오육칠팔구";
		String ex2 = "0125";
	
		ex2.chars().map(n -> n - 48).forEach(n -> System.out.print(hangle2.charAt(n)));
		System.out.println();
		
		
		// 강사님 코드
//		String hangle = "공일이삼사오육칠팔구";
//		String data = "1024";
//		data.chars().map(c -> c - 48).forEach(c -> System.out.print(hangle.charAt(c)));
		

	}	
}
