import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

	public void writeResult(String filename, Matrix matrix) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write("this is just a placeholder text");
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