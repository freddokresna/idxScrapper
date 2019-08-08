/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idxscrapper;

import entity.IdxScrap;
import entity.IdxScrapPK;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
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
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -177); // number represents number of days
        Date yesterday = cal.getTime();

        System.out.println("Yesterday's date is: " + yesterday);

        DateFormat dateFormatone = new SimpleDateFormat("yyyy-MM-dd");
        Date dateone = new Date();
        System.out.println(dateFormatone.format(dateone));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat fmts = new SimpleDateFormat("yyyy-MMMM-dd");
        Date startDate = yesterday;
//        Date startDate = formatter.parse("2019-02-13");
        Date endDate = formatter.parse(dateFormatone.format(dateone));
        System.err.println(startDate + "---" + endDate);
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        System.err.println(System.getProperty("user.dir"));
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.contains(
                "win")) {
            // declaration and instantiation of objects/variables
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\lib\\geckodriver-v0.23.0-win64\\geckodriver.exe");

        } else if (OS.contains(
                "nix") || OS.contains("nux") || OS.indexOf("aix") > 0) {

            // declaration and instantiation of objects/variables
            System.err.println("System.getProperty(\"user.dir\") " + System.getProperty("user.dir") + "/lib/geckodriver-v0.23.0-linux64/geckodriver");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/lib/geckodriver-v0.23.0-linux64/geckodriver");
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

        new WebDriverWait(driver,
                6000).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Thread.sleep(
                1000);
        clickInput(driver);

        for (LocalDate dates = start; dates.isBefore(end); dates = dates.plusDays(1)) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println(dates.toString() + " = " + start.toString()
            );
            System.out.println("----------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
            String month = dates.getMonth().toString();
            String date = String.valueOf(dates.getDayOfMonth());
            String year = String.valueOf(dates.getYear());
            String tanggal = year + "-" + month + "-" + date;
            Date dd = fmts.parse(tanggal);
            String tanggalDB = formatter.format(dd);
            WebElement dateBox = driver.findElement(By.xpath("//*[@id=\"dateFilter\"]"));
            WebElement yearInput = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/input"));
            WebElement monthInput = driver.findElement(By.xpath("/html/body/div/div[1]/div/span"));
            WebElement leftMonthSelect = driver.findElement(By.xpath("/html/body/div/div[1]/span[1]"));
            WebElement cariButton = driver.findElement(By.xpath("/html/body/main/div[1]/div[5]/button"));
            WebElement info = driver.findElement(By.xpath("//*[@id=\"stockTable_info\"]"));
            WebElement infoShow100 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[1]/div/div[1]/label/select/option[4]"));
            dateBox.click();
            new WebDriverWait(driver,
                    6000).until(presenceOfElementLocated(By.xpath("/html/body/div/div[1]/span[1]")));
            System.err.println(driver.findElement(By.xpath("/html/body/div/div[1]/span[1]")).getText().toLowerCase());
            System.err.println(month.toLowerCase());
            System.err.println(monthInput.getText().toLowerCase());
            while (!monthInput.getText().toLowerCase().equals(month.toLowerCase())) {
                driver.findElement(By.xpath("/html/body/div/div[1]/span[1]")).click();
                monthInput = driver.findElement(By.xpath("/html/body/div/div[1]/div/span"));
            }
            dateBox = driver.findElement(By.xpath("//*[@id=\"dateFilter\"]"));
            yearInput = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/input"));

            yearInput.clear();
            yearInput.sendKeys(year);

            dateBox.click();

            WebElement dataWidget = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div"));
            List<WebElement> columns = dataWidget.findElements(By.className("flatpickr-day"));

//        System.err.println(
//                "==================================================");
//        System.err.println(columns.size());
            int i = 1;
            for (WebElement cell : columns) {
                //Select xx. Date
//            System.err.println(cell.getText() + "tagname" + cell.getTagName() + "xpath" + cell.getAttribute("xpath") + "gettext" + cell.getAttribute("innerHTML") + cell.getAttribute("class"));
                if (cell.getAttribute("class").contains("prevMonthDay") || cell.getAttribute("class").contains("nextMonthDay")) {

                } else if (cell.getAttribute("innerHTML").equals(date)) {
                    String cellString = cell.toString();
                    String cellAfet = cellString.substring(cellString.lastIndexOf("xpath:"));
                    String cellFinal = cellAfet.substring(0, cellAfet.lastIndexOf("->"));
                    String cellFinals = cellFinal.substring(0, cellFinal.lastIndexOf("]]")).trim();
                    String cellFinalss = cellFinals.replace("xpath:", "").trim();

//                System.err.println(cellFinalss + "/span[" + i + "]");
//                System.err.println(
//                        driver.findElement(By.xpath(cellFinalss + "/span[" + i + "]")).getAttribute("innerHTML"));
                    driver.findElement(By.xpath(cellFinalss + "/span[" + i + "]")).click();
                    break;
                }
                i++;
            }

//        System.err.println(
//                "==================================================");
            infoShow100.click();
            cariButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"stockTable_processing\"]")));
            Thread.sleep(
                    3000);
