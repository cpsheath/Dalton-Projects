package name_assignment;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/* @author Celia Heath
 * @date 12/10/15
 * 
 * Details:
 * Asks user for their name and allows them to do 12 different things with their name.
 */

public class nameassignment {

	public static void main(String[] args) {

		//declarations:
		Scanner scan = new Scanner(System.in);
		int menu = 0;
		String name = "";

		//welcome and ask user for name:
		System.out.println("Hello, please type in your first name, middle name(s), and last name.");
		name = scan.nextLine();

		//convert name into char array:
		char[] namearray = name.toCharArray();

		//ask what user would like to do with their name and print menu:
		System.out.println("Hi, " + name + ", here is what you can do with your name:");
		System.out.println("1. Reverse name. \n2. Number of vowels.\n3. Consonant frequency.\n4. First name.\n5. Last name.\n6. Middle name(s).\n7. Hyphen or no hyphen.\n8. Convert to lowercase.\n9. Convert to uppercase.\n10. Mix up name.\n11. Last name palindrome or not.\n12. Initials.");

		//loop for asking the user what they want to do with their name
		while(true){
			System.out.println("What would you like to do with your name? (type the corresponding number)");

			while(true) {
				try {
					menu = scan.nextInt();
					break;
				} catch(InputMismatchException e) {
					System.out.println("that is not a number, please try again");
					scan = new Scanner(System.in);
				}
			}

			if (menu == 1){
				System.out.print("Here is your name backwards: "); reverseAndPrintArray(namearray);
			}
			else if(menu == 2){
				System.out.println("You have " + numberVowels(namearray) + " vowels in your name.");
			}
			else if(menu == 3){
				System.out.println("You have this many of a certain consanant in your name: " + consonantFrequency(namearray, 'l'));	
			}
			else if(menu == 4){
				System.out.print("Your first name is "); System.out.print(returnFirstName(namearray)); System.out.println(".");
			}
			else if (menu == 5){
				System.out.print("Your last name is "); System.out.print(returnLastName(namearray)); System.out.println(".");
			}
			else if (menu == 6){
				System.out.print("Your middle name(s) is "); System.out.print(returnMiddleName(namearray)); System.out.println(".");
			}
			else if(menu == 7){
				System.out.print("It is "); System.out.print(lastnameHyphen(namearray)); System.out.println(" that you have a hyphen in your last name.");
			}
			else if (menu == 8){
				System.out.print("Here is your lowercase name: "); convertLowercase(namearray);
			}
			else if (menu == 9){
				System.out.print("Here is your uppercase name: "); convertUppercase(namearray);
			}
			else if (menu == 10){
				System.out.print("Here is your name mixed up: "); randomizeName(namearray);
			}
			else if (menu == 11){
				System.out.print("It is "); System.out.print(firstNamePalindrome(namearray)); System.out.println(" that your name is a palindrome.");
			}
			else if (menu == 12){
				System.out.print("Here are your initials: ");System.out.println(initialsFromName(namearray));
			}
		} //end of while

	} //end of main

	//1) Reverse and display array.
	public static void reverseAndPrintArray(char[] array){
		for (int i = array.length-1; i >= 0; i--) {
			System.out.print(array[i]);
		}	
		System.out.println();
	}

	//2) Determine the number of vowels.
	public static int numberVowels(char[] array){
		int vowelcount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 'a' || array[i] == 'A'){
				vowelcount++;
			}
			else if (array[i] == 'e' || array[i] == 'E'){
				vowelcount++;
			}
			else if (array[i] == 'i' || array[i] == 'I'){
				vowelcount++;
			}
			else if (array[i] == 'o' || array[i] == 'O'){
				vowelcount++;
			}
			else if (array[i] == 'u' || array[i] == 'U'){
				vowelcount++;
			}
		}
		return vowelcount;
	}

	//3) Consonant frequency. Bonus: overload this method to return an array of the subtotals of EACH consonant.
	public static int consonantFrequency(char[] array, char consonant){
		int consonantcount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == consonant){
				consonantcount++;
			}
		}
		return consonantcount;
	}

	//4) Return first name.
	static char[] returnFirstName(char[] array){
		int a = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == ' ')
			{
				a = i;
				break;
			}
		}	
		char[] array2 = new char[a];
		for (int i = 0; i < array2.length; i++) {
			array2[i] = array[i];
		}
		return array2;
	}

	//5) Return last name.
	public static char[] returnLastName(char[] array) {
		int b = 0;
		for (int i = array.length-1; i >= 0; i--) {
			if (array[i] == ' '){
				b = i+1;
				break;
			}
		}
		char[] array2 = new char[(array.length)-b];
		for (int i = 0; i < array2.length; i++) {
			array2[i] = array[b+i];
		}
		return array2;
	}

	//6) Return middle name(s).
	public static char[] returnMiddleName(char[] array) {
		int a = 0;
		int b = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == ' ')
			{
				a = i;
				break;
			}
		}
		for (int i = array.length-1; i >= 0; i--) {
			if (array[i] == ' '){
				b = i+1;
				break;
			}
		}

		if((array.length-(a+2)-(array.length-b))>0){
			char[] array2 = new char[array.length-(a+2)-(array.length-b)];
			for (int i = 0; i < array2.length; i++) {
				array2[i] = array[a+(i+1)];
			}
			return array2;
		}
		else{
			char[] array2 = new char[0];
			return array2;
		}
	}

	//7) Return boolean if last name contains a hyphen.
	public static boolean lastnameHyphen(char[] array) {
		int b = 0;
		boolean go = false;
		for (int i = array.length-1; i >= 0; i--) {
			if (array[i] == ' '){
				b = i+1;
				break;
			}
		}
		char[] array2 = new char[(array.length)-b];
		for (int i = 0; i < array2.length; i++) {
			array2[i] = array[b+i];
		}
		for (int i = 0; i < array2.length; i++) {
			if (array2[i] == '-'){
				go = true;
			}
		}
		return go;
	}

	//8) Method to convert to lowercase.
	public static void convertLowercase(char[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] >64 && array[i] < 91){
				array[i]+=32;
				System.out.print(array[i]);
			}
			else {
				System.out.print(array[i]);
			}
		}
	}

	//9) Method to convert to uppercase.
	public static void convertUppercase(char[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 60 && array[i] < 123){
				array[i]-=32;
				System.out.print(array[i]);
			}
			else {
				System.out.print(array[i]);
			}
		}
	}

	//10) Modify array to create a random name (mix up letters).
	public static void randomizeName(char[] array){
		Random rand = new Random();
		int x = 0;
		int y = 0;
		for (int i = 0; i < array.length; i++) {
			x = rand.nextInt(array.length);
			y = rand.nextInt(array.length);
			char temp = array[x];
			array[x] = array[y];
			array[y] = temp;
			System.out.print(array[i]);
		}
	}

	//11) Return boolean if first name is a palindrome.
	public static boolean firstNamePalindrome(char[] array) {
		boolean go = false;
		char[] array2 = returnFirstName(array);
		for (int i = array2.length-1; i >= 0; i--) {
			if (array2[i] == array2[array2.length-i-1]){
				go = true;
			}
			else {
				go = false;
				return go;
			}
		}
		return go;
	}

	//14) Make initials from name.
	public static char[] initialsFromName(char[] array) {
		char[] array2 = new char[array.length];
		array2[0] = array[0];
		int a = 1;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == ' '){
				array2[a] = array[i+1];
				a++;
			}
		}
		return array2;
	}

}
