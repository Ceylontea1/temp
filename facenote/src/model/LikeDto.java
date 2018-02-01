package model;

public class LikeDto {
	private String contentid;
	private String likeuser;
	
	public LikeDto() {}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getLikeuser() {
		return likeuser;
	}
	public void setLikeuser(String likeuser) {
		this.likeuser = likeuser;
	}
}
