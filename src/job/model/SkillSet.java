package job.model;

public class SkillSet {
	protected int skillSetId;
	protected String SkillSetName;
	
	public SkillSet(int skillSetId) {
		super();
		this.skillSetId = skillSetId;
	}

	public SkillSet(int skillSetId, String skillSetName) {
		this.skillSetId = skillSetId;
		SkillSetName = skillSetName;
	}

	public SkillSet(String skillSetName) {
		SkillSetName = skillSetName;
	}

	public int getSkillSetId() {
		return skillSetId;
	}

	public void setSkillSetId(int skillSetId) {
		this.skillSetId = skillSetId;
	}

	public String getSkillSetName() {
		return SkillSetName;
	}

	public void setSkillSetName(String skillSetName) {
		SkillSetName = skillSetName;
	}
}
