import java.util.*;
public class SignificantFiguresCalculator {
	private static ArrayList<String> copies = new ArrayList<String>();
	private static int rand;
	private static int randLength;
	private static int randNum;
	public static void main (String[]args){
		Scanner console = new Scanner(System.in);
		intro();
		String number = console.nextLine();
		while (number.equals("") || number.equals(" ") || number.equals("0") || !(isNumber(number))){
			System.out.print("Enter a non-zero number: ");
			number = console.nextLine();
		}
		while (number.contains(" "))
			number = remove(number,number.indexOf(" "));
		if (number.toUpperCase().equals("TEST")){
			System.out.print("How many numbers would you like to be tested on? ");
			int tests = console.nextInt();
			long lStartTime = System.nanoTime();
			if (tests > 0){
				testNumber();
				long lEndTime = System.nanoTime();
				long difference = lEndTime - lStartTime;
				System.out.println("Elapsed milliseconds: " + difference/10000);
			}
		}
		else
			System.out.println("Your number has " + calculateSigFigs(number) + " Significant Figures.");
	}

	public static String testNumber (){
		String num = "";
		rand = (int)(Math.random()*5+1);;
		do{
			//1 & 2
			if (rand == 1 || rand == 2){
				randLength = (int)(Math.random()*5+1);
				randNum = (int)(Math.random()*randLength+1);
				num= "";
				for (int x = 0; x < randNum;x++){
					num+= "" + (int)(Math.random()*9+1);
				}
				for (int x = 0; x < randLength-randNum;x++) {
					num+= "0";
				}
				if (rand == 2){
					num+=".";
				}
			}
			//3 & 4
			else if (rand == 3 || rand == 4){
				randLength = (int)(Math.random()*5+1);
				randNum = (int)(Math.random()*randLength+1);
				num= "";
				for (int x = 0; x < randNum;x++){
					num+= "" + (int)(Math.random()*9+1);
				}
				num += ".";
				for (int x = 0; x < randLength-randNum;x++) {
					num+= "" + (int)(Math.random()*10);
				}
				if (rand == 4){
					randLength = (int)(Math.random()*2+1);
					if (randLength == 1){
						num+=" x 10^";
					}
					else {
						num+=" e ";
					}
					num+= "" + (int)(Math.random()*50);
				}
			}
			//5
			else {
				randLength = (int)(Math.random()*5+1);
				randNum = (int)(Math.random()*randLength+1);
				num= ".";
				for (int x = 0; x < randNum;x++){
					num+= "0";
				}
				for (int x = 0; x < randLength-randNum+1;x++) {
					num+= "" + (int)(Math.random()*9+1);
				}

			}
		}while (copies.contains(num));
		copies.add(num);
		System.out.println(num);
		return num;
	}

	public static int calculateSigFigs (String number){
		int length = number.length()-1;
		if (number.contains("x") && number.contains("10^")){
			number = number.substring(0,number.indexOf("x"));
			if (number.contains(".")){
				number = remove(number,number.indexOf("."));
			}
			length = number.length()-1;
			while (number.charAt(length) == '0'){
				number = remove(number, length);
				length = number.length()-1;
			}
			return number.length();
		}
		if (number.contains("e")){
			number = number.substring(0,number.indexOf("e"));
			if (number.contains(".")){
				number = remove(number,number.indexOf("."));
			}
			length = number.length()-1;
			while (number.charAt(length) == '0'){
				number = remove(number, length);
				length = number.length()-1;
			}
			return number.length();
		}
		if (number.charAt(0) == '0' || number.charAt(0) == '.'){
			while (number.charAt(0) == '0' || number.charAt(0) == '.'){
				number = remove(number,0);
			}
			return number.length();
		}
		else if (number.charAt(0) != '0' && number.contains(".")){
			return remove(number, number.indexOf(".")).length();
		}
		else if (number.charAt(0) != '0' && number.charAt(number.length()-1) != '0'){
			return number.length();
		}
		while (number.charAt(length) == '0'){
			number = remove(number, length);
			length = number.length()-1;
		}
		return number.length();
	}

	public static void intro (){

		System.out.println("This is a Significant Figures Calculator.");
		System.out.println("Available input formats are:");
		System.out.println("Integer, Double, and Scientific Notation (Use this format: Number x 10^N)\n");
		System.out.print("Enter a non-zero number or type TEST if you want a test: ");
	}
	public static boolean isNumber (String s){
		if ((s.contains("x") && s.contains("10^")) || s.contains("e") || s.toUpperCase().equals("TEST")){
			if (s.contains("e")){
				String temp = removeZADs(s.substring(s.indexOf("e")+1));
				s = removeZADs(s.substring(0,s.indexOf("e")));
				if (isNumber(s) && isNumber(temp))
					return true;
				return false;
			}
			return true;
		}
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	//Removes Zeros And Decimals from the last and first positions
	public static String removeZADs (String a){
		int length = a.length()-1;
		while (a.charAt(length) == '0'){
			a = remove(a, length);
			length = a.length()-1;
		}
		while (a.charAt(0) == '0' || a.charAt(0) == '.'){
			a = remove(a,0);
		}
		return a;
	}
	public static String remove (String a, int i){
		return a.substring(0,i) + a.substring(i+1);
	}
}