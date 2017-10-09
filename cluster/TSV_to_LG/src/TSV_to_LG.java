import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;


/**
 * Created by btl on 5/25/17. This class has methods that convert tab seperated value
 * files into labeled graph format. These labeled graph format files are used for Frequent
 * Subnetwork Mining algorithms.
 *
 */
public class TSV_to_LG
{
    public BufferedReader Input_File(String input_dir, String input_filename) throws IOException
    {
        String input_loc = new String(input_dir+input_filename);
        // create an object FileReader object which takes
        // input dir and filename.
        FileReader file_in = new FileReader(input_loc);
        // used as a cache to read in line by line
        // rather than loading file all at once.
        BufferedReader Buffered_Input_File = new BufferedReader(file_in);
        // Return BufferedReader Object
        return Buffered_Input_File;
    }

    public HashSet Generate_Hashset(BufferedReader input_file) throws IOException {
        // Hashset to compare against all gene names found
        HashSet<String> Vertex_Names = new HashSet<String>();
        // input line utitlized in BufferedReader
        String input_line = new String("");
        // skip the header file line
        input_file.readLine();
        // iterate through each line of the file
        while ((input_line = input_file.readLine()) != null)
        {
            //break up input string by delimited tabs
            String[] line_prts = input_line.split("\t");
            // parse the input string into a string array,
            // depends on how many
            String Col_1 = line_prts[0];
            String Col_2 = line_prts[1];
            String Col_3 = line_prts[2];
            // add to Hashset to ensure no duplicate
            // gene names are find.
            Vertex_Names.add(Col_1);
            Vertex_Names.add(Col_2);
        }
        // return poplulated HashSet
        return Vertex_Names;
    }
    public void Write_Vertex_Names_to_File(String output_dir, String output_filename, HashSet Vertex_Names) throws IOException {
        // output file location
        String output_loc = new String(output_dir+output_filename);
        String output_str = new String("");
        // Create an FileWriter class to write data
        // to output file.
        FileWriter Write_Results = new FileWriter(output_loc);
        // Count ==> Keeps track of global unqiue identifier number
        Integer Count = new Integer(0);
        // generate an output string used to write a new line
        // to to the file each iteration
        Iterator<String> Vertex_Names_Iterator = Vertex_Names.iterator();
        // while loop iterates through contents of HashSet, extracts
        // stores as a temp string, and uses a counter. After each iteration
        // the count is increased. The count number is paired with a unqiue
        // idenfication number. The id number and vertex name are combined together
        // into an output string. That string is then written to file, while creating
        // a line during each iteration. Once the end of the HashSet has been reached,
        // the while loop stops.
        while(Vertex_Names_Iterator.hasNext())
        {
            // Access each element in String HashSet and Convert
            String Node_Name = Vertex_Names_Iterator.next();
            //System.out.println("Count = "+Count);
            // Output line used to write to file
            output_str = Count+"\t"+Node_Name;
            // Write each output string to File
            Write_Results.write(output_str+"\n");
            // Increase count after each iteration
            Count++;
        }
        Write_Results.close();

    }
    public HashMap<String,Integer> import_vertex_names(String input_dir, String input_filename) throws IOException {
        // Use input_file method
        BufferedReader input_file = Input_File(input_dir,input_filename);
        String input_line = new String("");
        // iterate through the file line by line and populate to enter
        // keys and values into a HashMap
        HashMap<String,Integer> Vertex_Names_Dictionary = new HashMap<String,Integer>();
        // iterate through each line of the file
        while ((input_line = input_file.readLine()) != null)
        {
            //break up input string by delimited tabs
            String[] line_prts = input_line.split("\t");
            // parse the input string into a string array,
            // depends on how many
            Integer Vertex_id = Integer.parseInt(line_prts[0]);
            String Vertex_name = line_prts[1];
            // Insert the vertex id as a key
            // Insert the vertex name as a value
            //System.out.println("Vertex id = "+Vertex_id+"\t"+"vertex name = "+Vertex_name);
            Vertex_Names_Dictionary.put(Vertex_name,Vertex_id);
        }
        return Vertex_Names_Dictionary;
    }


