
public class MaxMHeap {

    //instance fields
    //reference to array that stores keys
    private int[] h;
    //size of heap
    private int size;
    //m: max number of children that a node has
    private int k;
    

    public MaxMHeap(int k, int n){
        this.h = new int[n];
        this.size = 0;
        this.k = k;
    }
    public MaxMHeap(int k, int[] a){
        this.h = new int[a.length];
        this.size = a.length;
        this.k = k;
        for (int i = 0; i < size; i++) {
            h[i] = a[i];
        }
        buildMaxHeap();
    }
    private void buildMaxHeap(){
        for(int i = (size - 1) / k; i >= 0; i--){
            percolateDown(i);
        }
    }

    public int getM(){
        return k;
    }

    public int getSize(){
        return size;
    }

    public void insert(int x){
        if (size == h.length) {
            // Allocate a new array with double the size of the current array
            int[] newH = new int[h.length * 2];
            // Copy the contents of the old array into the new array
            for (int i = 0; i < size; i++) {
                newH[i] = h[i];
            }
            // Set the new array as the heap array
            h = newH;
        }
        h[size] = x;
        size++;
        percolateUp(size - 1);
    }

    public int readMax() throws RuntimeException{
        if(size == 0){
            throw new RuntimeException("Heap is empty");
        }
        return h[0];
    }

    public int deleteMax() throws RuntimeException{
        if(size == 0){
            throw new RuntimeException("Heap is empty");
        }
        int max = h[0];
        h[0] = h[size-1];
        size--;
        percolateDown(0);
        return max;
    }


    public String toString(){
        String str = "";
        for(int i = 0; i < size; i++){
            str += h[i];
            if(i != size - 1){
                str += ", ";
            }
        }
        return str;
    }

    public static void sortArray(int k, int[] a){
        MaxMHeap heap = new MaxMHeap(k, a);
        for(int i = a.length - 1; i >= 0; i--){
            a[i] = heap.deleteMax();
        }
    }
    private void percolateUp(int i){
        int parent = (i - 1) / k;
        while(i > 0 && h[i] > h[parent]){
            swap(i, parent);
            i = parent;
            parent = (i - 1) / k;
        }
    }
    
    private void percolateDown(int i) {
        int maxChild = i * k + 1;
        while (maxChild < size) {
            // Find the maximum child among i's children
            for (int j = 1; j < k; j++) {
                int child = i * k + j + 1;
                if (child < size && h[child] > h[maxChild]) {
                    maxChild = child;
                }
            }
            if (h[i] < h[maxChild]) {
                swap(i, maxChild);
                i = maxChild;
                maxChild = i * k + 1;
            } else {
                break;
            }
        }
    }
    
    private void swap(int i, int j){
        int temp = h[i];
        h[i] = h[j];
        h[j] = temp;
    }
    

}
