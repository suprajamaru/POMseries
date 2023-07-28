package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.frameworkexception.FrameworkException;



public class ElementUtil {
			WebDriver driver;
		 static final int DEFAULT_TIMEOUT=15;

		public ElementUtil(WebDriver driver) {
			this.driver = driver;
		}
		public WebElement getElement(By locator,int timeout) {
			return waitForElementVisible(locator,timeout);
		}

		public WebElement getElement(By locator) {
			WebElement element = null;
			try {
				element = driver.findElement(locator);
				System.out.println("Element is  found with locator" + locator);
			} catch (NoSuchElementException e) {
				System.out.println("Element is not  found with locator" + locator);
					element =waitForElementVisible(locator,DEFAULT_TIMEOUT);
				}
				return element;
		}

		public void doSendkeys(By locator,String value) {
			if (value == null) {
				System.out.println("null values are not allowed");
				throw new FrameworkException("VALUECANNOTBENULL");
			}
			doClear(locator);

			getElement(locator).sendKeys(value);
		}

		public void doClear(By locator) {
			getElement(locator).clear();
		}

		public void doClick(By locator) {
			getElement(locator).click();
		}

		public String getElementText(By locator) {
			return getElement(locator).getText();
		}

		public Boolean CheckElementIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		}

		public String doGetsingleAttributeValue(By locator, String attrname) {
			return getElement(locator).getAttribute(attrname);
		}

		public List<WebElement> getElements(By locator) {
			return driver.findElements(locator);
		}

		public int getElementsCount(By locator) {
			return getElements(locator).size();
		}

		public List<String> getAttributeValue(By locator, String attrname) {
			List<WebElement> elelist = getElements(locator);
			List<String> eleattvaluelist = new ArrayList();
			for (WebElement e : elelist) {
				String attvalue = e.getAttribute(attrname);
				eleattvaluelist.add(attvalue);

			}
			return eleattvaluelist;

		}

		public List<String> getElementsTextList(By locator) {
			List<WebElement> elementslinkslist = getElements(locator);
			List<String> eletextlist = new ArrayList<String>();
			for (WebElement e : elementslinkslist) {
				String text = e.getText();
				// System.out.println(text);
				eletextlist.add(text);
			}
			return eletextlist;
		}

		public void clickElementfrompagesection(By locator, String eletext) {
			List<WebElement> elementlist = getElements(locator);
			for (WebElement e : elementlist) {
				String text = e.getText();
				// System.out.println(text);
				if (text.equals(eletext)) {
					e.click();
					break;
				}
			}
		}

		public void search(By searchlocator, String seachkey, By suggestionslocator, String suggestionname)
				throws InterruptedException {
			doSendkeys(searchlocator, seachkey);
			Thread.sleep(3000);
			List<WebElement> suggetionlist = getElements(suggestionslocator);
			System.out.println(suggetionlist.size());
			if (suggetionlist.size() > 0) {
				for (WebElement e : suggetionlist) {
					String text = e.getText();
					if (text.length() > 0) {
						System.out.println(text);
						if (text.contains(suggestionname)) {
							e.click();
							break;
						}
					} else {
						System.out.println("blank values--no suggestions");
						break;
					}
				}
			} else {
				System.out.println("no search suggestions found");
			}
		}

		public Boolean IsElementDisplayed(By locator) {
			List<WebElement> elelist = getElements(locator);
			if (elelist.size() > 0) {
				System.out.println(locator + "element is present");
				return true;
			}
			return false;
		}
	//********************DropDown Utils*********************

		public void doslectByindex(By locator, int index) {
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
		}

		public void doslectByVistbile(By locator, String text) {
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(text);
			;
		}

		public void doslectByValue(By locator, String Value) {
			Select select = new Select(getElement(locator));
			select.selectByValue(Value);
		}

		public int getDropDownValueCount(By locator) {
			return getAllDrodownOptions(locator).size() - 1;
		}

		public List<String> getAllDrodownOptions(By locator) {
			Select select = new Select(getElement(locator));
			List<WebElement> Optionslist = select.getOptions();
			List<String> OptionvalueList = new ArrayList();
			//System.out.println("total options:" + (Optionslist.size() - 1));
			for (WebElement e : Optionslist) {
				String text = e.getText();
				System.out.println(text);
				OptionvalueList.add(text); 

			}
			return OptionvalueList;
		}

		public Boolean doSelectDropDownValue(By locator, String dropdownvalue) throws InterruptedException {
			Select select = new Select(getElement(locator));
			List<WebElement> Optionslist = select.getOptions();
			Boolean flag = false;
			System.out.println("total options:" + (Optionslist.size() - 1));
			for (WebElement e : Optionslist) {
				String text = e.getText();
				System.out.println(text);
				if (text.contains(dropdownvalue)) {
					flag = true;
					Thread.sleep(3000);
					e.click();
					break;
				}
			}
			if (flag == false) {
				System.out.println(dropdownvalue + "is not present in" + locator);
			}
			return flag;
		}
	//for single and multiple selection of choices
	public  Boolean SelectChoices(By locator, String...value) {
		List<WebElement>Optionslist = getElements(locator);
		System.out.println(Optionslist.size());
		Boolean flag = false;
		if(!value[0].equalsIgnoreCase("all")) {
		for (WebElement e : Optionslist) {
			String text = e.getText();
			System.out.println(text);
			for(int i=0;i<value.length;i++) {
				if (text.equals(value[i])){
							flag = true;
							e.click();
							break;
				}
			}
			
		}
		
		}
		else {
			for(WebElement e:Optionslist) {
				String text=e.getText();
				if(!(text.equals("−"))) {
					flag=true;
					e.click();
				}
				
			}
	}
		if (flag == false) {
			System.out.println(value + "is not present in dropdown");
		}
		return flag;

	}
	//***************Actions class****************************
	public void doActionsClick(By locator) {
		Actions act=new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	public void doActionsClick(By locator,int timeout) {
		Actions act=new Actions(driver);
		act.click(waitforElementtobeclickable(locator,timeout)).build().perform();
	}
	public void DrogandDrop(By sourcelocator,By targetlocator) {
		Actions action=new Actions(driver);
		action.dragAndDrop(getElement(sourcelocator),getElement(targetlocator)).build().perform();
		
	}
	public void rightclickaction(By locator) {
		Actions action=new Actions(driver);
		action.contextClick(getElement(locator)).build().perform();
		 
	}
	public void twolevelmenuhandle(By parentmenu,By childmenu) throws InterruptedException {
	WebElement parentele=getElement(parentmenu);
	Actions action=new Actions(driver);
	action.moveToElement(parentele).build().perform();
	Thread.sleep(2000);
	doClick(childmenu);
		
		}
	public  void Twolevelmenuhandle(By parentmenu,String childmenulinktext) throws InterruptedException {
	WebElement parentele=getElement(parentmenu);
	Actions action=new Actions(driver);
	action.moveToElement(parentele).build().perform();
	Thread.sleep(2000);
	doClick(By.linkText(childmenulinktext));
		}
	public void Multilevelmenu(By parentmenu,String level1,String level2,String level3) throws InterruptedException {
		Actions act=new Actions(driver);
		WebElement parentmenuele=getElement(parentmenu);
		act.moveToElement(parentmenuele).build().perform();
		Thread.sleep(4000);
		WebElement level1ele=getElement(By.linkText(level1));
		act.moveToElement(level1ele).build().perform();
		Thread.sleep(4000);
		WebElement level2ele=getElement(By.linkText(level2));
		act.moveToElement(level2ele).build().perform();
		Thread.sleep(4000);
		doClick(By.linkText(level3));

	}
	//*************wait methods****************************
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does notnecessarily 
	 * mean that the element is visible.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public  WebElement waitForElementPresence(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return	wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	public  List<WebElement> waitForAllElementsPresence(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}
	/**
	 * An expectation for checking that an element is present on the DOM
	 *  of a page and visible.Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param firstname
	 * @param timeout
	 * @return
	 */

	public  WebElement waitForElementVisible(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	public  List<WebElement>waitForAllElementsVisible(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	public  WebElement waitforElementtobeclickable(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	public  void clickElementWhenReady(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
	public  Alert waitforAlertJsPopup(int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	public  String alertJsGettext(int timeout) {
		return waitforAlertJsPopup(timeout).getText();
	}
	public  void  alertaccept(int timeout) {
	waitforAlertJsPopup(timeout).accept();;
	}
	public  void  alertdismiss(int timeout) {
	waitforAlertJsPopup(timeout).dismiss();;
	}
	public  void  EnteralertValue(int timeout,String value) {
	waitforAlertJsPopup(timeout).sendKeys(value);;;
	}

	public  String waitfortitlecontains(String titlefraction,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.titleContains(titlefraction))) {
			  String  title1= driver.getTitle();
			   System.out.println(title1);
			   return title1;
		   }
		   else {
			   System.out.println("title is not found within the given timeout"+timeout);
			   return null;
		   }
	}
	public String waitfortitleandCapture(String title,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		   if(wait.until(ExpectedConditions.titleIs(title))) {
			  String  title1= driver.getTitle();
			   System.out.println(title1);
			   return title1;
		   }
		   else {
			   System.out.println("title is not found within the given timeout"+timeout);
			   return null;
		   }
		   }

	public  String waitforUrlcontains(String urlfraction,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		  if(wait.until(ExpectedConditions.urlContains(urlfraction))) {
			 String url= driver.getCurrentUrl();
			return url;
		  }
		  else {
			  System.out.println("url is not present within the given timeout"+timeout);
			  return null;
		  }
	}
	public  String waitforUrl(String url,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		  if(wait.until(ExpectedConditions.urlToBe(url))) {
			 String url1= driver.getCurrentUrl();
			return url1;
		  }
		  else {
			  System.out.println("url is not present within the given timeout"+timeout);
			  return null;
		  }
	}
	public  void waitforFrameandSwitchtoItbyNameorId(By idorname,int timeout) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorname));
	    }
	public  void waitforFrameandSwitchtoItbyIndex(int index,int timeout) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	    }
	public void waitforFrameandSwitchtoItbyLocator(By locator,int timeout) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	    }
	public void waitforFrameandSwitchtoItbyFrameElement(WebElement frameelement,int timeout) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameelement));
	    }

	}




