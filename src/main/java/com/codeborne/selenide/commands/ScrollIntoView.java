package com.codeborne.selenide.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.WebElement;

public class ScrollIntoView implements Command<WebElement> {
  @Override
  public WebElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) {
    String param = args[0].toString();
    locator.context().executeJavaScript("arguments[0].scrollIntoView(" + param + ")", proxy);
    return proxy;
  }
}