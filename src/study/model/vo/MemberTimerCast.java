package study.model.vo;

// 초 매개변수로 받아 필드값에 저장하는 객체 생성
public class MemberTimerCast {
	private int hour;
	private int minute;
	private int second;
	
	public MemberTimerCast() {}
	
	//받는 시간은 초단위
	public MemberTimerCast(int time) {
		
		int hour = time / 3600;
		int minute = (time % 3600) / 60;
		int second = (time % 3600) % 60;
		
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public MemberTimerCast(int hour, int minute, int second) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "MemberTimerCast [hour=" + hour + ", minute=" + minute + ", second=" + second + "]";
	}
	
	
	
	
	
	
	
	
	
}
