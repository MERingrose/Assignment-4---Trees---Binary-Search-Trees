import java.io.FileNotFoundException;

public class BookGenreSuggestions {
    /**
     * 
     * @author Mark Ringrose
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {

        /**
         * Suggests a book genre that might appeal to the user based on their
         * preferences
         * 
         */
        System.out.println("So, you'd like some reccommendations on book genres. (Type Y for Yes, N for No)");

        DecisionTree expert = new DecisionTree("input1.txt");
        expert.evaluate();
    }
}
