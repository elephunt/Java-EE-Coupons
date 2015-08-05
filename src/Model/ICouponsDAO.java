package Model;


public interface ICouponsDAO
{
    /**
     * Adds new coupon
     * @param coupon
     * @return
     */
    public boolean addCoupon(Coupon coupon);

    /**
     * Updates coupon
     * @param id
     * @param name
     * @param price
     * @param category
     * @param date
     * @param description
     * @param latitude
     * @param longitude
     * @return
     */
    public boolean update(int id, String name, double price, String category, String date,String description,String latitude,String longitude);

    /**
     * Deletes coupon
     * @param id
     * @return
     */
    public boolean delete(int id);
}
