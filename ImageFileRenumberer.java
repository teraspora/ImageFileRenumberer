/*

Title: ImageFileRenumberer
Author: John Lynch
Date:  June 2016
Function:  
	Renumber all files in folder
	(typically of form "imgnnnn.png")
	by adding or subtracting a constant
Usage: java ImageFileRenumberer <path to image files> -n | +n"
	e.g. java ImageFileRenumberer /home/john/photos +12
	will rename files by adding 12 to each number.
	
==============================================================
*/

import java.io.*;

public class ImageFileRenumberer {
	public static void main(String[] args) {
		System.out.println("\n========================================================================================" +
		"\n###################### Starting ImageFileRenumberer ########################" +
		"\n========================================================================================\n");	
	 
		if (args.length != 2) {
		 	showInstructions();
		 	System.exit(0);
		}
		else {
			int incr = 0;

		 	try {
    			incr = Integer.parseInt(args[1]);
			}
			catch (NumberFormatException nfe) {
				showInstructions();
		 		System.exit(1);
			}

			String filepath = args[0];
			File dir = new File(filepath);
			File[] images = null;
			int numImages = 0;

			try {
				images = dir.listFiles();
				numImages = images.length;
			}
			catch (NullPointerException npe) {
				System.out.println("\nInvalid path:  " + filepath);
				System.exit(1);
			}

			System.out.println("\nDirectory = " + dir);
			System.out.println("\nImage count = " + numImages);
			for (File file: images) {
				String name = file.getName();
				String prefix = name.substring(0, 3);
				if (prefix.startsWith("i")) prefix = "j" + prefix.substring(1);
				else  prefix = "i" + prefix.substring(1);
				String numString = name.substring(3, name.length() - 4);
				String suffix = name.substring(name.length() - 4);
				int num = 0;
				try {
    				num = Integer.parseInt(numString);
				}
				catch (NumberFormatException nfe) {
					System.out.println("\nFile " + name + " doesn't have a valid integer in the middle." + 
						"\nAll files to be renumbered must be named in the form xxxnnnnnxxxx, " + 
						"\nwhere x represents any valid filename character " + 
						"\nand nnnnn represents a sequence of digits, " +
						"\noptionally preceded by a + or - sign.   Example:  img40262.png");
			 		System.exit(0);
				}
				int n = num + incr;
				if (n < 0) {
					System.out.println("Process must not result in a negative number:  ignoring file " + name);
					continue;
				}
				numString = String.format ("%04d", n);
				// if (numString.length() < 5) numString = String.format("%05d", num + incr);
				file.renameTo(new File(dir, prefix + numString + suffix));
			}
		}
	}

	private static void showInstructions() {
		System.out.println("\nImage File Renumberer v0.1 by John Lynch (June 2016)" +
	 					   "\n====================================================" +
	 					   "\n\nUsage: java ImageFileRenumberer <path to image files> -n | +n" +
	 					   "\n e.g. java ImageFileRenumberer /home/john/photos/ +12" +
	 					   "\nwill rename files by adding 12 to each number." +
	 					   "\n====================================================");
	}
}