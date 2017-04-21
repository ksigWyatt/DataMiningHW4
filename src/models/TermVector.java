package models;
import java.util.HashMap;

public class TermVector {
    private int[] termVector; // the vector contain the word counts
    private boolean isSpam; // only used in training to mark if the email was spam
    private double magnitude; // the magnitude of the vector
    private String fileName; // the name of the file

    /**
     * used for the testing vectors
     * @param termVector the int[] of word counts in the email
     */
    public TermVector(int[] termVector){
        this.termVector = termVector;

        // calculate the magnitude here so to not have to do every time we want to cosine similarity
        double sum = 0;
        for(int i : termVector){
            sum += Math.pow(i,2);
        }
        this.magnitude = Math.sqrt(sum);
    }

    /**
     * this constructor is used only for training vectors
     * @param termVector the int[] of word counts in the email
     * @param isSpam spam or not
     */
    public TermVector(int[] termVector, boolean isSpam){
        this.termVector = termVector;
        this.isSpam = isSpam;
        double sum = 0;
        for(int i : termVector){
            sum += Math.pow(i,2);
        }
        this.magnitude = Math.sqrt(sum);
    }

    public int[] getTermVector() {
        return termVector;
    }

    public boolean isSpam() {
        return isSpam;
    }

    // used to map the values of each calculation of the cosine similarities to a Term vector since the calculation
    // of the cosine doesn't change due to k changing we can store the previous calculation here
    HashMap<TermVector, Double> previousCals = new HashMap<>();

    /**
     * used to calculate the cosine similar of the passed vector
     * @param vector the vector we want to cosine similarity to
     * @return the cosine similarity
     */
    public double cosineSimilarity(TermVector vector){
        if(previousCals.containsKey(vector)){
            return previousCals.get(vector);
        }

        double sum = 0;

        for(int i = 0; i < vector.getTermVector().length; i++){
            sum += this.termVector[i] * vector.getTermVector()[i];
        }

        previousCals.put(vector, sum / (this.magnitude * vector.getMagnitude()));

        return sum / (this.magnitude * vector.getMagnitude());
    }


    public double getMagnitude() {
        return magnitude;
    }

    /**
     * used for debugging and seeing the term vector
     */
    public void printVector(){
        for(int i : termVector){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTermVector(int[] termVector) {
        this.termVector = termVector;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
}
