package Model;




import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;
/**
 * Singleton Class Of coupons hibernate
 */
public class HibernateCouponsDAO implements ICouponsDAO
{

	static 	Logger  m_Logger = Logger.getLogger("HibernateCouponsDAO");
	private static HibernateCouponsDAO m_Instance = null;
	/**
	 * List Of All Coupon In Db
	 */
	private List<Coupon> m_Coupons = null;
	/**
	 * List Of All Admins In DB
	 */
	private List<AdminLogin> m_AdminLogins = null;
	SessionFactory m_factory = null;
	private HibernateCouponsDAO()
	{
		m_Coupons = new ArrayList<Coupon>();
		try {
			m_Logger.addAppender(new FileAppender(new SimpleLayout(), "log.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static HibernateCouponsDAO getInstance()
	{
		if(m_Instance == null)
		{
			m_Instance = new HibernateCouponsDAO();
		}

		return m_Instance;
	}

	/**
	 *
	 * @param i_Coupon - Get A coupon To Add in DB
	 * @return - boolean If Successes To Add in DB
	 */
	public boolean addCoupon(Coupon i_Coupon) {
		boolean flag = false;
		Coupon coupon = getCurrentCoupon(i_Coupon.getId());
		if (coupon == null) {

			Session session = m_factory.openSession();
			try {
				session.beginTransaction();
				session.save(i_Coupon);
				session.getTransaction().commit();
				flag = true;
				if (coupon != null) {
					m_Logger.info("Coupon Add" + coupon.toString());
				}
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					flag = false;
				}
			} finally {
				session.close();
				return  flag;
			}

		}
		return flag;
	}

	/**
	 *
	 * @param id - Id Coupon
	 * @param name
	 * @param price
	 * @param i_Category
	 * @param i_Date
	 * @param i_Description
	 * @param i_Latitude
	 * @param i_Longitude
	 * @return - true if sucessed
	 */
	 public boolean update(int id, String name, double price, String i_Category, String i_Date,String i_Description,String i_Latitude,String i_Longitude)
	 {
		 	boolean flag = false;

			Session session = m_factory.openSession();
			try 
			{
				session.beginTransaction();
				
				Coupon cou = (Coupon)session.get(Coupon.class, id);
				cou.setName(name);
				cou.setPrice(price);
				cou.setCategory(i_Category);
				cou.setDate(i_Date);
				cou.setDescription(i_Description);
				cou.setLatitude(i_Latitude);
				cou.setLongitude(i_Longitude);
				session.update(cou);
				session.getTransaction().commit();
				flag = true;
				if (cou != null) {
					m_Logger.info("Coupon Updated" + cou.toString());
				}
			
			} 
			catch (HibernateException e) {
				if (session.getTransaction() != null)
					session.getTransaction().rollback();
			} 
			finally 
			{
				session.close();
				return flag;
			}
	 }

	/**
	 * Delect coupon from DB by Id
	 * @param id
	 * @return - true if Suceesed
	 */
	 public boolean delete(int id)
	 {
		 	boolean flag = false;
			Session session = m_factory.openSession();
			try 
			{
				session.beginTransaction();
				
				Coupon cou = (Coupon)session.get(Coupon.class, id);
				session.delete((Coupon) cou);
				session.getTransaction().commit();
				flag = true;
				if (cou != null) {
					m_Logger.info("Coupon Deleted" + cou.toString());
				}
			} 
			catch (HibernateException e) {
				if (session.getTransaction() != null)
					session.getTransaction().rollback();
			} 
			finally 
			{
				session.close();
				return flag;
			}
	 }

	/**
	 * Method To Get All Coupon From DataBase
	 * @return All Coupons From Database
	 */
	public List<Coupon> getAllCoupons()
	{
		Session session = m_factory.openSession();
		try
		{
			session.beginTransaction();

			m_Coupons = session.createCriteria(Coupon.class).list();

			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
		}
		finally
		{
			if (m_Coupons != null) {
				m_Logger.info("All Coupons" + m_Coupons.toString());
			}
			session.close();
			return m_Coupons;
		}
	}

	/**
	 * Check if There Is Admin Login And Match Password From DB
	 * @param i_AdminName
	 * @param i_Password
	 * @return True If IT Exist I DB
	 */
	public boolean checkAdminLogin(String i_AdminName , String i_Password)
	{
		boolean flag = false;
		Session session = m_factory.openSession();

		try
		{
			session.beginTransaction();
			m_AdminLogins = session.createCriteria(AdminLogin.class).list();
			for (AdminLogin admin : m_AdminLogins){
				if ((admin.getName().equals(i_AdminName)) && (admin.getPassword().equals(i_Password)))
				{
					flag = true;
					if (m_AdminLogins != null)
					{
						m_Logger.info("Get Admin Password successful" + m_AdminLogins.toString());
					}

				}
			}
		}
		catch (HibernateException e )
		{
			if (m_AdminLogins != null) {
				m_Logger.info("failed To get Admin password" + m_AdminLogins.toString());
			}
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		}
		finally
		{

			session.close();
			return  flag;
		}
	}

	/**
	 * Get Specific Coupon From DB
	 * @param i_Id
	 * @return Return Coupon From Database
	 */
	public Coupon getCurrentCoupon(int i_Id)
	{
		Session session = m_factory.openSession();
		Coupon coupon = null;
		try
		{
			session.beginTransaction();

			coupon = (Coupon) session.get(Coupon.class, i_Id);
		}
		catch (HibernateException e)
		{
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		}
		finally
		{
			if (coupon != null) {
				m_Logger.info("Got Current Coupon" + coupon.toString());
			}
			session.close();
			return coupon;
		}
	}

	/**
	 * Returns Coupons Sorting By Category
	 * @param i_Category
	 * @return List Of Coupons
	 */
	public List<Coupon> getCouponsByCategory(String i_Category)
	{

	  List<Coupon> allCoupons = getAllCoupons();
		List<Coupon> couponsByCategory = new ArrayList<Coupon>();
		for( Coupon coupon : allCoupons)
		{
			if (coupon.getCategory().equals(i_Category))
			{
				couponsByCategory.add(coupon);
			}

		}
		m_Logger.info(couponsByCategory.toString());
		return couponsByCategory;
	}
}
