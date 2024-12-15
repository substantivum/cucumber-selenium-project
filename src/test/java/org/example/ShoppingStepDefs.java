package org.example;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingStepDefs extends AbstractStepDefs {

    @Given("the {string} is added to the cart")
    public void somethingIsAddedToTheCart(String item) {
        homePage.addItemToCart(item);
    }

    @Then("the price should read {string}")
    public void thePriceShouldRead(String total) {
        assertEquals(total, homePage.getTotal());
    }

    @Then("the cart is emptied")
    public void theCartIsEmptied() {
        homePage.emptyCart();
    }
    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        boolean isCartEmpty = homePage.isCartEmpty();
        assertTrue(isCartEmpty, "The cart is not empty");
    }

    @When("remove {string} from the cart")
    public void removeSomethingFromTheCart(String item) {
        homePage.removeItemFromCart(item);
    }


}
