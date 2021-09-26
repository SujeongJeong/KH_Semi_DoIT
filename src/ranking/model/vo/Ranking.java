package ranking.model.vo;

public class Ranking {

	private String ranking;
	private String nickName;
	private int s_no;
	private int s_time;
	private String profile_img;
	
	public Ranking() {}

	public Ranking(String ranking, String nickName, int s_time, String profile_img) {
		super();
		this.ranking = ranking;
		this.nickName = nickName;
		this.s_time = s_time;
		this.profile_img = profile_img;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getS_time() {
		return s_time;
	}

	public void setS_time(int s_time) {
		this.s_time = s_time;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	@Override
	public String toString() {
		return "Ranking [ranking=" + ranking + ", nickName=" + nickName + ", s_no=" + s_no + ", s_time=" + s_time
				+ ", profile_img=" + profile_img + "]";
	}

}



