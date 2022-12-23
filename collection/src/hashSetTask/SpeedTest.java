package hashSetTask;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class SpeedTest {
   public static void main(String[] args) {
      final int SIZE = 10_000_000;
      final List<Integer> arrayList = new ArrayList<Integer>(SIZE); 	// 위에서 선언한 값을 매개변수 capacity(용량)로 전달
      final Set<Integer> hashSet = new HashSet<Integer>(SIZE);
      final int DATA = 7_133_401;
      
      IntStream.range(0, SIZE).forEach(value -> {						// 람다식
         arrayList.add(value);											// 0부터 SIZE - 1, SIZE개의 숫자를 arrayList와 hashSet에 입력
         hashSet.add(value);
      });
      
      Instant start = Instant.now();									// Instant는 현재 시간, 시작하는 시간을 담음
      arrayList.contains(DATA);											// DATA 검색
      Instant end = Instant.now();										// 끝나는 시간
      long elapsedTime = Duration.between(start, end).toMillis();		// 끝나는 시간과 시작하는 시간 사이의 값을 밀리초로 표현
      System.out.println("arrayList : " + elapsedTime * 0.001 + "초");	// 초로 변환하여 출력
      
      start = Instant.now();
      hashSet.contains(DATA);
      end = Instant.now();
      elapsedTime = Duration.between(start, end).toMillis();
      System.out.println("hashSet : " + elapsedTime * 0.001 + "초");
   }
}
