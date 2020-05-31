import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * This class compares the performances of the following data structures:
 * OpenHashSet, ClosedHashSet, Java's LinkedList, Java's HashSet and
 * Java's TreeSet.
 */
public class SimpleSetPerformanceAnalyzer {

    private static final int TEST_REPETITIONS = 70000;
    private static final int WARM_UP_REPETITIONS = 70000;
    private static final int LINKED_LIST_TEST_REPETITIONS_FOR_CONTAINS = 7000;
    private static final int DATA_STRUCTURES_ARRAY_SIZE = 5;
    private static final String[] DATA_STRUCTURES_NAMES = {"Java's LinkedList", "ClosedHashSet",
            "OpenHashSet", "Java's TreeSet", "Java's HashSet"};

    private static final String HI_STRING = "hi";
    private static final String NEGATIVE_NUMBER_STRING = "-13170890158";
    private static final String TWENTY_THREE_STRING = "23";
    private static final String MILLISECONDS_UNIT_STRING = "milliseconds";
    private static final int CONVERT_NANO_TO_MILLI = 1000000;

    /**
     * This method is the main method which analyses the running time of adding
     * values or checking whether or not an item is in any of all the 5 types of
     * the data structures: OpenHashSet, ClosedHashSet, Java's LinkedList, Java's
     * HashSet and Java's TreeSet.
     * In order to do that we use the files data1.txt and data2.txt.
     * @param args args.
     */
    public static void main(String[] args){


        String[] data1Array, data2Array;

        data1Array = Ex3Utils.file2array("data1.txt");
        data2Array = Ex3Utils.file2array("data2.txt");

        String results = "%s: Adding Words Running Time: %d ms. \nContains '%s' Running " +
                "Time: %d ns. \nContains '%s' Running Time: %d ns.\n";

        SimpleSet[] dataStructuresArray = new SimpleSet[DATA_STRUCTURES_ARRAY_SIZE];

        // The arrays in which the running time of data1 is stored.
        long addingTimeResultsData1[] = new long[DATA_STRUCTURES_ARRAY_SIZE];
        long containsHiTimeResultsData1[] = new long[DATA_STRUCTURES_ARRAY_SIZE];
        long containsNegativeNUmberTimeResultsData1[] = new long[DATA_STRUCTURES_ARRAY_SIZE];

        // The arrays in which the running time of data2 is stored.
        long addingTimeResultsData2[] = new long[DATA_STRUCTURES_ARRAY_SIZE];
        long containsHiTimeResultsData2[] = new long[DATA_STRUCTURES_ARRAY_SIZE];
        long containsNum23TimeResultsData2[] = new long[DATA_STRUCTURES_ARRAY_SIZE];

        // Now we start analysing data1.txt.
        System.out.println("-------------Start checking Data1: -------------");

        restartDataStructuresArray(dataStructuresArray);
        addToArrayAnalysis(dataStructuresArray, data1Array, addingTimeResultsData1);

        checkContainsInArrayAnalysis(dataStructuresArray, HI_STRING, containsHiTimeResultsData1);
        checkContainsInArrayAnalysis(dataStructuresArray, NEGATIVE_NUMBER_STRING,
                containsNegativeNUmberTimeResultsData1);

        // Now we start analysing data2.txt.
        System.out.println("-------------Start checking Data2: -------------");

        restartDataStructuresArray(dataStructuresArray);
        addToArrayAnalysis(dataStructuresArray, data2Array, addingTimeResultsData2);
        checkContainsInArrayAnalysis(dataStructuresArray, HI_STRING, containsHiTimeResultsData2);
        checkContainsInArrayAnalysis(dataStructuresArray, TWENTY_THREE_STRING, containsNum23TimeResultsData2);


        // Now we print the results for data1.txt.
        System.out.println("-------------Printing Results: -------------\n");


        System.out.println("-------------Data1 Results: -------------\n");

        for(int i = 0; i < DATA_STRUCTURES_ARRAY_SIZE; i++){

            System.out.println(String.format(results, DATA_STRUCTURES_NAMES[i], addingTimeResultsData1[i],
                    HI_STRING, containsHiTimeResultsData1[i], NEGATIVE_NUMBER_STRING,
                    containsNegativeNUmberTimeResultsData1[i]));

        }

        // Now we print the results for data2.txt.
        System.out.println("\n-------------Data2 Results: -------------\n");

        for(int i = 0; i < DATA_STRUCTURES_ARRAY_SIZE; i++){

            System.out.println(String.format(results, DATA_STRUCTURES_NAMES[i], addingTimeResultsData2[i],
                    HI_STRING, containsHiTimeResultsData2[i], TWENTY_THREE_STRING,
                    containsNum23TimeResultsData2[i]));

        }

    }

