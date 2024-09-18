import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class MerosD {
    public static void main (String [] args) throws IOException {
        Random random = new Random();
        //random.nextInt(1000001) will give us next int in range of 0 - 1000000

        // for loop to create files with random values with 3 ranges of = 100 , 500 , 1000
        for(int i = 0 ; i <= 29; i++){
            if (i>=0 && i<=9){ // create 10 first files with 100 "folders"
                File newFile = new File("100foldersRange"+i+".txt");
                FileWriter myWriter = new FileWriter(newFile);
                for(int x = 0; x <= 9;x++){
                    int randomData = random.nextInt(1000001);
                    myWriter.write(Integer.toString(randomData) + "\n");
                }
                myWriter.close();
            }else if(i>=10 && i<=19){ // create another 10 files with 500 "folders"
                File newFile = new File("500foldersRange"+i+".txt");
                FileWriter myWriter = new FileWriter(newFile);
                for(int x = 0; x <= 49;x++){
                    int randomData = random.nextInt(1000001);
                    myWriter.write(Integer.toString(randomData) + "\n");
                }
                myWriter.close();
            } else if(i >= 20 && i <= 29){ // create another 10 files with 1000 "folders"
                File newFile = new File("1000foldersRange"+i+".txt");
                FileWriter myWriter = new FileWriter(newFile);
                for(int x = 0; x <= 99;x++){
                    int randomData = random.nextInt(1000001);
                    myWriter.write(Integer.toString(randomData) + "\n");
                }
                myWriter.close();
            }
        }
        // now we have all the files created all we need is to execute greedy1 and greedy-decreasing on each given range
        // creating objects of each algorithm for each range of folders (N = 100,500,1000)
        Greedy greedy100 = new Greedy();
        Greedy greedy500 = new Greedy();
        Greedy greedy1000 = new Greedy();

        Sort greedyDecreasing100 = new Sort();
        Sort greedyDecreasing500 = new Sort();
        Sort greedyDecreasing1000 = new Sort();

        String path = "C:\\Users\\Maria\\Desktop\\dmsdedomenon2\\";
        
        for(int i = 0; i <= 29; i++){
            // execute algorithms on 30 txt files , divide them by 3 so we can have the specific ranges in each
            //given object
            if(i >= 0 && i <= 9){
                File newFile = new File(path + "100foldersRange" +i + ".txt");
                greedy100.greedy(newFile);
                greedyDecreasing100.sort(newFile);

            } else if(i >= 10 && i<= 19){
                File newFile = new File(path + "500foldersRange" +i + ".txt");
                greedy500.greedy(newFile);
                greedyDecreasing500.sort(newFile);

            }else if(i>= 20 && i <= 29){
                File newFile = new File(path + "1000foldersRange" +i + ".txt");
                greedy1000.greedy(newFile);
                greedyDecreasing1000.sort(newFile);

            }
        }
        //Print results
        System.out.println("Range Of Folders ( N ) = 100, 500, 1000");
        System.out.println("Greedy-1 disks needed for 100 folders are " + greedy100.getNumOfDisks());
        System.out.println("Greedy-Decreasing disks needed for 100 folders are " + greedyDecreasing100.getDisksUsed()
        + "\n");
        System.out.println("Greedy-1 disks needed for 500 folders are " + greedy500.getNumOfDisks());
        System.out.println("Greedy-Decreasing disks needed for 500 folders are " + greedyDecreasing500.getDisksUsed()
        + "\n");
        System.out.println("Greedy-1 disks needed for 1000 folders are " + greedy1000.getNumOfDisks());
        System.out.println("Greedy-Decreasing disks needed for 1000 folders are " + greedyDecreasing1000.getDisksUsed());

    }
}
