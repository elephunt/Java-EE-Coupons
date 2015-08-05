package Model;

/**
 * Created by RomaMarg on 02.01.2015.
 */
public class AdminLogin {
    /**
     * Login Of Admin
     */
    private String Name =  null;
    /**
     * Password Of Admin
     */
    private String Password = null;

    /**
     * Gets the name of admin
     * @return
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name of admin
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Gets the admin password
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     * Sets the admin's password
     * @param password
     */
    public void setPassword(String password) {
        Password = password;
    }
}
