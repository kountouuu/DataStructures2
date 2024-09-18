import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sort {

    // implement Greedy - decreasing algorithm using QuickSort or MergeSort or HeapSort.
    // read all files but dont put them on the disk , sort them by decreasing size
    static int partition(int array[], int low,int high){
        //pivot element is rightmost element
        int pivot = array[high];

        //pointer for greater element
        int i = (low - 1);

        for (int j = low; j < high; j++){ // loop the whole list and compare to pivot and swap the according values if they are larger than pivot
            if (array[j] <= pivot){
                i++; // if an element is smaller than pivot swap it with greater element pointed by i

                int temp = array[i]; // swapping i with j
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // swap pivot element with greater
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;

        return (i + 1); // return starting partition position
    }

    static void quickSort(int array[],int low,int high){
        if ( low < high ){
            int pi = partition(array,low,high); // finding pivot element

            quickSort(array,low,pi-1); // sorting firsthalf
            quickSort(array,pi+1,high);// sorting secondhalf
        }
    }

    static int[] reverseArray(int[] array){
        // reversing array given as parameter
        int[] tempArray = new int[array.length];
        int j = array.length;
        for (int i = 0; i <= tempArray.length - 1; i++){
            tempArray[j - 1] = array[i];
            j--;
        }

        return tempArray;
    }
    // class variables and constructor
    private int[] memArray;
    private int memArrayPointer;
    private Disk firstDisk;
    private Disk[] diskArray;
    private int disksUsed;
    public float sumOfAllFolders;
    private int[] reversedMemArray ;

    Sort(){
        this.memArray = new int[100];
        this.memArrayPointer = 0;
        this.disksUsed = 0;
        this.diskArray = new Disk[100];
        this.firstDisk = new Disk();
        this.sumOfAllFolders = 0;
        this.diskArray[0] = firstDisk;
        this.reversedMemArray = new int[100];
    }

    public int getDisksUsed() {
        return disksUsed;
    }

    public void setDisksUsed(int disksUsed) {
        this.disksUsed = disksUsed;
    }

    // method for part D
    public Disk[] sort(File arg) {
        Disk[] disks = new Disk[100];
        try {
            Scanner myScanner = new Scanner(arg);
            int[] tmpArr = new int[100];
            int pointer = 0;

            while(myScanner.hasNext()){
                if(myScanner.hasNextInt()){
                    int data = myScanner.nextInt();
                    if (data < 0 || data > 1000000){
                        System.exit(0);
                    }
                    tmpArr[pointer] = data;
                    pointer++;
                }
            }
            myScanner.close();

            quickSort(tmpArr,0,tmpArr.length-1);

            tmpArr = reverseArray(tmpArr);

            for(int i = 0; i <= tmpArr.length - 1; i++){
                int data = tmpArr[i];
                int index = 0;
                boolean dataNotAdded = true;
                if(data == 0){ // if non zero element then parse it to disk
                    continue;
                }
                if(i == 0){
                    this.disksUsed++;
                    this.sumOfAllFolders += data;
                    Disk firstDisk = new Disk();
                    firstDisk.addData(data);
                    disks[i] = firstDisk;
                } else {
                    for(int w = 0 ; w < disks.length - 1; w++){
                        if(disks[w]!=null) { // to index non null elements
                            index++; // increasing helper index
                            if (disks[w].getFreeDiskSpace() >= data) { // if data is small enough
                                disks[w].addData(data); // allocate and set flag to false
                                this.sumOfAllFolders += data;
                                dataNotAdded=false;
                                break;
                            }
                        }
                    }
                    //
                    if(dataNotAdded){
                        this.disksUsed++;
                        this.sumOfAllFolders += data;
                        Disk nowAnotherDisk = new Disk();
                        nowAnotherDisk.addData(data);
                        disks[index] = nowAnotherDisk;
                        dataNotAdded = false;
                    }
                }
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return disks;
    }

        public static void main(String[] args){
        try {
            File fileToRead = new File(args[0]);
            Scanner sc = new Scanner(fileToRead);
            int[] memoryArray = new int[10000]; //memory read from files
            int memoryArrayPointer = 0; // pointer to int array
            while(sc.hasNextLine()){
                // reading int from file and adding it to int array
                int data = sc.nextInt();
                if(data<0||data>1000000){
                    System.exit(0);
                }
                memoryArray[memoryArrayPointer] = data;
                memoryArrayPointer++;
            }

            quickSort(memoryArray,0,memoryArrayPointer);
            int[] reversedMemoryArray = reverseArray(memoryArray);

            Disk firstDisk = new Disk();
            Disk[] diskArray = new Disk[100];
            diskArray[0] = firstDisk;

            boolean dataNotAdded;
            for(int x = 0 ; x < reversedMemoryArray.length - 1;x++){
                if(reversedMemoryArray[x] == 0){
                    continue; // eliminating 0s
                }
                int data = reversedMemoryArray[x];
                dataNotAdded = true;
                int index = 0;

                if(x == 0){
                    firstDisk.addData(data); // initialization of disk of first element
                } else {
                    // using for loop we check if we can insert the element in a disk in the disk array
                    for(int w = 0 ; w < diskArray.length - 1; w++){

                        if(diskArray[w]!=null) { // to index non null elements
                            index++; // increasing helper index
                            if (diskArray[w].getFreeDiskSpace() >= data) { // if data is small enough
                                diskArray[w].addData(data); // allocate and set flag to false
                                dataNotAdded=false;
                                break;
                            }
                        }
                    }
                    // if not we create another disk and insert it to the appropriate index
                    if(dataNotAdded) { // if data was not entered in a disk , create a new one and enter it
                        Disk newDisk = new Disk();
                        newDisk.addData(data);
                        diskArray[index] = newDisk; // incrementing index in above if statement ,
                        // every time we parse a non null element we increment thus we have the next available position
                    }
                }


            }
            for(int aa = 0 ; aa < diskArray.length - 1; aa++){
                if(diskArray[aa]!=null) {
                    System.out.println("Disk with ID: " + diskArray[aa].getUniqueDiskID() + " with free space of :" + diskArray[aa].getFreeDiskSpace());
                }
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