    public void Labeled_Graph_File_Writer(String input_dir,String input_filename,
                                          String output_dir,String output_filename,
                                          HashMap <String,Integer> Vertex_Names_Dictionary) throws IOException {
        // Input file and generate a BufferedReader class
        BufferedReader input_edgelist_file = Input_File(input_dir,input_filename);
        String input_line = new String("");
        // used to establish where to write the output file.
        String output_file_loc = new String(output_dir+output_filename);
        // String that is passed the output file to be written to
        String output_str = new String("");
        // Create FileWriter Object
        FileWriter Labeled_Graph_Output_File = new FileWriter(output_file_loc);
        Labeled_Graph_Output_File.write("# t 1"+"\n");
        Iterator Vertex_Names_Iterator = Vertex_Names_Dictionary.values().iterator();
        while(Vertex_Names_Iterator.hasNext())
        {
            // Access each element in String HashSet and Convert
            Object Node_Name = Vertex_Names_Iterator.next();
            // Increase count after each iteration
            // Output line used to write to file
            output_str = "v "+Node_Name+" "+Node_Name;
            // Write each output string to Files.write(output_str+"\n");
            Labeled_Graph_Output_File.write(output_str+"\n");
        }
        // skip the header file line
        String headerLine = input_edgelist_file.readLine();
        // iterate through each line of the file
        while ((input_line = input_edgelist_file.readLine()) != null)
        {
            //System.out.println(input_line);
            //break up input string by delimited tabs
            String[] line_prts = input_line.split("\t");
            // parse the input string into a string array,
            // depends on how many
            String source_vertex = line_prts[0];
            String target_vertex = line_prts[1];
            String edge_weight = line_prts[2];
            //System.out.println(source_vertex);
            //System.out.println(target_vertex);
            // cross reference vertex names dictionary
            // to get vertex ids. Write vertex i...9
            int source_vertex_id = Vertex_Names_Dictionary.get(source_vertex);
            int target_vertex_id = Vertex_Names_Dictionary.get(target_vertex);
            // string to be written to output file
            output_str = "e\t"+source_vertex_id+"\t"+target_vertex_id+"\t"+edge_weight;
            // write to file
            Labeled_Graph_Output_File.write(output_str+"\n");
        }
        Labeled_Graph_Output_File.close();
    }


    public void Subnetwork_Labeled_Graph_Format_to_EdgeList(String input_dir, String input_filename,
                                                            String output_dir, String output_filename,
                                                            HashMap Vertex_Names_Dictionary) throws IOException {
        // used to establish where to write the output file.
        String output_file_loc = new String(output_dir + output_filename);
        // to output file.
        FileWriter Write_Results = new FileWriter(output_file_loc);
        // String that is passed the output file to be written to
        String output_str = new String("");
        // Input file and generate a BufferedReader class
        BufferedReader input_subnetworks_file = Input_File(input_dir, input_filename);
        // input str
        String input_line = new String("");
        // input str with vertex info looks like
        // v 0 1 <== second element is local vertex id.
        //           third element is the global vertex id.
        HashMap LocalVertNameHM = new HashMap();
        if (input_line.charAt(0) == 'v') {
            String[] line_parts = input_line.split(" ");
            int vert_local_id = Integer.parseInt(line_parts[1]);
            int vert_global_id = Integer.parseInt(line_parts[2]);
            // put into localverthm
            LocalVertNameHM.put(vert_local_id, vert_global_id);
        }
        // look for lines only with e at the first character
        if (input_line.charAt(0) == 'e') {
            // break up input line into string array
            String[] line_parts = input_line.split(" ");
            // then do this stuff listed below
            // cross reference the vert id with vert name
            int source_vert_local_id = Integer.parseInt(line_parts[1]);
            int target_vert_local_id = Integer.parseInt(line_parts[2]);
            int source_vert_global_id = Integer.parseInt(LocalVertNameHM.get(source_vert_local_id).toString());
            int target_vert_global_id = Integer.parseInt(LocalVertNameHM.get(target_vert_local_id).toString());
            String edge_weight = line_parts[3];
            // double check and make sure there aren't any duplicate edges
            // that are written to file
            // Hashset to compare against all gene names found
            String source_vert_name = Vertex_Names_Dictionary.get(source_vert_global_id).toString();
            String target_vert_name = Vertex_Names_Dictionary.get(target_vert_global_id).toString();
            // construct string to write out to file
            output_str = source_vert_name + "\t" + target_vert_name + "\t" + edge_weight;
            //System.out.println(output_line);
            // now write to file
            Write_Results.write(output_str + "\n");
        }

        Write_Results.close();
    }

