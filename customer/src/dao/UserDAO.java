package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.UserVO;

public class UserDAO {
	
	// userStatus를 알려주기 위한 상수 선언
	public static final int DELETED_USER_CODE = -1;		// 탈퇴한 회원
	public static final int RESTORED_USER_CODE = 0;		// 탈퇴하지 않은 회원
	public static final int DUPLICATED_ID_CODE = 1;		// 중복 아이디
	public static final int ENABLED_ID_CODE = 2;		// 중복되지 않은, 가입이 가능한 아이디

	public Connection connection;						// 연결 객체
	public PreparedStatement preparedStatement;			// 쿼리문을 담을 객체
	public ResultSet resultSet;							// SELECT문 실행 시 결과행을 담아올 객체

	/**
	 * 
	 * @param userId
	 * @return 
	 *         	DELETED_USER_CODE = -1;<br>
	 * 			RESTORED_USER_CODE = 0;<br>
	 *         	DUPLICATED_ID_CODE = 1;<br>
	 *         	ENABLED_ID_CODE = 2;<br>
	 */
	// 아이디 중복검사
	public int checkId(String userId) {
		String query = "select userStatus from tbl_user where userId = ?";
		int code = 0, userStatus = 0;

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {									// userId는 중복이 없는 UK이기 때문에, 결과행이 존재한다는건 아이디가 중복 아이디란 얘기
				code = DUPLICATED_ID_CODE;							// code에 중복 아이디를 의미하는 상수 입력

				if (resultSet.getInt(1) == DELETED_USER_CODE) {		// resultSet의 첫번째 결과행(userStatus)가 탈퇴한 사용자의 상태일 경우
					code = DELETED_USER_CODE;						// code에 탈퇴한 상태를 의미하는 상수 입력

				}
			} else {
				code = ENABLED_ID_CODE;								// 결과행도 존재하지 않고(동일한 아이디가 존재하지 않고), userStatus가 탈퇴상태가 아니라면
																	// code에 중복되지 않은, 사용이 가능한 아이디를 의미하는 상수를 입력
			}

		} catch (SQLException e) {
			System.out.println("checkId()에서 쿼리문 오류");
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

		return code;
	}

	// 회원가입
	public boolean insert(UserVO userVO) {
		if (!check(userVO.getUserPhoneNumber())) {						// check() 메소드에 userVO의 userPhoneNumber를 전달하여 리턴받은 값이 false일 경우(이미 가입 아이디가 3개인 경우)
			return false;												// false를 리턴(회원가입 실패)
		}

		String query = "insert into tbl_user "
				+ "(userId, userPassword, userName, userAge, userPhoneNumber, userBirth) " + "values(?, ?, ?, ?, ?, ?)";
		int i = 0;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(++i, userVO.getUserId());
			preparedStatement.setString(++i, userVO.getUserPassword());
			preparedStatement.setString(++i, userVO.getUserName());
			preparedStatement.setInt(++i, userVO.getUserAge());
			preparedStatement.setString(++i, userVO.getUserPhoneNumber());
			preparedStatement.setString(++i, userVO.getUserBirth());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
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
		return true;
	}

