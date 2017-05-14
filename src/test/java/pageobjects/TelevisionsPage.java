package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

public class TelevisionsPage {
    @FindBy(xpath = "//strong[@data-test-element='total-count']")
    private WebElement totalNumberOfProductsAcrossAllPages;

    @FindBy(xpath = "//span[@data-test-element='pagination-limit']")
    private WebElement productsCountInTheCurrentPage;

    @FindBy(xpath="//label[@for='brands_samsung']")
    private WebElement brandSamsungFilterCheckbox;

    @FindBy(xpath = "(//li[@itemprop='itemListElement']//button[text()='Add to compare'])[1]")
    private WebElement firstAvailableAddToCompareButton;

    @FindBy(css = ".msg")
    private WebElement warningMessage;

    @FindBy(xpath = "//p[@itemprop='manufacturer']")
    private List<WebElement> manufacturerNameForAllProducts;

    @FindBy(xpath = "//p[@itemprop='price']")
    private List<WebElement> priceForAllProducts;

    @FindBy(xpath = "//button/span[text()='Clear all']")
    private WebElement clearFiltersButton;

    @FindBy(id = "product_listing_sorter")
    private WebElement sortDropDown;

    @FindBy(xpath = "//button[text()='Remove from compare']")
    private List<WebElement> removeFromCompareButton;

    @FindBy(xpath = "//a[@data-test-element='Next page']")
    private List<WebElement> nextPageButtons;

    @FindBy(xpath = "//a[@data-test-element='Next page']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//p[@data-test-element='tested-date']")
    private List<WebElement> testDateElements;

    int nextInteger;

    private By randomProductForComparison = By.xpath("(//li[@itemprop='itemListElement']//button)[%d]");
    //private By randomProductForComparison = By.xpath(String.format("(//li[@itemprop='itemListElement']//button)[%d]", nextInteger));


    public int getTotalNumberOfProductsOnTheCurrentPage() {
        return Integer.parseInt((productsCountInTheCurrentPage.getText()));
    }

    public int getCurrentProductCountAcrossAllPages() {
        return Integer.parseInt((totalNumberOfProductsAcrossAllPages.getText()));
    }

    public void addNumberOfTVsForComparison(WebDriver driver, int tvsToAdd) throws InterruptedException {
        int addedToCompareAlready, attempt = 1;
        do {
            //nextInteger = new Random().nextInt(getTotalNumberOfProductsOnTheCurrentPage() - 1);
            try {
                addAProductForComparison(driver);
            } catch (NoSuchElementException e) {
            }
            Thread.sleep(1000);
            addedToCompareAlready = removeFromCompareButton.size();
            attempt++;
        } while (attempt < 10 && addedToCompareAlready != tvsToAdd);
    }

    public void addAProductForComparison(WebDriver driver) throws InterruptedException {
        executeUsingJavaScript((JavascriptExecutor) driver, firstAvailableAddToCompareButton);
    }

    public void filterFor(WebDriver driver, String filerToSelect) throws InterruptedException {
        executeUsingJavaScript((JavascriptExecutor) driver, brandSamsungFilterCheckbox);
    }

    public boolean areAllProductsDisplayedBelongsTo(String expectedBrand) throws InterruptedException {
        do {
            Thread.sleep(1000);
            if (atLeastOneProductNotBelongsToBrand(expectedBrand, manufacturerNameForAllProducts)) return false;
            if (nextPageButtons.size() != 0) nextPageButton.click();
            else break;
        } while (true);
        return true;
    }

    private boolean atLeastOneProductNotBelongsToBrand(String expectedBrand, List<WebElement> elements) {
        for (WebElement element : elements) {
            if (!(element.getText().equalsIgnoreCase(expectedBrand)))
                return true;
        }
        return false;
    }

    public void clearAllFilter(WebDriver driver) throws InterruptedException {
        executeUsingJavaScript((JavascriptExecutor) driver, clearFiltersButton);
        Thread.sleep(2000);
    }

    private void executeUsingJavaScript(JavascriptExecutor jse, WebElement element) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        Thread.sleep(2000);
    }

    public void selectValueFromSortDropDown(WebDriver driver, String dropdownSelelctValue) throws InterruptedException {
        sortDropDown.click();
        driver.findElement(By.xpath("//option[text()='" + dropdownSelelctValue + "']")).click();
        Thread.sleep(1000);
    }

    public boolean isAttribtuePresent(WebDriver driver, String dropdown, String attribute) {
        WebElement element = driver.findElement(By.xpath("//option[text()='" + dropdown + "']"));
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }
        return result;
    }

    public boolean pricesAreInAscendingOrder() throws InterruptedException {
        do {
            Thread.sleep(1000);
            List<WebElement> elements = priceForAllProducts;
            for (int i = 0; i < elements.size() - 1; i++) {
                Double price1 = Double.parseDouble(elements.get(i).getText().substring(1).replaceAll(",", ""));
                Double price2 = Double.parseDouble(elements.get(i + 1).getText().substring(1).replaceAll(",", ""));
                if (price1 > price2) return false;
            }
            if (nextPageButtons.size() != 0) nextPageButtons.get(0).click();
            else break;
        } while (true);
        return true;
    }

    public boolean lastlyTestedTelevisionsListedFirst() throws InterruptedException {
        do {
            Thread.sleep(1000);
            List<WebElement> elements = testDateElements;
            for (int i = 0; i < elements.size() - 1; i++) {
                String first = elements.get(i).getText();
                String second = elements.get(i + 1).getText();
                if (!first.equals(second) && latestOf(first, second).equals(second))
                    return false;
            }
            if (nextPageButtons.size() != 0) nextPageButton.click();
            else break;
        } while (true);
        return true;
    }

    private String latestOf(String firstString, String secondString) {
        String[] firstStringSplit = firstString.replaceAll("Tested:", "").split("\\s+");
        String[] secondStringSplit = secondString.replaceAll("Tested:", "").split("\\s+");
        String firstProductMonth, secondProductMonth;
        int firstProductYear, secondProductYear;
        if (firstStringSplit[1].equals("-") || secondStringSplit[1].equals("-"))
            return firstString;
        else {
            firstProductMonth = firstStringSplit[1];
            secondProductMonth = secondStringSplit[1];
            firstProductYear = Integer.parseInt(firstStringSplit[2]);
            secondProductYear = Integer.parseInt(secondStringSplit[2]);
        }


        HashMap<String, Integer> hm = new HashMap();
        hm.put("Jan", 1);
        hm.put("Feb", 2);
        hm.put("Mar", 3);
        hm.put("Apr", 4);
        hm.put("May", 5);
        hm.put("Jun", 6);
        hm.put("Jul", 7);
        hm.put("Aug", 8);
        hm.put("Sep", 9);
        hm.put("Oct", 10);
        hm.put("Nov", 11);
        hm.put("Dec", 12);

        if (firstProductYear > secondProductYear) {
            return firstString;
        } else if (firstProductYear == secondProductYear && hm.get(firstProductMonth) >= hm.get(secondProductMonth)) {
            return firstString;
        }
        return secondString;
    }

    public boolean isWarnigMessagePresent(String expectedText) {
        return warningMessage.getText().equals(expectedText);
    }
}