    public void Threshold_Network(String input_dir, String input_filename, double threshold_value,
                                  String output_dir, String output_filename) throws IOException
    {
        // used to establish where to write the output file.
        String output_file_loc = new String(output_dir + output_filename+"-"+threshold_value);
        // to output file.
        FileWriter Write_Results = new FileWriter(output_file_loc+".tsv");
        // String that is passed the output file to be written to
        String output_str = new String("");
        // Input file and generate a BufferedReader class
        BufferedReader input_file = Input_File(input_dir, input_filename);
        // input str
        String input_line = new String("");
        // iterate through each line of the file
        while ((input_line = input_file.readLine()) != null)
        {
            //break up input string by delimited tabs
            String[] line_prts = input_line.split("\t");
            // parse the input string into a string array,
            // depends on how many
            String source_vertex = line_prts[0];
            String target_vertex = line_prts[1];
            String edge_weight = line_prts[2];
            // testing to use for debugging
            //System.out.println(input_line);
            // convert from string to double, and then
            double edge_weight_value = Double.valueOf(edge_weight);
            // if edge weight value is between postive and negative threshold
            // if edge weight value is between postive and negative threshold
            // then write that value to output file.
            if(Math.abs(edge_weight_value) >= threshold_value)
            {
                // combine all elements from string array into one
                // to be used to writing to file.
                output_str = source_vertex+"\t"+target_vertex+"\t"+edge_weight_value;
                // write to output file
                Write_Results.write(output_str+"\n");
            }
        }
        // close input and output files
        Write_Results.close();
        input_file.close();

    }

    public void sif_to_tsv_format(String input_dir,
                                  String input_filename,
                                  String output_dir,
                                  String output_filename) throws IOException
    {
        // used to establish where to write the output file.
        String output_file_loc = new String(output_dir + output_filename);
        // to output file.
        FileWriter Write_Results = new FileWriter(output_file_loc+".tsv");
        // String that is passed the output file to be written to
        String output_str = new String("");
        // Input file and generate a BufferedReader class
        BufferedReader input_file = Input_File(input_dir, input_filename);
        // input str
        String input_line = new String("");
        // iterate through each line of the file
        while ((input_line = input_file.readLine()) != null) {
            //break up input string by delimited tabs
            String[] line_prts = input_line.split("\t");
            // parse the input string into a string array,
            // depends on how many
            String source_vertex = line_prts[0];
            String edge_weight = line_prts[1];
            String target_vertex = line_prts[2];
            // construct output str
            output_str = source_vertex+"\t"+target_vertex+"\t"+edge_weight;
            // write out to file
            Write_Results.write(output_str+"\n");
        }
        // clean up your mess and close out of all opened files
        Write_Results.close();
        input_file.close();
    }

    public void tsv_to_sif_format(String input_dir,
                                  String input_filename,
                                  String output_dir,
                                  String output_filename) throws IOException
    {
        // used to establish where to write the output file.
        String output_file_loc = new String(output_dir + output_filename);
        // to output file.
        FileWriter Write_Results = new FileWriter(output_file_loc+".sif");
        // String that is passed the output file to be written to
        String output_str = new String("");
        // Input file and generate a BufferedReader class
        BufferedReader input_file = Input_File(input_dir, input_filename);
        // input str
        String input_line = new String("");
        // iterate through each line of the file
        while ((input_line = input_file.readLine()) != null) {
            //break up input string by delimited tabs
            String[] line_prts = input_line.split("\t");
            // parse the input string into a string array,
            // depends on how many
            String source_vertex = line_prts[0];
            String target_vertex = line_prts[1];
            String edge_weight = line_prts[2];
            // construct output str
            output_str = source_vertex+"\t"+edge_weight+"\t"+target_vertex;
            // write out to file
            Write_Results.write(output_str+"\n");
        }
        // clean up your mess and close out of all opened files
        Write_Results.close();
        input_file.close();
    }

    public void subnetwork_labeled_graph_format_to_sif_format()
    {

    }

    public void node_count(String input_dir,String input_filename) throws IOException {
        // Input file and generate a BufferedReader class
        BufferedReader input_file = Input_File(input_dir, input_filename);
        // create file reader
        String input_line = new String("");
        // has set to store column 1,2 vertex names
        Set<String> Column_1_Vertex_Names = new HashSet<String>();
        Set<String> Column_2_Vertex_Names = new HashSet<String>();

        // iterate through each line of the file
        while ((input_line = input_file.readLine()) != null)
        {
            //break up input string by delimited tabs
            String[] line_prts = input_line.split("\t");
            // parse the input string into a string array,
            // depends on how many
            String Col_1 = line_prts[0];
            String Col_2 = line_prts[1];
            String Col_3 = line_prts[2];
            // add to Hashset to ensure no duplicate
            // gene names are find.
            Column_1_Vertex_Names.add(Col_1);
            Column_2_Vertex_Names.add(Col_2);
        }
        // Take the union of both Hashsets, by adding one to the other.
        // using the addAll method. First make a copy of the orginal and then add
        // the second hashset to the copy.
        Set<String> copy_of_column_1_vertex_names = new HashSet<String>(Column_1_Vertex_Names);
        // add second hashset to copy of column1 vertex names
        copy_of_column_1_vertex_names.addAll(Column_2_Vertex_Names);
        // display the unique count to console
        System.out.println("Number of Unique Vertex Names is : "+copy_of_column_1_vertex_names.size());
        // clean up after your mess and close the open files
        input_file.close();
    }
}
