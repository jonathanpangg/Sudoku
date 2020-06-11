import java.util.*;
public class Sudoku{
	public static final String store = "123456789";
	public static void main(String [] args){
		String [][] arr = new String[11][11];
		String check = "123456789";
		setBoard(arr);
		printRules();
		pause(2500);
		Scanner kb = new Scanner(System.in);
		System.out.println("What difficulty do you want to play (easy, med, or hard): ");
		String diff = kb.next();
		String pick = "";
		int row = 0;
		int column = 0;
		diffBoard(row, column, pick, arr, check, store, diff);
		printBoard(arr);
		while(searchBoard(arr)==false){
			check = store;
			System.out.println("Enter a number: ");
			pick = kb.next();
			System.out.println("Enter the row of ur choice: ");
			row = kb.nextInt();
			pause(500);
			System.out.println("Enter the column of ur choice: ");
			column = kb.nextInt();
			for(int i = 0; i < 10; i++)
				System.out.println();
			System.out.print("Loading");
			for(int i = 0; i < 3; i++){
				System.out.print(" . ");
				pause(1000);
			}
			System.out.println();
			updateBoard(row, column, pick, arr, check, store);
			printBoard(arr);
		}
		System.out.println("CONGRATS!! \nCONGRATS!! \nYOU WIN!! \nCONGRATS!! \nCONGRATS!!");
	}
	public static void printRules(){
		System.out.println("WELCOME TO JONATHAN\'S SUDOKU!");
		pause(1000);
		System.out.println("Here are the rules: ");
		pause(1000);
		System.out.println("You can place numbers from 1-9.");
		pause(1000);
		System.out.println("You can't have the same numbers in the same row, same column, or same three by three box.");
		pause(1000);
	}
	public static void setBoard(String [][] arr){ // beginning board
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				arr[i][j] = " ";
			}
		}
		for(int i = 3; i < arr.length; i+=4){
			for(int j = 0; j < arr[i].length; j++){
				arr[i][j] = "-";
			}
		}
		for(int i = 0; i < arr.length; i++){
			for(int j = 3; j < arr[i].length; j+=4){
				arr[i][j] = "|";
			}
		}
	}
	public static void diffBoard(int row, int column, String pick, String [][] arr, String check, String store, String diff){
		if(diff.equals("easy")){
			for(int diffcount = 0; diffcount < 32; diffcount++){
				int r = (int)(Math.random()*11);
				int c = (int)(Math.random()*11);
				int t = (int)(Math.random()*9+1);
				while(completecheck(arr, check, t+"", store, r, c) == false){
					check = store;
					r = (int)(Math.random()*11);
					c = (int)(Math.random()*11);
					t = (int)(Math.random()*9+1);							
				}
				if(completecheck(arr, check, t+"", store, r, c)){
					arr[r][c] = t + "";
				}		
				check = store;
			}
		}
		else if(diff.equals("med")){
			for(int diffcount = 0; diffcount < 28; diffcount++){
				int r = (int)(Math.random()*11);
				int c = (int)(Math.random()*11);
				int t = (int)(Math.random()*9+1);
				while(completecheck(arr, check, t+"", store, r, c) == false){
					check = store;
					r = (int)(Math.random()*11);
					c = (int)(Math.random()*11);
					t = (int)(Math.random()*9+1);							
				}
				if(completecheck(arr, check, t+"", store, r, c)){
					arr[r][c] = t + "";
				}		
				check = store;
			}
		}
		else if(diff.equals("hard")){
			for(int diffcount = 0; diffcount < 25; diffcount++){
				int r = (int)(Math.random()*11);
				int c = (int)(Math.random()*11);
				int t = (int)(Math.random()*9+1);
				while(completecheck(arr, check, t+"", store, r, c) == false){
					check = store;
					r = (int)(Math.random()*11);
					c = (int)(Math.random()*11);
					t = (int)(Math.random()*9+1);							
				}
				if(completecheck(arr, check, t+"", store, r, c)){
					arr[r][c] = t + "";
				}
				check = store;
			}
		}
		else{
			Scanner temp = new Scanner(System.in);
			System.out.println("Wrong Input man, input easy, med, or hard!");
			String redun = temp.next();
			diffBoard(row, column, pick, arr, check, store, redun);
		}
	}
	public static void printBoard(String [][] arr){ // print board
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				System.out.print(arr[i][j]);
			}
			pause(250);
			System.out.println();
		}
	}
	public static void updateBoard(int row, int column, String pick, String [][] arr, String check, String store){
		int deter = 0;
		if(column<3){
			if(check.contains(arr[row][column])==false && boxleft(arr, check, pick, store, row) && rowscheck(arr, check, pick, row) && colscheck(arr, check, pick, column))
				arr[row][column] = pick;
			else
				deter++;
		}
		else if(column>3 && column <7){
			if(check.contains(arr[row][column])==false && boxmiddle(arr, check, pick, store, row) && rowscheck(arr, check, pick, row) && colscheck(arr, check, pick, column))
				arr[row][column] = pick; 
			else
				deter++;
		}
		else if(column>7){
			if(check.contains(arr[row][column])==false && boxright(arr, check, pick, store, row) && rowscheck(arr, check, pick, row) && colscheck(arr, check, pick, column))
					arr[row][column] = pick;
			else
				deter++;
		}
		if(deter==1){
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("This is not a valid choice! ");
			System.out.println("This is not a valid choice! ");
			System.out.println("This is not a valid choice! ");
			pause(3000);
		}
	}
	public static boolean searchBoard(String [][] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				if(arr[i][j].equals(" ")){
					return false;
				}
			}
		}
		return true;
	}
	public static boolean boxleft(String [][] arr, String check, String pick, String store, int row){ // checks the 3 left boxes
		if(row<3){
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		else if(row>3 && row<7){
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		else{
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		return check.contains(pick);
	}
	public static boolean boxmiddle(String [][] arr, String check, String pick, String store, int row){ // checks the 3 middle boxes
		if(row<3){
			for(int j = 4; j < 7; j++){
				for(int k = 4; k < 7; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		else if(row>3 && row<7){
			for(int j = 4; j < 7; j++){
				for(int k = 4; k < 7; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		else{
			for(int j = 4; j < 7; j++){
				for(int k = 4; k < 7; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		return check.contains(pick);
	}
	public static boolean boxright(String [][] arr, String check, String pick, String store, int row){ // checks the 3 right boxes
		if(row<3){
			for(int j = 8; j < 11; j++){
				for(int k = 8; k < 11; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		else if(row>3 && row<7){
			for(int j = 8; j < 11; j++){
				for(int k = 8; k < 11; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		else{
			for(int j = 8; j < 11; j++){
				for(int k = 8; k < 11; k++){
					if(check.contains(arr[j][k])){
						int index = check.indexOf(arr[j][k]);
						check = check.substring(0, index) + check.substring(index+1);
					}
				}
			}
		}
		return check.contains(pick);
	}
	public static boolean rowscheck(String [][] arr, String check, String pick, int row){ // checks rows
		for(int i = 0; i < arr.length; i++){
			if(check.contains(arr[row][i])){
				int index = check.indexOf(arr[row][i]);
				check = check.substring(0, index) + check.substring(index+1);
			}
		}
		return check.contains(pick);
	}
	public static boolean colscheck(String [][] arr, String check, String pick, int col){ // checks cols
		for(int i = 0; i < arr.length; i++){
			if(check.contains(arr[i][col])){
				int index = check.indexOf(arr[i][col]);
				check = check.substring(0, index) + check.substring(index+1);
			}
		}
		return check.contains(pick);
	}
	public static boolean completecheck(String [][] arr, String check, String pick, String store, int row, int column){
		if(column<3){
			return arr[row][column].equals(" ") && boxleft(arr, check, pick, store, row) && rowscheck(arr, check, pick, row) && colscheck(arr, check, pick, column);
		}
		else if(column>3 && column<7){
			return arr[row][column].equals(" ") && boxmiddle(arr, check, pick, store, row) && rowscheck(arr, check, pick, row) && colscheck(arr, check, pick, column);
		}
		else{
			return arr[row][column].equals(" ") && boxright(arr, check, pick, store, row) && rowscheck(arr, check, pick, row) && colscheck(arr, check, pick, column);
		}
	}
	public static void pause(int num){
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
