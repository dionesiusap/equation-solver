/******************************************************************************
 *  Compilation:  javac Interpolation.java
 *  Execution:    java Interpolation
 *
 *  Turn set of interpolation with the usual format into matrix
 *
 *  Example of usage
 *
 *  Interpolation interpolation = new Interpolation(2);
 *  interpolation.addInterpolation("2,1");
 *  interpolation.addInterpolation("1,2");
 *  interpolation.writeMatrix();
 *
 *  Output:
 *  1 2 1
 *  1 1 2
 *
 ******************************************************************************/

import java.util.*;

public class Interpolation {

    private ArrayList<ArrayList<Double>> matrix;
    private ArrayList<Double> resultColoumn;
    private ArrayList<Character> listVariable;
    private Integer numOfVariable;

    // Constructor
    // @param numOfVariable the number of x,y that the user going to input
    public Interpolation(Integer numOfVariable) {
        this.numOfVariable = numOfVariable;
        matrix = new ArrayList<ArrayList<Double>>();
        resultColoumn = new ArrayList<Double>();
        listVariable = new ArrayList<Character>();
    }

    // To add interpolation, the input parameter must be a String
    // With the format x,y
    public void addInterpolation(String interpolation) {
        String[] interpolationSplitter = interpolation.split(",");
        ArrayList<Double> lineOfMatrix = new ArrayList<Double>();

        // Add value to it's respective power into line
        for (int i = 0; i < numOfVariable; i++) {
            lineOfMatrix.add(Math.pow(Double.parseDouble(interpolationSplitter[0]), i));
        }
        lineOfMatrix.add(Double.parseDouble(interpolationSplitter[1]));
        // Add the complete line into the matrix
        matrix.add(lineOfMatrix);

        // Add the value from the left of the commas into the result coloumn
        resultColoumn.add(Double.parseDouble(interpolationSplitter[1]));
    }

    public ArrayList<ArrayList<Double>> toMatrix() {
        return matrix;
    }

    private void writeMatrix() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j));
            }
            System.out.println();
        }
    }

}
