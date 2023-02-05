import java.util.ArrayList;

public class BinTree {
    private TNode root;

    //start of method BinTree
    public BinTree(String[] a) throws IllegalArgumentException {
        //root node initialized with new data
        root = new TNode("", null, null);
        int i = 0; //counter created to track position

        //iterates through each string in array a
        while (i < a.length) {
            String codeword = a[i];
            //checks if codeword is binary string
            if (!(codeword.matches("[01]+"))) throw new IllegalArgumentException("Invalid argument!");
            //checks if codeword is prefix free
            if (!prefixCheck(a, codeword)) throw new IllegalArgumentException("Prefix condition violated!");
            //adds codeword to tree
            insert(codeword, "c" + i);

            i++; //increments counter
        }
    }

    //start of method insert
    private void insert(String codeword, String storeCode) {
        //variable to track position in tree
        TNode currentPos = root;
        //iterates through every character in the codeword
        for (char ch : codeword.toCharArray()) {
            //checks if current character is 0
            if (ch == '0') {
                //checks if left node is null
                if (currentPos.left == null) currentPos.left = new TNode("", null, null);
                currentPos = currentPos.left;
            } else {
                //checks if right node is null
                if (currentPos.right == null) currentPos.right = new TNode("", null, null);
                currentPos = currentPos.right;
            }
        }
        //stores the position in storeCode
        currentPos.data = storeCode;
    }

    //start of method prefixCheck
    //sees if codewords are prefix free
    private boolean prefixCheck(String[] a, String codeword) {
        //checks for duplicates in array
        for(int i =0;i<a.length;i++)
            for(int j =i+1;j<a.length;j++)
                if(a[i]==a[j]) return false;
        //checks if codeword is a prefix of any string in array

        for (String currentStr : a) {

            //checks if codeword length is less than or equall to length of string
            if(codeword.length()<=currentStr.length()){
                boolean matches = true;

                //compares characters in codeword and string for prefix
                for(int i = 0;i<codeword.length();i++){ 
                    if(codeword.charAt(i)!=currentStr.charAt(i)){
                        matches = false;
                        break;
                    }
                }
                //returns false if current string is a prefix
                if(matches && codeword.length() != currentStr.length()) return false;

            }
        }
        return true; //true if codeword is prefix free
    }

    //method printTree provided in lab manual
    public void printTree() {
        printTree(root);
    }
    private void printTree(TNode t){
        if(t!=null){
            printTree(t.left);
            if(t.left != null) System.out.print("I");
            System.out.print(t.data + " ");
            printTree(t.right);
        }
    }

    //start of method height
    public int height()  {return height(root);}
    //actual height calculation
    private int height(TNode t) {
        return t == null ? 0 : 1 + Math.max(height(t.left), height(t.right));
    }

    //start of method getCodewords
    public ArrayList<String> getCodewords(){
        //list that stores the codewords
        ArrayList<String> codewords = new ArrayList<String>();
        getCodewords(root, "", codewords);
        return codewords;
    }
    private void getCodewords(TNode node, String codeword, ArrayList<String> codewords){
        //checks if node is leaf node
        if (node.left == null && node.right == null) {
            codewords.add(codeword); //adds codeword to list
            return;
        }
        //traverse left sub-tree
        if (node.left != null) getCodewords(node.left, codeword + "0", codewords);
        //traverse right sub-tree
        if (node.right != null) getCodewords(node.right, codeword + "1", codewords);
        
    }

    //start of method convert
    public String[] convert() {
        ArrayList<String> result = new ArrayList<>();
        //add null to the beginning of each array
        result.add(null);

        //converts the tree
        convert(root, result, 1);

        //returns array as strings
        return result.toArray(new String[result.size()]);
    } 
    private void convert(TNode node, ArrayList<String> result,int currentLevel) {
        //checks if node is a leaf node
        //checks if height is less than height of the root node
        if(node.left == null && node.right == null && height(node)<height(root))currentLevel++;

        //checks if node has both left and right children
        if(node.left!=null && node.right != null){
            result.add("I"); //adds I to list for any internal node
            //checks if node is at desired height and less than root node height
            if(height(node)==currentLevel && height(node)<height(root)&&height(node)>1){
                result.add(null);
                result.add(null);
            }
            //converts left child
            convert(node.left, result, currentLevel++);
        }
        //checks if node has data withins
        //adds data to list
        if(node.data != "")result.add(node.data);

        //checks if node has left and right children
        //converts right child
        if(node.left!=null && node.right!=null)convert(node.right, result, currentLevel++);
    }


    public String encode(ArrayList<String> a){
        String encoded = "";
        int size = a.size();
        int max = 0;
        
        for(int i = 0;i<size;i++)
            if((a.get(i).charAt(1) - '0') > max)
                max = a.get(i).charAt(1) - '0';

        for(String symbol : a) encoded += (getCode(symbol, max));
        return encoded;
    }
    private String getCode(String symbol, int max){
        ArrayList<String> codewords = getCodewords();
        String converted = "";

        if(symbol.charAt(1) != '0') for(int i = 1;i<=symbol.charAt(1)- '0';i++) converted +="1";
        else converted = "0";

        if(max != symbol.charAt(1) - '0' && symbol.charAt(1) != '0') converted +="0";
        
        for(int j = 0; j < codewords.size();j++){
            String codeword = codewords.get(j);
            if(converted.equals(codewords.get(j))) return codeword;    
        }
        return null;
    }

    //start of method decode

    public ArrayList<String> decode(String a) throws IllegalArgumentException{

        ArrayList<String> decoded = new ArrayList<String>();
        ArrayList<String> codewords = getCodewords();
        
        int index = 0;

        while(index < a.length()){
            for(String codeword: codewords){
                if(a.startsWith(codeword,index)){
                    int num=0;
                    for(int i = 0; i<codeword.length();i++)if(codeword.charAt(i)-'0' == 1) num++;
                    
                    decoded.add("c"+num);
                    index += codeword.length();
                    break;
                }
            }
        }
        if(index!= a.length()) throw new IllegalArgumentException
            ("Input String is not a binary sequence or cannot be parsed into a sequence of codewords.");
        return decoded;
    }
    
    //start of method "toString" 
    //creates a string representation of an object by calling getCodewords method
    public String toString(){
        //initialize a list with codewords from prior method
        ArrayList<String> codewords = getCodewords();

        String stringRep = "";
        //iterate through the list
        //add each codeword to the string rep
        for ( String codeword: codewords) stringRep+= codeword+" ";
        //returns string representation
        return stringRep.toString();
    }
    
}