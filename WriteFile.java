import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WriteFile {

	public void saveEquationSolution(String filename, ArrayList<Double> result, ArrayList<Character> variable) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write("Solution for the system:\n");
	        if (variable.size() == 0) {
	            for (int i = 0; i < result.size(); i++) {
	                bw.write(Double.toString(result.get(i)));
	            }
	        }
	        else {
	            for (int i = 0; i < result.size(); i++) {
	                bw.write(variable.get(i) + ": " + result.get(i)+"\n");
	            }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void saveHilbertSolution(String filename, ArrayList<Double> solution) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write("Solution:\n");
            for (int i=0;i<solution.size();i++){
                bw.write("x"+Integer.toString(i+1)+" = "+solution.get(i).toString()+"\n");
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void saveInterpolationSolution(String filename, ArrayList<Double> solution) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write("Solution:\n");
            bw.write("y = ");
            for (int i=0;i<solution.size();i++){
                bw.write(solution.get(i).toString()+"x^"+Integer.toString(i));
                if (i!=solution.size()-1){
                    bw.write(" + ");
                }
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}