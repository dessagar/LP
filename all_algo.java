import java.util.Scanner;  
public class Main  
{
    void fcfs()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Total number of PROCESSES: ");
        int n=sc.nextInt();
        int pid[]=new int[n];
        int at[]=new int[n];
        int bt[]=new int[n];
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];
        int temp;
        float awt=0,ata=0;

        for(int i=0;i<n;i++)
        {
            System.out.println("Enter Process "+(i+1)+" ARRIVAL time: ");
            at[i]=sc.nextInt();
            System.out.println("Enter Process "+(i+1)+" BURST time: ");
            bt[i]=sc.nextInt();
            pid[i]=i+1;
        }

        for(int i=0;i<n;i++)
        {
            for (int j=0;j<n-(i+1);j++)
            {
                if(at[j]>at[j+1])
                {
                    temp=at[j];
                    at[j]=at[j+1];
                    at[j+1]=temp;
                    temp=bt[j];
                    bt[j]=bt[j+1];
                    bt[j+1]=temp;
                    temp=pid[j];
                    pid[j]=pid[j+1];
                    pid[j+1]=temp;
                }
            }
        }

        for(int i=0;i<n;i++)
        {
            if(i==0)
            ct[i]=at[i]+bt[i];
            else
            {
                if(at[i]>ct[i-1])
                ct[i]=at[i]+bt[i];
                else
                ct[i]=ct[i-1]+bt[i];
            }
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt[i];
            awt+=wt[i];
            ata+=tat[i];
        }

        System.out.println("\nid\t\tarrival\t\tburst\t\tcomplete\t\tturn\t\twaiting");
        for(int i=0;i<n;i++)
        {
            System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t"+wt[i]);
        }
        sc.close();
        System.out.println("Average Waiting Time : "+(awt/n));
        System.out.println("Average Turnaround Time : "+(ata/n));
    }
    
    void sjf()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Total number of PROCESSES: ");
        int n=sc.nextInt();
        int pid[]=new int[n];
        int at[]=new int[n];
        int bt[]=new int[n];
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];
        int f[]=new int[n];
        int temp,st=0,tot=0;
        float awt=0,ata=0;

        for(int i=0;i<n;i++)
        {
            System.out.println("Enter Process "+(i+1)+" ARRIVAL time: ");
            at[i]=sc.nextInt();
            System.out.println("Enter Process "+(i+1)+" BURST time: ");
            bt[i]=sc.nextInt();
            pid[i]=i+1;
            f[i]=0;
        }
        boolean a=true;
        while(true)
        {
            int c=n,min=999;
            if(tot==n)
            break;
        for(int i=0;i<n;i++)
        {
            if((at[i]<=st)&&(f[i]==0)&&(bt[i]<min))
            {
                min=bt[i];
                c=i;
            }
        }
        if(c==n)
        st++;
        else{
            ct[c]=st+bt[c];
            st+=bt[c];
            tat[c]=ct[c]-at[c];
            wt[c]=tat[c]-bt[c];
            f[c]=1;
            tot++;
            }
        }
        System.out.println("\nid\t\tarrival\t\tburst\t\tcomplete\t\tturn\t\twaiting");
        for(int i=0;i<n;i++)
        {
            awt+=wt[i];
            ata+=tat[i];
            System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t\t"+tat[i]+"\t\t"+wt[i]);
        }
        System.out.println("Average Waiting Time : "+(awt/n));
        System.out.println("Average Turnaround Time : "+(ata/n));
        sc.close();
    }
    
    void rr()
    {
        int n,i,qt,count=0,temp,sq=0,bt[],wt[],tat[],rem_bt[];  
        float awt=0,atat=0;  
        bt = new int[10];  
        wt = new int[10];  
        tat = new int[10];  
        rem_bt = new int[10];  
        Scanner s=new Scanner(System.in);  
        System.out.print("Enter the number of process (maximum 10) = ");  
        n = s.nextInt();  
        System.out.print("Enter the burst time of the process\n");  
        for (i=0;i<n;i++)  
            {  
                System.out.print("P"+i+" = ");   
                bt[i] = s.nextInt();  
                rem_bt[i] = bt[i];  
            }  
        System.out.print("Enter the quantum time: ");  
        qt = s.nextInt();  
        while(true)  
        {  
            for (i=0,count=0;i<n;i++)  
                {  
                        temp = qt;  
                        if(rem_bt[i] == 0)  
                            {  
                                count++;  
                                continue;  
                            }  
                        if(rem_bt[i]>qt)  
                        rem_bt[i]= rem_bt[i] - qt;  
                        else  
                            if(rem_bt[i]>=0)  
                                {  
                                    temp = rem_bt[i];  
                                    rem_bt[i] = 0;  
                                }  
                    sq = sq + temp;  
                    tat[i] = sq;  
                }  
            if(n == count)  
            break;  
        }  
        System.out.print("--------------------------------------------------------------------------------");  
        System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");  
        System.out.print("--------------------------------------------------------------------------------");  
        for(i=0;i<n;i++)  
            {  
                wt[i]=tat[i]-bt[i];  
                awt=awt+wt[i];  
                atat=atat+tat[i];  
                System.out.print("\n "+(i+1)+"\t "+bt[i]+"\t\t "+tat[i]+"\t\t "+wt[i]+"\n");  
            }  
        awt=awt/n;  
        atat=atat/n;  
        System.out.println("\nAverage waiting Time = "+awt+"\n");  
        System.out.println("Average turnaround time = "+atat);
    }
    
    void priority()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Total number of PROCESSES: ");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int pr[] = new int[n];
        int temp;
        float awt = 0, ata = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Process " + (i + 1) + " Priority: ");
            pr[i] = sc.nextInt();

            // System.out.println("Enter Process " + (i + 1) + " ARRIVAL time: ");
            // at[i] = sc.nextInt();

            System.out.println("Enter Process " + (i + 1) + " BURST time: ");
            bt[i] = sc.nextInt();

            pid[i] = i + 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (pr[j] > pr[j + 1]   ) {

                    temp = pr[j];
                    pr[j] = pr[j + 1];
                    pr[j + 1] = temp;

                    // temp = at[j];
                    // at[j] = at[j + 1];
                    // at[j + 1] = temp;
                    

                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    temp = pid[j];
                    pid[j] = pid[j + 1];

                    pid[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == 0)
                ct[i] = bt[i];
            else {
                if (at[i] > ct[i - 1])
                    ct[i] =  bt[i];
                else
                    ct[i] = ct[i - 1] + bt[i];
            }
            tat[i] = ct[i] ;
            wt[i] = tat[i] - bt[i];
            awt += wt[i];
            ata += tat[i];
        }

        System.out.println("\nid\t\tPR\t\tarrival\t\tburst\t\tcomplete\t\tturn\t\twaiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t\t" + pr[i] + "\t\t" +  "0"  + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t\t"
                    + tat[i] + "\t\t" + wt[i]);
        }
        sc.close();
        System.out.println("Average Waiting Time : " + (awt / n));
        System.out.println("Average Turnaround Time : " + (ata / n));
    }
    
    public static void main(String args[])  
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("1.FCFS \n 2.SJF \n 3.RR \n 4.priority");
        int n=sc.nextInt();
        
        Main obj=new Main();
        
        if(n==1)
		obj.fcfs();
	else if(n==2)
		obj.sjf();
	else if(n==3)
		obj.rr();
	else if(n==4)
		obj.priority();
		
	sc.close();	
    }
}    