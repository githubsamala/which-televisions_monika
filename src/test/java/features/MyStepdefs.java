package features;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.TelevisionsPage;

import javax.naming.InsufficientResourcesException;
import java.util.concurrent.TimeUnit;


public class MyStepdefs {

    private WebDriver driver;
    private int totalNumberOfTVs;
    private TelevisionsPage televisionsPage;

    @cucumber.api.java.Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @cucumber.api.java.After
    public void tearDown() {
        driver.quit();
    }

    @Given("^I am on television landing page$")
    public void iAmOnTelevisionLandingPage() throws Throwable {
        driver.navigate().to("http://www.which.co.uk/reviews/televisions"); //Navigate to the given site
        televisionsPage = PageFactory.initElements(driver, TelevisionsPage.class);
    }

    @And("^I have at least (\\d+) televisions listed$")
    public void iHaveAtLeastTelevisionsListed(int expectedNumberOfItems) throws Throwable {
        if (expectedNumberOfItems > televisionsPage.getTotalNumberOfProductsOnTheCurrentPage()) {
            throw new InsufficientResourcesException("Not enough televisions"); // throws this exception if we don't have enough tv's in the list to test this scenario
        }
    }

    @And("^I have added (\\d+) televisions to comparison$")
    public void iHaveAddedTelevisionsToComparison(int tvsToAdd) throws Throwable {
        televisionsPage.addNumberOfTVsForComparison(driver, tvsToAdd);
    }

    @When("^I attempt to add an another one$")
    public void iAttemptToAddAnAnotherOne() throws Throwable {
        televisionsPage.addAProductForComparison(driver);
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String expectedText) throws Throwable {
        Assert.assertTrue(televisionsPage.isWarnigMessagePresent(expectedText));
    }

    @When("^I filter for \"([^\"]*)\"$")
    public void iFilterFor(String filerToSelect) throws Throwable {
        televisionsPage.filterFor(driver, filerToSelect);
    }

    @Then("^I should only see \"([^\"]*)\" televisions$")
    public void iShouldOnlySeeTelevisions(String expectedBrand) throws Throwable {
        Assert.assertTrue("Unexpected Brand exists", televisionsPage.areAllProductsDisplayedBelongsTo(expectedBrand));
    }

    @When("^I clearAll the filter$")
    public void iClearAllTheFilter() throws Throwable {
        televisionsPage.clearAllFilter(driver);
    }

    @Then("^it filters$")
    public void itFilters() throws Throwable {
        int currentTelevisionCount = televisionsPage.getCurrentProductCountAcrossAllPages();
        Assert.assertTrue("Not filtered as expected", currentTelevisionCount < totalNumberOfTVs);
    }

    @And("^I note televisions count$")
    public void iHaveTelevisionsListed() throws Throwable {
        totalNumberOfTVs = televisionsPage.getCurrentProductCountAcrossAllPages();
    }

    @Then("^I should see original television count$")
    public void iShouldSeeOriginalTelevisionCount() throws Throwable {
        Thread.sleep(2000);
        int listAfterClearingTheFilter = televisionsPage.getCurrentProductCountAcrossAllPages();
        Assert.assertTrue("current count: " + listAfterClearingTheFilter + " doesn't match with the count noted: " + totalNumberOfTVs + " earlier", listAfterClearingTheFilter == totalNumberOfTVs);
    }

    @When("^I sort \"([^\"]*)\"$")
    public void iSort(String dropdownSelectValue) throws Throwable {
        televisionsPage.selectValueFromSortDropDown(driver, dropdownSelectValue);
    }

    @Then("^I should see televisions sorted from low to high$")
    public void iShouldSeeTelevisionsSortedFromLowToHigh() throws Throwable {
        Assert.assertTrue(televisionsPage.pricesAreInAscendingOrder());
    }

    @Then("^I should see \"([^\"]*)\" selected by default$")
    public void iShouldSeeSelectedByDefault(String dropdown) throws Throwable {
        Assert.assertTrue(televisionsPage.isAttribtuePresent(driver, dropdown, "selected"));
    }


    @And("^I should see the lastly tested televisions first$")
    public void iShouldSeeTheLastlyTestedTelevisionsFirst() throws Throwable {
        Assert.assertTrue(televisionsPage.lastlyTestedTelevisionsListedFirst());
    }
}