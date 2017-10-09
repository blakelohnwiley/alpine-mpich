Alpine MPICH Cluster
====================

Scaffolding project structure for a MPI cluster using [Alpine MPICH](https://hub.docker.com/r/nlknguyen/alpine-mpich) Docker image. Architecturally compatible with *Docker Swarm Mode*. Include a runner script that automates Docker commands.

Original software targets:
- Docker Engine version 1.12.1


Currently, this directory provides two ways to orchestrate the cluster (with different files requirement):
+ Using Docker Compose to replicate production environment on single Docker host
+ Using Docker Swarm Mode for real production environment across multiple Docker hosts.


# For single-host with Docker Compose 

Require **Docker Compose**; target version 1.8.0

Documentation: [wiki](https://github.com/blakelohnwiley/alpine-mpich/wiki/Single-Host-Orchestration)

Relevant files:

```
cluster
├── Dockerfile
├── README.md
├── cluster.sh
├── docker-compose.yml
├── project
│   ├── DistGraph
│   │   ├── Makefile
│   │   ├── README.md
│   │   ├── make
│   │   ├── scripts
│   │   ├── src
│   │   └── testdata
│   ├── Extract-Transform-Load-Cytoscape
│   │   └── ETL-Cytoscape.jar
│   ├── Grami
│   │   ├── GRAMI_UNDIRECTED_SUBGRAPHS.jar
│   │   ├── Grami-Use-Test-Case.sh
│   │   ├── README.md
│   │   ├── brachypodium-correlation-cutoff-0.98-parsed.lg
│   │   └── otu_table_ITS_Soil_Fungi-cleaned-sorenson-0.760000.lg
│   ├── Gspan
│   │   ├── Gspan-Use-Test-Case.sh
│   │   ├── README.md
│   │   ├── brachypodium-correlation-cutoff-0.98-parsed.lg
│   │   ├── otu_table_ITS_Soil_Fungi-cleaned-sorenson-0.760000.lg
│   │   └── parsemis.jar
│   ├── ScaleMine
│   │   ├── Datasets
│   │   ├── LICENCE.txt
│   │   ├── README.md
│   │   ├── compile.sh
│   │   └── src
├── ssh
│   ├── id_rsa
│   └── id_rsa.pub
└── swarm.sh
```
