import java.io.*;
import java.util.Scanner;
import  java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
public class Main
{

	public static void main(String[] args) throws Exception
	{
		String fileName;
		Scanner stdin = new Scanner(System.in);
		System.out.println("Enter File Name");
		fileName = stdin.nextLine();
		String filePath = ("C:\\Users\\psbre\\eclipse-workspace\\parking\\" + fileName + ".txt");
		File file = new File(filePath); 
			  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		int curr_capacity = 0;
		Duration duration = null;
		float paid = 0;
		LocalDateTime curr_time = null;
		String[] L_Plates = new String[100];
		LocalDateTime[] d_times = new LocalDateTime[100];
		LocalDateTime[] a_times = new LocalDateTime[100];
		String st;
		int lot_capacity = 10;
		int price_per_hour = 2;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime check_in = null;
		LocalDateTime check_out = null;
		double profit = 0;
		int count = 0;
		
		
		while ((st = br.readLine()) != null || curr_capacity > 0)
		{
			if(st != null)
				
			if(curr_capacity < lot_capacity)
			{
				check_in = LocalDateTime.now();

				    	Random rin = new Random();
				    	int nin = rin.nextInt(10);
				    	check_in = check_in.minusSeconds(nin);
				    	System.out.println("Vehicle " + st + " arrived at " + dtf.format(check_in));
				    	L_Plates[count] = st;
				    	a_times[count] = check_in;
				    	
				    	Random rout = new Random();
				    	int nout = rout.nextInt(10);
				    	check_out = check_in.plusSeconds(nout);
				    	d_times[count] = check_out;
				    	curr_capacity++;
				    	count++;
			}
			else if(curr_capacity >= lot_capacity)
			{
				System.out.println("Vehicle arrived, but lot was full");
				
			}
			curr_time = LocalDateTime.now();
			for (int i = 0; i < count; i++)
			{
				if(curr_time.isAfter(d_times[i]))
				{
					duration = Duration.between(a_times[i], d_times[i]);
					paid = duration.getSeconds() * price_per_hour; 
					profit += paid;
					System.out.println("Vehicle " + L_Plates[i] + 
							" is leaving at " + dtf.format(d_times[i]) + "." + 
							 " They stayed for " + Math.abs(duration.getSeconds()) + 
							 " seconds and payed $" + Math.abs(paid) + ".");
					d_times[i] = d_times[i].MAX;
					curr_capacity--;
				}
			}
		}
		System.out.println("Todays Profit = $" + profit);

	}

}
