package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import vo.UserVO;

public class UserDAO {

	private final int KEY = 3;

	public Connection connection; // 연결 객체
	public PreparedStatement preparedStatement; // 쿼리(SQL문) 객체
	public ResultSet resultSet; // 결과 테이블 객체

//   아이디 중복검사
	public boolean checkId(String id) {
//      USER_ID 컬럼에서 사용자가 입력한 id 검사
		String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_ID = ?";
		boolean check = false;
		try {
//         연결 객체 가져오기
			connection = DBConnecter.getConnection();
//         작성한 쿼리문을 preparedStatement에 전달
			preparedStatement = connection.prepareStatement(query);
//         ? 채우기(좌에서 우로 1부터 1씩 증가)
			preparedStatement.setString(1, id); 			// jdbc에서는 모든 인덱스가 1부터 시작, ?에 id 넣어줌
//         쿼리 실행
			resultSet = preparedStatement.executeQuery();	// 실행한 쿼리를 결과 테이블 객체에 담아줌
															// executeQuery는 SELECT문에서, executeUpdate는 int라서 건수를 반환
															// 2차원 배열
//         결과 행 1개 가져오기
			resultSet.next();
//         결과 첫번째 열 1개 가져오기
//         1이라면 사용자가 입력한 아이디가 1개 조회된 것이기 때문에 중복된 아이디이다.
			check = resultSet.getInt(1) == 1;				// 컬럼번호, 컬럼명 둘 다 오버로딩되어 있어서 둘다 써도 됨
															// 집계함수이므로 결과가 무조건 하나이므로 while문 돌릴 필요 없어짐
															// 1이면 아이디가 있다, 0이면 아이디가 없다(중복이 없으므로 무조건 0 아니면 1)

		} catch (SQLException e) {
			System.out.println("checkId()에서 쿼리문 오류");
		} finally {	// 닫는건 무조건 finally에서, 연 순서의 반대로
			try {
//            연결 객체 모두 닫기
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return check;
	}

//   회원가입
	public void insert(UserVO userVO) {
		String query = "INSERT INTO TBL_USER "
				+ "(USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB)"
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVO.getUserId());
			preparedStatement.setString(2, userVO.getUserName());
			preparedStatement.setString(3, userVO.getUserPassword());
			preparedStatement.setString(4, userVO.getUserPhone());
			preparedStatement.setString(5, userVO.getUserNickname());
			preparedStatement.setString(6, userVO.getUserEmail());
			preparedStatement.setString(7, userVO.getUserAddress());
			preparedStatement.setString(8, userVO.getUserBirthDate());
			preparedStatement.setString(9, userVO.getUserGender());
			preparedStatement.setString(10, userVO.getUserRecommenderId());
			preparedStatement.setString(11, userVO.getUserJob());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e); // 오류를 모르겠으면 출력해보기
			System.out.println("insert()에서 쿼리문 오류");

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

//   로그인
	public int login(String userId, String userPassword) {
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PASSWORD = ?";
		int userNumber = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPassword);
			resultSet = preparedStatement.executeQuery();		// SELECT만 QUERY로 나머지는 다 UPDATE로 써야함, 결과가 나오는건 SELECT뿐이니까!

			if (resultSet.next()) {								// 행이 없는데 열에 접근하면 오류 발생, 리턴타입이 boolean인걸 이용하여 조건식
				userNumber = resultSet.getInt("USER_NUMBER");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("login() 쿼리문 오류");
		} finally {
			try {
				// 연결 객체 모두 닫기
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userNumber;
	}

//   암호화
	public String encrypt(String password) {
		String encryptedPassword = "";
		for (int i = 0; i < password.length(); i++) {
			encryptedPassword += (char) (password.charAt(i) * KEY);
		}
		return encryptedPassword;
	}

//   회원탈퇴
	public void delete(int userNumber) {
		String query = "DELETE FROM TBL_USER WHERE USER_NUMBER = ?";
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("delete() 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

//   아이디 찾기
	public String findId(String userPhone) {							// USER_PHONE은 UNIQUE KEY이므로 중복이 없으므로 아이디 찾기에 사용 가능
		String userId = null;
		String query = "SELECT USER_ID FROM TBL_USER WHERE USER_PHONE = ?";
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhone);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {										// 중복이 없으므로 결과 행은 하나기 때문에
																		// 다음 행이 있는지 없는지 확인하기 위해 resultSet.next()를 조건식으로 활용
				userId = resultSet.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("findId() 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userId;
	}

	// 비밀번호 변경
	// 휴대폰번호 받아서 인증번호 확인하는건 화면쪽에서 하면 됨
	// 비밀번호 찾기는 아이디 중복검사와 같으므로 변경만 해주면 됨
	// 변경할 새로운 비밀번호와 비밀번호를 변경해줄 아이디 필요
	public void updateUserPassword(String userId, String userPassword) {
		String query = "UPDATE TBL_USER SET USER_PASSWORD = ? WHERE USER_ID = ?";
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPassword);
			preparedStatement.setString(2, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateUserPassword() 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	// 회원정보 수정
	// 어떤 정보를 수정했을 지 모르니까 UserVO 통쨰로 받아서 수정해야 함
	// 회원번호를 수정하려면 회원정보가 먼저 조회되어야 함
	public void update(UserVO userVO) {
		String query = "UPDATE TBL_USER "
				+ "SET USER_NAME=?, USER_PASSWORD=?, USER_PHONE=?, USER_NICKNAME=?, USER_ADDRESS=?, USER_BIRTH_DATE=?, USER_GENDER=? , USER_RECOMMENDER_ID=?, USER_JOB=?"
				+ "WHERE USER_NUMBER = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVO.getUserName());
			preparedStatement.setString(2, userVO.getUserPassword());
			preparedStatement.setString(3, userVO.getUserPhone());
			preparedStatement.setString(4, userVO.getUserNickname());
			preparedStatement.setString(5, userVO.getUserAddress());
			preparedStatement.setString(6, userVO.getUserBirthDate());
			preparedStatement.setString(7, userVO.getUserGender());
			preparedStatement.setString(8, userVO.getUserRecommenderId());
			preparedStatement.setString(9, userVO.getUserJob());
			preparedStatement.setInt(10, userVO.getUserNumber());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("update() 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

//   회원정보 조회
	public UserVO selectUser(int userNumber) {
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB FROM TBL_USER "
				+ "WHERE USER_NUMBER = ?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	// USER_BIRTH_DATE가 DATE 타입이므로 format이 일치해야 담을 수 있음

		int i = 0;
		UserVO userVO = new UserVO();
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();										// SELECT문 실행하면 어차피 결과로 나올 회원은 한명이므로
																	// resultSet.next()는 한번만 실행

			userVO.setUserNumber(resultSet.getInt(++i));			// 결과로 받아온 회원의 정보를 UserVO 객체를 하나 선언해서 다 담아서 화면에서 조회할 수 있도록 리턴해줌
			userVO.setUserId(resultSet.getString(++i));
			userVO.setUserName(resultSet.getString(++i));
			userVO.setUserPassword(resultSet.getString(++i));
			userVO.setUserPhone(resultSet.getString(++i));
			userVO.setUserNickname(resultSet.getString(++i));
			userVO.setUserEmail(resultSet.getString(++i));
			userVO.setUserAddress(resultSet.getString(++i));
			try {
				userVO.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));
			} catch (Exception e) {
				;
			}
			userVO.setUserGender(resultSet.getString(++i));
			userVO.setUserRecommenderId(resultSet.getString(++i));
			userVO.setUserJob(resultSet.getString(++i));

		} catch (SQLException e) {
			System.out.println("updateUser() 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userVO;
	}

//   추천수
	public int getTotalOfRecommender(String userId) {
		String query = "SELECT COUNT(USER_RECOMMENDER_ID) FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
		int count = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {	// resultSet.next가 참일 경우(있는 아이디일 경우)에만 count에 담음
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return count;
	}

	// 나를 추천한 사람
	// 추천한 사람이 하나가 아닐 수 있기 때문에(리턴할 UserVO가 많을 수 있기 때문에)
	// ArrayList<UserVO> 타입을 리턴
	public ArrayList<UserVO> selectRecommenders(String userId) {
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB FROM TBL_USER "
				+ "WHERE USER_RECOMMENDER_ID = ?";
		ArrayList<UserVO> recommenders = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		int i = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {	// 결과행이 하나가 아니기 때문에 resultSet.next()를 while문 돌림
				i = 0;
				UserVO userVO = new UserVO();
				userVO.setUserNumber(resultSet.getInt(++i));
				userVO.setUserId(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserPhone(resultSet.getString(++i));
				userVO.setUserNickname(resultSet.getString(++i));
				userVO.setUserEmail(resultSet.getString(++i));
				userVO.setUserAddress(resultSet.getString(++i));
				try {
					userVO.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));
				} catch (Exception e) {
					;
				}
				userVO.setUserGender(resultSet.getString(++i));
				userVO.setUserRecommenderId(resultSet.getString(++i));
				userVO.setUserJob(resultSet.getString(++i));

				recommenders.add(userVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return recommenders;
	}

	// 내가 추천한 사람
	public UserVO getMyRecommender(String userId) {
		// 쿼리로 *(ALL)을 리턴할 수 있지만 *는 있는 컬럼들을 모두 조회한 후에 가져오기 때문에 속도도 느리고 가독성도 떨어짐
		// 실무에서는 * 안쓰니까 컬럼명 다 적어주기
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB "
				+ "FROM TBL_USER WHERE USER_ID = " + "(" + "SELECT USER_RECOMMENDER_ID FROM TBL_USER "
				+ "WHERE USER_ID = ?" + ")";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO = new UserVO();

		int i = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				userVO.setUserNumber(resultSet.getInt(++i));
				userVO.setUserId(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserPhone(resultSet.getString(++i));
				userVO.setUserNickname(resultSet.getString(++i));
				userVO.setUserEmail(resultSet.getString(++i));
				userVO.setUserAddress(resultSet.getString(++i));
				try {
					userVO.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));
				} catch (Exception e) {
					;
				}
				userVO.setUserGender(resultSet.getString(++i));
				userVO.setUserRecommenderId(resultSet.getString(++i));
				userVO.setUserJob(resultSet.getString(++i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userVO;
	}
}
