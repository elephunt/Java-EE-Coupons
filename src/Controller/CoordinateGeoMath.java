package Controller;

/**
 * Created by RomaMarg on 13.01.2015.
 */

/**
 * Class To Calculate The Area Distance
 */
public class CoordinateGeoMath {
    /**
     *
     * @param lat1 - Latitutude Of user
     * @param lon1 - Longtitude Of User
     * @param lat2 - Latitude Of Coupon
     * @param lon2 - Longtitude Of Coupon
     * @return - Kilometers Of Area Around
     */
    public static double coordinateMath(double lat1, double lon1, double lat2, double lon2)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private static   double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }
    private static   double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

}
