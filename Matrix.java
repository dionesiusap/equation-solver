
/******************************************************************************
 *  Compilation:  javac EquationList.java
 *  Execution:    java EquationList
 *
 *  Solve matrix using gaussian elimmination
 *
 *  Example of usage
 *
 *  Tugas tugas = new Tugas();
 *  tugas.IsiPersamaan();
 *  2 1 3
 *  1 1 2
 *  tugas.gaussianElimination();
 *
 *  Output:
 *  1.0
 *  1.0
 *
 ******************************************************************************/
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eka
 */
public class Matrix {

     //@param args the command line arguments

    // public static void main(String[] args) {
    //     ArrayList<ArrayList<Double>> mat;
    //     int N;
    //     Scanner scn = new Scanner(System.in);

    //     N = scn.nextInt();
    //     mat = MakeMatriks(N, N + 1);

    //     IsiPersamaan(mat, N);

    //     gaussianElimination(mat, N);
    // }
    

    ArrayList<ArrayList<Double>> mat;
    ArrayList<Character> variable;

    // Use this constructor if you want to manually add matrix data
    // Take the size of the matrix as the parameter
    public Matrix(int sizeOfMatrix) {
        mat = MakeMatriks(sizeOfMatrix, sizeOfMatrix + 1);
    }

    // Use this constructor if you already have the matrix data and the associative variable
    public Matrix(ArrayList<ArrayList<Double>> matrix, ArrayList<Character> variable) {
        // Fill the local mat variable with the matrix from arguments
        mat = new ArrayList<ArrayList<Double>>(matrix);
        this.variable = new ArrayList<Character>(variable);

    }

    // Call the method to fill the matrix
    public void IsiPersamaan() {
        Scanner scn = new Scanner(System.in);
        int i, j;
        for (i = 0; i < mat.size(); i++) {
            for (j = 0; j < mat.size() + 1; j++) {
                mat.get(i).set(j, scn.nextDouble());
            }
        }
    }

    // Call the method to solve the matrix
    public void gaussianElimination() {

        int singular = BarisElim(mat, mat.size());

        /* jika matriks singular */
        if (singular != -1) {
            System.out.println("Singular Matrix.");

            if (mat.get(singular).get(mat.size()) != 0) {
                System.out.println("Sistem persamaan tidak konsisten");
            } else {
                System.out.println("Sistem persamaan memiliki solusi tak hingga");
            }

            return;
        }

        backSub(mat, mat.size());
    }

// untuk menswap 2 baris
    private void swap_baris(ArrayList<ArrayList<Double>> mat, int i, int j, int N) {
        ArrayList<Double> temp = mat.get(i);
        mat.set(i, mat.get(j));
        mat.set(j, temp);
    }

    private void print(ArrayList<ArrayList<Double>> mat, int N) {
        for (int i = 0; i < N; i++, System.out.println()) {
            for (int j = 0; j <= N; j++) {
                System.out.print(String.format("%lf ", mat.get(i).get(j)));
            }
        }
        System.out.println();
    }

// fungsi untuk mereduksi matriks
    private int BarisElim(ArrayList<ArrayList<Double>> mat, int N) {
        for (int k = 0; k < N; k++) {

            int i_max = k;
            double v_max = mat.get(i_max).get(k);

            for (int i = k + 1; i < N; i++) {
                if (Math.abs(mat.get(i).get(k)) > v_max) {
                    v_max = mat.get(i).get(k);
                    i_max = i;
                }
            }

            if (mat.get(k).get(i_max) == 0) {
                return k; // Matriks singular
            }

            /* menukar baris yang memiliki nilai terbesar dengan baris sekarang */
            if (i_max != k) {
                swap_baris(mat, k, i_max, N);
            }

            for (int i = k + 1; i < N; i++) {

                double f = mat.get(i).get(k) / mat.get(k).get(k);

                for (int j = k + 1; j <= N; j++) {
                    mat.get(i).set(j, mat.get(i).get(j) - mat.get(k).get(j) * f);
                }

                mat.get(i).set(k, 0.0);
            }

        }

        return -1;
    }

    private void backSub(ArrayList<ArrayList<Double>> mat, int N) {
        double x[] = new double[N];  // array untuk menyimpan solusi

        for (int i = N - 1; i >= 0; i--) {

            x[i] = mat.get(i).get(N);

            for (int j = i + 1; j < N; j++) {
                x[i] -= mat.get(i).get(j) * x[j];
            }

            x[i] = x[i] / mat.get(i).get(i);
        }

        System.out.println("\nSolution for the system:");
        if (variable.size() == 0) {
            for (int i = 0; i < N; i++) {
                System.out.println(x[i]);
            }
        }
        else {
            for (int i = 0; i < N; i++) {
                System.out.println(variable.get(i) + ": " + x[i]);
            }
        }
    }

    private ArrayList<ArrayList<Double>> MakeMatriks(int M, int N) {
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            ArrayList<Double> baris = new ArrayList<>();
            for (int j = 0; j < N; j++)
                baris.add(0.0);
            mat.add(baris);
        }
        return mat;
    }

}
