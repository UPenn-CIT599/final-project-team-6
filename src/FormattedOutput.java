import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class is to put all answers from RateCalculator into a text file
 * The name if the text file is "Team6Q&A.txt" 
 */
public class FormattedOutput {

	/**
	 * ArrayList for holding the answers to each question.
	 */
	private ArrayList<String> answers;

	public FormattedOutput(ArrayList<String> answers) {

		this.answers = answers;

	}

	/**
	 * Writes a .txt of the answers
	 */
	public void writeAnswers() {
		String filename = "Team6Q&A.txt";

		try (FileWriter fw = new FileWriter(filename)) {

			// Prints each line in answers
			for (String s : answers) {
				fw.write(s+ "\n");

			}

			System.out.println("Write is completed. Please find the answer text in the project directory.");

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not write the File out.  Check permissions, or contact course staff for help");
		}
	}

}
