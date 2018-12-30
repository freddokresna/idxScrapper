/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idxscrapper;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author freddo
 */
public class IdxScrapper {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        System.err.println(System.getProperty("user.dir"));
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            // declaration and instantiation of objects/variables
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\lib\\geckodriver-v0.23.0-win64\\geckodriver.exe");

        } else if (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0) {

            // declaration and instantiation of objects/variables
            System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "\\lib\\geckodriver-v0.23.0-linux64\\geckodriver.exe");
        } else {
            // declaration and instantiation of objects/variables
            throw new Exception("OS tidak di kenali");
        }
        WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
        //System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.idx.co.id/data-pasar/ringkasan-perdagangan/ringkasan-saham/";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        new WebDriverWait(driver, 6000).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Thread.sleep(100);
        clickInput(driver);

        WebElement dateBox = driver.findElement(By.xpath("//*[@id=\"dateFilter\"]"));
        WebElement yearInput = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/input"));
        WebElement monthInput = driver.findElement(By.xpath("/html/body/div/div[1]/div/span"));
        WebElement leftMonthSelect = driver.findElement(By.xpath("/html/body/div/div[1]/span[1]"));
        WebElement cariButton = driver.findElement(By.xpath("/html/body/main/div[1]/div[5]/button"));
        WebElement info = driver.findElement(By.xpath("//*[@id=\"stockTable_info\"]"));
        dateBox.click();
        yearInput.clear();

        yearInput.sendKeys(
                "2016");
        leftMonthSelect.click();

        dateBox.click();
        Thread.sleep(1000);

        WebElement dataWidget = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div"));
        List<WebElement> columns = dataWidget.findElements(By.className("flatpickr-day"));
        System.err.println("==================================================");
        System.err.println(columns.size());
        int i = 1;
        for (WebElement cell : columns) {
            //Select xx. Date
            System.err.println(cell.getText() + "tagname" + cell.getTagName() + "xpath" + cell.getAttribute("xpath") + "gettext" + cell.getAttribute("innerHTML") + cell.getAttribute("class"));
            if (cell.getAttribute("class").contains("prevMonthDay") || cell.getAttribute("class").contains("nextMonthDay")) {

            } else if (cell.getAttribute("innerHTML").equals("15")) {
                String cellString = cell.toString();
                String cellAfet = cellString.substring(cellString.lastIndexOf("xpath:"));
                String cellFinal = cellAfet.substring(0, cellAfet.lastIndexOf("->"));
                String cellFinals = cellFinal.substring(0, cellFinal.lastIndexOf("]]")).trim();
                String cellFinalss = cellFinals.replace("xpath:", "").trim();

                ///html/body/div/div[2]/div/div[2]/div/span[19]
                //html/body/div/div[2]/div/div[2]/div/span[21]
                System.err.println(cellFinalss + "/span[" + i + "]");
                System.err.println(
                        driver.findElement(By.xpath(cellFinalss + "/span[" + i + "]")).getAttribute("innerHTML"));
                driver.findElement(By.xpath(cellFinalss + "/span[" + i + "]")).click();
                break;
            }
            i++;
        }

        System.err.println(
                "==================================================");
        cariButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"stockTable_processing\"]")));
        System.err.println(info.getText());
        if (info.getText()
                .equals(new String("Showing 0 to 0 of 0 entries"))) {
            System.err.println("kosong");
        }
        //close Fire fox
//        driver.close();
    }

    private static void clickInput(WebDriver driver) {
        WebElement remarkInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[3]/label"));
        WebElement firstTradeInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[6]/label"));
        WebElement sebelumnyaInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[4]/label"));
        WebElement openPriceInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[5]/label"));
        WebElement indexIndividual = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[14]/label"));
        WebElement listedShare = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[15]/label"));
        WebElement offerInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[16]/label"));
        WebElement offerVolumeInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[17]/label"));
        WebElement bidInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[18]/label"));
        WebElement bidVolumeInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[19]/label"));
        WebElement lastTradingInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[20]/label"));
        WebElement tradeableShareInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[21]/label"));
        WebElement weightForIndexInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[22]/label"));
        WebElement foreginSellInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[23]/label"));
        WebElement foreignBuyInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[24]/label"));
        WebElement nonRegularVolumeInput = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[25]/label"));
        WebElement nonRegularValue = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[26]/label"));
        WebElement nonRegularFrequency = driver.findElement(By.xpath("/html/body/main/div[1]/div[4]/div[27]/label"));
        remarkInput.click();
        firstTradeInput.click();
        sebelumnyaInput.click();
        openPriceInput.click();
        listedShare.click();
        offerInput.click();
        indexIndividual.click();
        offerVolumeInput.click();
        bidInput.click();
        bidVolumeInput.click();
        lastTradingInput.click();
        tradeableShareInput.click();
        weightForIndexInput.click();
        foreginSellInput.click();
        foreignBuyInput.click();
        nonRegularVolumeInput.click();
        nonRegularValue.click();
        nonRegularFrequency.click();
    }

}
