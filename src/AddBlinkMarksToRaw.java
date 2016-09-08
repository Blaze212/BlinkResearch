import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class AddBlinkMarksToRaw {

	static String rawFilePath = "";
	static String blinksFilePath = "";
	double[] timeBlinks;
	double[] marks;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddBlinkMarksToRaw bmr = new AddBlinkMarksToRaw();


	}


	public AddBlinkMarksToRaw(){
		AnalyzeRawBlinks raw = new AnalyzeRawBlinks(rawFilePath, false);
		readBlinksFile(blinksFilePath);
		combineData(raw);
	}

	public void combineData(AnalyzeRawBlinks raw){
		String line;
		try(Writer writer = new BufferedWriter(new OutputStreamWriter 
				(new FileOutputStream("BartonStudy3Marked.csv"), StandardCharsets.UTF_8))){	
			writer.write("Time, " + "Blink");
			for(int i=0;i<raw.timestamps.length;i++){

				line = String.format("%d, %d, %d, %d, %d \n", 
						raw.timestamps[i], raw.leftPoints[i], raw.rightPoints[i],timeBlinks[i], marks[i]);
				writer.write(line);
			}
			System.out.println("Saved");
		}catch (Exception e){
			System.out.println("failed");
		}

		System.out.println("Saved");
	}


	public void readBlinksFile(String path){
		File f = new File(path);
		Scanner scanner = null;
		ArrayList<Double> timesAL = new ArrayList<Double>();
		ArrayList<Double> marksAL = new ArrayList<Double>();

		try {
			scanner = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.nextLine(); //skips the headers

		while(scanner.hasNextLine()) {
			String thisInfoString = scanner.nextLine();
			String[] pieces = thisInfoString.split(",");

			timesAL.add(Double.parseDouble(pieces[0]));
			marksAL.add(Double.parseDouble(pieces[1]));


			//System.out.printf("P: %s %s %s \n", pieces[0], pieces[1], pieces[2]);
		}
		timeBlinks= new double[timesAL.size()];
		marks = new double[timesAL.size()];

		for(int i=0;i<timesAL.size();i++){
			timeBlinks[i]= timesAL.get(i);
			marks[i]= marksAL.get(i);

		}

		//System.out.println(time);
		//System.out.println(left);
		//System.out.println(right);
		//System.out.println(timestamps[4]);
		//System.out.println(leftPoints[4]);
		//System.out.println(rightPoints[2354]);

	}

}
