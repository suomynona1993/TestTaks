package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;

import java.util.List;

import java.util.concurrent.TimeUnit;


public class Main {

    public static void main( String[] args)   {



        WebDriver currentChromeDriver = new ChromeDriver();
        currentChromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Устанавливаем неявное ожидание для драйвера,
                                                                               // на всякий случай, если забудем где-то указать явное.

        currentChromeDriver.get("http://google.com/ncr"); //Переходим к странице поиска


        try {   //Вводим поисковый запрос и отправляем
            WebElement SearchInputElement = (new WebDriverWait(currentChromeDriver, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@title = \"Search\"]"))));
            SearchInputElement.sendKeys("selenium");
            SearchInputElement.submit();
        } catch (TimeoutException e)
        {
            System.out.println("Тест не пройден: Поисковая строка не найдена за приемлемое время.");
            currentChromeDriver.quit();
            return;
        }


        try{
             List <WebElement> SERPLinks = new WebDriverWait(currentChromeDriver,10).
                until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class = \"srg\"]//div[@class = 'r']/a[not(@class='fl')]"),1)); //получаем только ссылки на сайты
                                                                                                                                            //исключая ссылка на предложение перевода страницы,
                                                                                                                                            //ждем, пока сформируется хотя бы одна ссылка

            if( SERPLinks.get(0).getAttribute("href").contains("seleniumhq.org") )
                System.out.println("Первая ссылка ведет на seleniumhq.org");
            else {
                System.out.println("Первая ссылка не ведет на seleniumhq.org");
                System.out.println("Первая ссылка:" + SERPLinks.get(0).getAttribute("href"));
            }
        } catch (TimeoutException e)
        {
            System.out.println("Тест не пройден: Поисковая выдача не сформировалась за приемлемое время.");
            currentChromeDriver.quit();
            return;
        }


        try {
            currentChromeDriver.findElement(By.xpath("//div[@id = 'top_nav']//a[string()='Images']")).click();
        } catch(ElementClickInterceptedException e)
        {
            System.out.println("Тест не пройден: Не удается найти элемент перехода к разделу \"Картинки\"");
            currentChromeDriver.quit();
            return;
        }

        try{
             List <WebElement> ImageLinks = new WebDriverWait(currentChromeDriver,10).
                until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a[contains(@href,\"imgres\")]"),1));

        if( ImageLinks.get(0).getAttribute("href").contains("seleniumhq.org") )
            System.out.println("Первое изображение с seleniumhq.org");
        else {
            System.out.println("Первое изображение не с seleniumhq.org");
            System.out.println("Ссылка на первое изображение:" + ImageLinks.get(0).getAttribute("href"));
        }} catch (TimeoutException e)
        {
            System.out.println("Тест не пройден: Поисковая выдача картинок не сформировалась за приемлемое время.");
            currentChromeDriver.quit();
            return;
        }

        try {
            currentChromeDriver.findElement(By.xpath("//div[@id = 'top_nav']//a[string()='All']")).click();
        }catch (ElementClickInterceptedException e)
        {
                System.out.println("Тест не пройден: Не удается найти элемент перехода к разделу \"Все\"");
                currentChromeDriver.quit();
                return;
        }


        try{
            List<WebElement> SERPLinks = new WebDriverWait(currentChromeDriver,10).
                until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class = \"srg\"]//div[@class = 'r']/a[not(@class='fl')]"),1));

        if( SERPLinks.get(0).getAttribute("href").contains("seleniumhq.org") )
            System.out.println("Первая ссылка все еще ведет на seleniumhq.org");
        else {
            System.out.println("Первая ссылка не ведет на seleniumhq.org");
            System.out.println("Первая ссылка:" + SERPLinks.get(0).getAttribute("href"));
        }} catch (TimeoutException e)
        {
        System.out.println("Тест не пройден: Поисковая выдача не сформировалась за приемлемое время.");
        currentChromeDriver.quit();
        return;
        }


        currentChromeDriver.quit();
    }

}
