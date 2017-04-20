package models;

public class TermVector {
    private int[] termVector;
    private boolean isSpam;
    private double cosSim;
    private double magnitude;

    public  TermVector(int[] termVector){
        this.termVector = termVector;
    }

    public TermVector(int[] termVector, boolean isSpam){
        this.termVector = termVector;
        this.isSpam = isSpam;
        this.magnitude = calculateMagnitude();
    }

    public int[] getTermVector() {
        return termVector;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public double calculateMagnitude(){
        double sum = 0;
        for(int i : termVector){
            sum += Math.pow(i,2);
        }
        return Math.sqrt(sum);
    }

    public double cosineSimilarity(TermVector vect){
        return dotProduct(this, vect) / (this.getMagnitude() * vect.getMagnitude());
    }

    public double dotProduct(TermVector d1, TermVector d2){
        double dotProduct = 0;
        for(int i = 0; i < d1.getTermVector().length; i++) {
            dotProduct += (d1.getTermVector()[i] * d2.getTermVector()[i]);
        }
        return dotProduct;
    }

    public void setCosSim(double sim){
        this.cosSim = sim;
    }

    public double getCosSim() {
        return cosSim;
    }

    public double getMagnitude() {
        return magnitude;
    }
}
