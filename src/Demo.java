import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
	
	public static void main(String[] args) {
		ArrayList<User> clientsList = new ArrayList<User>();

		Scanner sc = new Scanner(System.in);

		showMainMenu();
		System.out.println("Enter you choice: ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			registration(clientsList);
			break;
		case 2:
			logIn(clientsList);
			break;
		case 3:
			break;

		}
		
	}
	
	// При стартиране на програмата
	static void showMainMenu(){
		System.out.println("============== MENU ===============");
		System.out.println("1. Create new profile");
		System.out.println("2. Log in");
		System.out.println("3. Exit");
	}
	
	// Менюто, което се показва когато някой се логне
	static void showClientMenu(){
		System.out.println("===========YOUR MENU==========");
		System.out.println("1. View your favourites pics");
		System.out.println("2. View your pics");
		System.out.println("3. Exit");
		
	}
	
	// Отделих действията с Client менюто за по-голяма четимост на кода;
	static void clientMenuChoiceAction(User p){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice: ");
		int choice = sc.nextInt();
		switch(choice){
		case 1:
			p.showFavourites();
			break;
		case 2:
			p.showMyPics();
			break;
		case 3:
			break;
		}
	}
	
	//При регистрация
	static void registration(ArrayList<User>list){
		String firstName;
		String lastName;
		String email;
		String password;
		
		Scanner sc = new Scanner(System.in);
		firstName = sc.nextLine();
		lastName = sc.nextLine();
		do{
			System.out.println("Enter your email: ");
			email = sc.next();
		}while(!usernameisFree(email, list) && !emailValidate(email));
		do{
			System.out.println("You pass must be secure! It must contains lower case, upper case and number "
					+ "\nand must be between 5 and 20 symbols");
			System.out.println("Enter you password: ");
			password = sc.next();
		}while(!checkForSecurePassword(password));
		
		list.add(new User(firstName, lastName, email, password));
		System.out.println("Your account is created!!!");
		sc.close();
	}
	
	static void logIn(ArrayList<User> list){
		Scanner sc = new Scanner(System.in);
		String username;
		String password;
		System.out.println("Username: ");
		username = sc.nextLine();
		System.out.println("Password: ");
		password = sc.nextLine();
		
		if(!list.isEmpty()){
			for(User c : list){
				if(c.getUsername().equals(username)){
					if(c.getPass().equals(password)){
						System.out.println("Welcome!");
						showClientMenu();
						clientMenuChoiceAction(c);
					}
					else 
						System.out.println("Invalid password");
				}
				else
					System.out.println("Invalid username");
			}
		}
		else{
			System.out.println("No accounts exist");
		}
		sc.close();
	}
	
	// Проверка дали даденото име е свободно
	private static boolean usernameisFree(String username, ArrayList<User> list){
		for(User c : list){
			if(c.getUsername().equals(username)){
				System.out.println("This username is not free!");
				return false;
			}
		}
		return true;
	}
	
	// Проверка дали паролата е силна
	private static boolean checkForSecurePassword(String password){
		if(password.length()>=5 && password.length()<=20){
			return (checkForNumber(password) && checkForUpperCase(password) && checkForLowerCase(password));
		}
		return false;
	}
	
	private static boolean checkForNumber(String anyString){
		for(int i = 0; i<anyString.length(); i++){
			if(anyString.charAt(i)>='0' && anyString.charAt(i)<='9'){
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkForUpperCase(String anyString){
		for(int i = 0; i<anyString.length(); i++){
			if(anyString.charAt(i)>='A' && anyString.charAt(i)<='Z'){
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkForLowerCase(String anyString){
		for(int i = 0; i<anyString.length(); i++){
			if(anyString.charAt(i)>='a' && anyString.charAt(i)<='z'){
				return true;
			}
		}
		return false;
	}
	
	//e-mail validation
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public static boolean emailValidate(String emailStr) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		        return matcher.find();
		}
	
}
