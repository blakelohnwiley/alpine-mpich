#!/bin/ash
# name of input file name
input_filename=$1
# How many times should the SN appear before 
# being detected. 
freq=$2
# What search space restrictions?
maxNodes=$3
maxEdges=$4

# outputt file name
short_input_filename=${input_filename:0:5}
new_output_filename=$short_input_filename-$freq-$maxNodes-$maxEdges.txt
exec_file_name=exec_time_$short_input_filename-$freq-$maxNodes-$maxEdges.txt
# jar executeable
java -jar parsemis.jar --graphFile=$input_filename \
--minimumFrequency=$freq --maximumFrequency=$freq \
--minimumNodeCount=$maxNodes --maximumNodeCount=$maxNodes \
--minimumEdgeCount=$maxEdges --maximumEdgeCount=$maxEdges \
--outputFile=$new_output_filename --execFile_output=$exec_file_name
