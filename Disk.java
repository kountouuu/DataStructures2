import java.io.File;

public class Disk implements Comparable<Disk> {
    private final int diskSpace = 1000000; // units in megabytes = 1TB
    private static int ID = 0;
    private int uniqueDiskID;
    private int[] folders;
    private int sizeOfFolders;
    private int allocatedDiskSpace; // doesnt update value when folder is introduced
    private int freeDiskSpace;

    Disk(){
        ID++; // ID starts from 1
        this.uniqueDiskID = ID; // allocating unique ID
        this.freeDiskSpace = diskSpace; // allocating the 1tb worth of memory to disk
        this.folders = new int[10];
        this.sizeOfFolders = 0;
    }

    Disk(int arg){
        ID++;
        this.uniqueDiskID = ID;
        this.freeDiskSpace = diskSpace - arg;
        this.folders = new int[10];
        this.sizeOfFolders = 0;
    }
    public int getDiskSpace() {
        return diskSpace;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Disk.ID = ID;
    }

    public int getUniqueDiskID() {
        return uniqueDiskID;
    }

    public void setUniqueDiskID(int uniqueDiskID) {
        this.uniqueDiskID = uniqueDiskID;
    }

    public int getFreeSpace(){
        return freeDiskSpace;
    }

    public void addData(int data){
        this.freeDiskSpace -= data;
        this.allocatedDiskSpace += data;
        this.folders[sizeOfFolders] = data;
        sizeOfFolders++;
    }

    public String printFolders(){
        String s = "";
        for(int i = 0; i < this.sizeOfFolders ; i++){
            s += this.folders[i] + " ";
        }
        return s;
    }

    public int getAllocatedDiskSpace() {
        return allocatedDiskSpace;
    }

    public void setAllocatedDiskSpace(int allocatedDiskSpace) {
        this.allocatedDiskSpace = allocatedDiskSpace;
    }

    public int getFreeDiskSpace() {
        return freeDiskSpace;
    }

    public void setFreeDiskSpace(int freeDiskSpace) {
        this.freeDiskSpace = freeDiskSpace;
    }

    public int[] getFolders() {
        return folders;
    }

    public void setFolders(int[] folders) {
        this.folders = folders;
    }

    @Override
    public int compareTo(Disk o) {
        if(this.freeDiskSpace > o.getFreeSpace()){
            return 1;
        }else if(this.freeDiskSpace == o.getFreeSpace()){
            return 0;
        }else{
            return -1;
        }
    }
}
