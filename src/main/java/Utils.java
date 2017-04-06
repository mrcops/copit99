import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tools.ant.taskdefs.Touch;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;
import static java.sql.DriverManager.getDriver;

public class Utils {
    Random random = new Random();
    ArrayList<String> shoeSizes = new ArrayList<>();
    Robot robot1 = new Robot();


    private String generateString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public AndroidDriver<WebElement> setupAppium() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "6.0");
        capabilities.setCapability("deviceName", "Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.stockx.stockx");
        capabilities.setCapability("appActivity", "com.stockx.stockx.ui.activity.MainActivity");
        return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public void bidUsingAppium(AndroidDriver driver, AccountCredentials credentials)

    {
        boolean firstTime = true;
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement firstButton = driver.findElement(By.className("android.widget.ImageButton"));
        firstButton.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement login = driver.findElement(By.className("android.widget.TextView"));
        login.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement email = driver.findElement(By.id("com.stockx.stockx:id/login_email_edit_text"));
        WebElement password = driver.findElement(By.id("com.stockx.stockx:id/login_password"));
//        email.sendKeys("smoligorchik@gmail.com");
//        password.sendKeys("s9458905");
        email.sendKeys(credentials.getLogin());
        password.sendKeys(credentials.getPassword());
        WebElement submitLogin = driver.findElement(By.id("com.stockx.stockx:id/login_submit"));
        submitLogin.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searchButton = driver.findElement(By.id("com.stockx.stockx:id/action_search"));
        searchButton.click();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searchBar = driver.findElement(By.id("android:id/search_src_text"));
        //  searchBar.click();
        searchBar.sendKeys("sneaker madness Adidas yeezy");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> searchResults = driver.findElements(By.id("com.stockx.stockx:id/search_result_text"));

        System.out.println(searchResults.size());
        for (WebElement searchResult : searchResults) {
            searchResult.click();
            try {
                sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement bidButton = driver.findElement(By.id("com.stockx.stockx:id/lowest_ask_buy_button"));
            WebElement retailPrice = driver.findElement(By.id("com.stockx.stockx:id/retail_text"));
            String retailPriceString = retailPrice.getText().replace("$", "");
            //   System.out.println(retailPriceString);
            //int retailPriceInt = Integer.parseInt(retailPrice.getText());
            bidButton.click();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // WebElement someText = driver.findElement(By.id("com.stockx.stockx:id/item_pager_main_text"));
            if (firstTime == true) {
                swipe(driver);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebElement element = driver.findElement(By.id("com.stockx.stockx:id/tutorial_button"));
                element.click();
                firstTime = false;
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            WebElement bidPriceForm = driver.findElement(By.className("android.widget.EditText"));
            bidPriceForm.sendKeys("200");

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement placeBidButton = driver.findElement(By.id("com.stockx.stockx:id/form_next_button"));
            placeBidButton.click();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement confirmBidButton = driver.findElement(By.id("com.stockx.stockx:id/confirmation_next_button"));
            confirmBidButton.click();
            try {
                sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            WebElement backButton = driver.findElement(By.className("android.widget.ImageButton"));
//            backButton.click();
            WebElement crossButton = driver.findElement(By.className("android.widget.ImageButton"));
            crossButton.click();
            WebElement backButton2 = driver.findElement(By.className("android.widget.ImageButton"));
            backButton2.click();


        }
        WebElement backButton3 = driver.findElement(By.id("com.stockx.stockx:id/actionbar_icon_image"));
        backButton3.click();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement crossButton = driver.findElement(By.id("android:id/search_close_btn"));
        crossButton.click();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement crossButton2 = driver.findElement(By.id("android:id/search_close_btn"));
        crossButton2.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement iconImageButton = driver.findElement(By.id("com.stockx.stockx:id/actionbar_icon_image"));
        iconImageButton.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement logoutButton = driver.findElement(By.id("com.stockx.stockx:id/action_logout"));
        logoutButton.click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement logoutConfirm = driver.findElement(By.id("android:id/button1"));
        logoutConfirm.click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void verifyByGmail(WebDriver driver, String gmailLogin, String gmailPassword) {
        driver.get("https://mail.google.com");
        WebElement gmailLoginForm = driver.findElement(By.cssSelector("#Email"));
        gmailLoginForm.sendKeys(gmailLogin);
        gmailLoginForm.sendKeys(Keys.ENTER);
        WebElement gmailPasswordForm = driver.findElement(By.cssSelector("#Passwd"));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gmailPasswordForm.sendKeys(gmailPassword);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gmailPasswordForm.sendKeys(Keys.ENTER);
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String emailLink;
        List<WebElement> emails = driver.findElements(By.className("bog"));


        for (WebElement el : emails) {
            if (el.getText().contains("Verify Your Email to Buy & Sell on StockX")) {
                el.click();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emailLink = driver.getCurrentUrl();
                System.out.println(emailLink);
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                WebElement confirmationLink = driver.findElement(By.xpath("//img[@alt=\"Click Here to Verify Your Email\"]"));
//                WebElement body = driver.findElement(By.("body"));
//                System.out.println(confirmationLink.getText());
//                confirmationLink.click();

                for (int i = 1; i <= 13; i++) {
                    robot1.keyPress(KeyEvent.VK_TAB);
                    robot1.keyRelease(KeyEvent.VK_TAB);
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                robot1.keyPress(KeyEvent.VK_ENTER);
                robot1.keyRelease(KeyEvent.VK_ENTER);


                //  confirmationLink.click();
//                ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
//                driver.switchTo().window(tabs2.get(1));
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                List <WebElement> elements = driver.findElements(By.cssSelector("*"));
//                for (WebElement element : elements)
//                {
//                    System.out.println(element.getText());
//                }
                //   WebElement loginButton2 = driver.findElement(By.className("blue"));

                robot1.keyPress(KeyEvent.VK_CONTROL);
                robot1.keyPress(KeyEvent.VK_W);
                robot1.keyRelease(KeyEvent.VK_CONTROL);
                robot1.keyRelease(KeyEvent.VK_W);
                //  driver.switchTo().window(tabs2.get(0));
                driver.navigate().refresh();
                try {
                    sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebElement searchBar = driver.findElement(By.cssSelector("#gbqfq"));
                searchBar.sendKeys("/");
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 1; i <= 23; i++) {
                    robot1.keyPress(KeyEvent.VK_TAB);
                    robot1.keyRelease(KeyEvent.VK_TAB);
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                robot1.keyPress(KeyEvent.VK_ENTER);
                robot1.keyRelease(KeyEvent.VK_ENTER);
                //  Actions actions = new Actions(driver);
                // actions.moveToElement(driver.findElement(By.className("asa"))).click().build().perform();
                System.out.println("one cycle");

            }
        }
    }

    public void createAddressProfiles(Scanner scanner, Kryo kryo, File addressesFile) {
        List<AddressProfile> addressProfileList = new ArrayList<>();
        if (!addressesFile.exists() && !addressesFile.isDirectory()) {

            try {
                addressesFile.createNewFile();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }


        System.out.println("Enter how many address profiles do you want to create");
        String numberOfProfiles = scanner.nextLine();
        int numberOfProfilesInt = Integer.valueOf(numberOfProfiles);
        for (int i = 1; i <= numberOfProfilesInt; i++) {
            System.out.println("You are creating a new address profile");
            String firstName;
            String lastName;
            String country;
            String streetAddress;
            String aptSuitUnit;
            String city;
            String stateAndRegion;
            String zipPostalCode;
            String telephone;
//Syahrul Nizam
            firstName = "Syahrul";

            lastName = "Nizam";
            System.out.println("Enter a  country");
            country = scanner.nextLine();
            System.out.println("Enter the StreetAddress");
            streetAddress = scanner.nextLine();
            System.out.println("Enter a aptSuitUnit");
            aptSuitUnit = scanner.nextLine();
            System.out.println("Enter a city");
            city = scanner.nextLine();
            System.out.println("Enter a stateAndRegion");
            stateAndRegion = scanner.nextLine();
            System.out.println("Enter a Postal Code");
            zipPostalCode = scanner.nextLine();
            System.out.println("Enter a telephone");
            telephone = scanner.nextLine();
            AddressProfile profile = new AddressProfile();
            profile.setFirstName(firstName);
            profile.setLastName(lastName);
            profile.setCountry(country);
            profile.setStreetAddress(streetAddress);
            profile.setAptSuitUnit(aptSuitUnit);
            profile.setCity(city);
            profile.setStateAndRegion(stateAndRegion);
            profile.setTelephone(telephone);
            profile.setZipPostalCode(zipPostalCode);

            profile.setCountry(country);
            addressProfileList.add(profile);

            try {
                Output output = new Output(new FileOutputStream(addressesFile));
                kryo.writeObject(output, addressProfileList);
                output.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

//    public void verifyByEmail(WebDriver driver) {
//        driver.get("https://www.zoho.eu/mail/login.html");
////        WebElement loginButton = driver.findElement(By.cssSelector(".header > div:nth-child(2) > a:nth-child(2)"));
////        loginButton.click();
//        try {
//            sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//
//
//        // Simulate a key press
//        robot.keyPress(KeyEvent.VK_SHIFT);
//        robot.keyPress(KeyEvent.VK_S);
//        robot.keyRelease(KeyEvent.VK_SHIFT);
//        robot.keyRelease(KeyEvent.VK_S);
//
//
//        //  Syahrul@sightmail.org
////        Password : renegade21
//        robot.keyPress(KeyEvent.VK_Y);
//        robot.keyRelease(KeyEvent.VK_Y);
//        robot.keyPress(KeyEvent.VK_A);
//        robot.keyRelease(KeyEvent.VK_A);
//        robot.keyPress(KeyEvent.VK_H);
//        robot.keyRelease(KeyEvent.VK_H);
//        robot.keyPress(KeyEvent.VK_R);
//        robot.keyRelease(KeyEvent.VK_R);
//        robot.keyPress(KeyEvent.VK_U);
//        robot.keyRelease(KeyEvent.VK_U);
//        robot.keyPress(KeyEvent.VK_L);
//        robot.keyRelease(KeyEvent.VK_L);
//        robot.keyPress(KeyEvent.VK_SHIFT);
//        robot.keyPress(KeyEvent.VK_2);
//        robot.keyRelease(KeyEvent.VK_2);
//        robot.keyRelease(KeyEvent.VK_SHIFT);
//        robot.keyPress(KeyEvent.VK_S);
//        robot.keyRelease(KeyEvent.VK_S);
//        robot.keyPress(KeyEvent.VK_I);
//        robot.keyRelease(KeyEvent.VK_I);
//        robot.keyPress(KeyEvent.VK_G);
//        robot.keyRelease(KeyEvent.VK_G);
//        robot.keyPress(KeyEvent.VK_H);
//        robot.keyRelease(KeyEvent.VK_H);
//        robot.keyPress(KeyEvent.VK_T);
//        robot.keyRelease(KeyEvent.VK_T);
//        robot.keyPress(KeyEvent.VK_M);
//        robot.keyRelease(KeyEvent.VK_M);
//        robot.keyPress(KeyEvent.VK_A);
//        robot.keyRelease(KeyEvent.VK_A);
//        robot.keyPress(KeyEvent.VK_I);
//        robot.keyRelease(KeyEvent.VK_I);
//        robot.keyPress(KeyEvent.VK_L);
//        robot.keyRelease(KeyEvent.VK_L);
//        robot.keyPress(KeyEvent.VK_PERIOD);
//        robot.keyRelease(KeyEvent.VK_PERIOD);
//        robot.keyPress(KeyEvent.VK_O);
//        robot.keyRelease(KeyEvent.VK_O);
//        robot.keyPress(KeyEvent.VK_R);
//        robot.keyRelease(KeyEvent.VK_R);
//        robot.keyPress(KeyEvent.VK_G);
//        robot.keyRelease(KeyEvent.VK_G);
//
//        robot.keyPress(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_TAB);
//
////        Password : renegade21
//        robot.keyPress(KeyEvent.VK_R);
//        robot.keyRelease(KeyEvent.VK_R);
//        robot.keyPress(KeyEvent.VK_E);
//        robot.keyRelease(KeyEvent.VK_E);
//        robot.keyPress(KeyEvent.VK_N);
//        robot.keyRelease(KeyEvent.VK_N);
//        robot.keyPress(KeyEvent.VK_E);
//        robot.keyRelease(KeyEvent.VK_E);
//        robot.keyPress(KeyEvent.VK_G);
//        robot.keyRelease(KeyEvent.VK_G);
//        robot.keyPress(KeyEvent.VK_A);
//        robot.keyRelease(KeyEvent.VK_A);
//        robot.keyPress(KeyEvent.VK_D);
//        robot.keyRelease(KeyEvent.VK_D);
//        robot.keyPress(KeyEvent.VK_E);
//        robot.keyRelease(KeyEvent.VK_E);
//        robot.keyPress(KeyEvent.VK_2);
//        robot.keyRelease(KeyEvent.VK_2);
//        robot.keyPress(KeyEvent.VK_1);
//        robot.keyRelease(KeyEvent.VK_1);
//        try {
//            sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
////        List<WebElement> elements = driver.findElements(By.cssSelector("*"));
////        for(WebElement element:elements)
////        {
////            if (element.getText()!=null){
////            System.out.println(element.getText());}
////        }
//        // WebElement unread = driver.findElement(By.xpath("//*[@id=\"zm_unread\"]"));
//        // unread.click();
////List<WebElement> elements = driver.findElements(By.className("zmLst"));
////        System.out.println(elements.size());
//        //   WebElement letter = driver.findElement(By.xpath("//*[contains(text(), 'Verify Your Email to Buy & Sell on StockX')]"));
////        letter.sendKeys(Keys.ENTER);
//        //   Actions actions = new Actions(driver);
//        //  actions.moveToElement(letter).click().build().perform();
//        driver.findElement(By.cssSelector("#2981828000000011007 > div.zmLst > div.zmDtl > div.zmSub > span")).click();
//        driver.findElement(By.id("2981828000000011007_imgsrc_url_1")).click();
//    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void randomSizeSelect(WebDriver driver) {
        int randomIndex = random.nextInt(shoeSizes.size());
        String randomSize = shoeSizes.get(randomIndex);
        Select shoeSize = new Select(driver.findElement(By.cssSelector("#signup\\5b defaultSize\\5d")));
        ArrayList<WebElement> options = (ArrayList<WebElement>) shoeSize.getOptions();
        ArrayList<String> optionsValues = new ArrayList<>();
        for (WebElement option : options) {
            optionsValues.add(option.getText());
            //  System.out.println(option.getText());
        }
        shoeSize.selectByValue(randomSize);
    }

    public ArrayList<String> getRandomNameAndSurname(WebDriver driver)

    {
        ArrayList<String> nameAndSurname = new ArrayList<>();
        //go to random names generator
        driver.get("http://www.behindthename.com/random/random.php?number=2&gender=m&surname=&all=no&usage_dan=1&usage_dut=1&usage_eng=1");
        String randomName = driver.findElement(By.cssSelector("body > div.body-wrapper > div > center > p > span > a:nth-child(1)")).getText();
        String randomSurname = driver.findElement(By.cssSelector("body > div.body-wrapper > div > center > p > span > a:nth-child(2)")).getText();
        nameAndSurname.add(randomName);
        nameAndSurname.add(randomSurname);
        return nameAndSurname;

    }

    public AccountCredentials createAccount(WebDriver driver, List<String> nameAndSurname) {
        AccountCredentials accountCredentials = new AccountCredentials();
        WebElement firstNameForm = driver.findElement(By.cssSelector("#signup\\5b firstname\\5d"));
        WebElement lastNameForm = driver.findElement(By.cssSelector("#signup\\5b lastname\\5d"));
        WebElement userNameForm = driver.findElement(By.cssSelector("#signup\\5b username\\5d"));
        WebElement emailForm = driver.findElement(By.cssSelector("#signup\\5b email\\5d"));
        WebElement passwordForm = driver.findElement(By.cssSelector("#signup\\5b password\\5d"));
        WebElement submitButton = driver.findElement(By.cssSelector("#form-register > div.buttons-set > button > span.ladda-label"));

        String firstName = nameAndSurname.get(0);
        String lastName = nameAndSurname.get(1);
        String userName = firstName + getRandomNumberInRange(100, 999) + lastName;
        String email = firstName + lastName + getRandomNumberInRange(100, 999) + "@sightmail.org";
        String password = generateString(getRandomNumberInRange(10, 14));
        firstNameForm.sendKeys(firstName);
        lastNameForm.sendKeys(lastName);
        userNameForm.sendKeys(userName);
        emailForm.sendKeys(email);
        passwordForm.sendKeys(password);
        randomSizeSelect(driver);

        WebElement termsAndConditionsCheckBox = driver.findElement(By.id("tandcconfirm"));
        termsAndConditionsCheckBox.click();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        submitButton.click();

        accountCredentials.setLogin(email);
        accountCredentials.setPassword(password);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return accountCredentials;


    }

    public void createPaymentProfiles(Scanner scanner, Kryo kryo, File paymentFile) {

        List<PaymentProfile> paymentProfileList = new ArrayList<>();
        String creditCardNumber;
        String yearAndMonth;
        String securityCode;
        if (!paymentFile.exists() && !paymentFile.isDirectory()) {

            try {
                paymentFile.createNewFile();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        System.out.println("Enter how many payment profiles do you want to create");
        String numberOfProfiles = scanner.nextLine();
        int numberOfProfilesInt = Integer.valueOf(numberOfProfiles);
        for (int i = 1; i <= numberOfProfilesInt; i++) {
            System.out.println("You are creating new payment profile");
            PaymentProfile paymentProfile = new PaymentProfile();
            System.out.println("Enter a  creditCardNumber");
            creditCardNumber = scanner.nextLine();
            System.out.println("Enter a year and month;");
            yearAndMonth = scanner.nextLine();

            System.out.println("Enter a securityCode");
            securityCode = scanner.nextLine();
            paymentProfile.setCreditCardNumber(creditCardNumber);
            paymentProfile.setYearAndMonth(yearAndMonth);
            paymentProfile.setSecurityCode(securityCode);
            paymentProfileList.add(paymentProfile);
            try {
                Output output = new Output(new FileOutputStream(paymentFile));
                kryo.writeObject(output, paymentProfileList);
                output.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void pressTab() {
        robot1.keyPress(KeyEvent.VK_TAB);
        robot1.keyRelease(KeyEvent.VK_TAB);
    }

    public void setPaymentAndAddressProfiles(WebDriver driver, AccountCredentials credentials, List<AddressProfile> addressProfiles, List<PaymentProfile> paymentProfiles) {
        Random random = new Random();
        AddressProfile addressProfile = addressProfiles.get(random.nextInt(addressProfiles.size()));
        PaymentProfile paymentProfile = paymentProfiles.get(random.nextInt(paymentProfiles.size()));
        driver.get("https://stockx.com/login");
        WebElement loginForm = driver.findElement(By.cssSelector("#login\\5b username\\5d"));
        WebElement passwordForm = driver.findElement(By.cssSelector("#login\\5b password\\5d"));
        loginForm.sendKeys(credentials.getLogin());
        passwordForm.sendKeys(credentials.getPassword());
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
        loginButton.click();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://stockx.com/settings");
        WebElement buyingInfo = driver.findElement(By.cssSelector(".billing-info > h2:nth-child(1) > a:nth-child(2)"));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buyingInfo.click();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 3; i++) {
            pressTab();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        WebElement creditCardNumber = driver.switchTo().activeElement();
        creditCardNumber.sendKeys(paymentProfile.getCreditCardNumber());
        pressTab();
        WebElement expirationDate = driver.switchTo().activeElement();
        expirationDate.sendKeys(paymentProfile.getYearAndMonth());
        pressTab();
        WebElement cvv = driver.switchTo().activeElement();
        cvv.sendKeys(paymentProfile.getSecurityCode());
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        driver.findElement(By.name("credit-card-number")).clear();
//        driver.findElement(By.id("credit-card-number")).sendKeys("1123123123123124");
//        WebElement expirationDate = driver.findElement(By.cssSelector("#expiration"));
//        WebElement cvv = driver.findElement(By.cssSelector("#cvv"));
//       // cardNumber.sendKeys(paymentProfile.getCreditCardNumber());
//        expirationDate.sendKeys(paymentProfile.getCreditCardNumber());
//        cvv.sendKeys(paymentProfile.getSecurityCode());
//
        Select country = new Select(driver.findElement(By.cssSelector("#CreditCard-BillingAddress-countryCodeAlpha2")));


        WebElement aptSuit = driver.findElement(By.cssSelector("#CreditCard-BillingAddress-extendedAddress"));

        WebElement city = driver.findElement(By.cssSelector("#CreditCard-BillingAddress-locality"));
        WebElement postalCode = driver.findElement(By.cssSelector("#CreditCard-BillingAddress-postalCode"));
        WebElement telephone = driver.findElement(By.cssSelector("#CreditCard-BillingAddress-telephone"));
        country.selectByVisibleText(addressProfile.getCountry());


        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement streetAddress = driver.findElement(By.cssSelector("#CreditCard-BillingAddress-streetAddress"));
        streetAddress.sendKeys("");
        aptSuit.sendKeys(addressProfile.getAptSuitUnit());
        city.sendKeys(addressProfile.getCity());
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!addressProfile.getCity().equals("Singapore")) {
            Select stateAndRegion = new Select(driver.findElement(By.cssSelector("#CreditCard-BillingAddress-region")));
            stateAndRegion.selectByVisibleText(addressProfile.getStateAndRegion());
        }
        if(addressProfile.getCity().equals("Singapore"))
        {
            WebElement stateAndregionForm = driver.findElement(By.cssSelector("#CreditCard-BillingAddress-region"));
            stateAndregionForm.sendKeys(addressProfile.getStateAndRegion());
        }
        postalCode.sendKeys(addressProfile.getZipPostalCode());
        telephone.sendKeys(addressProfile.getTelephone());
        // pressTab();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement streetAddress2 = driver.findElement(By.cssSelector("#CreditCard-BillingAddress-streetAddress"));
        streetAddress2.sendKeys(addressProfile.getStreetAddress());
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement shipToMyBillingAddressButton = driver.findElement(By.cssSelector("#differentShipping"));
        // shipToMyBillingAddressButton.click();
        WebElement submitButton = driver.findElement(By.cssSelector("#update-billing-form > div.submit-container > button"));
        submitButton.click();
        try {
            sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();
    }

    public void swipe(AndroidDriver driver) {
        for (int i = 1; i <= 4; i++)
            driver.swipe(937, 942, 106, 853, 1000);
    }

    public Utils() throws AWTException {
        shoeSizes.add("9");
        shoeSizes.add("9.5");
        shoeSizes.add("10");
        shoeSizes.add("10.5");
        shoeSizes.add("11");
    }
}
