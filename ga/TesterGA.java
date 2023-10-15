import java.util.ArrayList;
import java.util.List;

/* TesterGA.java
 *
 * Runs GeneticAlgorithm.java and logs the results into a file using Writer.java.
 * GA testing setup is according to pass/fail criteria
 * Pass criteria - 50 success
 * Fail criteria - 100 failures
 *
 * @author: James M. Bayon-on
 * @version: 1.3
 */

public class TesterGA {
	Writer logWriter;
	static int success = 0;
	static int fail = 0;
	static GeneticAlgorithm ga;
	static int MAX_RUN;
	int MAX_LENGTH;
	long[] runtimes;
	static double sum=0;
	
	static int tailleEchi;
	
	private static List<Chromosome> ma;

	/* Instantiates the TesterGA class
	 *
	 */
	public TesterGA() {
		logWriter = new Writer();
		MAX_RUN = 1; //nbr fois d'execution
		runtimes = new long[MAX_RUN];
	}

	/* Test method accepts the N/max length, and parameters mutation rate and max epoch to set for the GA accordingly.
	 *
	 * @param: max length/n
	 * @param: mutation rate for GA
	 * @param: max epoch for GA
	 */
	public void test(int maxLength, double mutationRate, int maxEpoch) {
		MAX_LENGTH = maxLength;
		ga = new GeneticAlgorithm(MAX_LENGTH);										//define ga here
		ga.setMutation(mutationRate);
		ga.setEpoch(maxEpoch);
		long testStart = System.nanoTime();
		String filepath = "GA-N"+MAX_LENGTH+"-"+mutationRate+"-"+maxEpoch+".txt";
		long startTime = 0;
        long endTime = 0;
        long totalTime = 0;
        
        
        
		logParameters();
        for(int i = 0; i < MAX_RUN; ) {												//run 50 sucess to pass passing criteria
        	startTime = System.currentTimeMillis();
        	if(ga.algorithm()) {
        		endTime = System.currentTimeMillis();
        		totalTime = endTime - startTime;
        		
        		System.out.println("Done");
        		System.out.println("run "+(i+1));
            	System.out.println("time in millisec: "+totalTime);
            	System.out.println("Success!");
            	sum = sum+totalTime;
            	
            	runtimes[i] = totalTime;
            	i++;
            	success++;
            	
            	//write to log
            	logWriter.add((String)("Run: "+i));
            	logWriter.add((String)("Runtime in millisec: "+totalTime));
            	logWriter.add((String)("Found at epoch: "+ga.getEpoch()));
            	logWriter.add((String)("Population size: "+ga.getPopSize()));
            	logWriter.add("");
            	
            	for(Chromosome c: ga.getSolutions()) {								//write solutions to log file
					logWriter.add(c);
					logWriter.add("");
    			}
        	} else {																//count failures for failing criteria
        		fail++;
        		System.out.println("Fail!");
        		//System.out.println(ga.getSolutions().get(i).getConflicts());
        	}
        	
        	if(fail >= 100) {
        		System.out.println("Cannot find solution with these params");
        		break;
        	}
        	startTime = 0;															//reset time
        	endTime = 0;
        	totalTime = 0;
        }
	
        System.out.println("Number of Success: " +success);
        System.out.println("Number of failures: "+fail);
        System.out.println("La moyenne de temps d execution: "+sum/MAX_RUN);
        logWriter.add("Runtime summary");
        logWriter.add("");
        
        
		for(int x = 0; x < runtimes.length; x++){									//print runtime summary
			logWriter.add(Long.toString(runtimes[x]));
		}
		
		long testEnd = System.nanoTime();
		logWriter.add(Long.toString(testStart));
		logWriter.add(Long.toString(testEnd));
		logWriter.add(Long.toString(testEnd - testStart));
		
      
       	logWriter.writeFile(filepath);
       	printRuntimes();
	}

