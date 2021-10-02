package testeDeSistema;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;


public class REQMantemLivroTests {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}


	@Test
		//Livro cadastrado com sucesso
	  public void REQ01CT01() {
	    espera();
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    espera();
	    driver.findElement(By.linkText("Livros")).click();
	    driver.findElement(By.id("isbn")).click();
	    driver.findElement(By.id("isbn")).sendKeys("6745");
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).sendKeys("Cicrano da silva");
	    driver.findElement(By.id("titulo")).click();
	    driver.findElement(By.id("titulo")).sendKeys("As trancas do rei careca");
	    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	    espera();
	    driver.findElement(By.cssSelector("body")).click();
	    assertTrue(driver.findElement(By.id("paginaConsulta")).getText().equals("Lista de livros"));
	  }
	
	@Test
	//Livro atualizado com sucesso
	  public void REQ01CT02() {
		espera();
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    espera();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    espera();
	    driver.findElement(By.cssSelector("tr:nth-child(2) .btn-primary")).click();
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).sendKeys("Sim");
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).click();
	    assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).getText().equals("Sim"));
	  }
	
	  @Test
	  //Livros consultados com sucesso
	  public void REQ01CT03() {
		espera();
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    espera();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    espera();
	    driver.findElement(By.cssSelector("body")).click();
	    assertTrue(driver.findElement(By.id("paginaConsulta")).getText().equals("Lista de livros"));
	  }
	  
	  @Test
	  //Excluindo um livro com sucesso
	  public void REQ01CT04() {
		espera();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
		espera();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.id("isbn")).click();
	    driver.findElement(By.id("isbn")).sendKeys("6778");
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).sendKeys("Fulano Da Silva");
	    driver.findElement(By.id("titulo")).click();
	    driver.findElement(By.id("titulo")).sendKeys("A volta");
	    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	    espera();
	    driver.findElement(By.linkText("Excluir")).click();
	    espera();
	    driver.findElement(By.cssSelector("td:nth-child(3)")).click();
	    assertFalse(driver.findElement(By.cssSelector("td:nth-child(3)")).getText().equals("A volta"));
	  }

	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}
}
