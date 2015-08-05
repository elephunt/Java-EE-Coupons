package Controller;
import Model.AdminLogin;
import Model.Coupon;
import Model.HibernateCouponsDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RomaMarg on 05.01.2015.
 */
@WebServlet(name = "MainController", urlPatterns = "/MainController")
public class MainController extends HttpServlet {
    /**
     * Property Of Hibernate
     */
    private Model.HibernateCouponsDAO m_CouponHibernate = Model.HibernateCouponsDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            performTask(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            performTask(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get request from doPost or doGet and Sort What Action To do
     * @param request
     * @param response
     * @throws NoSuchAlgorithmException - For Md5 Auth.
     */
    private void performTask(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        try {
            checkIfThereIsCoordinates(request, response);
            String comingFrom = request.getParameter("JspName");
            if (comingFrom == null) {
                loadHomePage(request, response);
            } else {
                if (comingFrom.equals("HomePage")) {
                    loadHomePage(request, response);
                }
                if (comingFrom.equals("AdminLogin")) {
                    adminLogin(request, response);

                }
                if (comingFrom.equals("CheckLogin")) {
                    adminLogin(request, response);

                }
                if (comingFrom.equals("AddNewCoupon")) {
                    addNewCoupon(request, response);
                }
                if (comingFrom.equals("DeleteCoupon")) {
                    deleteCoupon(request, response);
                }
                if (comingFrom.equals("UpdateCoupon")) {
                    updateCoupon(request, response);
                }
                if (comingFrom.equals("RemoveFromCart")) {
                    removeFromCart(request, response);
                }
                if (comingFrom.equals("AddToCart")) {
                    addToCart(request, response);
                }
                if (comingFrom.equals("AdminAction")) {
                    adminActions(request, response);
                }
                if (comingFrom.equals("LogOut")) {
                    logOut(request, response);
                }
                if (comingFrom.equals("SortCart")) {
                    sortCartByCategory(request, response);
                }
                if (comingFrom.equals("CheckValidateCouponsInCart"))
                {
                    updateCouponsByDate(request, response);
                    forwardPage(request, response, "MyCart.jsp");
                }

            }
        }
         catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    //-----------------------HOMEPAGE.JSP------------------------------------------//

    /**
     * Method To Load Home Page of Coupons And Check If the Category Is On, So it Sort Coupons To Show On HomePage
     * And Before Load Checks The Dates Of ALl Coupons
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    private void loadHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        updateCouponsByDate(request, response);
        String category = request.getParameter("Category");
        if (category != null && !category.equals("All Coupons"))
        {
            List<Coupon> listOfCategories = HibernateCouponsDAO.getInstance().getCouponsByCategory(category);
            request.setAttribute("ListOfAllCouponsInDB", listOfCategories );
        }
        else {
            if (!checkIfThereIsCoordinates(request, response))
            {
                List<Coupon> listOfAllCoupons = Model.HibernateCouponsDAO.getInstance().getAllCoupons();
                request.setAttribute("ListOfAllCouponsInDB", listOfAllCoupons);
            }

        }
        //For Junit Test Index
        request.setAttribute("JunitTestIndexes",1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/HomePage.jsp");
        dispatcher.forward(request, response);
    }

    //-------------------------addNewCoupon.JSP---------------------------------------------//

    /**Load All Date From AddNewCoupon,Check Validate Of fields And Add If there is no error in fields
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     * Something wrong with filling form so status -52
     *
     */
    private void addNewCoupon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, NoSuchAlgorithmException {
        boolean succeedToAdd = false;
        //Print All errors that happened To Fill In Form
        List<String> errorMessages = new ArrayList<String>();
        boolean fieldsCorrect = true;
        response.setContentType("text/html");
        int id = 0;
        double price = 0;
        String date = null;
        String category = null;
        String name = null;
        String description= request.getParameter("description");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        try {
            id = Integer.parseInt(request.getParameter("Id"));
            if (id < 0) {
                fieldsCorrect = false;
                //For Junit Test Index
                request.setAttribute("JunitTestIndexes", 52);
                errorMessages.add("Id cant be negative number");
            }
        }
        catch (NumberFormatException numberFormatException) {
            //Something wrong with filling form
            request.setAttribute("JunitTestIndexes", 52);
            fieldsCorrect = false;
            errorMessages.add("Id field has to be integer number");
        }
        name = request.getParameter("Name");
        if (name != null) {
            if (name.trim().equals("")) {
                //Something wrong with filling form
                request.setAttribute("JunitTestIndexes", 52);
                errorMessages.add("Name field cannot be empty");
                fieldsCorrect = false;
            }
        }
        try {
            price = Double.parseDouble(request.getParameter("Price"));
        }
        catch (NumberFormatException numberFormatException)
        {
            //Something wrong with filling form
            request.setAttribute("JunitTestIndexes", 52);
            fieldsCorrect = false;
            errorMessages.add("Price field has to be double");
        }
             category = request.getParameter("Category");
             date = request.getParameter("Date");

          if (date.trim().equals("")) {
              //Something wrong with filling form for Junit TEst
              request.setAttribute("JunitTestIndexes", 52);
              fieldsCorrect = false;
                errorMessages.add("Date field cannot be empty");
        }
            if (fieldsCorrect)
            {
                Coupon coupon = new Coupon(id, name, price, category, date, description, latitude, longitude);
                 succeedToAdd = m_CouponHibernate.addCoupon(coupon);
                if (succeedToAdd) {
                    request.setAttribute("messageCoupon", "New Coupon was added");
                    adminLogin(request, response);
                    request.setAttribute("JunitTestIndexes", 5);

                }
                else {
                    //Something got Wrong With Database
                    request.setAttribute("JunitTestIndexes", 52);
                    errorMessages.add("Coupon already existent");
                }

            }
             if (!succeedToAdd || !fieldsCorrect) {
                 //Something Go Wrong it Return List Of errors To jsp And Forward This
                 request.setAttribute("errorMessage", errorMessages);
                 forwardPage(request, response, "AddNewCoupon.jsp");
             }
        }

    //------------------------------Forwarding------------------------------------------------------//

    /**
     * Method TO Forward From Servlet To Jsp
     * @param request
     * @param response
     * @param i_Name - Name Of Page To Forward With extension of page
     * @throws ServletException
     * @throws IOException
     * something goes wrong filling form status 62
     */
    private  void forwardPage(HttpServletRequest request, HttpServletResponse response, String i_Name) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/" + i_Name);
        dispatcher.forward(request, response);
    }

    //------------------------deleteCoupon.Jsp-----------------------------------------------//

    /**
     * Delete Coupon From DB
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    private void deleteCoupon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, NoSuchAlgorithmException {
        response.setContentType("text/html");
        int id = Integer.parseInt((String)request.getParameter("id"));
        boolean succeedToDelete = m_CouponHibernate.delete(id);
        if (succeedToDelete)
        {
            HttpSession session = request.getSession();
            //Delete Coupon In Cart Also If Somebody have This Coupon In His Cart
            List<Coupon> list = (List<Coupon>) session.getAttribute("ListCouponsInCart");
            if (list != null) {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() == id) {
                            list.remove(i);
                            break;
                        }
                    }

                    session.setAttribute("ListCouponsInCart", list);
                }
            }
            //For Junit Test Index
            request.setAttribute("JunitTestIndexes", 6);
            request.setAttribute("messageCoupon", "was deleted successful");
        }
        else
        {
            //Something got wrong With DataBase
            request.setAttribute("JunitTestIndexes", 62);
            request.setAttribute("messageCoupon", "There was a problem with deleting the coupon");
        }
        //return to AdminPage After Action
        adminLogin(request, response);
    }

    //-----------------------updateCoupon.JSP--------------------------------------------------//

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     * something goes wrong status 72 -fiiling form
     */
    private void updateCoupon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, NoSuchAlgorithmException {
        response.setContentType("text/html");
        List<String> errorMessages = new ArrayList<String>();
        boolean fieldsCorrect = true;
        int id;
        double price = 0;
        String name = request.getParameter("Name");
        String strPrice = request.getParameter("Price");
        String category = request.getParameter("Category");
        String date = request.getParameter("Date");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String description = request.getParameter("description");
        id = Integer.parseInt(request.getParameter("Id"));
        if (name.trim().equals(""))
        {
            errorMessages.add("Name cannot be empty");
            fieldsCorrect = false;
            //For Junit Test Index
            request.setAttribute("JunitTestIndexes", 72);
        }

        try {
            price = Double.parseDouble(strPrice);
            if (price < 0)
            {
                errorMessages.add("Price have to be more than 0");
                fieldsCorrect = false;
                //For Junit Test Index (Bad Index)
                request.setAttribute("JunitTestIndexes", 72);
            }
        }
        catch (NumberFormatException numberFormatException)
        {
            //For Junit Test Index
            request.setAttribute("JunitTestIndexes", 72);
            fieldsCorrect = false;
            errorMessages.add("Price field has to be double");
        }
        //Checks That All Fields In Form Correct
        if (fieldsCorrect) {
                boolean succeedToUpdate = m_CouponHibernate.update(id, name, price, category, date, description, latitude, longitude);
                if (succeedToUpdate) {
                    HttpSession session = request.getSession();
                    //Update Coupon Also In Cart

                    List<Coupon> list = (List<Coupon>) session.getAttribute("ListCouponsInCart");
                    if (list != null) {
                        if (list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getId() == id) {
                                    list.set(i, m_CouponHibernate.getCurrentCoupon(id));
                                    break;
                                }
                            }
                            session.setAttribute("ListCouponsInCart", list);
                        }
                    }
                    request.setAttribute("JunitTestIndexes",7);
                    request.setAttribute("messageCoupon", "Coupon was updated");
                } else {
                    request.setAttribute("JunitTestIndexes",72);
                    request.setAttribute("messageCoupon", "There was a problem with updating coupon");
                }
            //Return To AdminPage
                adminLogin(request, response);
        } else
        {
            //If There Was An error It Will Return To Form Page With Errors List
            Coupon coupon = HibernateCouponsDAO.getInstance().getCurrentCoupon(id);
            request.setAttribute("coupon", coupon);
            request.setAttribute("errorMessage", errorMessages);
            forwardPage(request, response, "UpdateCoupon.jsp");
        }
    }
    //--------------------adminLogin.Jsp,AdminPage.JSP,LogOut--------------------------------------------------------//

