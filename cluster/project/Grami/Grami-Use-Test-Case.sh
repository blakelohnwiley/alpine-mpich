#!/bin/ash
# name of input file name
input_filename=$1
# How many times should the SN appear before 
# being detected. 
freq=$2
# What search space restrictions?
maxNodes=$3
maxEdges=$4vi 

# used to modify output file name
short_input_filename=${input_filename:0:5}
new_output_filename=$short_input_filename-$freq-$maxNodes-$maxEdges.txt
# jar executeable
java -jar GRAMI_UNDIRECTED_SUBGRAPHS.jar \
datasetFolder=$PWD/ filename=$input_filename \
soutput=$new_output_filename \
freq=$freq maxnodes=$maxNodes maxedges=$maxEdges
