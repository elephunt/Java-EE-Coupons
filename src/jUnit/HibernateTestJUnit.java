package jUnit;
import Model.Coupon;
import org.junit.Before;
import org.junit.Test;
import sun.java2d.SurfaceDataProxy;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by RomaMarg on 10.01.2015.
 */
public class HibernateTestJUnit {

    private Model.HibernateCouponsDAO m_couponTestHibernate = Model.HibernateCouponsDAO.getInstance();

    @Before
    public void GetConnection()
    {

    }

    /**
     * Test For Database By Deleting All Coupons In Start And Adding Two Coupons
     */
    @Test
    public void addNewCouponHibernateAndDelete()
    {
        List<Coupon> testList = m_couponTestHibernate.getAllCoupons();
        for (Coupon coupTest : testList)
        {
            m_couponTestHibernate.delete(coupTest.getId());
        }
        testList = m_couponTestHibernate.getAllCoupons();
        assertEquals(0, testList.size());
        assertNull(m_couponTestHibernate.getCurrentCoupon(1));
        Coupon firstCoupon = new Coupon();
        Coupon secondCoupon = new Coupon();
        initializeCoupon(firstCoupon, secondCoupon);
        m_couponTestHibernate.addCoupon(firstCoupon);
        m_couponTestHibernate.addCoupon(secondCoupon);
         testList = m_couponTestHibernate.getAllCoupons();
        assertNotNull(testList);
        assertEquals(2, testList.size());
    }

    @Test
    public void updateCouponHibernate()
    {
        Coupon firstCoupon = new Coupon();
        Coupon secondCoupon = new Coupon();
        initializeCoupon(firstCoupon, secondCoupon);
        secondCoupon = m_couponTestHibernate.getCurrentCoupon(firstCoupon.getId());
        assertEquals(1000.0, secondCoupon.getPrice(), 0.00);
        firstCoupon.setPrice(550);
        m_couponTestHibernate.update(firstCoupon.getId(), firstCoupon.getName(), firstCoupon.getPrice(), firstCoupon.getCategory(), firstCoupon.getDate(), firstCoupon.getDescription(), firstCoupon.getLatitude(), firstCoupon.getLongitude());
        assertEquals(550, m_couponTestHibernate.getCurrentCoupon(firstCoupon.getId()).getPrice(), 0.001);
    }

    /**
     *
     * @param i_FirstCoupon - First coupon to initialize
     * @param i_SecondCoupon - //--//--/
     */
    private void initializeCoupon(Coupon i_FirstCoupon, Coupon i_SecondCoupon){
        i_FirstCoupon.setId(5);
        i_FirstCoupon.setName("FirstCoupon");
        i_FirstCoupon.setPrice(1000);
        i_FirstCoupon.setDate("20-01-2015 22:00");
        i_FirstCoupon.setCategory("Food");
        i_FirstCoupon.setLatitude("33");
        i_FirstCoupon.setLongitude("33");
        i_FirstCoupon.setDescription("good");
        i_SecondCoupon.setId(8);
        i_SecondCoupon.setName("FirstCoupon");
        i_SecondCoupon.setPrice(1000);
        i_SecondCoupon.setDate("20-01-2015 22:00");
        i_SecondCoupon.setCategory("first coupon");
        i_SecondCoupon.setLatitude("33");
        i_SecondCoupon.setLongitude("33");
        i_SecondCoupon.setDescription("Second");
    }
}
