

public class MaxPQ{
    private Disk[] heap; // heap to store data in
    private int size; // size of heap

    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;

    MaxPQ(){
        this.heap = new Disk[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }

    public void add(Disk item){
        if (size == heap.length - 1){
            grow();
        }

        heap[++size] = item; // placing item in next slot
        swim(size);
    }

    public Disk peek(){
        if(size == 0){
            return null;
        }
        return heap[1];
    }

    public Disk getMax(){
        if (size == 0) {
            return null;
        }
        // reference the root item , switch with rightmost leaf , decrement , sink rightmost leaf and return root
        Disk root = heap[1];
        heap[1] = heap[size];
        size--;
        //sink(1);
        return root;
    }
    private void swim(int i){
        if (i == 1){
            return;
        }
        int parent = i / 2;

        while (i != 1 && heap[i].compareTo(heap[parent]) > 0){
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }
    private void sink(int i){
        int left = 2 * i;
        int right = left + 1;

        if (left > size){
            return;
        }
        while (left <= size){
            int max = left;
            if (right <= size){
                if (heap[left].compareTo(heap[right]) > 0){
                    max = right;
                }
                if (heap[i].compareTo(heap[max]) >= 0){
                    return ;
                }
                else{
                    swap(i,max);
                    i = max;
                    left = i * 2;
                    right = left + 1;
                }
            }
        }
    }
    private void swap(int i,int j){
        Disk tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void grow() {
        Disk[] newHeap = new Disk[heap.length + AUTOGROW_SIZE];
        for (int i = 0 ; i <= size ; i++){
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

}