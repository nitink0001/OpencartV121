package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass {

	
	//3rd col - given data is valid or invalid i know this 100%, b/c i registered this 4 account on this website for practice purpose
	//4th col - in application it is logedin or not
	//5th col - if, given valid data = logedin --> test is passed
	//          if, given invalid data = not logedin --> test is passed
	//
	//          if, given valid data = not logedin --> test is failed
	//          if, given valid data =logedin --> test is failed
	
	//in master.xml file when i run, all 5 row data of excel., xlsx file is get passed., test case is passed,   b/c in front of valid row., loggedin data i put Valid  and
	//                                                                                                              in front of invalid row., not loggedin data i put Invalid
	
	
	
	
	// here we can take positive and negative test cases
	
	
	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="Datadriven")// getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp) throws InterruptedException
	{
		logger.info("**** Starting TC_003_LoginDDT ****");


		try
        {
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//Login
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			//MyAccount
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists();

			// Data is valid -- login success -- test pass -- logout
			//               -- login failed  -- test fail
			//
			//Data is invalid -- login success -- test fail -- logout
			//                -- login failed  -- test pass   

			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(true); //  test pass
				}
				else
				{
					Assert.assertTrue(false);
				}    	
			}

			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false); //  test fail
				}
				else
				{
					Assert.assertTrue(true);
				}    	
			}
        }
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		
	    logger.info("**** Finished TC_003_LoginDDT ****");

	}
}