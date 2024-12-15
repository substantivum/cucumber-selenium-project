package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class HomePage {

    private static final String PAGE_URL = "https://www.saucedemo.com/";
    private static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";

    private final WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "product-sort-container")
    private WebElement filterDropdown;
    @FindBy(css = "#login_button_container > div > form > div.error-message-container.error > h3")
    private WebElement errorMessage;
    @FindBy(css = "#checkout_summary_container > div > div.summary_info > div.summary_total_label")
    private WebElement priceLabel;

    private static final Map<String, By> textFields = Map.of(
            "Username", By.id("user-name"),
            "Password", By.id("password"),
            "First Name", By.id("first-name"),
            "Last Name", By.id("last-name"),
            "Zip Code", By.id("postal-code")
    );

    private static final Map<String, By> itemButtons = Map.of(
            "Sauce Labs Backpack", By.id("add-to-cart-sauce-labs-backpack"),
            "Sauce Labs Bike Light", By.id("add-to-cart-sauce-labs-bike-light"),
            "Sauce Labs Bolt T-Shirt", By.id("add-to-cart-sauce-labs-bolt-t-shirt"),
            "Sauce Labs Fleece Jacket", By.id("add-to-cart-sauce-labs-fleece-jacket"),
            "Sauce Labs Onesie", By.id("add-to-cart-sauce-labs-onesie"),
            "Test.allTheThings() T-Shirt (Red)", By.id("add-to-cart-test.allthethings()-t-shirt-(red)")
    );

    private static final Map<String, By> removeButtons = Map.of(
            "remove-sauce-labs-backpack", By.id("remove-sauce-labs-backpack"),
            "remove-sauce-labs-bike-light", By.id("remove-sauce-labs-bike-light"),
            "remove-sauce-labs-bolt-t-shirt", By.id("remove-sauce-labs-bolt-t-shirt"),
            "remove-sauce-labs-fleece-jacket", By.id("remove-sauce-labs-fleece-jacket"),
            "remove-sauce-labs-onesie", By.id("remove-sauce-labs-onesie")
    );

    private static final Map<String, By> navigationButtons = Map.of(
            "Login", By.id("login-button"),
            "Cart", By.className("shopping_cart_link"),
            "Checkout", By.id("checkout"),
            "Continue", By.id("continue"),
            "Finish", By.id("finish"),
            "Back Home", By.id("back-to-products")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public String getPage() {
        return driver.getCurrentUrl();
    }
    public void openInventory() {
        driver.get(INVENTORY_URL);
        PageFactory.initElements(driver, this);
    }
    public void closePage() {
        driver.quit();
    }

    public void fillOutField(String field, String text) {
        driver.findElement(textFields.get(field)).sendKeys(text);
    }

    public void clickButton(String button) {
        driver.findElement(navigationButtons.get(button)).click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void addItemToCart(String item) {
        driver.findElement(itemButtons.get(item)).click();
    }

    public String getTotal() {
        return priceLabel.getText();
    }

    public String getInventoryUrl() {
        return INVENTORY_URL;
    }

    public void emptyCart() {
        driver.findElement(navigationButtons.get("Finish")).click();
        driver.findElement(navigationButtons.get("Back Home")).click();
    }

    public boolean isFilterDropdownDisplayed() {
        return driver.findElement(By.className("product_sort_container")).isDisplayed();
    }

    public void clickFilterDropdown() {
        driver.findElement(By.className("product_sort_container")).click();
    }

    public void selectFilterOption(String option) {
        driver.findElement(By.cssSelector("option[value='" + option + "']")).click();
    }


    public boolean areItemsSortedByPrice(String order) {
        boolean sorted = true;

        // Get the list of prices as an array of strings
        String[] prices = driver.findElements(By.className("inventory_item_price")).stream()
                .map(WebElement::getText)
                .toArray(String[]::new);

        // Convert prices to numeric values for proper comparison
        float[] numericPrices = new float[prices.length];
        for (int i = 0; i < prices.length; i++) {
            // Remove the dollar sign and parse to float
            numericPrices[i] = Float.parseFloat(prices[i].replace("$", ""));
        }

        // Compare adjacent prices based on the selected order
        for (int i = 0; i < numericPrices.length - 1; i++) {
            if (order.equals("low to high")) {
                if (numericPrices[i] > numericPrices[i + 1]) {
                    sorted = false;
                    break;
                }
            } else {
                if (numericPrices[i] < numericPrices[i + 1]) {
                    sorted = false;
                    break;
                }
            }
        }

        return sorted;
    }




    public boolean areItemsSortedByName(String order) {
        boolean sorted = true;
        String[] names = driver.findElements(By.className("inventory_item_name")).stream()
                .map(WebElement::getText)
                .toArray(String[]::new);
        for (int i = 0; i < names.length - 1; i++) {
            if (order.equals("A to Z")) {
                if (names[i].compareTo(names[i + 1]) > 0) {
                    sorted = false;
                    break;
                }
            } else {
                if (names[i].compareTo(names[i + 1]) < 0) {
                    sorted = false;
                    break;
                }
            }
        }
        return sorted;
    }

    public boolean isCartEmpty() {
        WebElement cartList = driver.findElement(By.cssSelector(".cart_list"));
        List<WebElement> cartItems = cartList.findElements(By.className("cart_item"));
        return cartItems.isEmpty();
    }

    public boolean waitForElementToDisappear(By element) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public String convertToRemoveButtonId(String productName) {
        String lowerCaseName = productName.toLowerCase();
        String formattedName = lowerCaseName.replace(" ", "-");
        System.out.println("remove-" + formattedName);
        return "remove-" + formattedName;
    }

    public void removeItemFromCart(String item) {
        String myItemID = convertToRemoveButtonId(item);
        driver.findElement(removeButtons.get(myItemID)).click();
    }

}
