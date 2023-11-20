// import java.util.Arrays;

public class Next_Fit 
{
    
    static void NextFit(int blockSize[], int m, int processSize[], int n) {
        
        int allocation[] = new int[n];
        int j = 0;
        int status[]= new int[m];
                
        for (int i = 0; i < allocation.length; i++)
        {
            allocation[i] = -1;
            status[i]=0;
        }  
        
        for (int i = 0; i < n; i++) {
            
            int count = 0;
            while (j < m) 
            {
                count++; // makes sure that for every process we traverse through entire array maximum
                         // once only.This avoids the problem of going into infinite loop if memory is
                         // not available
                System.out.print(count +" ");         
                if (blockSize[j] >= processSize[i]) {
                    
                    allocation[i] = j;
                    status[j]=1;
                    blockSize[j] -= processSize[i];
                    break;
                }
                
                j = (j + 1) % m;
            }
        }

        System.out.print("\nProcess No.\tProcess Size\tBlock no.\n");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t\t\t\t\t\t" + processSize[i]+ "\t\t\t\t\t\t");
            if (allocation[i] != -1) 
            {
                System.out.print(allocation[i] + 1);
            } else 
            {
                System.out.print("Not Allocated");
            }
            System.out.println("");
        }
    }

    // Driver program
    static public void main(String[] args) {
        System.out.print("\n Next Fit Memory Allocation \n");
        int blockSize[] = { 5, 10, 20 };
        int processSize[] = { 10, 20, 5 };
        int m = blockSize.length;
        int n = processSize.length;
        NextFit(blockSize, m, processSize, n);
    }
}