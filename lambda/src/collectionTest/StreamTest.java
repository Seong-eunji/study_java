package collectionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
	public static void main(String[] args) {
		// forEach()
		// 여러 값을 가지고 있는 컬렉션 객체에서 forEach 메소드를 사용할 수 있다.
		// forEach 메소드에서는 Consumer 타입의 값을 매개변수로 받음
		// Consumer는 함수형 인터페이스이기 때문에 람다식을 사용할 수 있다.
		// 매개변수에는 컬렉션 객체 안에 들어있는 값들이 순서대로 담기고,
		// 화살표 뒤에서는 실행하고 싶은 문장을 작성한다.
		HashMap<String, Integer> chinaTown = new HashMap<String, Integer>();
		ArrayList<Integer> datas = new ArrayList<Integer>();
		
		chinaTown.put("자장면", 4500);
		chinaTown.put("짬뽕", 5500);
		chinaTown.put("탕수육", 14500);
		
		
		// Collection -> Integer -> Stream으로 타입 변환
		// Stream의 메소드인 forEach를 사용하기 위해서
		// forEach는 Consumer 타입을 매개변수로 받기 때문에
		// Consumer를 람다식으로 구현(Stream은 모두 함수형 인터페이스이므로 하나의 추상메소드만을 가짐, 람다식으로 구현 가능)
		// 입력하면 Consumer로 받을 수 있음
				
		// price를 매개변수로 받음
		// 매개변수의 '()'소괄호도 생략 가능
		chinaTown.values().stream().forEach(price -> System.out.println(price));	// 저장공간 -> 값
		chinaTown.values().forEach(price -> System.out.println(price));				// 자주 사용하기 때문에 Stream 생략 가능
		chinaTown.values().forEach(System.out::println);							// 매개변수를 받아서 바로 그대로 쓰는 경우
																					// 매개변수를 입력하지 않아도 메소드가 알아서 앞에 담겨 있는 값들을 받아서 사용
																					// 매개변수 생략, 메소드의 소속::메소드명 적기
		datas.add(10);
		datas.add(20);
		datas.add(30);
		datas.add(40);
		
		datas.forEach(value -> {				// 두 문자 이상일 경우에는 중괄호 열기
			System.out.println(value);			// 그렇지만 간결하게 쓰는게 목적이므로 보통 한 줄로 마무리
			System.out.println("안녕");
		});
		
		
		// 전달받은 매개변수를 그대로 메소드에 사용할 경우에는 참조형 문법을 사용할 수 있다.
		// 소속::메소드명 --> 전달받은 값을 메소드의 매개변수로 바로 전달해준다.
		datas.forEach(System.out::println);		// 매개변수를 생략, 메소드가 datas에 담긴 값을 알아서 받아서 출력
		
		
		// IntStream.range(start, end) : start부터 end - 1까지		
		// IntStream.rangeClosed(start, end) : start부터 end까지
		IntStream.range(1, 10).forEach(System.out::println);		// 1 ~ 9, Exclusive는 입력받은 값을 포함하지 않음
		
		// 1~10까지 출력
		IntStream.rangeClosed(1, 10).forEach(System.out::println);	// 1 ~ 10, Inclusive는 입력받은 값을 포함함
		
		
		// chars() : 문자열을 IntStream으로 변경
		// 문자 스트림으로 바꿔주는 것처럼 보이나 문자 스트림은 없음.
		// 그러나 문자형은 정수로 변환이 가능하므로 문자형 배열을 IntStream으로 바꿔줌
		String data = "ABC";
		data.chars().forEach(System.out::println);							// IntStream으로 변환되었으므로 참조형 문법을 사용하면 int로 출력됨
		data.chars().forEach(number -> System.out.println((char)number));	// int로 변환된 값을 다시 char로 변환하여 하나씩  출력
		
		
		// map() : 기존 값을 원하는 값으로 변경
		// 리턴값이 있음
		// IntStream이 된 data를 각각 +3하여 forEach가 char로 형변환하여 하나씩 출력하도록 함
		String data2 = "ABC";
		data2.chars().map(number -> number + 3).forEach(v -> System.out.println((char)v));
		
		
		// filter() : 조건식을 작성하여 false일 경우 제외시킨다.
		// filter가 아니라 filter가 받는 매개변수가 함수형 인터페이스(구현하는 것도 매개변수로 전달하기 위해서)
		IntStream.rangeClosed(1, 10).filter(n -> n % 2 == 0).forEach(System.out::println);
		
		
		// sorted()
		Integer[] arData = {10, 40, 20, 30};
		
		// 배열을 arrayList로 바꿀 수 있음
		// ArrayList 생성자에는 무조건 배열만 입력 가능
		// ArrayList에 관련된 알고리즘은 Collections, 배열에 관련된 알고리즘은 Arrays에 담겨있음 
		// Arrays의 asList라는 메소드에 배열의 시작주소를 담아주면 배열의 모든 요소들이 ArrayListd에 담김
		
		// Arrays.asList(자료형... a)
		// 자료형 뒤에 ... 를 쓰면 가변인자라고 함
		// 가변인자 : 개수가 정해져 있지 않다(사용자가 몇 개의 값을 넣을지 모를 때)
//		ArrayList<Integer> datas = new ArrayList<Integer>(Arrays.asList(arData));
		
		// 배열 선언 없이도 넣기 가능
		ArrayList<Integer> datas2 = new ArrayList<Integer>(Arrays.asList(10, 40, 20, 30));
		System.out.println(datas2);
		
		// 오름차순
		datas2.stream().sorted().forEach(System.out::println);
		// 내림차순
		// 원래는 sorted()안의 매개변수로 Comparactor를 구현해야 하지만
		// Collections의 reverseOrder 메소드에서 Comparactor를 구현해놓았기 때문에 가져와 사용하면 됨
		datas2.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
		
		
		// collect() : 결과를 다양한 타입으로 리턴해준다.
		// 결과를 다시 List, Map, String 등의 다양한 타입으로 바꾸어줌
		// collect는 Collector를 매개변수로 받기 때문에 Collector를 구현해야 하지만
		// Collectors에서 이미 구현하여 Collector를 리턴값으로 돌려주는 메소드들이 있음 
		// List는 ArrayList의 부모타입이므로 downCasting하여 ArrayList에 담아줌
		
		// int 배열로 선언된 arData를 ArrayList<Integer>로 변환하여 담아주고 Stream 타입으로 변환
		// sorted()만 사용하면 오름차순이므로 Collections의 reverseOrder 메소드를 사용하여 내림차순으로 변경
		// collect를 사용하여 list로 변경, ArrayList로 downCasting하여 results에 담음
		ArrayList<Integer> results = 
				(ArrayList)datas2.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		System.out.println(results);
		
		
		// Collectors.joining("구분점");
		// 문자열 타입의 값을 원하는 구분점으로 연결하여 리턴한다.
		// 문자열 타입이 아닌 값은 joining을 사용할 수 없다.
		
		// delimiter : 구분점
		// valueOf : 박싱,  ***value : 언박싱
		String result = datas2.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
		System.out.println(result);
	}
}
