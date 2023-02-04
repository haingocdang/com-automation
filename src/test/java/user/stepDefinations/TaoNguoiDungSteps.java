package user.stepDefinations;

import commons.GlobalConstants;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import pageObjects.mercury.TaoNguoiDungPageObject;
import pageUIs.alpaca.CommonPageUI;
import commons.VerifyHelper;
import cucumber.api.java.en.When;
import cucumberOption.Hooks;

public class TaoNguoiDungSteps {

	WebDriver driver;
	VerifyHelper verify;
	TaoNguoiDungPageObject taoNguoiDungPage;

	public TaoNguoiDungSteps() {
		driver = Hooks.openAndQuitBrowser();
		verify = VerifyHelper.getData();
		taoNguoiDungPage = PageGeneratorManager.getTaoNguoiDungPage(driver);
	}

	/*@When("^I click to Tao Moi button$")
	public void iClickToTaoMoiButton() {
		taoNguoiDungPage.clickToElement(driver, CommonPageUI.TAO_MOI_BUTTON);

	}*/

	@When("^I input Ten Dang Nhap with value \"([^\"]*)\"$")
	public void iInputTenDangNhapWithValue(String tenDangNhap) {
		taoNguoiDungPage.nhapTenDangNhap(tenDangNhap);
	}

	@When("^I input Ho Va Ten with value \"([^\"]*)\"$")
	public void iInputHoVaTenWithValue(String hoVaTen) {
		taoNguoiDungPage.nhapHoVaTen(hoVaTen);

	}

	@When("^I input Email with value \"([^\"]*)\"$")
	public void iInputEmailWithValue(String email) {
		taoNguoiDungPage.nhapEmail(email);

	}

	@When("^I select \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void iSelectWithValue(String tenSelectBox, String giaTriChon) {
		taoNguoiDungPage.chonDonVi(tenSelectBox, giaTriChon);

	}


}
