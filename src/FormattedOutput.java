package team6Final; 

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class generates a display of all answers from RateCalculator into a text file.
 * The name of the text file is "Team6Q&A.txt".
 */
public class FormattedOutput {

	/**
	 * This is an arrayList which stores the answers to each question.
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

			System.out.println("Write is complete. Please find the answer text in the project directory.");

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not write the File.");
		}
	}

}