package com.codeborne.selenide;

import com.codeborne.selenide.impl.WebDriverContainer;
import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.headless;

public class WebDriverRunner {
  public static WebDriverContainer webdriverContainer = new WebDriverThreadLocalContainer();

  public static final String CHROME = "chrome";

  /**
   * @deprecated use {@link #INTERNET_EXPLORER}
   */
  @Deprecated
  public static final String IE = "ie";
  public static final String INTERNET_EXPLORER = "internet explorer";
  public static final String EDGE = "edge";
  public static final String FIREFOX = "firefox";
  public static final String LEGACY_FIREFOX = "legacy_firefox";

  /**
   * To use Safari webdriver, you need to include extra dependency to your project:
   * &lt;dependency org="org.seleniumhq.selenium" name="selenium-safari-driver" rev="2.+" conf="test-&gt;default"/&gt;
   */
  public static final String SAFARI = "safari";

  /**
   * To use HtmlUnitDriver, you need to include extra dependency to your project:
   * <dependency org="org.seleniumhq.selenium" name="selenium-htmlunit-driver" rev="2.+" conf="test->default"/>
   *
   * It's also possible to run HtmlUnit driver emulating different browsers:
   * <p>
   * java -Dbrowser=htmlunit:firefox
   * </p>
   * <p>
   * java -Dbrowser=htmlunit:chrome
   * </p>
   * <p>
   * java -Dbrowser=htmlunit:internet explorer   (default)
   * </p>
   * etc.
   */
  public static final String HTMLUNIT = "htmlunit";

  /**
   * To use PhantomJS, you need to set system property: -Dselenide.browser=phantomjs
   */
  public static final String PHANTOMJS = "phantomjs";

  /**
   * To use OperaDriver, you need to include extra dependency to your project:
   * &lt;dependency org="com.opera" name="operadriver" rev="1.5" conf="test-&gt;default"/&gt;
   */
  public static final String OPERA = "opera";

  /**
   * To use JbrowserDriver, you need to include extra dependency to your project:
   * <dependency org="com.machinepublishers" name="jbrowserdriver" rev="[0.13.0, 2.0)" conf="test-&gt;default"/&gt;
   */
  public static final String JBROWSER = "jbrowser";

  /**
   * Use this method BEFORE opening a browser to add custom event listeners to webdriver.
   * @param listener your listener of webdriver events
   */
  public static void addListener(WebDriverEventListener listener) {
    webdriverContainer.addListener(listener);
  }

  /**
   * Tell Selenide use your provided WebDriver instance.
   * Use it if you need a custom logic for creating WebDriver.
   *
   * It's recommended not to use implicit wait with this driver, because Selenide handles timing issues explicitly.
   *
   * <br>
   *
   * NB! Be sure to call this method before calling <code>open(url)</code>.
   * Otherwise Selenide will create its own WebDriver instance and would not close it.
   *
   * <p>
   * NB! When using your custom webdriver, you are responsible for closing it.
   *     Selenide will not take care of it.
   * </p>
   *
   * <p>
   * NB! Webdriver instance should be created and used in the same thread.
   *     A typical error is to create webdriver instance in one thread and use it in another.
   *     Selenide does not support it.
   *     If you really need using multiple threads, please use #com.codeborne.selenide.WebDriverProvider
   * </p>
   *
   * <p>
   * P.S. Alternatively, you can run tests with system property
   * <pre>  -Dbrowser=com.my.WebDriverFactory</pre>
   *
   * which should implement interface #com.codeborne.selenide.WebDriverProvider
   * </p>
   */
  public static void setWebDriver(WebDriver webDriver) {
    webdriverContainer.setWebDriver(webDriver);
  }

  /**
   * Get the underlying instance of Selenium WebDriver.
   * This can be used for any operations directly with WebDriver.
   */
  public static WebDriver getWebDriver() {
    return webdriverContainer.getWebDriver();
  }

  /**
   * Sets Selenium Proxy instance
   *
   * @param webProxy
   */
  public static void setProxy(Proxy webProxy) {
    webdriverContainer.setProxy(webProxy);
  }

  /**
   * Get the underlying instance of Selenium WebDriver, and assert that it's still alive.
   * @return new instance of WebDriver if the previous one has been closed meanwhile.
   */
  public static WebDriver getAndCheckWebDriver() {
    return webdriverContainer.getAndCheckWebDriver();
  }

  /**
   * Get selenide proxy. It's activated only if Configuration.proxyEnabled == true
   * @return null if proxy server is not started
   */
  public static SelenideProxyServer getSelenideProxy() {
    return webdriverContainer.getProxyServer();
  }

  static SelenideDriver getSelenideDriver() {
    return webdriverContainer.getSelenideDriver();
  }

  /**
   * Close the browser if it's open
   */
  public static void closeWebDriver() {
    webdriverContainer.closeWebDriver();
  }

  /**
   * @return true iff instance of Selenium WebDriver is started in current thread
   */
  public static boolean hasWebDriverStarted() {
    return webdriverContainer.hasWebDriverStarted();
  }

  private static Browser browser() {
    return new Browser(browser, headless);
  }

  /**
   * Is Selenide configured to use Firefox browser
   */
  public static boolean isFirefox() {
    return browser().isFirefox();
  }

  /**
   * Is Selenide configured to use legacy Firefox driver
   */
  public static boolean isLegacyFirefox() {
    return browser().isLegacyFirefox();
  }

  /**
   * Is Selenide configured to use Chrome browser
   */
  public static boolean isChrome() {
    return browser().isChrome();
  }

  /**
   * Is Selenide configured to use Internet Explorer browser
   */
  public static boolean isIE() {
    return browser().isIE();
  }

  /**
   * Is Selenide configured to use Microsoft EDGE browser
   */
  public static boolean isEdge() {
    return browser().isEdge();
  }

  /**
   * Is Selenide configured to user Safari browser
   */
  public static boolean isSafari() {
    return browser().isSafari();
  }

  /**
   * Is Selenide configured to use headless browser (HtmlUnit or PhantomJS)
   */
  public static boolean isHeadless() {
    return browser().isHeadless();
  }

  /**
   * Does this browser support "alert" and "confirm" dialogs.
   */
  public static boolean supportsModalDialogs() {
    return browser().supportsModalDialogs();
  }

  /**
   * Does this browser support javascript
   */
  public static boolean supportsJavascript() {
    return hasWebDriverStarted() && getWebDriver() instanceof JavascriptExecutor;
  }

  /**
   * Is Selenide configured to use HtmlUnit browser
   */
  public static boolean isHtmlUnit() {
    return browser().isHtmlUnit();
  }

  /**
   * Is Selenide configured to use PhantomJS browser
   */
  public static boolean isPhantomjs() {
    return browser().isPhantomjs();
  }

  /**
   * Is Selenide configured to use Opera browser
   */
  public static boolean isOpera() {
    return browser().isOpera();
  }

  /**
   * Is Selenide configured to use JBrowser browser
   */
  public static boolean isJBrowser() {
    return browser().isJBrowser();
  }

  /**
   * Delete all the browser cookies
   */
  public static void clearBrowserCache() {
    webdriverContainer.clearBrowserCache();
  }

  /**
   * @return the source (HTML) of current page
   */
  public static String source() {
    return webdriverContainer.getPageSource();
  }

  /**
   * @return the URL of current page
   */
  public static String url() {
    return webdriverContainer.getCurrentUrl();
  }

  /**
   * @return the URL of current frame
   */
  public static String currentFrameUrl() {
    return webdriverContainer.getCurrentFrameUrl();
  }
}
