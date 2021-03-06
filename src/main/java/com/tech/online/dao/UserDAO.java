package com.tech.online.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import com.tech.online.poimpl.SMUsersBettingHistoryPOImpl;
import com.tech.online.poimpl.SMUsersDetailsPOImpl;
import com.tech.online.poimpl.SMUsersOtpListPOImpl;
import com.tech.online.poimpl.TeamsSMUsersTaggingPOImpl;


@Component
public class UserDAO {
	AnnotationConfiguration config;
	SessionFactory sessFacty;
	Session session;

	public UserDAO() {
		config = new AnnotationConfiguration().configure();
		sessFacty = config.buildSessionFactory();
	}
	
	
	public Boolean verifyUserAthentication(String user,String pwd) {
		boolean userFound =false;
		 try
         {
			 Session session = sessFacty.openSession();
			 Transaction tnx = session.beginTransaction();
			 tnx = session.beginTransaction();       
            String SQL_QUERY =" FROM SMUsersDetailsPOImpl s WHERE s.username=:name AND s.passwd=:pwd";
           
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter("name", user);
			query.setParameter("pwd", pwd);
			List list = query.list();
			if ((list != null) && (list.size() > 0)) {
				userFound = true;
			}
			session.close();

		} catch (Exception e) {
		}
		return userFound;
	}

	public Boolean saveNewRegistrationForm(String user, String pwd, String fname, String lname, String mobile,
			String gender, String gmail, String bday) {
		boolean userFound = false;
		try {
			Session session = sessFacty.openSession();
			Transaction tnx = session.beginTransaction();
			SMUsersDetailsPOImpl userDetailsObj = new SMUsersDetailsPOImpl();
			userDetailsObj.setUserId("1003");
			userDetailsObj.setUsername(user);
			userDetailsObj.setPasswd(pwd);
			userDetailsObj.setGmail(gmail);
			userDetailsObj.setFirstName(fname);
			userDetailsObj.setLastName(lname);
			userDetailsObj.setMobile(mobile);
			userDetailsObj.setBday(bday);
			userDetailsObj.setGender(gender);
			session.save(userDetailsObj);
			tnx.commit();
			session.close();
			userFound = true;

		} catch (Exception e) {
		}
		return userFound;
	}
	
      public boolean checkExistingUser(String user) {		
		boolean result=false;
		 try
         {
			 Session session = sessFacty.openSession();
			 Transaction tnx = session.beginTransaction();
			 tnx = session.beginTransaction();       
            String SQL_QUERY1 =" FROM SMUsersDetailsPOImpl  WHERE username=:name";           
			Query query1 = session.createQuery(SQL_QUERY1);
			query1.setParameter("name",user);			
			List<SMUsersDetailsPOImpl> list = query1.list();
			if ((list != null) && (list.size() > 0)) {				
				result=true;
			}
			else{
				result=false;
			}
			session.close();				
	     }
		 catch (Exception e) {
			
		}
		 return result; 		
	}
      
      public boolean checkExistingMail(String mail) {		
  		boolean result=false;
  		 try
           {
  			 Session session = sessFacty.openSession();
  			 Transaction tnx = session.beginTransaction();
  			 tnx = session.beginTransaction();       
              String SQL_QUERY1 =" FROM SMUsersDetailsPOImpl  WHERE gmail=:name";           
  			Query query1 = session.createQuery(SQL_QUERY1);
  			query1.setParameter("name",mail);			
  			List<SMUsersDetailsPOImpl> list = query1.list();
  			if ((list != null) && (list.size() > 0)) {				
  				result=true;
  			}else{
  				result=false;
  			}
  			session.close();				
  	     }
  		 catch (Exception e) {
  			
  		}
  		 return result; 		
  	} 
      
      public String getOTP(String mail) {		
    		String result=null;
    		 try
             {
    			 Session session = sessFacty.openSession();
    			  Transaction tnx = session.beginTransaction();       
                String SQL_QUERY1 ="select smuser.otp from SMUsersOtpListPOImpl smuser where smuser.gmail=:name";           
    			Query query1 = session.createQuery(SQL_QUERY1);
    			query1.setParameter("name",mail);			
    			List<SMUsersOtpListPOImpl> list = query1.list();
    			if ((list != null) && (list.size() > 0)) {    				
    				Iterator it = list.iterator();
    				while(it.hasNext())
    				{
    					String i = (String)it.next();
    					result=i;
    				}    				
    			}
    			session.close();				
    	     }
    		 catch (Exception e) {
    			
    		}
    		 return result; 		
    	}    
	
	public List<SMUsersDetailsPOImpl> getUserDetails(String user,String pwd) {
		
		List<SMUsersDetailsPOImpl> userList=null;
		 try
         {
			 Session session = sessFacty.openSession();
			 Transaction tnx = session.beginTransaction();
			 tnx = session.beginTransaction();       
            String SQL_QUERY1 =" FROM SMUsersDetailsPOImpl  WHERE username=:name AND passwd=:pwd";
           
			Query query1 = session.createQuery(SQL_QUERY1);
			query1.setParameter("name",user);
			query1.setParameter("pwd",pwd);
			List<SMUsersDetailsPOImpl> list = query1.list();

			if ((list != null) && (list.size() > 0)) {

				userList = list;
			}
			session.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return userList;

	}

	public List getMatchesList() {
		List matchList =null;
		try
        {
			 Session session = sessFacty.openSession();
			 Transaction tnx = session.beginTransaction();
			 tnx = session.beginTransaction();       
           String SQL_QUERY1 =" FROM MatchesListPOImpl";
          
			Query matchquery = session.createQuery(SQL_QUERY1);			
			List matchlist = matchquery.list();

			if ((matchlist != null) && (matchlist.size() > 0)) {

				matchList = matchlist;
			}
			session.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return matchList;

	}

	public List<SMUsersBettingHistoryPOImpl> getTeamsList() {
		List<SMUsersBettingHistoryPOImpl> result =null;
		try
        {
			 Session session = sessFacty.openSession();
			 Transaction tnx = session.beginTransaction();
			 tnx = session.beginTransaction();       
           String SQL_QUERY1 ="select matchNumber,userName,bet,match,matchDate,status from SMUsersBettingHistoryPOImpl";
          
           Query matchquery = session.createQuery(SQL_QUERY1);	
			

           matchquery.setCacheable(true);
			result = matchquery.list();
			/*List<SMUsersBettingHistoryPOImpl> teamlist = teamquery.list();
           
			if ((teamlist != null) && (teamlist.size() > 0)) {
				result=teamlist;
			}*/
			session.close();

		} catch (Exception e) {
		}
		return result;

	}
	
	public List<TeamsSMUsersTaggingPOImpl> getTaggedTeams(String Username) {
		List<TeamsSMUsersTaggingPOImpl> result =null;
		try
        {
			 Session session = sessFacty.openSession();
			 Transaction tnx = session.beginTransaction();
			 tnx = session.beginTransaction();       
           String SQL_QUERY1 =" FROM TeamsSMUsersTaggingPOImpl WHERE username=:name";
          
			Query teamquery = session.createQuery(SQL_QUERY1);	
			teamquery.setParameter("name",Username);
			List teamlist = teamquery.list();

			if ((teamlist != null) && (teamlist.size() > 0)) {				
				
				result= teamlist;
			}
			session.close();
				
	}
		 catch (Exception e) {
			// TODO: handle exception
		}
		 return result;
		
		
	}
	
}

