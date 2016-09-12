package xmlextract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.Config;

public class XMLExtractor {

	// <cat|dog|mouse|fish

	private static String reg = "<article\\s.*|<inproceedings\\s.*|<proceedings.*|<book\\s.*|"
			+ "<incollection\\s.*|<phdthesis\\s.*|<mastersthesis\\s.*|<www\\s.*";
	// private static String reg = "<inproceedings.*";
	private static String capRegx = "(<)(\\w+)(\\s)";
	private static Pattern countPattern = Pattern.compile(reg);
	private static Pattern capTrailPattern = Pattern.compile(capRegx);

	// Counter for each type of record:
	private static int counter_article = 0;
	private static int counter_inproceedings = 0;
	private static int counter_proceedings = 0;
	private static int counter_book = 0;
	private static int counter_incollection = 0;
	private static int counter_phdthesis = 0;
	private static int counter_mastersthesis = 0;
	private static int counter_www = 0;

	public static void main(String args[]) {

		File fileIn = new File(Config.XMLFileFrom);
		File fileOut = new File(Config.XMLOutput);
		fileCreate(fileOut);
		
		System.out.println("Running extracting... could take a while...");
		readAndWrite(fileIn, fileOut);
		
		String result = null;
		
		result = String.format("%s %s: %d, %s: %d,%s: %d, %s: "
				+ "%d,%s: %d,%s: %d,%s: %d,%s: %d", "Extracted: ","articles",counter_article,
				"inproceddings",counter_inproceedings,"proceddings",counter_proceedings,
				"books",counter_book,"incollections",counter_incollection,"phdthesis",counter_phdthesis,
				"masterthesis",counter_mastersthesis,"www",counter_www);
		
		System.out.println(result);
		System.out.println("finished");
		// "+fileOut.getAbsolutePath() );

	}

	private static void readAndWrite(File fileIn, File fileOut) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		BufferedReader br = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileIn);
			br = new BufferedReader(new InputStreamReader(fis));
			// create file output
			fileWriter = new FileWriter(fileOut);
			printWriter = new PrintWriter(fileWriter);
			String line = null;
			boolean termination = false;
			String terminationTag = "";
			boolean skipMode = false;
			while ((line = br.readLine()) != null) {	
				if(termination == true){
					break;
				}
				
				int headCheckCode = headCheck(line);
				
				if (!skipMode && (headCheckCode == 0 || headCheckCode == 1) ) {
					printWriter.println(line); // write everything
				} else if (headCheckCode == 1) {
					skipMode = false;
					printWriter.println(line);
				} else if (headCheck(line) == -1) {
					skipMode = true;
				}
				
				if(allSatisfied()){
					terminationTag = endTagCapture(line);
					//printWriter.println(line);
					while ((line = br.readLine()) != null){
						printWriter.println(line);
						if(line.trim().equals(terminationTag)){
							termination = true;
							break;
						}
					}
				}
				
			}
			if(!termination){
				System.out.println("missing some records because original db does not have enough data!");
			}
			printWriter.println("</dblp>");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printWriter.close();
			try {
				fis.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Check each line and see if it is the start of a new record, if it is
	 * increase the corresponding counter.
	 * 
	 * @param str
	 * @return 1 if it is a recognized starting tag, 0 if it is a no-matching
	 *         tag line, -1 if this tag's record is full we what to skip it.
	 */
	private static int headCheck(String str) {
		Matcher matcher = countPattern.matcher(str);
		if (matcher.matches()) {
			// increase counter based on whats captured:
			String supposed_tag = endTagCapture(str);
			if (supposed_tag.equals("</article>")) {
				if (counter_article == Config.articleExtractNum) {
					return -1;
				}
				++counter_article;
			} else if (supposed_tag.equals("</inproceedings>")) {
				if (counter_inproceedings == Config.inproceedingsExtractNum) {
					return -1;
				}
				++counter_inproceedings;
			} else if (supposed_tag.equals("</proceedings>")) {
				if (counter_proceedings == Config.proceedingsRetriveNum) {
					return -1;
				}
				++counter_proceedings;
			} else if (supposed_tag.equals("</book>")) {
				if (counter_book == Config.bookExtractNum) {
					return -1;
				}
				++counter_book;
			} else if (supposed_tag.equals("</incollection>")) {
				if (counter_incollection == Config.incollectionExtractNum) {
					return -1;
				}
				++counter_incollection;
			} else if (supposed_tag.equals("</phdthesis>")) {
				if (counter_phdthesis == Config.phdthesisExtractNum) {
					return -1;
				}
				++counter_phdthesis;
			} else if (supposed_tag.equals("</mastersthesis>")) {
				if (counter_mastersthesis == Config.mastersthesisExtractNum) {
					return -1;
				}
				++counter_mastersthesis;
			} else if (supposed_tag.equals("</www>")) {
				if (counter_www == Config.wwwExtractNum) {
					return -1;
				}
				++counter_www;
			} else {
				System.out.println("caught unexpected tag");
				// return 0;
			}
			return 1;
		}
		return 0;
	}

	/**
	 * create destination file
	 * 
	 * @param file
	 * @return
	 */
	public static int fileCreate(File file) {
		try {
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("Create file " + file.getAbsolutePath());
			} else {
				System.out.println("File already exists");
				return 0;
			}
		} catch (IOException e) {
			System.out.println("File creation exception");
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * when find a recognized tag, get the closing tag of that e.g &ltarticle
	 * ....&gt would return &lt/article&gt
	 * 
	 * @param tag
	 * @return closing tag
	 */
	private static String endTagCapture(String tag) {
		Matcher m2 = capTrailPattern.matcher(tag);
		if (m2.find()) {
			return "</" + m2.group(2) + ">";
		}
		return "";
	}

	private static boolean allSatisfied() {
		if (counter_article >= Config.articleExtractNum && counter_inproceedings >= Config.inproceedingsExtractNum
				&& counter_proceedings >= Config.proceedingsRetriveNum && counter_book >= Config.bookExtractNum
				&& counter_incollection >= Config.incollectionExtractNum
				&& counter_phdthesis >= Config.phdthesisExtractNum
				&& counter_mastersthesis >= Config.mastersthesisExtractNum && counter_www >= Config.wwwExtractNum) {
			return true;
		}
		return false;
	}

}
