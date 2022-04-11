package in.wynk.pages;

import in.wynk.PageElements.CommonElements;
import in.wynk.PageElements.DashboardElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;


public class SongDashboardPage extends DriverActionUtils {

    DashboardElements dbElements ;
    HashMap<String, String> dashboardHashMapFroValidation;
    public HashMap<String, String> songAnalyticsForValidation;
    ArrayList<String> songWhichHasNoDataOnDashBoard ;

    ArrayList<String> songWithBrokenArtWork ;
    CommonElements commonEle;

    public SongDashboardPage(Reporting Reporter, in.wynk.framework.Assert Assert,
                             in.wynk.framework.SoftAssert SoftAssert, DashboardElements dbElements,
                              CommonElements commonEle)
    {
        super(Reporter, Assert, SoftAssert);
        this.dbElements  =dbElements;
        this.dashboardHashMapFroValidation = new HashMap<>();
        this.songWhichHasNoDataOnDashBoard = new ArrayList<>();
        this.commonEle = commonEle;
        this.songWithBrokenArtWork = new ArrayList<>();
        this.songAnalyticsForValidation = new HashMap<>();
    }

    public boolean waitTillFollowerButtonAppear()
    {
       WebElement e = getElementWhenClickable(dbElements.getFollowerButton(), 1000);

       if(e!=null)
        {
           return true;
        }
           return false;
    }

     public  boolean ifDasboardHeadingsPresent()
     {
        boolean b1 = checkIfElementPresent(dbElements.getArtistDashBoardHeading(),5);
        boolean b2 =  checkIfElementPresent(dbElements.getArtistDashBoardHeading_2(), 5);

         if(b1 && b2)
         {
             return true;
         }
         return false;
     }

     public boolean ifArtistNamePresent()
     {
         boolean b1 = checkIfElementPresent(dbElements.getNameOfArtistDashboard(), 5);
         if(b1)
         {
             String s = getElementWhenPresent(dbElements.getNameOfArtistDashboard()).getText();
             dashboardHashMapFroValidation.put("nameOfArtistOnDashboard", s);
             return true;
         }
         dashboardHashMapFroValidation.put("nameOfArtistOnDashboard", null);
         return false;
     }

     public boolean ifViewOnWynkButtonPresent()
     {
         return checkIfElementPresent(dbElements.getViewOnWynkButton(), 5);
     }

     public boolean clickViewOnWynkButton()
     {
         String s1 = getDriver().getWindowHandle();
         if(ifViewOnWynkButtonPresent())
         {
             click(dbElements.getViewOnWynkButton(),"View On Wynk Button", 5);
         }
         String s2 = getDriver().getWindowHandle();

         if(!s1.equalsIgnoreCase(s2))
         {
           if( getDriver().getCurrentUrl().contains(getFirstNameOfArtist()))
             {
                 return true;
             }
         }
         return false;
     }

     public String getFirstNameOfArtist()
     {
         String artistName = null;
         if(dashboardHashMapFroValidation.containsKey("nameOfArtistOnDashboard"))
         {
             String s = dashboardHashMapFroValidation.get("nameOfArtistOnDashboard");
             if(s.contains(" "))
             {
                 artistName = s.split(" ")[0].replace(" ", "");
             }
         }
         int k =9, s=10;
         for (int i=0,j=k;i<j &&j<s;i++, j++)
         {


         }
         return artistName;
     }

     public boolean isLast7DaysFilterPresent()
    {
      return checkIfElementPresent(dbElements.getLast7DaysDropButton(),5);
    }

    public String getDatesLast7DaysText()
    {
       return getElementWhenPresent(dbElements.getLast7DaysDatesTextDropDown(),5).getText().
               split("from ")[1];
    }

    public String getFollowersCount()
    {
       return getElementWhenPresent(dbElements.getTotalNumberFollowerText(), 5).getText();
    }

    public String getUniqueListenersCount()
    {
        return getElementWhenPresent(dbElements.getTotalNumberUniqueListenersText(), 5).getText();
    }

    public String getTotalStreamsCount()
    {
        return getElementWhenPresent(dbElements.getTotalNumberTotalStreamsText(), 5).getText();
    }

    public Integer totalSinglesAsPerTableHeading()
    {
      return Integer.parseInt(getElementWhenPresent(dbElements.getSinglesWithNumberText(),5)
              .getText().split("Singles ")[1].substring(1,2));
    }

    public Integer songCountOnSinglePage()
    {
        return getWebElementsList(dbElements.getTotalSongsCountPageWise()).size();
    }

    public boolean checkIfDataIsComingForAllTheSongs()
    {
        int size = songCountOnSinglePage();
        int col = 0;
        for(int i =1 ;i<=size;i++)
        {
           col = getDriver().findElements(By.xpath("//tbody[@class='ant-table-tbody']//tr["+i+"]//td")).size();
           for (int j =1 ;i<=size;i++)
            {
              String s =  getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr["+i+"]//td["+j+"]/div")).getText();
                if(s.isEmpty() || s == null)
                {
                  String d =  getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr["+i+"]//td[1]/div")).getText();
                   songWhichHasNoDataOnDashBoard.add(d);
                }
            }
        }

        if(!songWhichHasNoDataOnDashBoard.isEmpty())
        {
            for(int i=0;i<songWhichHasNoDataOnDashBoard.size();i++) {

                Reporter.log(songWhichHasNoDataOnDashBoard.get(0) +
                                " song of artist"+ dashboardHashMapFroValidation.get("nameOfArtistOnDashboard") +
                                " doesn't have data", "It should have non empty data",
                        "It is having empty data","");
            }
            return false;
        }
        return true;
    }