	/* Converts the parameters of GA to string and adds it to the string list in the writer class
	 *
	 */
	public void logParameters() {
        logWriter.add("Genetic Algorithm");
        logWriter.add("Parameters");
        logWriter.add((String)("MAX_LENGTH/N: "+MAX_LENGTH));
        logWriter.add((String)("STARTING_POPULATION: "+ga.getStartSize()));
        logWriter.add((String)("MAX_EPOCHS: "+ga.getMaxEpoch()));
        logWriter.add((String)("MATING_PROBABILITY: "+ga.getMatingProb()));
        logWriter.add((String)("MUTATION_RATE: "+ga.getMutationRate()));
        logWriter.add((String)("MIN_SELECTED_PARENTS: "+ga.getMinSelect()));
        logWriter.add((String)("MAX_SELECTED_PARENTS: "+ga.getMaxSelect()));
        logWriter.add((String)("OFFSPRING_PER_GENERATION: "+ga.getOffspring()));
        logWriter.add((String)("MINIMUM_SHUFFLES: "+ga.getShuffleMin()));
        logWriter.add((String)("MAXIMUM_SHUFFLES: "+ga.getShuffleMax()));
        logWriter.add("");
	}

	/* Prints the runtime summary in the console
	 *
	 */
	public void printRuntimes() {
		for(long x: runtimes){
			System.out.println("run with time "+x+" millisec");
		}	
	}
	

	public Chromosome  main(Chromosome maliste, int n, Double m, Integer k) {
		TesterGA tester = new TesterGA();
		
		tester.test(n ,m, k);
		
		
		Chromosome r = ga.getSolutions().get(0);
		System.out.println(r.getMaxLength());
		System.out.println("mutation: "+m);
		System.out.println("epoc: "+k);
		//System.out.println(r);
		//tester.test(20, 0.001, 5000);
		//tester.test(8, 0.001, 1000);
/*		tester.test(12, 0.001, 1000);
		tester.test(16, 0.001, 1000);
		tester.test(20, 0.001, 1000);
		
		tester.test(20, 0.001, 1000);
		tester.test(20, 0.005, 1000);
		tester.test(20, 0.01, 1000);
		tester.test(20, 0.05, 1000);
		tester.test(20, 0.1, 1000);
		
		tester.test(16, 0.001, 5000);
		tester.test(16, 0.005, 5000);
		tester.test(16, 0.01, 5000);
		tester.test(16, 0.05, 5000);
		tester.test(16, 0.1, 5000);
		
		tester.test(20, 0.001, 5000);
		tester.test(20, 0.005, 5000);
		tester.test(20, 0.01, 5000);
		tester.test(20, 0.05, 5000);
		tester.test(20, 0.1, 5000);
		
 		tester.test(16, 0.001, 1000);
		tester.test(16, 0.005, 1000);
		tester.test(16, 0.01, 1000);
		tester.test(16, 0.05, 1000);
		tester.test(16, 0.1, 1000);
			
		tester.test(16, 0.001, 5000);
		tester.test(16, 0.005, 5000);
		tester.test(16, 0.01, 5000);
		tester.test(16, 0.05, 5000);
		tester.test(16, 0.1, 5000);

		tester.test(16, 0.001, 10000);
		tester.test(16, 0.005, 10000);
		tester.test(16, 0.01, 10000);
		tester.test(16, 0.05, 10000);
		tester.test(16, 0.1, 10000);

		tester.test(20, 0.001, 1000);
		tester.test(20, 0.005, 1000);
		tester.test(20, 0.01, 1000);
		tester.test(20, 0.05, 1000);
		tester.test(20, 0.1, 1000);
		
		tester.test(20, 0.001, 5000);
		tester.test(20, 0.005, 5000);
		tester.test(20, 0.01, 5000);
		tester.test(20, 0.05, 5000);
		tester.test(20, 0.1, 5000);

		tester.test(20, 0.001, 10000);
		tester.test(20, 0.005, 10000);
		tester.test(20, 0.01, 10000);
		tester.test(20, 0.05, 10000);
		tester.test(20, 0.1, 10000);	
*/
		//return null;
		return r;
		
	}
	public static double getT() {
		return sum/MAX_RUN;
	}
	public static double getPopSize() {
		return ga.getPopSize();
	}
	public static double getIter() {
		return ga.getEpoch();
	}
	public static int getSucc() {
		return success;
	}
	
	public static int getFail() {
		return fail;
	}
//success
}
