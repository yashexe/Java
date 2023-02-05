import java.util.ArrayList;
import java.util.Arrays;

public class SampleTestBinTree {

	public static void main(String[] args) {
		
		//data for testing constructor-convert:
		//1) Valid, sorted
		String[] d1={"0", "10", "11"};
		
		//2) Valid, unsorted
		String[] d9={"110","0", "111", "10"};//unsorted d2
		
		//3)InValid
		//3.1: Not binary. "Invalid argument!" expected
		String[] d10={"0", "109", "11b"}; //sorted
		
		//3.2: Duplicate codeword. “Prefix condition violated!” expected
		String[] d12={"1000", "0", "1000", "101"};
		
		//3.3: Not a prefix-free. “Prefix condition violated!” expected
		String[] d14={"0", "10", "11","01"};
		
		//data for testing encode and decode:
		//1) Valid sequence
		ArrayList<String> enc1= new ArrayList<>(Arrays.asList( new String[]{"c2", "c1", "c1","c0","c2","c2","c0"} ));
		String dec1 = "111010011110";
		
		
		
		
		//----------------------------------- Test Starts Here------------------------------------
		System.out.println("---------------------**TEST BEGINS**--------------------------------" );
		System.out.println("\nSection1: Testing constructor;" );
		System.out.println("**Part 1, Valid, Sorted**" );
		
		System.out.println("--------Test 1------------" );

		BinTree a1 = new BinTree(d1);
		System.out.println("Output: " );
		a1.printTree();
		System.out.println("\nExpect: \nc0 I c1 I c2" );
		
		
		
		//______________________________________________________________________________
		System.out.println("\n**Part 2, Valid, Unsorted**" );
		
		System.out.println("--------Test 2------------" );

		BinTree a9 = new BinTree(d9);
		System.out.println("Output: " );
		a9.printTree();
		System.out.println("\nExpect: \nc1 I c3 I c0 I c2" );
		
		
		
		//__________________________________________________________________________
		System.out.println("\n**Part 3, Invalid, Not binary**" );
		
		System.out.println("--------Test 3------------" );
		try {
		BinTree a10 = new BinTree(d10);
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
		//**********
		System.out.println("--------Test 4------------" );
		try {
			BinTree a10 = new BinTree(d12);
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
		
		//***********
		System.out.println("--------Test 5------------" );
		try {
		BinTree a10 = new BinTree(d14);
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
		
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing constructor finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSection2: Testing height()" );
		
		System.out.println("--------Test 6------------" );

		int h1=a1.height();
		System.out.println("The height is: "+h1);
		
		
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing height() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSection 3: Testing getCodewords()" );
		System.out.println("--------Test 7------------" );

		String lt1=a1.getCodewords().toString();
		System.out.println("Output: "+lt1);
		String d111=Arrays.toString(d1);
		System.out.println("Expect: "+d111);
		
		
		
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing getCodewords() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSection 4: Testing convert()" );
		
		System.out.println("--------Test 8------------" );
		String[] con1=a1.convert();
		System.out.println("Output: "+Arrays.toString(con1));
		String exp1="[null, I, c0, I, null, null, c1, c2]";
		System.out.println("Expect: "+exp1);
		
		
		
		//String[] strArray = arrayList.toArray(new String[arrayList.size()]);
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing convert() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSection 5: Testing encode()" );
		System.out.println("--------Test 9------------" );

		String encod1=a1.encode(enc1);
		System.out.println("Output: " +encod1);
		System.out.println("Expect: " +dec1);
		
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing encode() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSection 6: Testing decode()" );

		System.out.println("--------Test 10------------" );

		ArrayList<String> decod1=a1.decode(dec1);
		System.out.println("Output: " +decod1.toString());
		System.out.println("Expect: " +enc1.toString());
		
		
		//_________________________________________________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing decode() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSection 7: Testing toString()" );
		
		System.out.println("--------Test 11------------" );

		String s9=a9.toString();
		System.out.println("Output: " +s9);
		System.out.println("Expect: " +"0 10 110 111 ");
		
		
		System.out.println("\n-----------------------------Testing Done!-------------------------------------");

	}

}