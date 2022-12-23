package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.TicketingVO;

public class TicketingDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

//   예매하기 -> 입력받은 영화 정보 저장
	public void ticketing(TicketingVO ticketingVO) {
		String query = "INSERT INTO TBL_TICKETING "
				+ "(TICKETING_NUMBER, TICKETING_MOVIE, TICKETING_THEATER, TICKETING_THEATER_NUMBER, TICKETING_TIME, TICKETING_SEAT, USER_PHONE) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ticketingVO.getTicketingNumber());
			preparedStatement.setString(2, ticketingVO.getTicketingMovie());
			preparedStatement.setString(3, ticketingVO.getTicketingTheater());
			preparedStatement.setString(4, ticketingVO.getTicketingTheaterNumber());
			preparedStatement.setString(5, ticketingVO.getTicketingTime());
			preparedStatement.setString(6, ticketingVO.getTicketingSeat());
			preparedStatement.setString(7, ticketingVO.getUserPhone()); /* getUserPhone */
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ticketing()에서 쿼리 오류 남");
		} finally {
			try {
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
	}

//   예매내역조회 -> 입력받은 전화번호와 일치하는 예매내역 전부
	public ArrayList<TicketingVO> getTicketingList(String userPhone) {

		ArrayList<TicketingVO> ticketingLists = new ArrayList<TicketingVO>();

		String query = "SELECT TICKETING_NUMBER, TICKETING_MOVIE, TICKETING_THEATER, TICKETING_THEATER_NUMBER, TICKETING_TIME, TICKETING_SEAT, USER_PHONE "
				+ "FROM TBL_TICKETING WHERE USER_PHONE = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhone);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				TicketingVO ticketingVO = new TicketingVO();

				ticketingVO.setTicketingNumber(resultSet.getString(1));
				ticketingVO.setTicketingMovie(resultSet.getString(2));
				ticketingVO.setTicketingTheater(resultSet.getString(3));
				ticketingVO.setTicketingTheaterNumber(resultSet.getString(4));
				ticketingVO.setTicketingTime(resultSet.getString(5));
				ticketingVO.setTicketingSeat(resultSet.getString(6));				
				ticketingVO.setUserPhone(resultSet.getString(7));

				ticketingLists.add(ticketingVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getTicketingList()에서 쿼리문 오류");
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
		return ticketingLists;

	}

//   예매취소 -> TBL_TICKETING에서 정보 삭제

	public void deleteTicketing(String ticketingNumber, String userPhone) {
		String query = "DELETE FROM TBL_TICKETING WHERE TICKETING_NUMBER = ? AND USER_PHONE = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ticketingNumber);
			preparedStatement.setString(2, userPhone);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("deleteTicketing()에서 쿼리 오류남");
		} finally {
			try {
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
	}
	
	// 좌석정보 조회
	public ArrayList<String> getSeats(TicketingVO ticketingVO) {
		String query = "SELECT TICKETING_SEAT FROM TBL_TICKETING WHERE TICKETING_MOVIE = ?"
						+ "AND TICKETING_THEATER = ? AND TICKETING_THEATER_NUMBER = ? AND TICKETING_TIME = ?";
		ArrayList<String> reservedSeats = new ArrayList<String>();

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ticketingVO.getTicketingMovie());
			preparedStatement.setString(2, ticketingVO.getTicketingTheater());
			preparedStatement.setString(3, ticketingVO.getTicketingTheaterNumber());
			preparedStatement.setString(4, ticketingVO.getTicketingTime());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				reservedSeats.add(resultSet.getString(1));
			}

		} catch (SQLException e) {
			System.out.println("getSeats()에서 쿼리 오류");
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
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return reservedSeats;
	}
	
	// 좌석 중복조회
	public boolean checkSeat(TicketingVO ticketingVO) {
		String query = "SELECT COUNT(*) FROM TBL_TICKETING WHERE TICKETING_MOVIE = ? AND TICKETING_THEATER = ?"
					+ " AND TICKETING_THEATER_NUMBER = ? AND TICKETING_TIME = ? AND TICKETING_SEAT = ?";
		boolean check = false;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ticketingVO.getTicketingMovie());
			preparedStatement.setString(2, ticketingVO.getTicketingTheater());
			preparedStatement.setString(3, ticketingVO.getTicketingTheaterNumber());
			preparedStatement.setString(4, ticketingVO.getTicketingTime());
			preparedStatement.setString(5, ticketingVO.getTicketingSeat());
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			check = resultSet.getInt(1) == 1;

		} catch (SQLException e) {
			System.out.println("checkSeat()에서 쿼리 오류");
			e.printStackTrace();
		} catch (NullPointerException npe) {
			return false;
		}
		
		finally {
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
		return check;
	}
	
}
