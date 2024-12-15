package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class FilterStepDefs extends AbstractStepDefs {

    @Given("the 'Filter' dropdown is displayed")
    public void theFilterDropdownIsDisplayed() {
        assertTrue(homePage.isFilterDropdownDisplayed(), "'Filter' dropdown should be visible on the page");
    }

    @When("the 'Filter' dropdown is selected")
    public void theFilterDropdownIsSelected() {
        homePage.clickFilterDropdown();
    }

    @When("the low to high option is chosen")
    public void theUserSelectsPriceLowToHighOption() {
        homePage.selectFilterOption("lohi");
    }

    @When("the high to low option is chosen")
    public void theUserSelectsPriceHighToLowOption() {
        homePage.selectFilterOption("hilo");
    }

    @When("the A to Z option is chosen")
    public void theUserSelectsNameAToZOption() {
        homePage.selectFilterOption("az");
    }

    @When("the Z to A option is chosen")
    public void theUserSelectsNameZToAOption() {
        homePage.selectFilterOption("za");
    }

    @Then("the items should be displayed in ascending order of price")
    public void theItemsShouldBeDisplayedInAscendingOrderOfPrice() {
        assertTrue(homePage.areItemsSortedByPrice("low to high"), "Items should be sorted by price in ascending order");
    }

    @Then("the items should be displayed in descending order of price")
    public void theItemsShouldBeDisplayedInDescendingOrderOfPrice() {
        assertTrue(homePage.areItemsSortedByPrice("high to low"), "Items should be sorted by price in descending order");
    }

    @Then("the items should be displayed in alphabetical order")
    public void theItemsShouldBeDisplayedInAlphabeticalOrder() {
        assertTrue(homePage.areItemsSortedByName("A to Z"), "Items should be sorted alphabetically from A to Z");
    }

    @Then("the items should be displayed in reverse alphabetical order")
    public void theItemsShouldBeDisplayedInReverseAlphabeticalOrder() {
        assertTrue(homePage.areItemsSortedByName("Z to A"), "Items should be sorted alphabetically from Z to A");
    }
}
