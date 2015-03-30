/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlvalidate;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.*;
import java.util.Date;
import org.xml.sax.SAXException;

/**
 *
 * @author Alexander R> Daher
 * Data Management & Analytics - Research and Innovation
 * XmlValidate.jar validates an .XSD file versus its corresponding .XML and outputs the results to a .CSV file
 */
public class XmlValidate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Two arguments are needed: XML_FILE_PATH XSD_FILE_PATH");
            System.out.println("Full usage:");
            System.out.println("java -jar XmlValidate.jar XML_FOLDER_OR_FILE_PATH XML_FILE_MASK XSD_FILE_PATH OUTPUT_CSV_FILE OUTPUT_LOG_FILE ");
            System.out.println("Sample usage:");
            System.out.println("java -jar XmlValidate.jar /usr/jsmith/xmlfolder /usr/jsmith/valid.xsd .xml /usr/jsmith/validate_result.csv /usr/jsmith/validate_result.log");
            return;
        }
        String folderPath = args[0];
        String schemaPath = args[1];
        String fileEndsWith = (args.length>2)?args[2]:".xml";
        String outputCsvFile = (args.length>3)?args[3]:"validation.csv";
        String outputLogFile = (args.length>4)?args[4]:"CONSOLE";
        validate(folderPath, fileEndsWith, schemaPath, outputCsvFile, outputLogFile);
    }
    /** validate folder with all files inside for XSD schema path and put result in CSV file provided */
    public static void validate(String folderPath, String fileEndsWith, String schemaPath, String outputCsvPath, String outputLogFile) {
        // get print log for output messages
        PrintStream logStream;
        try {
            logStream = new PrintStream(outputLogFile);
            logStream.println("START==============================================================================");
            logStream.println("Start processing at " + (new Date()).toString());
        } catch (Exception ex) {
            logStream = System.out;
            logStream.println("Cannot use output log file: " + outputLogFile + ", reason: " + ex.getMessage());
        }
        try {
            logStream.println("START validation of folder/file: " + folderPath + " with XSD schema: " + schemaPath + " and write result to output CSV file: " + outputCsvPath);
            // create validator
            logStream.println("Create schema validator using XSD file: " + schemaPath);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(schemaPath));
            Validator validator = schema.newValidator();
            logStream.println("Created validator: " + validator);
            logStream.println("Try to create result file: " + outputCsvPath);
            // create output file
            PrintStream csvOutputStream = new PrintStream(outputCsvPath);
            csvOutputStream.print("xsd_file_name,xml_file_name,validate,validate_reason");
            // create File object for source path - could be file or folder
            File folder = new File(folderPath);
            validateFolderOrFile(folder, fileEndsWith, schemaPath, validator, csvOutputStream, logStream);
            logStream.println("Validation FINISHED, closing output file");
            csvOutputStream.close();
            logStream.println("END==============================================================================");
        } catch (Exception ex) {
            logStream.println("Exception while validating: " + ex.getMessage());
        }
    }
    /** validate folder or file with validator given and put result to output stream*/
    public static void validateFolderOrFile(File folderOrFile, final String fileEndsWith, String schemaPath, Validator validator, java.io.PrintStream csvOutputStream, PrintStream logStream) {
        logStream.println("Validate folder or file: " + folderOrFile.getAbsolutePath());
        if (folderOrFile.isDirectory()) {
            logStream.println("Path " + folderOrFile.getAbsolutePath() + " is a folder");
            File[] subfiles = folderOrFile.listFiles(new java.io.FilenameFilter() {
                public boolean accept(File dir, String name) {
                    if (name.endsWith(fileEndsWith)) {
                        return true;
                    }
                    return false;
                }
            });
            logStream.println("Found " + subfiles.length + " files inside folder:  " + folderOrFile.getAbsolutePath());
            for (File singleFile : subfiles) {
                validateFolderOrFile(singleFile, fileEndsWith, schemaPath, validator, csvOutputStream, logStream);
            }
        } else if (folderOrFile.isFile()) {
            logStream.println("Path " + folderOrFile.getAbsolutePath() + " is a file");
            validateFile(folderOrFile, schemaPath, validator, csvOutputStream, logStream);
        } else {
            logStream.println("Path " + folderOrFile.getAbsolutePath() + " is not a file OR folder, not processing");
        }
    }
    /** validate single file */
    public static boolean validateFile(File fileToValidate, String schemaPath, Validator validator, java.io.PrintStream csvOutputStream, PrintStream logStream) {
        String xmlFilePath = fileToValidate.getAbsolutePath();
        String xmlCanonicalPath = null;
        try {
            xmlCanonicalPath = fileToValidate.getCanonicalPath();
        } catch (Exception ex) {
            logStream.println("Cannot get canonical name for file to validate: " + fileToValidate.getAbsolutePath());
        }
        boolean validateResult;
        String validateError;
        try {
            Source xmlFile = new StreamSource(new File(xmlFilePath));
            validator.validate(xmlFile);
            logStream.println(xmlFilePath + " is valid");
            validateResult = true;
            validateError = "OK";
        } catch (SAXException e) {
            logStream.println(xmlFilePath + " is NOT valid");
            logStream.println("Reason: " + e.getLocalizedMessage());
            validateResult = false;
            validateError = "SAXException: " + e.getLocalizedMessage();
        } catch (IOException e) {
            logStream.println("Cannot read file: " + xmlFilePath);
            validateResult = false;
            validateError = "Cannot read file: " + e.getLocalizedMessage();
        } catch (Exception e) {
            logStream.println("Exception: " + e.getLocalizedMessage());
            validateResult = false;
            validateError = "Exception: " + e.getLocalizedMessage();
        }
        csvOutputStream.print("\n\"" + schemaPath + "\",\"" + xmlCanonicalPath + "\"," + validateResult + ",\"" + validateError + "\"");
        return validateResult;
    }
}
