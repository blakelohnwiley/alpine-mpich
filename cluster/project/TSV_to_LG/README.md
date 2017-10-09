
##Overview:

# Converting tab seperated files to labeled graph file format. These labeled graph format files are used for Frequent
Subnetwork Mining algorithms: Gspan, Grami, ScaleMine, and DistGraph.

```
##Contents:

    README .................................................  This file
    ETL-Cytoscape.jar.......................................  java compiled files
    SRC_*/ .................................................  Directory containing java source files
    brachypodium-correlation-cutoff-0.98-parsed.tsv.........  Tab seperated network file
    otu_table_ITS_Soil_Fungi-cleaned-sorenson-0.760000.tsv..  Tab seperated network file
    


## Dependencies
- Java 8 JRE or greater



##Running: ETL-Cytoscape.jar
```
java -jar ETL-Cytoscape.jar input_dir input_filename


input_dir : input directory for input file.
input_filename : the input tsv file
```

Example:
```
java -jar ETL-Cytoscape.jar $PWD/ brachypodium-correlation-cutoff-0.98-parsed.tsv
```

##Output:
- Dictionary text file that contains the numeric value associated with each vertex string name. 
- The generated labeled graph format file. 


