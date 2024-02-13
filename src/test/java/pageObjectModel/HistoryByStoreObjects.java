package pageObjectModel;

public class HistoryByStoreObjects {

public static String loginURL = "https://app.tryloop.ai/login/password";

public static String email = "//h3[text() = 'Email']/following-sibling::div/div/input";

public static String password = "//h3[text() = 'Password']/following-sibling::div/div/input";

public static String loginBtn = "//button[text() = 'Login']";

public static String emailToPhnPopUp = "//button[contains(text() ,'Skip for now')]";

public String ChargeBacks = "//span[text()='3P Chargebacks']";

public String HistoryByStore = "//span[contains(text(),'History by Store')]";

public String ReversalTable = "//table";
}
