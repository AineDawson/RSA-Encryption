import java.util.*;
import java.io.*;
import java.math.*;
//Dawson Murphy

//This program and its corresponding decryption program uses RSA for Cryptography. This program
//takes in a message from the user and encypts it, returning the encypted message in the form of 
//an ArrayList of BigIntegers. It then can be saved as a text file if so desired.
public class encryption2 {
	//Map holds the list of characters and their assigned integers
	Map<Character,Integer> cToI=new HashMap<Character,Integer>();
	List<Integer>cMess=new ArrayList<Integer>();
	encryption2(){
		//Assigns each character to be coded to a two digit number
		cToI.put('a', 69);
		cToI.put('b', 36);
		cToI.put('c', 74);
		cToI.put('d', 59);
		cToI.put('e', 46);
		cToI.put('f', 79);
		cToI.put('g', 48);
		cToI.put('h', 26);
		cToI.put('i', 51);
		cToI.put('j', 63);
		cToI.put('k', 62);
		cToI.put('l', 43);
		cToI.put('m', 72);
		cToI.put('n', 98);
		cToI.put('o', 75);
		cToI.put('p', 64);
		cToI.put('q', 88);
		cToI.put('r', 47);
		cToI.put('s', 45);
		cToI.put('t', 22);
		cToI.put('u', 77);
		cToI.put('v', 27);
		cToI.put('w', 15);
		cToI.put('x', 34);
		cToI.put('y', 31);
		cToI.put('z', 93);
		cToI.put(':', 44);
		cToI.put('-', 50);
		cToI.put(',', 80);
		cToI.put('.', 49);
		cToI.put(';', 56);
		cToI.put('\'',53);
		cToI.put(' ', 95);
		cToI.put('#', 10);
		cToI.put('%', 11);
		cToI.put('1', 73);
		cToI.put('2', 41);
		cToI.put('3', 71);
		cToI.put('4', 18);
		cToI.put('5', 29);
		cToI.put('6', 83);
		cToI.put('7',55);
		cToI.put('8', 20);
		cToI.put('9', 33);
		cToI.put('@', 19);
		cToI.put('!', 30);
		cToI.put('$', 76);
		cToI.put('&', 54);
		cToI.put('*', 38);
		cToI.put('(', 52);
		cToI.put(')', 42);
		cToI.put('?', 24);
		cToI.put('/', 21);
		cToI.put('=', 28);
		cToI.put('+', 25);
		cToI.put('_', 82);
		cToI.put('[', 23);
		cToI.put(']', 61);
		cToI.put('{', 39);
		cToI.put('}', 66);
	}
	//Takes in a string and splits it into an array of strings, each of a max length of 10.
	//This is required in order to encode longer messages.
	public ArrayList<String> strings10(String x){
		String s;
		ArrayList<String> aS=new ArrayList<String>();
		while(x.length()>=10){
			s=x.substring(0, 10);
			aS.add(s);
			x=x.substring(10, x.length());
		}
		aS.add(x);
		return aS;
	}
	//Method to take in a string from the console, and makes it lower case since the
	//program cannot encode upper case characters. It then call strings10 to split
	//the input string into smaller strings, and returns the ArrayList strings10 returns.
	public ArrayList<String> numbers2(){
		Scanner console=new Scanner(System.in);
		System.out.println("Input message");
		String x=console.nextLine().toLowerCase();
		ArrayList<String> y=strings10(x);
		//System.out.println(y);
		return y;
	}
	//Takes in the ArrayList of Strings and returns an ArrayList of the equivalent BigDecimal for
	//each String using cToI to convert each character to an int.
	public ArrayList<BigDecimal> numbersComplicated(ArrayList<String> aL){
		ArrayList<BigDecimal> bcMess=new ArrayList<BigDecimal>();
		//Iterates through each string in aL
		for(int a=0;a<aL.size();a++){
			//ArrayList to hold the integer equivalent for each character in a String
			ArrayList<Integer> nMessage=new ArrayList<Integer>();
			String x=aL.get(a);
			//Iterates through each String and converts each character into an int
			//using cToI, adding the int to nMessage
			for(int i=0; i<x.length();i++){
				char y=x.charAt(i);
				int z=cToI.get(y);
				nMessage.add(z);
				//System.out.println(nMessage);
			}
			//BigDecimal to hold the int version of the string
			BigDecimal s=new BigDecimal("0");
			//Iterates thorugh nMessage, and take the integers in the array and concatenates them
			//into one BigDecimal
			for(int i=nMessage.size()-1;i>=0;i--){
				//Takes the ints in nMessage, and puts them to a power depending
				//on their position in nMessage. (The last int is left alone, second to last is
				//put to the power of 10^2, third to last is put to 10^4, and so on till it gets
				//to the first element)
				double s2=nMessage.get(i)*Math.pow(10, 2*(nMessage.size()-i-1));
				//System.out.println(s2);
				//Converts the double into a BigDecimal
				BigDecimal s3=new BigDecimal(s2);
				//Adds the new BigDecimal message to the overall BigDecimal 
				s=s.add(s3);
			}
			//System.out.println(s);
			//Adds the new BigDecimal to bcMess
			bcMess.add(s);
		}
		//System.out.println(bcMess);
		return bcMess;
	}
	//Takes the BigDecimal version of the input message and encrypts it
	public ArrayList<BigInteger> encryptArray(ArrayList<BigDecimal> aL){
		ArrayList<BigInteger> codedMessage=new ArrayList<BigInteger>();
		//The public key
		BigInteger y=new BigInteger("65537");
		//The modulos
		BigInteger z=new BigInteger("145906768007583323230186939349070635292401872375357164399581871019873438799005358938369571402670149802121818086292467422828157022922076746906543401224889672472407926969987100581290103199317858753663710862357656510507883714297115637342788911463535102712032765166518411726859837988672111837205085526346618740053");
		//Encyrpts each BigInteger in aL separately
		for(int a=0;a<aL.size();a++){
			BigInteger x=aL.get(a).toBigInteger();
			BigInteger E=x.modPow(y,z);
		codedMessage.add(E);
		}
		return codedMessage;
	}

	public static void main(String[] args)throws FileNotFoundException{
		encryption2 x=new encryption2();
		Scanner console=new Scanner(System.in);
		ArrayList<String> one=new ArrayList<String>();
		boolean goodInput=false;
		ArrayList<BigDecimal> temp= new ArrayList<BigDecimal>();
		//Attempts to take in a message from the user and turn it into a BigDecimal
		//Alerts the user if the message contains characters that are not supported
		//and prompts them to try again.
		while(goodInput==false){
			try{
				one=x.numbers2();
				System.out.println(one);
				temp=x.numbersComplicated(one);
				goodInput=true;
			}catch(NullPointerException e){
				System.out.println("One or more of the characters you typed in is not supported by this program.");
				System.out.println("Please edit your message and try again");
			}
		}
		ArrayList<BigDecimal> two=temp;
		System.out.println(two);
		
		ArrayList<BigInteger> three=x.encryptArray(two);
		System.out.print("The encrypted message is: ");
		System.out.println(three);
		System.out.println("If you would like to save the encrypted message, type Y. Otherwise, type anything else.");
		String reply=console.nextLine();
		if(reply.toLowerCase().equals("y")){
			System.out.print("Save the results as: "); //asks what the user what file to save result in
			String resfile=console.next(); 
			PrintStream output=new PrintStream(new File(resfile));
			for(int a=0; a<three.size();a++){
				output.println(three.get(a));
			}
		}
		System.out.println("Thank you for using the encryption software");
		
	}
}
