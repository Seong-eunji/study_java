package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.UserVO;

public class UserDAO {
	private final int KEY = 3;						// 암호화를 위한 상수 KEY 선언
	
	public Connection connection;					// 연결 객체
	public PreparedStatement preparedStatement;		// 쿼리 객체
	public ResultSet resultSet;						// 결과 테이블 객체
	
	// 아이디 중복검사
	// 아이디를 조회했을 때 USER_ID는 UK로 중복이 없으므로 일치하는 행을 찾았을 때 COUNT하면
	// 결과는 0 아니면 1이므로 화면에 boolean을 리턴해 결과를 알려준다.
	public boolean checkId(String id) {
		String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_ID = ?";
		boolean check = false;
		
		try {
			connection = DBConnecter.getConnection();					// 연결 객체 가져오기(db와 연결하면 드라이버가 열리기 때문에 나중에 닫아주어야 함)
			preparedStatement = connection.prepareStatement(query);		// 위에 적은 query를 preparedStatement에 전달하기
			preparedStatement.setString(1, id);							// ?자리에 id 입력하기, 인덱스는 1부터
			resultSet = preparedStatement.executeQuery();				// SELECT절이기 때문에 executeQuery로 실행하여 resultSet에 결과를 담아줌
			
			// resultSet은 2차원 배열
			// 그러나 이번엔 집계함수를 사용하여 결과값이 무조건 나오므로 if문 쓰지 않아도 됨
			// 결과값도 무조건 하나 나오므로 while문 쓰지 않아도 됨
			resultSet.next();											// 결과 테이블의 행 가져오기
			check = resultSet.getInt(1) == 1; 							// getInt()는 현재 행의 지정된 column값(첫번째 column값)을 가져오는 메소드
																		// USER_ID는 중복이 없으므로 1이면 일치하는 id 찾음, 0이면 일치하는 id 없음
			
		} catch (SQLException e) {
			System.out.println("checkId()에서 쿼리문 오류");
		} finally {											// 닫아주는 건 finally에서, 연 순서의 반대로 닫아주기
			try {
				if(resultSet != null) {						// resultSet이 열려있다면(null이라면 열려있지 않음)
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {						// 예외가 생겨 close()를 못하게 되면 Exception이 발생할 수 있고 파일이 삭제되지 않을 수도 있음
				throw new RuntimeException(e.getMessage());	// 일부러 예외를 발생시켜 프로그램을 강제종료시킨다.
															// e.getMessage는 발생한 예외 e를 받아와 원인을 출력해줌
			}
		}
		return check;
	}
	
	// 회원가입
	// DB에 추가만 하면 되므로 화면에 리턴해줄 값이 없으므로 void 타입
	public void insert(UserVO userVO) {
		String query = "INSERT INTO TBL_USER "
				+ "(USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB)"
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int i = 0;	// preparedStatement에 값을 넣을 때 인덱스로 활용할 변수
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(++i, userVO.getUserId());					// jdbc는 인덱스 1부터 시작하므로 ++i;
			preparedStatement.setString(++i, userVO.getUserName());					// 전달받은 userVO 객체에 저장된 정보를 preparedStatement 객체에 전달하여 저장
			preparedStatement.setString(++i, encrypt(userVO.getUserPassword()));	// DB에 암호화된 비밀번호가 저장되도록 userVO에 저장된 비밀번호를 encrypt()에 전달하여 암호화 후 전달 
			preparedStatement.setString(++i, userVO.getUserPhone());
			preparedStatement.setString(++i, userVO.getUserNickname());
			preparedStatement.setString(++i, userVO.getUserEmail());
			preparedStatement.setString(++i, userVO.getUserAddress());
			preparedStatement.setString(++i, userVO.getUserBirthDate());
			preparedStatement.setString(++i, userVO.getUserGender());
			preparedStatement.setString(++i, userVO.getUserRecommenderId());
			preparedStatement.setString(++i, userVO.getUserJob());
			preparedStatement.executeUpdate();										// UPDATE문이므로 executeUpdate()로 실행
																					// 결과 테이블이 없으므로 resultSet에 담아줄 필요 없음
																					// executeUpdate()의 리턴 타입은 int로 실행된 행의 건수를 리턴해줌(0을 리턴하면 실행 건수가 없으므로 update 실패)
		} catch (SQLException e) {
			System.out.println("insert()에서 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
				
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 로그인
	// id와 pw를 받아서 일치하는 UserVO를 찾아 PK인 USER_NUMBER를 리턴해주기 위해 int 리턴타입
	public int logIn(String userId, String userPassword) {
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PASSWORD = ?";
		int userNumber = 0;
		
		try {
			connection = DBConnecter.getConnection();					// 연결 객체 불러오기
			preparedStatement = connection.prepareStatement(query);		// 쿼리문 preparedStatement 객체에 전달
			preparedStatement.setString(1, userId);						// 첫번째 ?에 userId 전달
			preparedStatement.setString(2, userPassword);				// 두번째 ?에 userPassword 전달
			resultSet = preparedStatement.executeQuery();				// SELECT문이므로 executeQuery()로 실행하여 결과테이블 resultSet에 담음
			
			if(resultSet.next()) {										// resultSet.next()는 결과행을 하나씩 가져오고 boolean타입을 리턴
				userNumber = resultSet.getInt(1);						// 결과행이 있다면 getInt(1)가 첫번째 컬럼의 값을 리턴, userNumber 변수에 담는다.
			}															// 첫번째 컬럼 = USER_NUMBER, getInt("USER_NUMBER")로 써도 됨
			
		} catch (SQLException e) {
			System.out.println("logIn()에서 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userNumber;
	}
	
	// 암호화
	// 비밀번호를 입력받으면 암호화된 비밀번호를 리턴함
	public String encrypt(String userPassword) {
		String encryptedPassword = "";
		for (int i = 0; i < userPassword.length(); i++) {
			encryptedPassword += (char)(userPassword.charAt(i) * KEY);	// 입력받은 userPassword의 한 문자씩 끊어서 KEY를 곱해준 후
		}																// 다시 char로 형변환하여 encryptedPassword에 하나씩 담아줌
		return encryptedPassword;
	}

	// 회원탈퇴
	// DELETE만 하면 되므로 화면에 리턴해줄 필요 없으므로 void
	public void delete(int userNumber) {
		String query = "DELETE FROM TBL_USER WHERE USER_NUMBER = ?";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);					// ?에 userNumber 전달, int 타입이므로 setInt 사용
			preparedStatement.executeUpdate();							// DELETE문이므로 executeUpdate()로 실행
			
		} catch (SQLException e) {
			System.out.println("delete()에서 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 아이디 찾기
	// USER_PHONE이 중복이 없는 UK이므로 전화번호를 이용하여 USER_ID 찾기
	public String findId(String userPhone) {
		String query = "SELECT USER_ID FROM TBL_USER WHERE USER_PHONE = ?";
		String userId = "";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhone);					// ?에 userPhone 전달
			resultSet = preparedStatement.executeQuery();				// SELECT문이므로 executeQuery()로 실행하여 resultSet에 결과 테이블 담아줌
			
			if(resultSet.next()) {										// 결과 행이 하나이므로 while문 사용하지 않고 if문의 조건식으로 활용
				userId = resultSet.getString(1);						// 첫번째 컬럼(USER_ID)의 값을 userId에 담아줌
			}
		
		} catch (SQLException e) {
			System.out.println("findId()에서 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userId;
	}
	
	// 비밀번호 변경
	// 비밀번호를 변경하려는 사용자의 id와 새로운 비밀번호를 매개변수로 받아옴
	public void updateUserPassword(String userId, String userPassword) {
		String query = "UPDATE TBL_USER SET USER_PASSWORD = ? WHERE USER_ID = ?";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPassword);			// 첫번째 ?에 userPassword 전달
			preparedStatement.setString(2, userId);					// 두번째 ?에 userId 전달
			preparedStatement.executeUpdate();						// UPDATE문이므로 executeUpdate()로 실행, 결과테이블을 담을 필요 없음
			
		} catch (SQLException e) {
			System.out.println("updateUserPassword()에서 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 회원정보 수정
	// 어떤 정보를 수정했을지 알 수 없으므로 userVO 타입을 통째로 매개변수로 받는다.
	public void update(UserVO userVO) {
		String query = "UPDATE TBL_USER SET USER_NAME = ?, USER_PASSWORD = ?, USER_PHONE = ?, USER_NICKNAME = ?, USER_EMAIL = ?, USER_ADDRESS = ?, USER_BIRTH_DATE = ?, USER_GENDER = ?, USER_JOB = ?"
				+ "WHERE USER_NUMBER = ?";
		int i = 0;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(++i, userVO.getUserName());			// 쿼리를 전달한 preparedStatement 객체에 전달받은 userVO의 정보들을 입력해준다.
			preparedStatement.setString(++i, userVO.getUserPassword());
			preparedStatement.setString(++i, userVO.getUserPhone());
			preparedStatement.setString(++i, userVO.getUserNickname());
			preparedStatement.setString(++i, userVO.getUserEmail());
			preparedStatement.setString(++i, userVO.getUserAddress());
			preparedStatement.setString(++i, userVO.getUserBirthDate());
			preparedStatement.setString(++i, userVO.getUserGender());
			preparedStatement.setString(++i, userVO.getUserJob());
			preparedStatement.setInt(++i, userVO.getUserNumber());			// userNumber는 int 타입이므로 setInt() 사용
			preparedStatement.executeUpdate();								// UPDATE문이므로 executeUpdate()로 실행
			
		} catch (SQLException e) {
			System.out.println("update()에서 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 회원정보 조회
	// 화면에 회원에 대한 정보를 모두 전달하기 위해서 UserVO 타입을 리턴
	public UserVO selectUser(int userNumber) {
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB "
				+ "FROM TBL_USER WHERE USER_NUMBER = ?";
		UserVO userVO = new UserVO();
		int i = 0;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);					// ?에 userNumber 전달
			resultSet = preparedStatement.executeQuery();				// SELECT문이므로 executeQuery()로 실행, 결과 테이블을 resultSet에 담음
			
			resultSet.next();											// 결과 행 불러오기
			userVO.setUserNumber(resultSet.getInt(++i));				// userVO 객체에 resultSet 결과 행의 컬럼값을 하나씩 담아주기
			userVO.setUserId(resultSet.getString(++i));
			userVO.setUserName(resultSet.getString(++i));
			userVO.setUserPassword(resultSet.getString(++i));
			userVO.setUserPhone(resultSet.getString(++i));
			userVO.setUserNickname(resultSet.getString(++i));
			userVO.setUserEmail(resultSet.getString(++i));
			userVO.setUserAddress(resultSet.getString(++i));
			userVO.setUserBirthDate(resultSet.getString(++i));
			userVO.setUserGender(resultSet.getString(++i));
			userVO.setUserRecommenderId(resultSet.getString(++i));
			userVO.setUserJob(resultSet.getString(++i));
			
		} catch (SQLException e) {
			System.out.println("selectUser()에서 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userVO;
	}
	
	// 추천 수
	// 추천 수를 알고 싶은 사용자의 userId를 매개변수로 받아온다.
	// 왜냐하면 USER_RECOMMENDER_ID에서 USER_ID를 조회할거니까
//	public int getTotalOfRecommender(String userId) {
//		String query = "SELECT COUNT(USER_RECOMMENDER_ID) FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
//		int recommendCount = 0;
//		
//		try {
//			connection = DBConnecter.getConnection();					// 연결 객체 불러오기
//			preparedStatement = connection.prepareStatement(query);		// 쿼리문을 preparedStatement 객체에 전달
//			preparedStatement.setString(1, userId);						// ?에 userId 전달
//			resultSet = preparedStatement.executeQuery();				// SELECT문이므로 executeQuery()로 실행, 결과 테이블을 resultSet에 담음
//			
//			if(resultSet.next()) {										// 결과 행 가져오기, 집계함수를 사용했으므로 결과는 하나이기 때문에 while문 없이 사용
//				recommendCount = resultSet.getInt(1);					// 결과 행의 첫번째 컬럼의 값(COUNT(USER_RECOMMENDER_ID))을 recommendCount 변수에 담음
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("getTotalOfRecommender()에서 쿼리 오류");
//			e.printStackTrace();
//		} finally {
//			try {
//				if(resultSet != null) {
//					resultSet.close();
//				}
//				if(preparedStatement != null) {
//					preparedStatement.close();
//				}
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
//		}
//		return recommendCount;
//	}
	
	// 추천수
	public int getTotalOfRecommender(String userId) {
		// 입력받은 userId를 그대로 selectRecommenders()로 전달
		// 리턴받은 ArrayList<UserVO>의 사이즈를 센 후 리턴하면 추천 수
		int recommendCount = selectRecommenders(userId).size();
		return recommendCount;
	}
	
	
	// 나를 추천한 사람
	// 추천한 사람이 하나가 아닐 수 있기 때문에(리턴할 UserVO가 많을 수 있기 때문에)
	// ArrayList<UserVO> 타입을 리턴
	public ArrayList<UserVO> selectRecommenders(String userId){
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB "
				+ "FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
		ArrayList<UserVO> arUserVO = new ArrayList<UserVO>();			// 리턴할 UserVO들을 담을 ArrayList
		UserVO userVO = new UserVO();											// 결과 테이블의 정보들을 담아 ArrayList에 한번에 추가하기 위한 UserVO 객체 선언
		int i = 0;														// 결과 행의 컬럼 인덱스로 쓰기 위한 변수 선언
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);						// ?에 userId 전달
			resultSet = preparedStatement.executeQuery();				// SELECT문이므로 executeQuery()로 실행, 결과 테이블을 resultSet에 담음
			
			while(resultSet.next()) {									// 몇 개의 행이 담겨있을 지 모르므로 while문 돌림, boolean 타입이므로 불러올 결과행이 없으면 false 반환
				userVO.setUserNumber(resultSet.getInt(++i));			// 결과행의 컬럼 값들을 하나씩 userVO 객체에 담아줌
				userVO.setUserId(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserPhone(resultSet.getString(++i));
				userVO.setUserNickname(resultSet.getString(++i));
				userVO.setUserEmail(resultSet.getString(++i));
				userVO.setUserAddress(resultSet.getString(++i));
				userVO.setUserBirthDate(resultSet.getString(++i));
				userVO.setUserGender(resultSet.getString(++i));
				userVO.setUserRecommenderId(resultSet.getString(++i));
				userVO.setUserJob(resultSet.getString(++i));
				
				arUserVO.add(userVO);									// 모든 정보를 담은 UserVO 객체를 ArrayList에 추가한다.
			}
			
		} catch (SQLException e) {
			System.out.println("selectRecommenders()에서 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return arUserVO;
	}
	
	
	// 내가 추천한 사람
	public UserVO getMyRecommender(String userId) {
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB FROM TBL_USER WHERE USER_ID = "
				+ "(SELECT USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_ID = ?)";		// 내가 추천한 아이디를 USER_ID로 가지고 있는 사람의 전체 정보를 조회하기 위해서 서브쿼리 사용
		UserVO userVO = new UserVO();															// 결과로 조회된 사람의 전체 정보를 담기 위한 UserVO 객체 선언
		int i = 0;																		// 결과 행의 컬럼 인덱스로 사용하기 위한 변수 선언
		
		try {
			connection = DBConnecter.getConnection();									// 연결 객체 불러오기
			preparedStatement = connection.prepareStatement(query);						// 쿼리문 preparedStatement 객체에 전달
			preparedStatement.setString(1, userId);										// ?에 userId 전달
			resultSet = preparedStatement.executeQuery();								// SELECT문이므로 executeQuery()로 실행, 결과 테이블 resultSet에 담음
			
			if(resultSet.next()) {														// 결과 행이 존재한다면
				userVO.setUserNumber(resultSet.getInt(++i));							// UserVO 객체에 결과 행의 컬럼 값들을 하나씩 담아줌
				userVO.setUserId(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserPhone(resultSet.getString(++i));
				userVO.setUserNickname(resultSet.getString(++i));
				userVO.setUserEmail(resultSet.getString(++i));
				userVO.setUserAddress(resultSet.getString(++i));
				userVO.setUserBirthDate(resultSet.getString(++i));
				userVO.setUserGender(resultSet.getString(++i));
				userVO.setUserRecommenderId(resultSet.getString(++i));
				userVO.setUserJob(resultSet.getString(++i));
			}
			
		} catch (SQLException e) {
			System.out.println("getMyRecommender()에서 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userVO;
	}
}







