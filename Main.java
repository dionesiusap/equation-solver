
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
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        // Standard I/O from keyboard
        Scanner read = new Scanner(System.in);
        
        // User UI for program
        System.out.println(" _________  ___  ___  ________  _______   ________");
        System.out.println("|\\___   ___\\\\  \\|\\  \\|\\   __  \\|\\  ___ \\ |\\   ____\\");
        System.out.println("\\|___ \\  \\_\\ \\  \\\\\\  \\ \\  \\|\\ /\\ \\   __/|\\ \\  \\___|_");
        System.out.println("     \\ \\  \\ \\ \\  \\\\\\  \\ \\   __  \\ \\  \\_|/_\\ \\_____  \\");
        System.out.println("      \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\_|\\ \\|____|\\  \\");
        System.out.println("       \\ \\__\\ \\ \\_______\\ \\_______\\ \\_______\\____\\_\\  \\");
        System.out.println("        \\|__|  \\|_______|\\|_______|\\|_______|\\_________\\");
        System.out.println(" ________  ___       ________  _______   ___\\|_________|");
        System.out.println("|\\   __  \\|\\  \\     |\\   ____\\|\\  ___ \\ |\\   __  \\");
        System.out.println("\\ \\  \\|\\  \\ \\  \\    \\ \\  \\___|\\ \\   __/|\\ \\  \\|\\  \\");
        System.out.println(" \\ \\   __  \\ \\  \\    \\ \\  \\  __\\ \\  \\_|/_\\ \\  \\\\\\  \\");
        System.out.println("  \\ \\  \\ \\  \\ \\  \\____\\ \\  \\|\\  \\ \\  \\_|\\ \\ \\  \\\\\\  \\");
        System.out.println("   \\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\");
        System.out.println("    \\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|_______|");
        
        System.out.println("\nPress Enter key to continue...");
        try {
            System.in.read();
        }  
        catch(Exception e){}

        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        char repeat = 'y';
        System.out.print("Hello! This is a program for solving a set of linear equations.\nYou can solve a linear equation system, a Hilbert matrix, or an interpolation.");
        while (repeat=='y'){
            System.out.println("\nChoose your choice:\n1. Linear equation system\n2. Hilbert matrix\n3. Interpolation");
            System.out.print("Your choice (1/2/3): ");
            int method = read.nextInt();
            read.nextLine();

            while (method!=1 && method!=2 && method!=3){
                System.out.println("Please enter either 1, 2, or 3.");
                System.out.print("Your choice (1/2/3): ");
                method = read.nextInt();
                read.nextLine();
            }

            System.out.println("You can also choose to input manually or from a file (EXCEPT for number 2: Hilbert matrix, in which case you can only input manually).");
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
                    ReadFile readEqnFile = new ReadFile();
                    readEqnFile.readEquationFrom(filename, equationList);
                }

                equationList.createMatrix();
                equationList.writeMatrix();
                Matrix matrix = new Matrix(equationList.toMatrix(), equationList.getListOfVariable());
                matrix.gaussianElimination();
                matrix.writeSolution();
                
                System.out.print("\nDo you want to save the solution (y/n)? ");
                char save;
                save = read.next().charAt(0);
                read.nextLine();
                while (save!='y' && save!='n'){
                    System.out.println("Please enter either y or n.");
                    System.out.print("Your choice (y/n): ");
                    save = read.next().charAt(0);
                    read.nextLine();
                }
                if (save=='y'){
                    WriteFile writeEqnFile = new WriteFile();
                    writeEqnFile.saveEquationSolution("LinearEquationSoln.txt", matrix.getSolution(), equationList.getListOfVariable());
                }

            }

            // Hilbert Matrix
            else if (method==2){
                int n;
                System.out.print("\nInput n: ");
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
                hilbMatrix.writeMatrix();
                hilbMatrix.gaussianElimination();

                ArrayList<Double> solution = new ArrayList<Double>();
                solution = hilbMatrix.getSolution();

                System.out.println("\nSolution:");
                for (int i=0;i<solution.size();i++){
                    System.out.println("x"+Integer.toString(i+1)+" = "+solution.get(i).toString());
                }

                System.out.print("\nDo you want to save the solution (y/n)? ");
                char save;
                save = read.next().charAt(0);
                read.nextLine();
                while (save!='y' && save!='n'){
                    System.out.println("Please enter either y or n.");
                    System.out.print("Your choice (y/n): ");
                    save = read.next().charAt(0);
                    read.nextLine();
                }
                if (save=='y'){
                    WriteFile writeHilbertFile = new WriteFile();
                    writeHilbertFile.saveHilbertSolution("HilbertMatrixSoln.txt", solution);
                }
            }

            // Interpolation
            else if (method==3){
                int p;
                System.out.print("\nInput number of points: ");
                p = read.nextInt();
                read.nextLine();

                Interpolation interpolation = new Interpolation(p);

                if (inp==1){
                    int j;
                    String point;
                    System.out.println("Point input format: x,y");
                    for (j=0;j<p;j++){
                        System.out.print("Input point "+(j+1)+": ");
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

                ArrayList<Double> solution = new ArrayList<Double>();
                solution = interpolatematrix.getSolution();

                System.out.println("\nSolution:");
                System.out.print("y = ");
                for (int i=0;i<solution.size();i++){
                    System.out.print(solution.get(i).toString()+"x^"+Integer.toString(i));
                    if (i!=solution.size()-1){
                        System.out.print(" + ");
                    }
                }
                System.out.println();

                System.out.print("\nDo you want to save the solution (y/n)? ");
                char save;
                save = read.next().charAt(0);
                read.nextLine();
                while (save!='y' && save!='n'){
                    System.out.println("Please enter either y or n.");
                    System.out.print("Your choice (y/n): ");
                    save = read.next().charAt(0);
                    read.nextLine();
                }
                if (save=='y'){
                    WriteFile writeInterpolationFile = new WriteFile();
                    writeInterpolationFile.saveInterpolationSolution("InterpolationSoln.txt", solution);
                }
            }

            System.out.print("Do you want to repeat the program (y/n)? ");
            repeat = read.next().charAt(0);
            read.nextLine();
            while (repeat!='y' && repeat!='n'){
                System.out.println("Please enter either y or n.");
                System.out.print("Your choice (y/n): ");
                repeat = read.next().charAt(0);
                read.nextLine();
            }
        }
    }
}
