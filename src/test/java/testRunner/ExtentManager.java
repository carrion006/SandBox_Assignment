package testRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;

    public static ExtentReports getExtentReports(){

        if (extentReports == null){
            extentReports = new ExtentReports();
            ExtentSparkReporter extentSparkReporter =new ExtentSparkReporter("Reports/ExtentReport.html");
            extentReports.attachReporter(extentSparkReporter);
        }

        return extentReports;
    }

}
