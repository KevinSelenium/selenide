package com.codeborne.selenide;

import static com.codeborne.selenide.WebDriverRunner.CHROME;
import static com.codeborne.selenide.WebDriverRunner.EDGE;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;
import static com.codeborne.selenide.WebDriverRunner.HTMLUNIT;
import static com.codeborne.selenide.WebDriverRunner.INTERNET_EXPLORER;
import static com.codeborne.selenide.WebDriverRunner.JBROWSER;
import static com.codeborne.selenide.WebDriverRunner.LEGACY_FIREFOX;
import static com.codeborne.selenide.WebDriverRunner.OPERA;
import static com.codeborne.selenide.WebDriverRunner.PHANTOMJS;
import static com.codeborne.selenide.WebDriverRunner.SAFARI;
import static com.codeborne.selenide.WebDriverRunner.IE;

public class Browser {
  public final String name;
  public final boolean headless;

  public Browser(String name, boolean headless) {
    this.name = name;
    this.headless = headless;
  }

  public boolean isHeadless() {
    return isHtmlUnit() || isPhantomjs() || headless;
  }

  public boolean isChrome() {
    return CHROME.equalsIgnoreCase(name);
  }

  public boolean isFirefox() {
    return FIREFOX.equalsIgnoreCase(name);
  }

  public boolean isLegacyFirefox() {
    return LEGACY_FIREFOX.equalsIgnoreCase(name);
  }

  public boolean isIE() {
    return INTERNET_EXPLORER.equalsIgnoreCase(name) || IE.equalsIgnoreCase(name);
  }

  public boolean isEdge() {
    return EDGE.equalsIgnoreCase(name);
  }

  public boolean isSafari() {
    return SAFARI.equalsIgnoreCase(name);
  }

  public boolean isHtmlUnit() {
    return name != null && name.startsWith(HTMLUNIT);
  }

  public boolean isPhantomjs() {
    return PHANTOMJS.equalsIgnoreCase(name);
  }

  public boolean isOpera() {
    return OPERA.equalsIgnoreCase(name);
  }

  public boolean isJBrowser() {
    return JBROWSER.equalsIgnoreCase(name);
  }

  public boolean supportsModalDialogs() {
    return !isPhantomjs() && !isSafari();
  }
}