    public boolean isArtCoverForAllsongsPresent()
    {
        int size = songCountOnSinglePage();
        int col = 0;
        for(int i =1 ;i<=size;i++)
        {
            col = getDriver().findElements(By.xpath("//tbody[@class='ant-table-tbody']//tr["+i+"]//td")).size();
            for (int j =1 ;i<=size;i++)
            {
                String s =  getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr["+i+"]//td["+j+"]//img"))
                        .getAttribute("src");

                if(s.contains(".jpg") || s.contains(".JPEG") || s.contains(".png") || s.contains(".JPG"))
                {
                }
                else
                {
                   songWithBrokenArtWork.add(getDriver().findElement(By.
                           xpath("//tbody[@class='ant-table-tbody']//tr["+i+"]//td[1]/div")).getText());
                }
            }
        }
        if(!songWithBrokenArtWork.isEmpty())
        {
            for(int i=0;i<songWithBrokenArtWork.size();i++)
            {
                Reporter.log(songWithBrokenArtWork.get(0) +
                                " song of artist"+ dashboardHashMapFroValidation.get("nameOfArtistOnDashboard") +
                                " doesn't have data", "It should have non broken artwork",
                        "It is having broken artwork","");
            }
            return false;
        }
        return true;
    }



    public void clickLastXDaysDropDown()
    {
        click(dbElements.getLastXDaysDropButton(),"Last X Days Drop down", 5);
    }


    public void clickLast7DaysFilterInsideDropDown()
    {
        click(dbElements.getLast7DaysDatesTextDropDown(),"Last 7 Days filter inside Drop down", 5);
    }

    public void clickLast30DaysFilterInsideDropDown()
    {
        click(dbElements.getLast30DayFilterInsideDropDown(),"Last 30 Days filter inside Drop down", 5);
    }

    public void clickLast90DaysFilterInsideDropDown()
    {
        click(dbElements.getLast90DayFilterInsideDropDown(),"Last 90 Days filter inside Drop down", 5);
    }


    public boolean isLikeCountAndHTActivatedCountTabPresent()
    {

        if (getWebElementsList(dbElements.getLikesAndHTActivatesTextList()).get(0).isDisplayed() &&
                getWebElementsList(dbElements.getLikesAndHTActivatesTextList()).get(1).isDisplayed())
        {
            dashboardHashMapFroValidation.put("likesOnReleaseCount",getWebElementsList(dbElements.getLikesAndHTActivatesTextList()).get(0).getText());
            dashboardHashMapFroValidation.put("htActivatedOnReleaseCount",getWebElementsList(dbElements.getLikesAndHTActivatesTextList()).get(1).
                    getText());
            return true;
        }
        return false;
    }


    public boolean isSearchTextBoxPresent()
    {
        return  checkIfElementPresent(commonEle.getSearchBoxStudioPage() ,5);
    }



    public void clickFirstLiveSongInTable()
    {
        int size = songCountOnSinglePage();
        int col = 0;
        if(size>=1)
        {
            col = getWebElementsList(dbElements.getTotalHeader()).size();

            String header = getDriver().findElement(By.xpath("//thead[@class='ant-table-thead']//tr/th[1]")).getText();
            String value = getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr[1]//td[1]/div/p")).getText();
            songAnalyticsForValidation.put(header,value);
            for (int j =2 ;j<=col;j++)
            {

                 header = getDriver().findElement(By.xpath("//thead[@class='ant-table-thead']//tr/th["+j+"]")).getText();
                 value = getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr[1]//td["+j+"]/div")).getText();
                songAnalyticsForValidation.put(header,value);

                if(value.isEmpty() || value == null)
                {
                    String d =  getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr[1]//td[1]/div/p")).getText();
                    songWhichHasNoDataOnDashBoard.add(d);
                }
            }

            getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr[1]//td[1]")).click();
        }
    }

    public static enum HeaderName
    {
        TITLE, TOTAL_STREAMS, UNIQUE_LISTENERS, LIKES, HELLOTUNES_ACTIVATED;
    }

    public String getHeaderName(HeaderName names)
    {
        switch (names)
        {
            case TITLE:
               return "TITLE";

            case TOTAL_STREAMS:
                return "TOTAL STREAMS";

            case UNIQUE_LISTENERS:
                return "UNIQUE LISTENERS";

            case LIKES:
                return "LIKES";

            case HELLOTUNES_ACTIVATED:
                return "HELLOTUNES ACTIVATED";

        }
        return null;
    }


    public void clickFilterDropdown()
    {
        click(dbElements.getLast7DaysDatesTextDropDown(),"Last x days filter", 5);
    }




    }
