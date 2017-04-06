import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class StockXBotMain {

    public static void main(String[] args) {
        List<AddressProfile> addressProfiles = new ArrayList<>();
        List<PaymentProfile> paymentProfiles = new ArrayList<>();
        ArrayList<AccountCredentials> accountCredentialsesList = new ArrayList<>();
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
      //  WebDriver driver = new FirefoxDriver();
         System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
          WebDriver driver = new ChromeDriver();
        Utils utils = null;
        try {
            utils = new Utils();
        } catch (AWTException e) {
            e.printStackTrace();
        }


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
//        utils.verifyByEmail(driver);
        //  driver.get("https://mail.google.com/mail/u/0/#inbox");
        Scanner scanner = new Scanner(System.in);
        Kryo kryo = new Kryo();
        File addressesFile = new File("src/main/resources/addresses.bin");
        File paymentFile = new File("src/main/resources/paymentProfiles.bin");
        if (addressesFile.exists() && !addressesFile.isDirectory()) {

            try {
                Input input1 = new Input(new FileInputStream(addressesFile));
                addressProfiles = kryo.readObject(input1, ArrayList.class);
                System.out.println("You currently have " + addressProfiles.size() + " adress profiles");
                input1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (paymentFile.exists() && !paymentFile.isDirectory()) {

            try {
                Input input2 = new Input(new FileInputStream(paymentFile));
                paymentProfiles = kryo.readObject(input2, ArrayList.class);
                System.out.println("You currently have " + paymentProfiles.size() + " payment profiles");
                input2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        if (!addressesFile.exists()) {
            utils.createAddressProfiles(scanner, kryo, addressesFile);
        }
        if (!paymentFile.exists()) {
            utils.createPaymentProfiles(scanner, kryo, paymentFile);
        }
//        System.out.println("Enter the gmail login");
//        String gmailLogin = scanner.nextLine();
//        System.out.println("Enter gmail password");
//        String gmailPassword = scanner.nextLine();

        //driver.get("https://mail.google.com");
        System.out.println("Enter a number of accounts you want to create");
        String numberOfAccountsString = scanner.nextLine();
        int numberOfAccountsInt = Integer.valueOf(numberOfAccountsString);
        for (int i = 1; i <= numberOfAccountsInt; i++) {
            //  WebElement element = driver.findElement(By.xpath("//div[contains(text(),'Verify Your Email to Buy & Sell on StockX')]"));

            ArrayList<String> randomNameAndSurname = utils.getRandomNameAndSurname(driver);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.get("https://stockx.com/");
            WebElement signUpButton = driver.findElement(By.className("blue"));
            signUpButton.click();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AccountCredentials credentials = utils.createAccount(driver, randomNameAndSurname);
            accountCredentialsesList.add(credentials);
            System.out.println(credentials);
            try {
                sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // String ok = scanner.nextLine();
           // utils.verifyByGmail(driver, gmailLogin, gmailPassword);
            driver.close();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            utils.setPaymentAndAddressProfiles(driver, credentials, addressProfiles, paymentProfiles);
            AndroidDriver appiumDriver = null;
            try {
                appiumDriver = utils.setupAppium();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            utils.bidUsingAppium(appiumDriver, credentials);
            try {
                sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

