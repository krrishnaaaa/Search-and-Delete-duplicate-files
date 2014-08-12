/*

		Copyright 2014 Krrishnaaaa
		
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ListFiles {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Provide directory to search");
			return;
		}
		ArrayList<Files> mFiles = new ArrayList<Files>();

		String rootPath = args[0];

		File folder = new File(rootPath);

		System.out.println("\nSearching for duplicate files on path: " + rootPath);

		System.out.println("Processing started at " + new Date());

		addFileToList(folder, mFiles);

		ArrayList<DuplicateDetails> dups = new ArrayList<DuplicateDetails>();

		for (int i = 0; i < mFiles.size(); i++) {
			Set<String> completePath = new HashSet<String>();
			DuplicateDetails details = new DuplicateDetails();
			for (int j = i + 1; j < mFiles.size(); j++) {
				Files f1 = mFiles.get(i);
				Files f2 = mFiles.get(j);
				if (f1.equals(f2)) {
					completePath.add(f1.getFilePath());
					completePath.add(f2.getFilePath());
					mFiles.remove(j);
					j--;
				}
				if (completePath.size() > 0) {
					details.filePath = completePath;
					details.md5 = f1.getMd5();
				}
			}
			if (completePath.size() > 0) {
				dups.add(details);
			}
		}

		System.out.println("\nDuplicate(s) of (" + dups.size() + ") file(s) found.");
		String fileName = "/home/krishna/toDelete"
							 + args[0].replaceAll(File.separator, "_").replaceAll(" ", "_");
		System.out.println("\nList of duplicate files is stored in " + fileName);
		File logFile = new File(fileName);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(logFile));

			for (DuplicateDetails details : dups) {
				writer.write(details.md5+"\n");
				for (String completePath : details.filePath) {
					writer.write(completePath+"\n");
				}
			}
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if(writer != null) writer.close();
		    } catch (Exception e) {
		    }
		}
		
		System.out.println("\nProcessing ended at " + new Date());
	}
	
	private static int counter = 0;
	
	private static void addFileToList(File mFile, ArrayList<Files> mFiles) {
		if (mFile.isFile()) {
			counter++;
			String path = mFile.getAbsolutePath();
			double kb = mFile.length()/1024.0;
			String message = String.format("\r(%d) files scanned. Current file size : %10.2f KB", counter, kb);
			System.out.print(message);
			addFileToList(path, mFiles);
		} else if (mFile.isDirectory()) {
			for (File child : mFile.listFiles()) {
				addFileToList(child, mFiles);
			}
		}
	}
	
	static void addFileToList(String filePath, ArrayList<Files> mFiles) {
		String md5 = fileToMD5(filePath);
		Files files = new Files();
		files.setFilePath(filePath);
		files.setMd5(md5);
		mFiles.add(files);
	}

	public static String fileToMD5(String filePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
			byte[] buffer = new byte[1024];
			MessageDigest digest = MessageDigest.getInstance("MD5");
			int numRead = 0;
			while (numRead != -1) {
				numRead = inputStream.read(buffer);
				if (numRead > 0)
					digest.update(buffer, 0, numRead);
			}
			byte[] md5Bytes = digest.digest();
			return convertHashToString(md5Bytes);
		} catch (Exception e) {
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private static String convertHashToString(byte[] md5Bytes) {
		String returnVal = "";
		for (int i = 0; i < md5Bytes.length; i++) {
			returnVal += Integer.toString((md5Bytes[i] & 0xff) + 0x100, 16).substring(1);
		}
		return returnVal.toUpperCase();
	}

}

