import java.util.*;
import java.io.*;
import java.math.*;
//Dawson Murphy

//This program and its corresponding encryption program uses RSA for cryptography. This program
//takes in a coded message in the form of an array of BigIntegers, either from the user or from 
//a file, and decrypts it, returning the decrypted message in the form of a String.
//It then can be saved as a text file if so desired.

public class decryption2 {
	//Map holds the list of characters and their assigned integers
	HashMap<Integer,Character> iToC=new HashMap<Integer,Character>();
	ArrayList<Integer>iMess=new ArrayList<Integer>();
	//Assigns each character to be coded to a two digit number
	decryption2(){
		iToC.put(69,'a');
		iToC.put(36,'b');
		iToC.put(74, 'c');
		iToC.put(59, 'd');
		iToC.put(46, 'e');
		iToC.put(79, 'f');
		iToC.put(48, 'g');
		iToC.put(26, 'h');
		iToC.put(51, 'i');
		iToC.put(63, 'j');
		iToC.put(62, 'k');
		iToC.put(43, 'l');
		iToC.put(72, 'm');
		iToC.put(98, 'n');
		iToC.put(75, 'o');
		iToC.put(64, 'p');
		iToC.put(88, 'q');
		iToC.put(47, 'r');
		iToC.put(45, 's');
		iToC.put(22, 't');
		iToC.put(77, 'u');
		iToC.put(27, 'v');
		iToC.put(15, 'w');
		iToC.put(34, 'x');
		iToC.put(31, 'y');
		iToC.put(93, 'z');
		iToC.put(44,':');
		iToC.put(50,'-');
		iToC.put(80,',');
		iToC.put(49,'.');
		iToC.put(56,';');
		iToC.put(53,'\'');
		iToC.put(95,' ');
		iToC.put(10,'#');
		iToC.put(11,'%');
		iToC.put(73, '1');
		iToC.put(41, '2');
		iToC.put(71, '3');
		iToC.put(18, '4');
		iToC.put(29, '5');
		iToC.put(83, '6');
		iToC.put(55, '7');
		iToC.put(20, '8');
		iToC.put(33, '9');
		iToC.put(19, '@');
		iToC.put(30, '!');
		iToC.put(76, '$');
		iToC.put(54, '&');
		iToC.put(38, '*');
		iToC.put(52, '(');
		iToC.put(42, ')');
		iToC.put(24, '?');
		iToC.put(21, '/');
		iToC.put(28, '=');
		iToC.put(25, '+');
		iToC.put(82, '_');
		iToC.put(23, '[');
		iToC.put(61, ']');
		iToC.put(39, '{');
		iToC.put(66, '}');
	}
	//If the user has chosen to type in the encrypted message, gets the message from the user
	//on BigInteger at a time. 
	public ArrayList<BigInteger> getcMess(){
		ArrayList<BigInteger> numMess=new ArrayList<BigInteger>();
		Scanner console=new Scanner(System.in);
		System.out.println("Input message to decrypt");
		boolean ans=true;
		//Takes in numbers from the user as long as they keep typing yes 
		//when prompted.
		while(ans==true){
			BigInteger x=console.nextBigInteger();
			numMess.add(x);
			System.out.println("Is there another number? (Yes or No)");
			String YoN=console.next();
			if(YoN.equalsIgnoreCase("Yes")){
				ans=true;
			}else{
				ans=false;
			}
		}
		//System.out.println(numMess);
		return numMess;
	}
	//Takes the ArrayList containing the encrypted message and decrypts each 
	//BigInteger in it individually. 
	public ArrayList<BigInteger> decryptMess(ArrayList<BigInteger> x){
		ArrayList<BigInteger> decoded=new ArrayList<BigInteger>();
		//The private key
		BigInteger d=new BigInteger("89489425009274444368228545921773093919669586065884257445497854456487674839629818390934941973262879616797970608917283679875499331574161113854088813275488110588247193077582527278437906504015680623423550067240042466665654232383502922215493623289472138866445818789127946123407807725702626644091036502372545139713");
		//The modulo
		BigInteger n=new BigInteger("145906768007583323230186939349070635292401872375357164399581871019873438799005358938369571402670149802121818086292467422828157022922076746906543401224889672472407926969987100581290103199317858753663710862357656510507883714297115637342788911463535102712032765166518411726859837988672111837205085526346618740053");
		for(int a=0; a<x.size();a++){
			BigInteger b=x.get(a);
			BigInteger c=b.modPow(d,n);
			decoded.add(c);
		}
		return decoded;
	}
	//Takes the decoded message as an ArrayList of BigIntegers, splits
	//the BigIntegers into two digit integers, and then calls decmess to
	//turn the integer into a character. 
	public ArrayList<String> numToLet(ArrayList<BigInteger> x){
		ArrayList<String> aL=new ArrayList<String>();
		//Takes each BigInteger and decrypts it into a string.
		for(int a=0; a<x.size();a++){
			ArrayList<Integer> p=split(x.get(a));
			String s=decmess(p);
			aL.add(s);
		}
		//System.out.println(aL);
		return aL;
	}

