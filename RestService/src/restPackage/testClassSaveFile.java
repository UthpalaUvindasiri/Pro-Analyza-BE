package restPackage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class testClassSaveFile {

	public static void main(String[] args) {
		System.out.println("started");
		//
		try {
			File ff = new File("D:\\4thyearBackEnd\\temp_files\\");
			FileUtils.cleanDirectory(ff);
			SaveFiles sf = new SaveFiles("24");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
