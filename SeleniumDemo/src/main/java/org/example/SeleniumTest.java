package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Allen
 */
public class SeleniumTest {
    public static Set<Cookie> cookies = new HashSet<>(123);

    @Test
    public void go() throws InterruptedException {
        //设置驱动文件路径
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Allen\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //创建ChromeDriver对象
        ChromeDriver driver = new ChromeDriver();
        //虚拟登录，初始化cookies
        init(driver);
        driver.quit();
        // 创建 ChromeOptions对象
        ChromeOptions options = new ChromeOptions();


        // 禁用CSS
        // 通过addArguments方法添加命令行参数
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        // 创建一个Map对象来存储偏好设置项
        Map<String, Object> prefs = new HashMap<>(256);
        prefs.put("permissions.default.image", 2);
        prefs.put("permissions.default.stylesheet", 2);
        options.setExperimentalOption("prefs", prefs);
        // 将Map对象传递给setExperimentalOption()方法
        ChromeDriver driver1 = new ChromeDriver(options);
        log(driver1);
        driver1.findElement(By.className("input-search")).sendKeys("周杰伦");
        driver1.findElement(By.className("btn-search")).click();
    }

    public void init(ChromeDriver driver) throws InterruptedException {
        //打开登录网页
        driver.get("https://passport.damai.cn/login?ru=https%3A%2F%2Fwww.damai.cn%2F");
        //创建窗口最大化
        driver.manage().window().maximize();
        //登录并保存cookie
        Thread.sleep(5000L);
        cookies = driver.manage().getCookies();
    }

    public void log(ChromeDriver driver) {
        //设置cookie并打开主界面
        driver.get("https://www.damai.cn/");
        cookies.forEach(cookie -> driver.manage().addCookie(cookie));
        driver.navigate().refresh();
    }
}