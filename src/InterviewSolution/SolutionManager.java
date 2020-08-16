package InterviewSolution;

public class SolutionManager {

	public static void main(String[] args) {
		String inputFile = args[0];
		String outputFile = args[1];

		LocationProcessor cp = new LocationProcessor(inputFile);
		cp.writeResultFile(outputFile);
		
	}
}
