#!/usr/bin/env ash
mpic++ -std=gnu++11 -O3 -o ../pfsm -I/usr/include/boost -L/usr/lib -lboost_thread-mt -lboost_system-mt *.cpp
exit