package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DBConnecter {
	public static final String PATH = "boxOffice.txt";				// 경로
	
	public static BufferedWriter getWriter() throws IOException{	// BufferedWriter의 새로운 객체를 리턴해주는 메소드
		return new BufferedWriter(new FileWriter(PATH));
	}
	
	public static BufferedWriter getAppend() throws IOException{	// BufferedWriter의 이전의 데이터를 그대로 갖고 있는 객체를 리턴해주는 메소드
		return new BufferedWriter(new FileWriter(PATH, true));
	}
	
	public static BufferedReader getReader() throws IOException{	// BufferedReader의 새로운 객체를 리턴해주는 메소드
		return new BufferedReader(new FileReader(PATH));
	}
}



















