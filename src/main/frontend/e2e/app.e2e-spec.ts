import { G2SellerWebClientPage } from './app.po';

describe('g2-seller-web-client App', function() {
  let page: G2SellerWebClientPage;

  beforeEach(() => {
    page = new G2SellerWebClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('gs works!');
  });
});
