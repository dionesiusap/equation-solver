
/******************************************************************************
 *
 *  Compilation:  javac Main.java
 *  Execution:    java Main
 *
 *  Main program for solving linear equations.
 *
 *  %java Main
 *  2
 *  test.txt
 *
 *  Output:
 *  Solution for the system:
 *  x: 1.0
 *  y: 1.0
 *
 ******************************************************************************/

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Clearing screen on UNIX terminals
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Standard I/O from keyboard
        Scanner read = new Scanner(System.in);
        
        // User UI for program
        System.out.println("Hello! This is a program for solving a set of linear equations.\nYou can either input your equations manually or from a file.");
        System.out.println("Choose your choice:\n1. Input equations manually\n2. Read equations from file");
        System.out.print("Your choice (1/2): ");
        int method = read.nextInt();
        read.nextLine();
        // System.out.println("Now choose whether your input is in form of equation string (ax+by=c) or matrix:\n1. Input is equation string\n2. Input is array");
        // System.out.print("Your choice (1/2): ");
        // int form = read.nextInt();
        while (method!=1 && method!=2){
            System.out.println("Please enter either 1 or 2.");
            System.out.print("Your choice (1/2): ");
            method = read.nextInt();
            read.nextLine();
        }

        EquationList equationList = new EquationList();
        // equationList.addEquation("2x + 1y = 3");
        // equationList.addEquation("1x + 1y = 2");
        if (method==1){
            System.out.print("\nNumber of equations: ");
            int n = read.nextInt();
            read.nextLine();

            int i;
            String eqn;
            for (i=0;i<n;i++){
                System.out.print("Input equation "+(i+1)+": ");
                eqn = read.nextLine();
                equationList.addEquation(eqn);
            }
        }
        else{
            System.out.print("\nWhat is the name of your file? ");
            String filename = read.nextLine();
            ReadFile readfile = new ReadFile();
            readfile.readEquationFrom(filename, equationList);
        }

        equationList.createMatrix();
        equationList.writeMatrix();
        Matrix matrix = new Matrix(equationList.toMatrix(), equationList.getListOfVariable());
        matrix.gaussianElimination();

    }

}
