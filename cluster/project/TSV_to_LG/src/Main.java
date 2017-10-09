import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Created by btl on 5/25/17. This is the main class used in ETL_to_Cytoscape.jar file.
 * Requires two input parameters passed from the command line.
 * 1st parameter: is the input directory.
 * 2nd parameter: is the input tsv file. 
 */
public class Main
{

    public static void main(String[] args) throws IOException
    {
        // used to create an object to access the methods
        // found in the class.

        TSV_to_LG convert_tsv = new TSV_to_LG();
        try{
            long start = System.currentTimeMillis();
            // input parameters
            String input_dir = args[0]/;
            String input_filename = args[1];
            String output_dir = input_dir;
            String output_filename = input_filename.replace(".tsv","-Dictionary.txt");
            System.out.println("created thresholded network");
            // Generate Buffered Input File

            // input file must be file format of tsv!!!!!!
            BufferedReader input_file = convert_tsv.Input_File(input_dir, input_filename);
            System.out.println("imported threshold network");
            // Generate Vertex Names
            System.out.println("created vertex names for  thresholded network");
            HashSet Vertex_Names = convert_tsv.Generate_Hashset(input_file);
            // Pass Vertex_Names Hashset and write to output_file
            convert_tsv.Write_Vertex_Names_to_File(output_dir,
                    output_filename
                    ,Vertex_Names);

            System.out.println("Importing Generated output Dictionary");

            HashMap imported_vertex_names = convert_tsv.import_vertex_names(output_dir, output_filename);

            System.out.println("Imported Dictionary with Vertex Names ");
            // Convert from edge list with edge weights to labeled graph format
            // input file must be of tsv file format
            // output file must be labeled graph format- must end with .lg

            // converting between tsv format to lg format
            System.out.println("Converting from tsv file format to lg format");
            String new_output_file = new String();
            new_output_file = output_filename.replace("-Dictionary.txt",".lg");
            convert_tsv.Labeled_Graph_File_Writer(input_dir,
                    input_filename,
                    output_dir,
                    new_output_file,
                    imported_vertex_names);

            System.out.println("Completed");
//            System.out.println("Finished");
            long end = System.currentTimeMillis();
            // total time
            long total_time = (end-start)/1000;
            System.out.println("Total execution time : "+total_time);
        }catch(ArithmeticException e){System.out.println(e);}
    }
}
