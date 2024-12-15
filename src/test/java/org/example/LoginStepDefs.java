package org.example;

import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginStepDefs extends AbstractStepDefs {

    @Then("the {string} message is shown")
    public void theErrorMessageMessageIsShown(String errorMessage) {
        assertEquals(errorMessage, homePage.getErrorMessage());
    }

    @Then("the user is redirected to the Products page")
    public void theUserIsRedirectedToThePage() {
        String currentPage = homePage.getPage();
        assertEquals(currentPage, homePage.getInventoryUrl());
    }

    @Then("the {string} button clicked")
    public void theButtonClicked(String button) {
        homePage.clickButton(button);
    }
}
