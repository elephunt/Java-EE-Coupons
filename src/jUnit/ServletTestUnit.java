package jUnit;

import Controller.MainController;

import com.mockrunner.mock.web.WebMockObjectFactory;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.servlet.ServletTestModule;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Created by RomaMarg on 16.01.2015.
 */
public class ServletTestUnit {

    private ServletTestModule tester;
    private WebMockObjectFactory factory;
    private MainController servletController;
    private String[] statusesFromServlet = {"HomePage", "AdminLogin", "CheckLogin", "AddNewCoupon", "DeleteCoupon", "UpdateCoupon", "RemoveFromCart", "AddToCart",
            "AdminAction", "LogOut", "SortCart", "CheckValidateCouponsInCart"};
    private String adminUserNamePassword = "Admin";

    @Before
    public void setup() {
        factory = new WebMockObjectFactory();
        tester = new ServletTestModule(factory);
    }

    /**
     * Test For Loading HomePage From Servlet
     */
    @Test
    public void performTaskTestHomePage() {
        tester.addRequestParameter("JspName", statusesFromServlet[0]);
           tester.createServlet(MainController.class);
           tester.doGet();
        assertEquals(1, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }

    /**
     * By Default HomePage Loads
     */
    @Test
    public void performTaskLoadHomeDefault()
    {
        tester.createServlet(MainController.class);
        tester.doGet();
       assertEquals(1, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }

    /**
     * Check The Servlet By Login Admin
     * Status 3 - He succeed To login
     */
    @Test
    public void performTaskAdminLogin()
    {
        tester.addRequestParameter("JspName", statusesFromServlet[1]);
        tester.addRequestParameter("AdminName", adminUserNamePassword);
        tester.addRequestParameter("Password", adminUserNamePassword);
        tester.createServlet(MainController.class);
        tester.doGet();
        assertEquals(1, factory.getMockRequest().getAttribute("LoginGood"));
    }

    @Test
    public void performTaskCheckLogin()
    {

    }

    /**
     * Add Coupon With Wrong Fields
     */
    @Test
    public void performTaskAddNewCouponWrong()
    {
        tester.addRequestParameter("JspName", statusesFromServlet[3]);
        tester.addRequestParameter("Id", "-2");
        tester.addRequestParameter("Name", "");
        tester.addRequestParameter("Price", "-55.5");
        tester.addRequestParameter("Food", "Category");
        tester.addRequestParameter("Date", "");
        tester.createServlet(MainController.class);
        tester.doGet();
        //52 is that something goes wrong in filling form to add new coupon
        assertEquals(52, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }

    /**
     * Add Right coupon
     */
    @Test
    public void performTaskAddNewCouponRight()
    {
        tester.addRequestParameter("JspName", statusesFromServlet[3]);
        tester.addRequestParameter("AdminName", adminUserNamePassword);
        tester.addRequestParameter("Password", adminUserNamePassword);
        tester.addRequestParameter("Id", "5");
        tester.addRequestParameter("Name", "First");
        tester.addRequestParameter("Price", "1000");
        tester.addRequestParameter("Category", "Food");
        tester.addRequestParameter("Date", "20-01-2015 22:00");
        tester.createServlet(MainController.class);
        tester.doGet();
        //5 is that he succeed to add coupon
        assertEquals(5, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }

    /**
     * Deleting Coupon ,The only problem Can Be Here is Connection With DB
     * 6- good  62 - fail
     */
    @Test
      public void performTaskDeleteCoupon()
    {
        tester.addRequestParameter("JspName", statusesFromServlet[4]);
        tester.addRequestParameter("id", "5");
        tester.addRequestParameter("AdminName", adminUserNamePassword);
        tester.addRequestParameter("Password", adminUserNamePassword);
        tester.createServlet(MainController.class);
        tester.doGet();
        //6 is that he succeed to delete coupon
        assertEquals(6, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }

    /**
     * Deleting Wrong Coupon
     */
    @Test
    public void performTaskDeleteCouponWrong()
    {
        tester.addRequestParameter("JspName", statusesFromServlet[4]);
        tester.addRequestParameter("id", "2");
        tester.addRequestParameter("AdminName", adminUserNamePassword);
        tester.addRequestParameter("Password", adminUserNamePassword);
        tester.createServlet(MainController.class);
        tester.doGet();
        //5 is that he succeed to add coupon
        assertEquals(62, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }

    @Test
    public void performTaskUpdateCouponWrong()
    {
        tester.addRequestParameter("JspName", statusesFromServlet[5]);
        tester.addRequestParameter("Id", "8");
        tester.addRequestParameter("Name", "First");
        tester.addRequestParameter("Price", "-5000");
        tester.addRequestParameter("Category", "Food");
        tester.addRequestParameter("Date", "20-01-2015 22:00");
        tester.createServlet(MainController.class);
        tester.doGet();
        assertEquals(72, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }

    @Test
    public void performTaskUpdateCouponRight()
    {
        tester.addRequestParameter("AdminName", adminUserNamePassword);
        tester.addRequestParameter("Password", adminUserNamePassword);
        tester.addRequestParameter("JspName", statusesFromServlet[5]);
        tester.addRequestParameter("Id", "8");
        tester.addRequestParameter("Name", "First");
        tester.addRequestParameter("Price", "5000");
        tester.addRequestParameter("Category", "Food");
        tester.addRequestParameter("Date", "20-01-2015 22:00");
        tester.createServlet(MainController.class);
        tester.doGet();
        assertEquals(7, factory.getMockRequest().getAttribute("JunitTestIndexes"));
    }
}
