public class YoungT {
    //instance variables
    //reference to 2D array storing integers
    private int[][] arr;
    //finite integers in tableau
    private int finite;
    //integer representing infinity
    private int infinity;

    public YoungT(int k, int n, int infinity){
        if (k < 10)  k = 10;
        if (n < 10)  n = 10;
        if (infinity < 100) infinity = 100;
    
        this.arr = new int[k][n];
        this.finite = 0;
        this.infinity = infinity;

    }
    public YoungT(int[][] a){
        this.arr = a;

        for(int c = 0;c<arr.length;c++){
            if(c+1 == arr.length)break;
            if(arr[c][arr[0].length-1] > arr[c+1][arr[0].length-1] ){
                swap(arr, c, arr[0].length - 1, c+1, arr[0].length - 1);
            }
        }
        this.finite = a.length * a[0].length;
        //find maximum value in the 2D array and set as infinity
        int max = a[0][0];
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[0].length; j++){
                if(a[i][j] > max) max = a[i][j];
            }
        }
        this.infinity = max * 10;
    }
    private static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }

    public int getNumElem(){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                if(arr[i][j] != infinity) count++;
            }
        }
        return count;

    }

    public int getInfinity(){
        return this.infinity;
    }

    public boolean isEmpty(){
        return (getNumElem() == infinity);
    }

    public boolean isFull(){
        return (getNumElem() == finite);

    }

public boolean insert(int x) {
    if (isFull()) return false;
    
    int i = arr.length - 1;
    int j = arr[0].length - 1;
    while (i >= 0 && j >= 0) {
        if (arr[i][j] == infinity) {
            i--;
            continue;
        }
        if (arr[i][j] < x) {
            arr[i][j] = x;
            finite++;
            return true;
        }
        if (i > 0 && arr[i-1][j] > x) {
            int temp = arr[i][j];
            arr[i][j] = x;
            x = temp;
        }
        j--;
    }
    
    arr[0][j+1] = x;
    finite++;
    return true;
}


    public int readMin() throws RuntimeException{
        if(isEmpty()) throw new RuntimeException("Young tableau is empty");
        
        return arr[0][0];
    }

    public int deleteMin() throws RuntimeException{
        if (isEmpty()) throw new RuntimeException("Cannot delete minimum element, the Young tableau is empty");
        
        int minimum = arr[0][0],row = 0, col = 0;
        arr[0][0] = (minimum == infinity) ? finite : infinity;
        while (true) {
            int least = minimum, r = row, c = col;
            if (r+1 < arr.length && arr[r+1][c] < least) {
                least = arr[r+1][c];
                r++;
                c = col;
            }
            if (c+1 < arr[0].length && arr[r][c+1] < least) {
                least = arr[r][c+1];
                r = row;
                c = c++;
            }
            if (least == minimum) break;
            
            arr[row][col] = least;
            arr[r][c] = minimum;
            row = r;
            col = c;
        }
        return minimum;
    }
    
    public boolean find(int x) throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Tableau is empty");
        
        int row = 0;
        int col = arr[0].length - 1;
    
        while (row < arr.length && col >= 0) {
            int current = arr[row][col];
    
            if (current == x) return true;
            else if (current > x) col--;
            else row++;
    
        }
    
        return false;
    }
    public String toString() {
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == infinity) {
                    result += "inf";
                } else {
                    result += arr[i][j];
                }
                if (j != arr[i].length - 1) {
                    result += ", ";
                }
            }
            if (i != arr.length - 1) {
                result += ", ";
            }
        }
        return result;
    }
    
}
