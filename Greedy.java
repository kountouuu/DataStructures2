import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Greedy {

    private MaxPQ maxPQ;
    private Disk dsk;
    private int numOfDisks;
    private float sumOfAllFolders;

    Greedy(){
        this.numOfDisks = 1;
        this.sumOfAllFolders = 0;
        this.dsk = new Disk();
        this.maxPQ = new MaxPQ();
        this.maxPQ.add(this.dsk);
    }

    public MaxPQ getMaxPQ() {
        return maxPQ;
    }

    public void setMaxPQ(MaxPQ maxPQ) {
        this.maxPQ = maxPQ;
    }

    public Disk getDsk() {
        return dsk;
    }

    public void setDsk(Disk dsk) {
        this.dsk = dsk;
    }

    public int getNumOfDisks() {
        return numOfDisks;
    }

    public void setNumOfDisks(int numOfDisks) {
        this.numOfDisks = numOfDisks;
    }

    public float getSumOfAllFolders() {
        return sumOfAllFolders;
    }

    public void setSumOfAllFolders(float sumOfAllFolders) {
        this.sumOfAllFolders = sumOfAllFolders;
    }

    public void greedy(File arg){
        try{
            Scanner myReader = new Scanner(arg);
            // while scanning the file , we process the int read from the file accordingly,
            // and update class variables to print at the end
            while(myReader.hasNextLine()){
                int data = myReader.nextInt();
                //System.out.println(data);
                // if data exceeds 1tb of data or data is of negative value
                if (data < 0 || data > 1000000){
                    System.exit(0);
                }
                // now we will check if the root item of our priority queue has
                // enough space to accommodate the data we wish to write
                if ( data < this.maxPQ.peek().getFreeSpace()){
                    this.sumOfAllFolders += data;
                    this.maxPQ.peek().addData(data);
                }else{
                    Disk anotherDisk = new Disk();
                    this.numOfDisks += 1;
                    anotherDisk.addData(data);
                    this.maxPQ.add(anotherDisk);
                }
            }
            myReader.close();
            return;
        } catch (NoSuchElementException | FileNotFoundException e) {
            return;
        }

    }
    public static void main(String[] args){
        // initializing first Disk , MaxPQ and two class variables to print info later
        Disk firstDisk = new Disk();
        MaxPQ maxPriorityQueue = new MaxPQ();
        maxPriorityQueue.add(firstDisk);

        float sumOfAllFolders = 0;
        int numberOfDisksUsed = 1;

        try{
            File myFile = new File(args[0]);
            Scanner myReader = new Scanner(myFile);
            // while scanning the file , we process the int read from the file accordingly,
            // and update class variables to print at the end
            while(myReader.hasNextLine()){
                int data = myReader.nextInt();
                // if data exceeds 1tb of data or data is of negative value
                if (data < 0 || data > 1000000){
                    System.exit(0);
                }
                // now we will check if the root item of our priority queue has
                // enough space to accommodate the data we wish to write
                if ( data < maxPriorityQueue.peek().getFreeSpace()){
                    sumOfAllFolders += data;
                    firstDisk.addData(data);
                }else{
                    Disk anotherDisk = new Disk();
                    numberOfDisksUsed += 1;
                    anotherDisk.addData(data);
                    maxPriorityQueue.add(anotherDisk);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //printing results
        System.out.println("Sum of all folders = " + sumOfAllFolders);
        System.out.println("Total number of disks used = " + numberOfDisksUsed);

        while(!(maxPriorityQueue.peek() == null)){
            Disk s = maxPriorityQueue.getMax();
            System.out.println("id " + s.getUniqueDiskID() + " " + s.getFreeDiskSpace() + ": " + s.printFolders());
        }
    }
}