    /**
     * Check The Admin fields To Login
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, NoSuchAlgorithmException {
        List<String> errorMessages = new ArrayList<String>();
        boolean filedError = false;
        response.setContentType("text/html");
        boolean yourAreAdmin = false;
        HttpSession session = request.getSession();
        AdminLogin admin = null;
        String adminName = request.getParameter("AdminName");
        String sPassword = request.getParameter("Password");
        if (adminName != null && sPassword != null)
        {
            if (adminName.trim().equals(""))
            {
                errorMessages.add("Name cannot be empty");
                filedError = true;
            }
            if (sPassword.trim().equals(""))
            {
                errorMessages.add("Password cannot be empty");
                filedError = true;
            }
            //All Field In Login Form Is Right
            if (!filedError) {
                  admin = new AdminLogin();
                //Call To Encrypt Password by Md5
                   String md5Password = md5Hash(sPassword);
                   yourAreAdmin = m_CouponHibernate.checkAdminLogin(adminName, md5Password);
                if (yourAreAdmin) {
                    admin.setName(adminName);
                    admin.setPassword(md5Password);
                    session.setAttribute("Admin", admin);
                    //For Junit Test
                    request.setAttribute("LoginGood",1);
                }
                //Error And Return List Of error To Form Login
                else
                {
                    errorMessages.add("Wrong Password or Login Name");
                    request.setAttribute("errorMessage", errorMessages);
                    forwardPage(request, response, "AdminLogin.jsp");
                }
            }
            else
            {
                request.setAttribute("errorMessage", errorMessages);
                forwardPage(request, response, "AdminLogin.jsp");
            }
        }
        else
        {
            request.setAttribute("JunitTestIndexes", 4);
            //If you didn't Log Out From Session
            admin = (AdminLogin) session.getAttribute("Admin");
            yourAreAdmin = m_CouponHibernate.checkAdminLogin(admin.getName(), admin.getPassword());
        }
        //If your Admin And Still Didn't Log Out From Session
        if (yourAreAdmin)
        {
            List<Coupon> list = m_CouponHibernate.getAllCoupons();
            updateCouponsByDate(request, response);
            request.setAttribute("ListOfAllCouponsInDB", list);
            request.setAttribute("messageCoupon" , request.getAttribute("messageCoupon"));
            forwardPage(request, response, "AdminPage.jsp");
        }
    }


    /**
     * Log Out From Admin Page - remove Admin Login And Password from Session
     * @param request
     * @param response
     * @throws ServletException
     * @throws ParseException
     * @throws IOException
     */
    private void logOut(HttpServletRequest request,HttpServletResponse response) throws ServletException, ParseException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("Admin");
        loadHomePage(request, response);
    }

    //------------------------adminActions---------------------------------------------------------------//

    /**
     * Choose Action From AdminPage TO Update Coupon Or Delete Coupon
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    private void adminActions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, NoSuchAlgorithmException {
        String test = request.getParameter("Button");
        String id = request.getParameter("id");
        String delete = "Delete";
        String update = "Update";
        HttpSession session = request.getSession();
        if (update.equals(test))
        {
            Coupon coupon = HibernateCouponsDAO.getInstance().getCurrentCoupon(Integer.parseInt(id));
            request.setAttribute("coupon", coupon);
            forwardPage(request, response, "UpdateCoupon.jsp");
        }
        if (delete.equals(test))
        {
            request.setAttribute("id", id);
            forwardPage(request, response, "DeleteCoupon.jsp");
        }
        else
        {
            //If Press Button Empty It Forward Back To AdminPage
           adminLogin(request, response);
        }
    }

    //----------------------Cart--------------------------------------------------------//

    /**
     * Method To Add Cart To Cart, All Coupon Save In list,And List Saves In Session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String message= null;
        boolean contains = false;
        String id = request.getParameter("id");
        Coupon coupon = m_CouponHibernate.getCurrentCoupon(Integer.parseInt(id));
        HttpSession session = request.getSession();
        //Check If IT Exist In DataBase
        if (coupon != null) {
            List<Coupon> list = (List<Coupon>) session.getAttribute("ListCouponsInCart");
            if (list == null) {
                list = new ArrayList<Coupon>();
            }
            //Check That He is Not In Cart ALready
            for (Coupon currentCoupon : list) {
                if (currentCoupon.getId() == coupon.getId()) {
                    message = "This Coupon Already In Cart";
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                list.add(coupon);
                message = "Added To Your Cart";
            }
            request.setAttribute("messageCart", message);
            //Save List Of Coupon in Cart in Session
            session.setAttribute("ListCouponsInCart", list);
            updateCouponsByDate(request, response);
        }
        //forward Back To HomePage
        loadHomePage(request, response);
    }

    /**
     * Removing Coupon From Cart
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        List<Coupon> list = (List<Coupon>) session.getAttribute("ListCouponsInCart");
        Coupon coupon = m_CouponHibernate.getCurrentCoupon(Integer.parseInt(id));
        //Check Thats Coupon Still Exist in Db
            if (coupon != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == coupon.getId()) {
                    list.remove(i);
                    break;
                }
            }
        }
        else
        {
            //If IT Deleted In Db IT Delete From List
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).getId() == Integer.parseInt(id))
                {
                    list.remove(i);
                    break;
                }
            }
        }
        session.setAttribute("ListCouponsInCart", list);
        forwardPage(request, response, "MyCart.jsp");
    }

    //-----------------------------Check Methods And Encryption Method----------------------------------//
    /**
     * To check Date OF Coupon
     * @param i_CouponsToCheck - List OF Coupons To Check
     * @param i_CartCoupons - Boolean That Sais If IT Coupons From Cart Or DB
     * @throws ParseException
     */
    private void checkCouponsDate( List<Coupon> i_CouponsToCheck, boolean i_CartCoupons) throws ParseException {
        Date currentDate = new Date();
        for (int i = 0;i < i_CouponsToCheck.size(); i++)
        {
            String sdate = i_CouponsToCheck.get(i).getDate();
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date date = simpleDate.parse(sdate);
            //Check If Date Is Still Validate
            if (date.compareTo(currentDate) < 0)
            {
                if (i_CartCoupons) {
                    i_CouponsToCheck.remove(i_CouponsToCheck.get(i));
                }
                else
                {
                    m_CouponHibernate.delete(i_CouponsToCheck.get(i).getId());
                }
            }
        }
    }

    /**
     * Md5 Algorith To Encrypt
     * @param i_Password - Password that gets From Admin Login
     * @return
     */
    private String md5Hash(String i_Password)
    {
        String digest = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(i_Password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2*hash.length);
            for(byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
            }catch (UnsupportedEncodingException ex)
        {
               ex.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest;
    }

    /**
     * Method For Sort Coupon By Category In Cart
     * @param request
     * @param response
     * @throws ServletException
     * @throws ParseException
     * @throws IOException
     */
    private void sortCartByCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, ParseException, IOException
    {
        String category = request.getParameter("Category");
        HttpSession session = request.getSession();
        List<Coupon> allItemInCart = (List<Coupon>) session.getAttribute("ListCouponsInCart");
        List<Coupon> sortedItemInCategoryCart = new ArrayList<Coupon>();
        if(!category.equals("All Coupons")) {
            for (Coupon coupon : allItemInCart) {
                if (coupon.getCategory().equals(category)) {
                    sortedItemInCategoryCart.add(coupon);
                }
            }
            session.setAttribute("SortedCouponsInCart", sortedItemInCategoryCart);
        }
        forwardPage(request, response, "MyCart.jsp");
    }

    /**
     * Check If The Coupon Is In Area
     * @param request
     * @param response
     * @return
     * @throws ServletException
     */
    private boolean checkIfThereIsCoordinates(HttpServletRequest request,HttpServletResponse response) throws ServletException
    {
        boolean realCoordinates = true;
        try {
            List<Coupon> coupons = new ArrayList<Coupon>();
            List<Coupon> couponsByArea = new ArrayList<Coupon>();

            double latitude, longitude;
            String strLongitude;
            String strLatitude;
            String path = request.getQueryString();
            if (path != null ) {
                strLatitude = path.substring(0, path.indexOf(","));
                strLongitude = path.substring(path.indexOf(",") + 1, path.length());

                latitude = Double.parseDouble(strLatitude);
                longitude = Double.parseDouble(strLongitude);

                if (realCoordinates) {
                    coupons = m_CouponHibernate.getAllCoupons();
                    for (Coupon coupon : coupons) {
                        if (coupon.getLatitude() != null && coupon.getLongitude() != null) {
                            if (CoordinateGeoMath.coordinateMath(latitude, longitude, Double.parseDouble(coupon.getLatitude()), Double.parseDouble(coupon.getLongitude())) < 10) {
                                couponsByArea.add(coupon);
                            }
                        }
                    }
                    request.setAttribute("ListOfAllCouponsInDB", couponsByArea);
                }
            }
            else
            {
                realCoordinates = false;
            }
        }
            catch (NumberFormatException e)
            {
                realCoordinates = false;
            }
        catch (StringIndexOutOfBoundsException e)
        {
            realCoordinates = false;
        }

            finally {
                return  realCoordinates;
            }
        }

    /**
     * Check date in Coupons that in Db and Coupons that in Cart
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    private void updateCouponsByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        checkCouponsDate(m_CouponHibernate.getAllCoupons(), false);
        HttpSession session = request.getSession();
        List<Coupon> cartList = (List<Coupon>)session.getAttribute("ListCouponsInCart");
        if(cartList!=null) {
            checkCouponsDate(cartList, true);
        }
    }




}
