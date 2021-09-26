package ranking.model.vo;

public class Ranking {

	private int rank;		// 랭킹
	private String nickName;	// 닉네임
	private int s_no;			// 스터디방 번호
	private int s_time;			// 총 공부시간
	private String profile_img;	// 프로필 이미지 경로
	private String rank_img;	// 랭킹 아이콘 이미지
	
	public Ranking() {}

	public Ranking(int rank, String nickName, int s_time, String profile_img) {
		super();
		this.rank = rank;
		this.nickName = nickName;
		this.s_time = s_time;
		this.profile_img = profile_img;
	}

	public Ranking(int rank, String nickName, int s_time, String profile_img, String rank_img) {
		super();
		this.rank = rank;
		this.nickName = nickName;
		this.s_time = s_time;
		this.profile_img = profile_img;
		this.rank_img = rank_img;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public String getRank_img() {
		return rank_img;
	}

	public void setRank_img(String rank_img) {
		this.rank_img = rank_img;
	}

	@Override
	public String toString() {
		return "Ranking [rank=" + rank + ", nickName=" + nickName + ", s_no=" + s_no + ", s_time=" + s_time
				+ ", profile_img=" + profile_img + ", rank_img=" + rank_img + "]";
	}

	
}



