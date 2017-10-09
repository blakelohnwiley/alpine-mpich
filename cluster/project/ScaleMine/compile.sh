#!/usr/bin/env ash
cd src/
mpic++ -w -O3 -o ../pfsm -lboost_thread-mt -lboost_system *.cpp
cd ..
