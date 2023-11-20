public class WorstFit {
    
    static void worstFit(int blockSize[], int m, int processSize[],int n)
    {
        
        int allocation[] = new int[n];
        
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;
       
        for (int i = 0; i < n; i++) 
        {
            
            int wstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (wstIdx == -1)
                        wstIdx = j;
                    else if (blockSize[wstIdx] < blockSize[j])
                        wstIdx = j;
                }
            }
            
            if (wstIdx != -1) {
                
                allocation[i] = wstIdx;

                blockSize[wstIdx] -= processSize[i];
            }
        }
        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t\t\t\t\t" + processSize[i] + "\t\t\t\t\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    // Driver Method
    public static void main(String[] args) {
        System.out.print("\n Worst Fit Memory Allocation \n");
        int blockSize[] = { 30, 50, 200, 700, 980 };
        int processSize[] = { 20, 200, 500, 50 };
        int m = blockSize.length;
        int n = processSize.length;
        worstFit(blockSize, m, processSize, n);
    }
}