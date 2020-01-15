package job.tools;

import java.sql.SQLException;
import java.util.List;

import job.dal.*;
import job.model.*;

public class Inserter {
	public static void main(String[] args) throws SQLException {
		CompanyDao companyDao = CompanyDao.getInstance();
		Company c1 = companyDao.getCompanyById(1);
		List<Company> cList1 = companyDao.getCompanyForState("CA");
		System.out.format("Reading company: i:%s n:%s u:%s y:%s c:%s s:%s c:%s z:%s ct:%s d:%s cc:%s \n",
				c1.getCompanyId(), c1.getCompanyName(), c1.getURL(), c1.getYearFounded(), c1.getCity(), 
				c1.getState(), c1.getCountry(), c1.getZipCode(), c1.getCompanyType(), c1.getDescription(),
				c1.getCompanyCategory().getCompanyCategoryId());
		for(Company c : cList1) {
			System.out.format("Reading company: i:%s n:%s u:%s y:%s c:%s s:%s c:%s z:%s ct:%s d:%s cc:%s \n",
					c.getCompanyId(), c.getCompanyName(), c.getURL(), c.getYearFounded(), c.getCity(), 
					c.getState(), c.getCountry(), c.getZipCode(), c.getCompanyType(), c.getDescription(),
					c.getCompanyCategory().getCompanyCategoryId());
		}
		
		JobDao jobDao = JobDao.getInstance();
		Job j1 = jobDao.getJobById(1);
		System.out.format("Reading job: j:%s c:%s m:%s d:%s jl:%s w:%s c:%s jd:%s p:%s u:%s \n",
				j1.getJobId(), j1.getCompany().getCompanyId(), j1.getMainJobTitle(), j1.getDateAdvertised(), 
				j1.getJobLocation().getJobLocationId(), j1.getWorkType().getWorkType(), j1.getClassification(), 
				j1.getJobDescription(), j1.getPageURL(), j1.getUserAccount().getUserAccountId());
		List<Job> jList1 = jobDao.getJobForCompany(c1);
		for(Job j : jList1) {
			System.out.format("Reading job: j:%s c:%s m:%s d:%s jl:%s w:%s c:%s jd:%s p:%s u:%s \n",
					j.getJobId(), j.getCompany().getCompanyId(), j.getMainJobTitle(), j.getDateAdvertised(), 
					j.getJobLocation().getJobLocationId(), j.getWorkType().getWorkType(), j.getClassification(), 
					j.getJobDescription(), j.getPageURL(), j.getUserAccount().getUserAccountId());
		}
	}
}