    /**
     * This method analyses the running time of checking whether or not an item is in any of all
     * the 5 types of the data structures: OpenHashSet, ClosedHashSet, Java's LinkedList, Java's
     * HashSet and Java's TreeSet.
     * @param dataStructuresArray the array which contains all the 5 data structures.
     * @param stringToSearch the string we need to search for in each data structure.
     * @param runTimeResults the array of running time results we need to update.
     */
    private static void checkContainsInArrayAnalysis(SimpleSet[] dataStructuresArray, String stringToSearch,
                                                     long[] runTimeResults){

        long startingTime, endingTime, difference;

        startingTime = System.nanoTime();

        //Firstly we analyze the linked list running time:
        for(int j = 0; j < LINKED_LIST_TEST_REPETITIONS_FOR_CONTAINS; j++){
            dataStructuresArray[0].contains(stringToSearch);
        }
        endingTime = System.nanoTime();
        difference = endingTime - startingTime;
        runTimeResults[0] = difference / LINKED_LIST_TEST_REPETITIONS_FOR_CONTAINS;

        //Now we are analyzing all the other data structures:
        for(int i = 1 ; i < DATA_STRUCTURES_ARRAY_SIZE; i++){
            warmUp(dataStructuresArray[i], stringToSearch);
            startingTime = System.nanoTime();
            for(int j = 0 ; j < TEST_REPETITIONS; j++){
                dataStructuresArray[i].contains(stringToSearch);
            }
            endingTime = System.nanoTime();
            difference = endingTime - startingTime;
            runTimeResults[i] = difference / TEST_REPETITIONS;

        }

    }

    /**
     * This method analyses the running time of adding values to any of all the 5 types of the data
     * structures: OpenHashSet, ClosedHashSet, Java's LinkedList, Java's HashSet and Java's TreeSet.
     * @param dataStructuresArray the array which contains all the 5 data structures.
     * @param testedDataArray the data structure we want to test its performance.
     * @param runTimeResults the array of running time results we need to update.
     */
    private static void addToArrayAnalysis(SimpleSet[] dataStructuresArray, String[] testedDataArray,
                                           long[] runTimeResults){

        long startingTime, endingTime, difference;
        for(int i = 0; i < DATA_STRUCTURES_ARRAY_SIZE; i++){
            startingTime = System.nanoTime();
            System.out.println(DATA_STRUCTURES_NAMES[i] + "Start analyzing: ");
            for(int j = 0; j < testedDataArray.length; j++){
                dataStructuresArray[i].add(testedDataArray[j]);
            }

            endingTime = System.nanoTime();
            difference = (endingTime - startingTime) / CONVERT_NANO_TO_MILLI;
            System.out.println("Data Structure was loaded in " + (difference) + " "
                    + MILLISECONDS_UNIT_STRING + "\n");
            runTimeResults[i] = difference;

        }
    }

    /**
     * This method warms up the JVM by calling the contains function of the
     * relevant data structure 70,000 times before we start counting the running time
     * of checking whether or not an item is in the data structure, except java's linked list.
     * @param testedSet the data structure we want to test its performance.
     * @param stringToSearch the string we need to search for in the data structure.
     */
    private static void warmUp(SimpleSet testedSet, String stringToSearch){
        for(int i = 0; i < WARM_UP_REPETITIONS; i++){
            testedSet.contains(stringToSearch);
        }
    }

    /**
     * This method initializes the array of all the 5 data structures we want to check.
     * @param dataStructuresArray the array of all the 5 data structures we want to check.
     */
    private static void restartDataStructuresArray(SimpleSet[] dataStructuresArray){

        dataStructuresArray[0] = new CollectionFacadeSet(new LinkedList<String>());
        dataStructuresArray[1] = new ClosedHashSet();
        dataStructuresArray[2] = new OpenHashSet();
        dataStructuresArray[3] = new CollectionFacadeSet(new TreeSet<String>());
        dataStructuresArray[4] = new CollectionFacadeSet(new HashSet<String>());
    }

}
