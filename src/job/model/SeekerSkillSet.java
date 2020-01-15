package job.model;

public class SeekerSkillSet {
	
	protected int SeekerSkillSetId;
	protected String UserAccountId;
	protected int SkillSetId;
	protected String SkillLevel;
	
	public SeekerSkillSet(int seekerSkillSetId, String userAccountId, int skillSetId, String skillLevel) {
		this.SeekerSkillSetId = seekerSkillSetId;
		this.UserAccountId = userAccountId;
		this.SkillSetId = skillSetId;
		this.SkillLevel = skillLevel;
	}

	public int getSeekerSkillSetId() {
		return SeekerSkillSetId;
	}

	public void setSeekerSkillSetId(int seekerSkillSetId) {
		this.SeekerSkillSetId = seekerSkillSetId;
	}

	public String getUserAccountId() {
		return UserAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.UserAccountId = userAccountId;
	}

	public int getSkillSetId() {
		return SkillSetId;
	}

	public void setSkillSetId(int skillSetId) {
		this.SkillSetId = skillSetId;
	}

	public String getSkillLevel() {
		return SkillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.SkillLevel = skillLevel;
	}

}
