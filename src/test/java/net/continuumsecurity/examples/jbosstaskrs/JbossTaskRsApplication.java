package net.continuumsecurity.examples.jbosstaskrs;

import net.continuumsecurity.Config;
import net.continuumsecurity.Credentials;
import net.continuumsecurity.UserPassCredentials;
import net.continuumsecurity.behaviour.ILogin;
import net.continuumsecurity.behaviour.ILogout;
import net.continuumsecurity.behaviour.INavigable;
import net.continuumsecurity.web.WebApplication;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class JbossTaskRsApplication extends WebApplication implements ILogin,
        ILogout,INavigable {

    public JbossTaskRsApplication() {
        super();

    }

    @Override
    public void openLoginPage() {
        driver.get(Config.getInstance().getBaseUrl() + "login.mvc");
        findAndWaitForElement(By.id("exampleInputEmail1"));
    }

    @Override
    public void login(Credentials credentials) {
        UserPassCredentials creds = new UserPassCredentials(credentials);
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys(creds.getUsername());
        driver.findElement(By.id("exampleInputPassword1")).clear();
        driver.findElement(By.id("exampleInputPassword1")).sendKeys(creds.getPassword());
        driver.findElement(By.tagName("button")).click();
    }

    // Convenience method
    public void login(String username, String password) {
        login(new UserPassCredentials(username, password));
    }

    @Override
    public boolean isLoggedIn() {
        return true;
        // driver.get(Config.getInstance().getBaseUrl()+"task/list");
        // if (driver.getPageSource().contains("Tasks")) {
        //     return true;
        // } else {
        //     return false;
        // }
    }

    public void viewProfile() {
        driver.findElement(By.linkText("Profile")).click();
    }

    public void viewAlicesProfile() {
        viewProfile();
    }

    public void viewBobsProfile() {
        viewProfile();
    }

    @Override
    public void logout() {
        //driver.findElement(By.linkText("Logout")).click();
    }

    public void search(String query) {
        findAndWaitForElement(By.linkText("Tasks")).click();
        driver.findElement(By.id("q")).clear();
        driver.findElement(By.id("q")).sendKeys(query);
        WebElement searchBtn = driver.findElement(By.name("search-button"));
        searchBtn.click();
    }

    public void viewAllUsers() {
        driver.get(Config.getInstance().getBaseUrl() + "admin/list");
    }

    public void navigate() {
        //openLoginPage();
        //login(Config.getInstance().getDefaultCredentials());
        //viewProfile();
        //search("test");
    }

}
