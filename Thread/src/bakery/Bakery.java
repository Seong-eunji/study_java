package bakery;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Bakery {
   public static void main(String[] args) {
      BreadMaker breadMaker = new BreadMaker();
      Thread maker = new Thread(breadMaker);
      BreadPlate breadPlate = BreadPlate.getInstance();
      String[] btns = {"빵 먹기", "나가기"};
      int choice = 0;
      
      ImageIcon icon = new ImageIcon("src/img/bread2.gif");		// 대화상자에 이미지 삽입을 위한 객체
      
      maker.start();					// 쓰레드 시작
      
      while(true) {
         choice = JOptionPane.showOptionDialog(null, "", "빵집", JOptionPane.DEFAULT_OPTION,
               JOptionPane.PLAIN_MESSAGE, icon, btns, null);

         if(choice == 0) {				// btns의 인덱스 0(빵 먹기) 선택 시
            breadPlate.eatBread();
         }else {						// btns의 인덱스 1(나가기) 선택 시
//        	System.exit(0);			// 시스템 종료
			maker.interrupt();
			break;
         }
      }
   }
}

