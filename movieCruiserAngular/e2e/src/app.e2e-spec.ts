import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';

describe('MovieCruiserFrontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MovieCruiser');
  });

  
  it('should be redirected to /login on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click()
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('Super user');
    browser.element(by.id('lastName')).sendKeys('Super lastUser');
    browser.element(by.id('userId')).sendKeys('Super User12');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.id('register-user')).click();
    page.navigateTo();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login user and navigate to popular movies', () => {
    browser.element(by.id('userId')).sendKeys('Super User12');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.id('login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/popular');
  });

  it('should be able to search for movies', () => {
    browser.element(by.id('search-button')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/search');
    browser.element(by.id('search-button-input')).sendKeys('Super');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems=element.all(by.id('movie-title'));
    expect(searchItems.count()).toBe(20);
    for(let i=0;i<1;i+=1){
      expect(searchItems.get(1).getText()).toContain('Super');
    }
  });

  it('should be able to add movie to watchlist', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems=element.all(by.id('movie-thumbnail'));
    expect(searchItems.count()).toBe(20);
    searchItems.get(0).click();
    browser.element(by.id('addButton')).click();
  });
});
