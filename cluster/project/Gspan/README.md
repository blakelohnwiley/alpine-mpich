gSpan
=====

gSpan (graph-based substructure pattern mining), which discovers frequent substructures without candidate generation. gSpan builds a new lexicographic order among graphs, and maps each graph to a unique minimum DFS code as its canonical label. Based on this lexicographic order gSpan adopts the depth-first search strategy to mine frequent connected subgraphs efficiently. Our performance study shows that gSpan substantially outperforms previous algorithms, sometimes by an order of magnitude.

For more details, check our paper: Mohammed Elseidy, Ehab Abdelhamid, Spiros Skiadopoulos, and Panos Kalnis. "X. Yan and J. Han. GSPAN: Graph-based substructure pattern mining. In Proc. of ICDM, pages 721–724, 2002."

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
    - gSpan scpirt has 4 input parameters:
        - inputfile
        - frequency threshold for subnetwork
        - number of nodes in subnetwork, used to restrict the search space
        - number of edges in subnetwork, used to restrict the search space

EXAMPLES:
=====
    - ./gSpan.sh brachypodium-correlation-cutoff-0.98-parsed.lg 1 3 3 #mines brachypodium dataset for subetworks with frequncy of one with 3 nodes and 3 edges. 
    - ./gSpan.sh otu_table_ITS_Soil_Fungi-cleaned-sorenson-0.760000.lg 1 3 3 #mines brachypodium dataset for subetworks with frequncy of one with 3 nodes and 3 edges.

