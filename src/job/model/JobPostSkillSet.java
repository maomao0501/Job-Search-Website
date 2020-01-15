package job.model;

public class JobPostSkillSet {
	protected int jobPostSkillSetId;
    protected Job job;
    protected SkillSet skillSet;
    protected String skillLevel;
    
	public JobPostSkillSet(int jobPostSkillSetId, Job job, SkillSet skillSet, String skillLevel) {
		this.jobPostSkillSetId = jobPostSkillSetId;
		this.job = job;
		this.skillSet = skillSet;
		this.skillLevel = skillLevel;
	}

	public JobPostSkillSet(Job job, SkillSet skillSet, String skillLevel) {
		this.job = job;
		this.skillSet = skillSet;
		this.skillLevel = skillLevel;
	}

	public JobPostSkillSet(int jobPostSkillSetId) {
		this.jobPostSkillSetId = jobPostSkillSetId;
	}

	public int getJobPostSkillSetId() {
		return jobPostSkillSetId;
	}

	public void setJobPostSkillSetId(int jobPostSkillSetId) {
		this.jobPostSkillSetId = jobPostSkillSetId;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public SkillSet getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
}
