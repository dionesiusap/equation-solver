
/******************************************************************************
 *  Compilation:  javac EquationList.java
 *  Execution:    java EquationList
 *
 *  Turn set of equation with the usual format into matrix
 *  Note that the name of the variable must consist of one word
 *  Or else the program will crash
 *
 *  Example of usage
 *
 *  EquationList equationList = new EquationList();
 *  equationList.addEquation("3x + 5y + 6z + 2a = 3");
 *  equationList.addEquation("4x + 4y + 6z + 2a = 6");
 *  equationList.addEquation("4x + 4y + 6z + 2a = 6");
 *  equationList.createMatrix();
 *  equationList.writeMatrix();
 *
 *  Output:
 *  3 5 6 2 3
 *  3 4 6 2 6
 *  3 4 6 2 6
 *
 ******************************************************************************/

import java.util.*;

public class EquationList {

    private ArrayList<ArrayList<Integer>> matrix;
    private ArrayList<Integer> resultColoumn;
    private HashMap<Character, Integer> associative_variable;
    private ArrayList<String> equationList;
    private Integer numOfVariable = 0;


    public EquationList() {
        matrix = new ArrayList<ArrayList<Integer>>();
        associative_variable = new HashMap<Character, Integer>();
        equationList = new ArrayList<String>();
    }

    public void addEquation(String equation) {
        equationList.add(removeSpace(equation));
    }

    public void createMatrix() {
        //System.out.println(associative_variable.size());
        for (int i = 0; i < equationList.size(); i++) {
            parseVariable(equationList.get(i));
            //System.out.println(associative_variable.size());
        }
        // Fill the dinamyc array with 0
        fillEmptyMatrix(associative_variable.size(), equationList.size());
        resultColoumn = new ArrayList<Integer>(equationList.size());
        // System.out.println("Number of line " + matrix.size());
        // System.out.println("Number of equation " + equationList.size());
        for (int i = 0; i < equationList.size(); i++) {
            matrix.set(i, parseCoefficient(equationList.get(i), i));
        }
        // Adding result coloumn
        for (int i = 0; i < equationList.size(); i++) {
            matrix.get(i).add(resultColoumn.get(i));
        }
    }

    public void writeMatrix() {
        // System.out.println(associative_variable.size());
        // System.out.println(matrix.size());
        // System.out.println(matrix.get(0).size());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println("");
        }
    }

    public ArrayList<ArrayList<Integer>> toMatrix() {
        return matrix;
    }

    private ArrayList<Integer> parseCoefficient(String equation, int index) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(Collections.nCopies(associative_variable.size(), 0));
        // System.out.println("associative_variable: " + associative_variable.size());
        // Split the string using "+" and "=" as the separator
        String[] parsedEquation = equation.split("\\s*\\+|\\=\\s*");
        for (int i = 0; i < parsedEquation.length - 1; i++) {
            // Parsing the coefficient as integer
            Character variable = parsedEquation[i].charAt(parsedEquation[i].length() - 1);
            parsedEquation[i] = parsedEquation[i].substring(0, parsedEquation[i].length() - 1);
            Integer coefficient = Integer.parseInt(parsedEquation[i]);

            // Add the new coefficient into the matrix Line
            // System.out.println(associative_variable.get(variable));
            arrayList.set(associative_variable.get(variable), coefficient);
        }
        resultColoumn.add(index, Integer.parseInt(parsedEquation[parsedEquation.length - 1]));
        // System.out.println("arrayList size: " + arrayList.size());
        return arrayList;
    }

    private String removeSpace(String equation) {
        // Split the string into array of string
        // with \\s as the separator
        String[] removedSpace = equation.split(" ");
        equation = "";
        // Concat the array of string into one single
        // string
        for (int i = 0; i < removedSpace.length; i++) {
            equation += removedSpace[i];
        }
        return equation;
    }

    /* Replaced with HashMap
    private int addVariable(Character variable) {
        // Only add new variable if it is not found yet
        if (associative_variable.indexOf(variable) == -1) {
            associative_variable.add(variable);
            return 1;
        }
        return -1;
    }
    */

    private void parseVariable(String equation) {
        // Split the string using "+" and "=" as the separator
        String[] parsedEquation = equation.split("\\s*\\+|\\=\\s*");
        for (int i = 0; i < parsedEquation.length - 1; i++) {
            // Parsing the variable
            Character variable = parsedEquation[i].charAt(parsedEquation[i].length() - 1);
            if(!associative_variable.containsKey(variable)) {
                associative_variable.put(variable, numOfVariable);
                numOfVariable++;
            }
        }
    }

    private void fillEmptyMatrix(int size_x, int size_y) {
        // Dummy empty array list that will be added to the matrix
        // System.out.println("Number of coloumn: " + size_x);
        ArrayList<Integer> arrayList = new ArrayList<Integer>(Collections.nCopies(size_x, 0));
        // System.out.println("Number of coloumn: " + arrayList.size());
        // Add the empty array list to the matrix
        for (int i = 0; i < size_y; i++) {
            matrix.add(arrayList);
        }
        // System.out.println("Number of line: " + matrix.size());
    }

}
