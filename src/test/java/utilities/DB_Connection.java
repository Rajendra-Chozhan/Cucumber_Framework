//package utilities;
//
//import basepackage.BaseClass;
//import org.openqa.selenium.By;
//import java.sql.DriverManager;
//import java.sql.*;
//public class DB_Connection extends BaseClass {
//
//
//
//    public static void main(String[] args) throws Exception {
//        // Step 1: Open browser and go to app
////
////        driver.get("https://example-employee-app.com/login");
////
////        driver.findElement(By.id("username")).sendKeys("john.doe");
////        driver.findElement(By.id("password")).sendKeys("Test@123");
////        driver.findElement(By.id("login-button")).click();
////
////        Thread.sleep(2000); // Use WebDriverWait for real use
////
////        // Step 2: Get name from UI
////        String uiFullName = driver.findElement(By.id("employee-full-name")).getText();
////        System.out.println("UI Name: " + uiFullName);
//
//        // Step 3: Connect to SQL Server DB
//        String url = "jdbc:sqlserver://localhost:1433;" +
//                "databaseName=master;" +
//                "integratedSecurity=true;" +
//                "encrypt=true;" +
//                "trustServerCertificate=true;";
//
//        String user = "CHOZHAN\\Rajendra Chozhan";
//        String password = "121600";
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        Connection conn = DriverManager.getConnection(url);
//
//        // Step 4: Execute query
//        String newquery = "SELECT * FROM kunden";
//        java.sql.PreparedStatement ps = conn.prepareStatement(newquery);
//        ResultSet rs = ps.executeQuery(newquery);
//
//        // Print column names
//        for (int i = 1; i <= columnCount; i++) {
//            System.out.print(metaData.getColumnName(i) + "\t");
//        }
//        System.out.println("\n-----------------------------------------");
//
//        // Print all rows
//        while (rs.next()) {
//            for (int i = 1; i <= columnCount; i++) {
//                String value = rs.getString(i);
//                System.out.print((value != null ? value : "NULL") + "\t");
//            }
//            System.out.println();
//        }
//
//
//        // Step 5: Compare UI and DB
////        if (uiFullName.equals(dbFullName)) {
////            System.out.println("✅ Match! UI and DB full names are the same.");
////        } else {
////            System.out.println("❌ Mismatch! UI: " + uiFullName + " | DB: " + dbFullName);
////        }
//
//        // Clean up
//        rs.close();
//        stmt.close();
//        conn.close();
//
//    }
//
//}
