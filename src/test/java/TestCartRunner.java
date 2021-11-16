import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class TestCartRunner {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("http://automationpractice.com/index.php");

    }

    @Test
    public void CartTest() {
        assertEquals("My Store", webDriver.getTitle()); //Провереям тот ли сайт открылся
        webDriver.findElement(By.cssSelector("img[title='Blouse']")).click(); //Выбираем товар, в данном случае Blouse
        webDriver.findElement(By.cssSelector("button[name='Submit']")).click();
        webDriver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
        webDriver.get("http://automationpractice.com/index.php?controller=order");
        assertEquals("Order - My Store", webDriver.getTitle()); //Провереям тот ли сайт открылся
        webDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a")).click();//обновляем страницу путем нажатия на кнопку корзины
        assertNotEquals("Your shopping cart is empty.", webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p"))); //Проверка на пустоту корзины
        webDriver.quit();

    }

    @Test
    public void DeleteGoods() {
        assertEquals("My Store", webDriver.getTitle()); //Провереям тот ли сайт открылся
        webDriver.findElement(By.cssSelector("img[title='Blouse']")).click(); //Выбираем товар, в данном случае Blouse
        webDriver.findElement(By.cssSelector("button[name='Submit']")).click();
        webDriver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
        webDriver.get("http://automationpractice.com/index.php?controller=order");
        assertEquals("Order - My Store", webDriver.getTitle()); //Провереям тот ли сайт открылся
        webDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a")).click();//обновляем страницу путем нажатия на кнопку корзины
        webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[7]/div/a")).click();//Удаляем товар
        assertEquals("Your shopping cart is empty.", webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p"))); //Проверка на пустоту корзины
        webDriver.quit();
    }

}
