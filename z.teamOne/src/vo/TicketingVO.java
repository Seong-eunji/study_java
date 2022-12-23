package vo;

public class TicketingVO {
	private String ticketingNumber;
	private String ticketingMovie;
	private String ticketingTheater;
	private String ticketingTheaterNumber;
	private String ticketingTime;
	private String ticketingSeat;
	private String userPhone;

	public TicketingVO() {
		;
	}

	public String getTicketingNumber() {
		return ticketingNumber;
	}

	public void setTicketingNumber(String ticketingNumber) {
		this.ticketingNumber = ticketingNumber;
	}

	public String getTicketingMovie() {
		return ticketingMovie;
	}

	public void setTicketingMovie(String ticketingMovie) {
		this.ticketingMovie = ticketingMovie;
	}

	public String getTicketingTheater() {
		return ticketingTheater;
	}

	public void setTicketingTheater(String ticketingTheater) {
		this.ticketingTheater = ticketingTheater;
	}

	public String getTicketingTheaterNumber() {
		return ticketingTheaterNumber;
	}

	public void setTicketingTheaterNumber(String ticketingTheaterNumber) {
		this.ticketingTheaterNumber = ticketingTheaterNumber;
	}

	public String getTicketingTime() {
		return ticketingTime;
	}

	public void setTicketingTime(String ticketingTime) {
		this.ticketingTime = ticketingTime;
	}

	public String getTicketingSeat() {
		return ticketingSeat;
	}

	public void setTicketingSeat(String ticketingSeat) {
		this.ticketingSeat = ticketingSeat;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		String str = "[예매번호]" + ticketingNumber + " [영화]" + ticketingMovie + " [극장]" + ticketingTheater + " [상영관]"
				+ ticketingTheaterNumber + " [상영시간]" + ticketingTime + " [좌석]" + ticketingSeat + " [연락처]" + userPhone;
		return str;
	}
}