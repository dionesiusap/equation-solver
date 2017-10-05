import java.util.*;

public class HilbertMatrix {

    private ArrayList<ArrayList<Double>> matrix;
    private Integer numOfVariable;

    public HilbertMatrix(Integer numOfVariable) {
        this.numOfVariable = numOfVariable;
        matrix = new ArrayList<ArrayList<Double>>();
        fillMatrix();
    }
    public ArrayList<ArrayList<Double>> toMatrix(){
        return matrix;
    }

    private void fillMatrix() {
        for (int i = 0; i < numOfVariable; i++) {
            ArrayList<Double> lineOfMatrix = new ArrayList<Double>();
            for (int j = 0; j < numOfVariable; j++) {
                double p = (double) 1/(i+j+1);
                lineOfMatrix.add(p);
            }
            lineOfMatrix.add(1.0);
            matrix.add(lineOfMatrix);
        }
    }
}
