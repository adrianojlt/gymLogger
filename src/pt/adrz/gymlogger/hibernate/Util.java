package pt.adrz.gymlogger.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		
		SessionFactory sf = null;
		
		try {
			sf = new Configuration().configure("mysql.cfg.xml").buildSessionFactory();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return sf;
	}
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
    	getSessionFactory().close();
    }
}
