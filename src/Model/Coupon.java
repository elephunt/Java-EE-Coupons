package Model;


public class Coupon {

    private int m_Id;
    private String m_Name;
    private double m_Price;
    private String m_Date;
    private String m_Category;
    private String m_Description;
    private String m_Latitude;
    private String m_Longitude;

    /**
     * coupon constructor
     * @param id
     * @param name
     * @param price
     * @param i_Category
     * @param date
     * @param i_Description
     * @param i_Latitude
     * @param i_Longitude
     */
    public Coupon(int id, String name, double price, String i_Category, String date,String i_Description,String i_Latitude,String i_Longitude)
    {
        setId(id);
        setName(name);
        setPrice(price);
        setDate(date);
        setCategory(i_Category);
        setDescription(i_Description);
        setLatitude(i_Latitude);
        setLongitude(i_Longitude);
    }

    /**
     * Default constructor
     */
    public Coupon()
    {
    	
    }

    /**
     * Sets the id of coupon
     * @param i_Id
     */
    public void setId(int i_Id) {
		this.m_Id = i_Id;
	}

    /**
     * Sets the name of coupon
     * @param i_Name
     */
	public void setName(String i_Name) {
		this.m_Name = i_Name;
	}

    /**
     * Sets the price of coupon
     * @param i_Price
     */
	public void setPrice(double i_Price) {
		this.m_Price = i_Price;
	}

    /**
     * Sets the date of coupon
     * @param i_Date
     */
    public void setDate(String i_Date) {
        this.m_Date = i_Date;
    }

    /**
     * Gets the if of coupon
     * @return
     */
	public int getId()
    {
        return m_Id;
    }

    /**
     * Gets the name of coupon
     * @return
     */
    public String getName()
    {
        return m_Name;
    }

    /**
     * Gets the price of coupon
     * @return
     */
    public double getPrice()
    {
        return m_Price;
    }

    /**
     * Gets the date of coupon
     * @return
     */
    public String getDate() {
        return m_Date;
    }

    /**
     * Overrides toString methond
     * @return
     */
	@Override
	public String toString() {
		return "Coupon [id=" + m_Id + ", name=" + m_Name + ", price="
				+ m_Price + "date="+ m_Date + "Location"  + m_Description +"]";
	}

    /**
     * Gets the category of coupon
     * @return
     */
    public String getCategory() {
        return m_Category;
    }

    /**
     * Sets the category of coupon
     * @param m_Category
     */
    public void setCategory(String m_Category) {
        this.m_Category = m_Category;
    }

    /**
     * Gets the description of coupon
     * @return
     */
    public String getDescription() {
        return m_Description;
    }

    /**
     * Sets the description of coupon
     * @param m_Description
     */
    public void setDescription(String m_Description) {
        this.m_Description = m_Description;
    }

    /**
     * Gets the Latitude
     * @return
     */
    public String getLatitude() {
        return m_Latitude;
    }

    /**
     * Sets the Latitude
     * @param m_Latitude
     */
    public void setLatitude(String m_Latitude) {
        this.m_Latitude = m_Latitude;
    }

    /**
     * Gets Longitude
     * @return
     */
    public String getLongitude() {
        return m_Longitude;
    }

    /**
     * Sets Longitude
     * @param m_Longitude
     */
    public void setLongitude(String m_Longitude) {
        this.m_Longitude = m_Longitude;
    }


}