	// 로그인
	public int login(String userId, String userPassword) {
		String query = "select userNumber, userStatus from tbl_user where userId = ? and userPassword =?";
		int userNumber = 0;

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPassword);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {											// 로그인 한 아이디는 하나이므로 결과행도 하나
				userNumber = resultSet.getInt("userNumber");				// 결과행이 존재한다면 userNumber 컬럼의 값을 변수에 담아줌
																			// 로그인 상태를 유지해주기 위해서 userNumber 변수를 리턴

				if (resultSet.getInt(2) == DELETED_USER_CODE) {				// 결과행의 userStatus가 탈퇴한 상태라면
					userNumber = DELETED_USER_CODE;							// userNumber 변수에도 탈퇴한 사용자라는 걸 알려주기 위해서 탈퇴 코드를 담아줌
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("login() 쿼리문 오류");
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
		return userNumber;
	}

	// 아이디 찾기
	public ArrayList<UserVO> findId(String userPhoneNumber) {					// 동일한 핸드폰번호를 가지는 아이디를 모두 알아내어 리턴해주어야 하므로 ArrayList타입을 리턴

		String query = "select userNumber, userId, userPhoneNumber from tbl_user where userPhoneNumber = ?";
		ArrayList<UserVO> users = new ArrayList<UserVO>();

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhoneNumber);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {											// 몇 개의 아이디가 있을지 모르므로 while문 사용
				UserVO userVO = new UserVO();									// userVO 객체 선언
				userVO.setUserNumber(resultSet.getInt(1));						// userVO에 결과행의 컬럼값들을 담아줌
				userVO.setUserId(resultSet.getString(2));
				userVO.setUserPhoneNumber(resultSet.getString(3));

				users.add(userVO);												// ArrayList에 userVO 추가
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
		return users;
	}

	// 회원 정보 전체 수정
	public void update(UserVO userVO) {
		String query = "update tbl_user " + "set userName=?, userPassword=?, userPhoneNumber=?, userAge=?, userBirth=? "
				+ "where userNumber = ?";
		int i = 0;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(++i, userVO.getUserName());
			preparedStatement.setString(++i, userVO.getUserPassword());
			preparedStatement.setString(++i, userVO.getUserPhoneNumber());
			preparedStatement.setInt(++i, userVO.getUserAge());
			preparedStatement.setString(++i, userVO.getUserBirth());
			preparedStatement.setInt(++i, userVO.getUserNumber());
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

	// 회원 전체 정보 조회
	public UserVO selectUser(int userNumber) {
		String query = "select userNumber, userId, userPassword, userName, userAge, userPhoneNumber, userStatus, userBirth from tbl_user "
				+ "where userNumber=?";
		int i = 0;
		UserVO userVO = new UserVO();
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {											// 결과행이 있다면, 일치하는 아이디가 있다면
				userVO.setUserNumber(resultSet.getInt(++i));				// userVO에 결과행의 컬럼값들을 담아줌
				userVO.setUserId((resultSet.getString(++i)));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserAge(resultSet.getInt(++i));
				userVO.setUserPhoneNumber(resultSet.getString(++i));
				userVO.setUserStatus(resultSet.getInt(++i));
				userVO.setUserBirth(resultSet.getString(++i));
			}
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

	// 회원탈퇴
	public void delete(int userNumber) {
		String query = "update tbl_user set userStatus=? where userNumber= ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, DELETED_USER_CODE);					// userStatus에 탈퇴 코드 넣어줌
			preparedStatement.setInt(2, userNumber);
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

	// 회원탈퇴 복구
	public void restore(int userNumber) {
		String query = "update tbl_user set userStatus=? where userNumber = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, RESTORED_USER_CODE);				// userStatus에 복구 코드 넣어줌
			preparedStatement.setInt(2, userNumber);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("restore() 쿼리문 오류");
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

	// 핸드폰번호로 조회했을 때 가입된 아이디의 개수 확인
	private boolean check(String userPhoneNumber) {
		String query = "select count(userPhoneNumber) as result from tbl_user where userPhoneNumber = ?";
		boolean check = false;

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhoneNumber);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {				
				check = resultSet.getInt("result") < 3;					// 결과행의 result(count(userPhoneNumber)) 컬럼이 3보다 작을 경우 check에 true
			}															// true : 가입 아이디가 3개보다 적으므로 가입 가능, false : 가입 아이디가 3개이므로 가입 불가능

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("login() 쿼리문 오류");
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
		return check;
	}

	// 핸드폰번호로 가입한 전체 회원 정보 조회
	public ArrayList<UserVO> findUsersByUserPhoneNumber(String userPhoneNumber) {	// 동일한 핸드폰번호를 가지는 회원의 전체저보를 모두 알아내어 리턴해주어야 하므로 ArrayList타입을 리턴
		String query = "select userNumber, userId, userName, userAge, userPhoneNumber, userBirth, userStatus from tbl_user "
				+ "where userPhoneNumber = ?";
		ArrayList<UserVO> users = new ArrayList<UserVO>();
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhoneNumber);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserVO userVO = new UserVO();
				userVO.setUserNumber(resultSet.getInt(1));
				userVO.setUserId(resultSet.getString(2));
				userVO.setUserName(resultSet.getString(3));
				userVO.setUserAge(resultSet.getInt(4));
				userVO.setUserPhoneNumber(resultSet.getString(5));
				userVO.setUserBirth(resultSet.getString(6));
				userVO.setUserStatus(resultSet.getInt(7));
				users.add(userVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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

		return users;
	}
}
