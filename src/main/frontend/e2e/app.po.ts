import { browser, element, by } from 'protractor';

export class G2SellerWebClientPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('gs-root h1')).getText();
  }
}
