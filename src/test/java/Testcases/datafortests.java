package Testcases;


import groovy.lang.GString;
import org.testng.annotations.DataProvider;

public class datafortests {

    @DataProvider(name = "DataForPost")
    public Object[][] data_for_post() {
        return new Object[][]{
                {"Benny", "Bell"},
                {"jerry", "june"}
        };
    }

    @DataProvider(name = "DataForPut")
    public Object[][] data_for_put() {

        return new Object[][]{
                {"cerulean", "roy"},
                {"fuchsia", "rose"}
        };
    }

    @DataProvider(name = "pagenumbers")
    public Object[][] getPageNumbers() {
        return new Object[][]{{1}, {2}, {3}}; // Add more page numbers as needed
    }

    @DataProvider(name = "userIds")
    public Object[][] getUserIds() {
        return new Object[][]{
                {1},
                {2},
                {3}
        };

    }
}




