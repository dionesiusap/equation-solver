
/******************************************************************************
 *
 *  Compilation:  javac FileReader.java
 *  Execution:    java FileReader
 *
 *  Reads string from file one line at a time.
 *
 *	Original author: Deron Eriksson (http://www.avajava.com/)
 *	With neccessary modifications by: Dionesius Agung
 *  
 *	Example of Usage
 *	
 *	In program:
 *	ReadFile test = new ReadFile();
 *	test.readEquationFrom("test.txt", equationList);
 *
 *	test.txt content:
 *	1x + 2y = 4
 *	2x + 3y = 7
 *
 *	Output:
 *	1 2 4
 *	2 3 7
 *
 ******************************************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public void readEquationFrom(String filename, EquationList equationList) {
		try {
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				equationList.addEquation(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public void readMatrixFrom(String filename, Matrix matrix) {
	// 	try {
	// 		File file = new File(filename);
	// 		FileReader fileReader = new FileReader(file);
	// 		BufferedReader bufferedReader = new BufferedReader(fileReader);
	// 		StringBuffer stringBuffer = new StringBuffer();
	// 		String line;
	// 		while ((line = bufferedReader.readLine()) != null) {
	// 			stringBuffer.append(line);
	// 			equationList.addEquation(line);
	// 			stringBuffer.append("\n");
	// 		}
	// 		fileReader.close();
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// }
}