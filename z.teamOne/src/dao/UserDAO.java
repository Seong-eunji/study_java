package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.UserVO;

public class UserDAO {
	private final static int KEY = 6;
	
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
   
//   로그인 -> 로그인 성공 시 userId 리턴
	public UserVO login(String userId, String userPassword) {

		String query = "SELECT USER_PHONE, USER_ID, USER_PASSWORD, USER_NAME FROM TBL_USER WHERE USER_ID = ? AND USER_PASSWORD = ?";
		UserVO userVO = new UserVO();

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, encrytedPassword(userPassword));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				userVO.setUserPhone(resultSet.getString(1));
				userVO.setUserId(resultSet.getString(2));
				userVO.setUserPassword(resultSet.getString(3));
				userVO.setUserName(resultSet.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("login()에서 쿼리문 오류 났습니다.");
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
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return userVO;
	}
   
//   회원가입 -> 입력받은 회원 정보 저장
	public void join(UserVO userVO) {
		String query = "INSERT INTO TBL_USER"
				+ "(USER_PHONE, USER_ID, USER_PASSWORD, USER_NAME)"
				+ "VALUES(?, ?, ?, ?)";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVO.getUserPhone());
			preparedStatement.setString(2, userVO.getUserId());
			preparedStatement.setString(3, encrytedPassword(userVO.getUserPassword()));
			preparedStatement.setString(4, userVO.getUserName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("join()에서 쿼리문 오류 났습니다.");
		}finally {
			
			try {
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
//    아이디 중복검사
   public boolean checkId(String userId) {
      String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_ID = ?";
      boolean check = false;
      
      try {
         connection = DBConnecter.getConnection();
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, userId);
         resultSet = preparedStatement.executeQuery();
         
         resultSet.next();
//         true : 사용가능한 아이디, 0
//         false : 중복된 아이디, 1
         check = resultSet.getInt(1) != 1;
         
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("checkId()에서 쿼리문 오류 났습니다");
      }finally {
         
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null){
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
         }
      }
      
      return check;
   }
   
   // 핸드폰 중복검사
   public boolean checkPhone(String userPhone) {
	   String query = "SELECT COUNT(USER_PHONE) FROM TBL_USER WHERE USER_PHONE = ?";
	   boolean check = false;
	   
	   try {
		   connection = DBConnecter.getConnection();
		   preparedStatement = connection.prepareStatement(query);
		   preparedStatement.setString(1, userPhone);
		   resultSet = preparedStatement.executeQuery();
		   
		   resultSet.next(); 
		   check = resultSet.getInt(1) != 1; 
		   
	   } catch (SQLException e) {
		   e.printStackTrace();
		   System.out.println("checkPhone()에서 쿼리문 오류 났습니다");
	   }finally {
		   
		   try {
			   if(resultSet != null) {
				   resultSet.close();
			   }
			   if(preparedStatement != null){
				   preparedStatement.close();
			   }
			   if(connection != null) {
				   connection.close();
			   }
		   } catch (SQLException e) {
			   e.printStackTrace();
			   throw new RuntimeException(e.getMessage());
		   }
	   }
	   return check;
   }
   
// 암호화
   public String encrytedPassword(String userPassword) {
      String encrytedPw = "";
      
      for (int i = 0; i < userPassword.length(); i++) {
         encrytedPw += (char)(userPassword.charAt(i) * KEY);
      }
      
      return encrytedPw;
   }
   
////   복호화
//   public String decrytedPassword(String encrytedPw) {
//      String decrytedPw = "";
//      
//      for (int i = 0; i < encrytedPw.length(); i++) {
//         decrytedPw += (char)(encrytedPw.charAt(i) / KEY );
//      }
//      
//      return decrytedPw;
//   }
   
// 비밀번호 변경
   public boolean changePassword(UserVO userVO) {
      String query = "UPDATE TBL_USER SET USER_PASSWORD = ? "
            + "WHERE USER_PHONE = ?";
      boolean check = false;
      
      try {
         connection = DBConnecter.getConnection();
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, encrytedPassword(userVO.getUserPassword()));
         preparedStatement.setString(2, userVO.getUserPhone());
         
         if(preparedStatement.executeUpdate() == 1) {
            check = !check;
         }
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("chagePassword()에서 쿼리문 오류");
      }finally {
         try {
            if(preparedStatement != null){
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
         }
      }
      return check;
   }
   
   // 회원 탈퇴
   public boolean deleteUser(String userPhone) {
	      String query = "DELETE FROM TBL_USER WHERE USER_PHONE = ?";
	      boolean check = false;
	      try {
	         connection = DBConnecter.getConnection();
	         preparedStatement = connection.prepareStatement(query);
	         preparedStatement.setString(1, userPhone);
	         if(preparedStatement.executeUpdate() == 1) {
	        	 check = !check;
	         }
	      
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("deleteUser()에서 쿼리문 오류");
	      } finally {
	         try {
	            if(preparedStatement != null){
	               preparedStatement.close();
	            }
	            if(connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	         }
	      }
	      return check;
	   }
   
}