//        System.err.println(info.getText());

            if (info.getText()
                    .equals(new String("Showing 0 to 0 of 0 entries"))) {
                System.err.println("kosong");
            } else {
                // Grab the table
                WebElement table = driver.findElement(By.xpath("//*[@id=\"stockTable\"]"));
                int nextCount = 1;
                while (nextCount < 8) // Now get all the TR elements from the table
                {

                    Thread.sleep(
                            2000);
                    WebDriverWait waits = new WebDriverWait(driver, 100);

                    waits.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"stockTable_processing\"]")));
                    WebElement next = driver.findElement(By.xpath("//*[@id=\"stockTable_next\"]"));
                    List<WebElement> allRows = table.findElements(By.tagName("tr"));

// And iterate over them, getting the cells
                    for (WebElement row : allRows) {
                        IdxScrap idxScrap = new IdxScrap();
                        IdxScrapPK idxScrapPK = new IdxScrapPK();
                        IdxScrapJpaController controller = new IdxScrapJpaController();
                        List<WebElement> cells = row.findElements(By.tagName("td"));

                        int indexTabel = 1;
                        // Print the contents of each cell
                        for (WebElement cell : cells) {
                            if (indexTabel == 2) {
                                idxScrapPK.setKodeSaham(cell.getText());
                                System.err.println("idexTabel = " + indexTabel);
                                System.err.println("setKodeSaham = " + idxScrapPK.getKodeSaham());
                            }
                            if (indexTabel == 3) {
                                idxScrap.setNamaPerusahaan(cell.getText());
                            }
                            if (indexTabel == 4) {
                                idxScrap.setRemarks(cell.getText());
                            }
                            if (indexTabel == 5) {
                                idxScrap.setSebelumnya(cell.getText());
                            }
                            if (indexTabel == 6) {
                                idxScrap.setOpenPrice(cell.getText());
                            }
                            if (indexTabel == 7) {
                                idxScrap.setFirstTrade(cell.getText());
                            }
                            if (indexTabel == 8) {
                                idxScrap.setTertinggi(cell.getText());
                            }
                            if (indexTabel == 9) {
                                idxScrap.setTerendah(cell.getText());
                            }
                            if (indexTabel == 10) {
                                idxScrap.setPenutupan(cell.getText());
                            }
                            if (indexTabel == 11) {
                                idxScrap.setSelisih(cell.getText());
                            }
                            if (indexTabel == 12) {
                                idxScrap.setVolume(cell.getText());
                            }
                            if (indexTabel == 13) {
                                idxScrap.setNilai(cell.getText());
                            }
                            if (indexTabel == 14) {
                                idxScrap.setFrekuensi(cell.getText());
                            }
                            if (indexTabel == 15) {
                                idxScrap.setIndexIndividual(cell.getText());
                            }
                            if (indexTabel == 16) {
                                idxScrap.setListedShare(cell.getText());
                            }
                            if (indexTabel == 17) {
                                idxScrap.setOffer(cell.getText());
                            }
                            if (indexTabel == 18) {
                                idxScrap.setOfferVolume(cell.getText());
                            }
                            if (indexTabel == 19) {
                                idxScrap.setBid(cell.getText());
                            }
                            if (indexTabel == 20) {
                                idxScrap.setBidVolume(cell.getText());
                            }
                            if (indexTabel == 21) {
                                idxScrap.setLastTradingDate(cell.getText());
                            }
                            if (indexTabel == 22) {
                                idxScrap.setTradeableShare(cell.getText());
                            }
                            if (indexTabel == 23) {
                                idxScrap.setWeightForIndex(cell.getText());
                            }
                            if (indexTabel == 24) {
                                idxScrap.setForeignSell(cell.getText());
                            }
                            if (indexTabel == 25) {
                                idxScrap.setForeignBuy(cell.getText());
                            }
                            if (indexTabel == 26) {
                                idxScrap.setNonRegularVolume(cell.getText());
                            }
                            if (indexTabel == 27) {
                                idxScrap.setNonRegularValue(cell.getText());
                            }
                            if (indexTabel == 28) {
                                idxScrap.setNonRegularFrequency(cell.getText());
                            }
                            indexTabel++;
                        }
                        System.err.println("==================================================");
                        boolean isEmpty = "".equals(idxScrapPK.getKodeSaham());
                        if (idxScrapPK.getKodeSaham() != null) {
                            idxScrapPK.setTanggal(dd);
                            idxScrap.setIdxScrapPK(idxScrapPK);
                            System.err.println(idxScrap.getIdxScrapPK().toString());
                            try {
                                controller.create(idxScrap);

                            } catch (Exception e) {
//                                break;
                                System.err.println("something happen!!!");
                                e.printStackTrace();
                            }
                            idxScrap = new IdxScrap();
                            idxScrapPK = new IdxScrapPK();

                        }
                    }

                    nextCount++;
                    next.click();
                    Thread.sleep(
                            2000);
                }
            }
        }
        //close Fire fox
        driver.close();
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
