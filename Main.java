
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
        System.out.println("Hello! This is a program for solving a set of linear equations.\nYou can solve a linear equation system, a Hilbert matrix, or an interpolation.");
        System.out.println("Choose your choice:\n1. Linear equation system\n2. Hilbert matrix\n3. Interpolation");
        System.out.print("Your choice (1/2/3): ");
        int method = read.nextInt();
        read.nextLine();

        while (method!=1 && method!=2 && method!=3){
            System.out.println("Please enter either 1, 2, or 3.");
            System.out.print("Your choice (1/2/3): ");
            method = read.nextInt();
            read.nextLine();
        }
        
        System.out.println("You can also choose to input manually or from a file.");
        System.out.println("Choose your choice:\n1. Input manually\n2. Read from file");
        System.out.print("Your choice (1/2): ");
        int inp = read.nextInt();
        read.nextLine();

        while (inp!=1 && inp!=2){
            System.out.println("Please enter either 1 or 2.");
            System.out.print("Your choice (1/2): ");
            method = read.nextInt();
            read.nextLine();
        }

        // Linear Equation
        if (method==1){
            EquationList equationList = new EquationList();
            // equationList.addEquation("2x + 1y = 3");
            // equationList.addEquation("1x + 1y = 2");
            if (inp==1){
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
                ReadFile readeqnfile = new ReadFile();
                readeqnfile.readEquationFrom(filename, equationList);
            }

            equationList.createMatrix();
            equationList.writeMatrix();
            Matrix matrix = new Matrix(equationList.toMatrix(), equationList.getListOfVariable());
            matrix.gaussianElimination();
        }

        // Hilbert Matrix
        else if (method==2){
            int n;
            System.out.print("Input n: ");
            n = read.nextInt();
            read.nextLine();
            // else{
            //     System.out.print("\nWhat is the name of your file? ");
            //     String filename = read.nextLine();
            //     ReadFile readhilbertfile = new ReadFile();
            //     readhilbertfile.readHilbertNFrom(filename);
            // }

            HilbertMatrix hilbertMatrix = new HilbertMatrix(n);
            Matrix hilbMatrix = new Matrix(hilbertMatrix.toMatrix());
            hilbMatrix.gaussianElimination();

            ArrayList<Double> solution;
            solution = hilbMatrix.getSolution();

            for (int i=0;i<solution.size();i++){
                System.out.println(solution.get(i).toString()+"x"+Integer.toString(i));
            }
        }

        // Interpolation
        else if (method==3){
            int p;
            System.out.print("Input number of points: ");
            p = read.nextInt();
            read.nextLine();

            Interpolation interpolation = new Interpolation(p);

            if (inp==1){
                int j;
                String point;
                for (j=0;j<p;j++){
                    point = read.nextLine();
                    interpolation.addInterpolation(point);
                }
            }
            else{
                System.out.print("\nWhat is the name of your file? ");
                String filename = read.nextLine();
                ReadFile readpointfile = new ReadFile();
                readpointfile.readPointFrom(filename, interpolation);
            }

            interpolation.toMatrix();
            Matrix interpolatematrix = new Matrix(interpolation.toMatrix());
            interpolatematrix.gaussianElimination();

            ArrayList<Double> solution;
            solution = interpolatematrix.getSolution();

            for (int i=0;i<solution.size();i++){
                System.out.println(solution.get(i).toString()+"x^"+Integer.toString(i));
            }
        }

    }

}
