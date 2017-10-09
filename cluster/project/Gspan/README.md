GraMi
=====

GraMi is a novel framework for frequent subgraph mining in a single large  graph, GraMi outperforms existing techniques by 2 orders of magnitudes. GraMi  supports finding frequent subgraphs as well as frequent patterns, Compared to subgraphs, patterns offer a more powerful version of matching that captures  transitive interactions between graph nodes (like friend of a friend) which are very common in modern applications. Also, GraMi supports user-defined  structural and semantic constraints over the results, as well as approximate results.

For more details, check our paper: Mohammed Elseidy, Ehab Abdelhamid, Spiros Skiadopoulos, and Panos Kalnis. "GRAMI: Frequent Subgraph and Pattern Mining in a Single Large Graph. PVLDB, 7(7):517-528, 2014."

CONTENTS:
=====

    README ...................................................      This file
    GRAMI_UNDIRECTED_SUBGRAPHS.jar............................      Jar complied version of Grami
    grami ....................................................      script to run GraMi
    brachypodium-correlation-cutoff-0.98-parsed.lg............      brachypodium label graph dataset
    otu_table_ITS_Soil_Fungi-cleaned-sorenson-0.760000.lg.....      otu_table_ITS_Soil_Fungi dataset


REQUIREMENTS:
=====

Java JRE v1.6.0 or later

INSTALLATION:
=====
    - GraMi scpirt has 4 input parameters:
        - inputfile
        - frequency threshold for subnetwork
        - number of nodes in subnetwork, used to restrict the search space
        - number of edges in subnetwork, used to restrict the search space

EXAMPLES:
=====
    - ./Grami.sh brachypodium-correlation-cutoff-0.98-parsed.lg 1 3 3 #mines brachypodium dataset for subetworks with frequncy of one with 3 nodes and 3 edges. 
    - ./Grami.sh otu_table_ITS_Soil_Fungi-cleaned-sorenson-0.760000.lg 1 3 3 #mines brachypodium dataset for subetworks with frequncy of one with 3 nodes and 3 edges.

 
Contributors
=====

    @Ehab-abdelhamid
    @ElSeidy
