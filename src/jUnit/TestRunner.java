package jUnit;

import Controller.MainController;
import com.sun.net.httpserver.Authenticator;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by RomaMarg on 15.01.2015.
 */
public class TestRunner {

    public static void main(String []args)
    {
        Result result = JUnitCore.runClasses(HibernateTestJUnit.class);
          for (Failure fail:result.getFailures())
          {
              System.out.print(fail + "\n");
          }

        System.out.print(result.wasSuccessful() + "\n");
        Result servletResult = JUnitCore.runClasses(ServletTestUnit.class);
        for (Failure servletFail:servletResult.getFailures())
        {
            System.out.print(servletFail + "\n");
        }
        System.out.print(servletResult.wasSuccessful() + "\n");
    }
}