	//Takes in a BigInteger, and splits it into a series of two digit
	//ints, and returns those ints in an ArrayList of Integers.
	public ArrayList<Integer> split(BigInteger x){
		ArrayList<Integer> mess=new ArrayList<Integer>();
		Stack<Integer> temp=new Stack<Integer>();
		BigInteger hundred=new BigInteger("100");
		//Iterates through the BigInteger until it is zero, removing and storing 
		//the smallest two digits each time.
		while(x.compareTo(BigInteger.ZERO) > 0){
			int y=x.remainder(hundred).intValue();
			temp.push(y);
			x=x.divide(hundred);
		}
		//Takes the digits stored in the temp stack and puts them 
		//into an ArrayList
		while(!temp.isEmpty()){
			mess.add(temp.pop());
		}
		//System.out.println(mess);
		return mess;
	}
	//Takes in an ArrayList of Integers, and uses iToC to 
	//translate the Integers into characters. Returns the 
	//characters as a String.
	public String decmess(ArrayList<Integer> x){
		String z="";
		for(int i=0; i<x.size();i++){
			int y=x.get(i);
			char c=iToC.get(y);
			z=z+c;
		}
		return z;
	}
	//Checks with the user if they want to type in the message
	//or retrieve it from a file. Repeats until a valid response is given
	public static String stringOrFile(Scanner console){
		System.out.print("Is it a file? (yes or no)"); //asks user
		String response=console.next();
		if (response.equalsIgnoreCase("yes")||response.equalsIgnoreCase("no")){ //If its an invalid response
			return response; //returns user response if valid
		}else{
			System.out.println("Invalid response");
			return stringOrFile(console);
		}
	}
	//Takes in a file name from the user and attempts to get an encrypted message from the file.
	public ArrayList<BigInteger> fileChoice(Scanner console)throws FileNotFoundException{
		ArrayList<BigInteger> numMess=new ArrayList<BigInteger>();
		System.out.print("Input file name? "); //Asks for file name
		String file=console.next();
		File file1=new File(file);
		//Tests to see if the file exists, and continually asks until it receives a valid file
		if (!file1.exists()){ //If file doesn't exist
			System.out.println("File does not exist");
			return fileChoice(console);
		}
		Scanner input= new Scanner(file1);
		//Reads through the file and puts the contents into an ArrayList
		while(input.hasNext()){
				BigInteger x=input.nextBigInteger();
				//System.out.println(x);
				numMess.add(x);
			}
		return numMess;
	}
	public static void main(String[] args)throws FileNotFoundException{
		Scanner console=new Scanner(System.in);
		decryption2 x=new decryption2();
		ArrayList<BigInteger> bI;
		//Checks with the user if they want to type in the message
		//or retrieve it from a file.
		String sOrF=stringOrFile(console);
		if (sOrF.equalsIgnoreCase("no")){
			bI=x.getcMess();
		}else{
			bI=x.fileChoice(console);
		}
		//System.out.println(bI);
		ArrayList<BigInteger> dM=x.decryptMess(bI);
		ArrayList<String> decmess=x.numToLet(dM);
		for(int i=0;i<decmess.size();i++){
			System.out.print(decmess.get(i));
		}
	}
}


