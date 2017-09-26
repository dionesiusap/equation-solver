
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

    ArrayList<ArrayList<Integer>> matrix;
    ArrayList<Integer> resultColoumn;
    ArrayList<Character> associative_variable;
    ArrayList<String> equationList;

    public EquationList() {
        matrix = new ArrayList<ArrayList<Integer>>(0);
        associative_variable = new ArrayList<Character>(0);
        equationList = new ArrayList<String>(0);
    }

    public void addEquation(String equation) {
        equationList.add(removeSpace(equation));
    }

    public void createMatrix() {
        for (int i = 0; i < equationList.size(); i++) {
            parseVariable(equationList.get(i));
        }
        // Fill the dinamyc array with 0
        fillEmptyMatrix(associative_variable.size(), equationList.size());
        resultColoumn = new ArrayList<Integer>(equationList.size());
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
        ArrayList<Integer> arrayList = new ArrayList<Integer>(associative_variable.size());
        // Split the string using "+" and "=" as the separator
        String[] parsedEquation = equation.split("\\s*\\+|\\=\\s*");
        for (int i = 0; i < parsedEquation.length - 1; i++) {
            // Parsing the coefficient as integer
            Character variable = parsedEquation[i].charAt(parsedEquation[i].length() - 1);
            parsedEquation[i] = parsedEquation[i].substring(0, parsedEquation[i].length() - 1);
            Integer coefficient = Integer.parseInt(parsedEquation[i]);

            // Add the new coefficient into the matrix Line
            arrayList.add(associative_variable.indexOf(variable), coefficient);
        }
        resultColoumn.add(index, Integer.parseInt(parsedEquation[parsedEquation.length - 1]));
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

    private int addVariable(Character variable) {
        // Only add new variable if it is not found yet
        if (associative_variable.indexOf(variable) == -1) {
            associative_variable.add(variable);
            return 1;
        }
        return -1;
    }

    private void parseVariable(String equation) {
        // Split the string using "+" and "=" as the separator
        String[] parsedEquation = equation.split("\\s*\\+|\\=\\s*");
        for (int i = 0; i < parsedEquation.length - 1; i++) {
            // Parsing the variable
            Character variable = parsedEquation[i].charAt(parsedEquation[i].length() - 1);
            addVariable(variable);
        }
    }

    private void fillEmptyMatrix(int size_x, int size_y) {
        // Dummy empty array list that will be added to the matrix
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < size_x; i++) {
            arrayList.add(0);
        }
        // System.out.println("Number of coloumn: " + arrayList.size());
        // Add the empty array list to the matrix
        for (int i = 0; i < size_y; i++) {
            matrix.add(arrayList);
        }
        // System.out.println("Number of line: " + matrix.size());
    }

}
