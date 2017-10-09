# Alpine MPICH Frequent Subnetwork Mining

Docker was used as the platform to implement and test four frequent subnetwork mining algorithms: gSpan, Grami, ScaleMine, and DistGraph. This repository contains a modified version of this repository [https://github.com/NLKNguyen/alpine-mpich]. 

Distributed MPI cluster setup instruction: [https://github.com/blakelohnwiley/alpine-mpich/tree/master/cluster](https://github.com/blakelohnwiley/alpine-mpich/tree/master/cluster)  
* **[Single Host Orchestration](https://github.com/blakelohnwiley/alpine-mpich/wiki/Single-Host-Orchestration)**


----
`base image` ([Dockerfile](https://github.com/blakelohnwiley/alpine-mpich/blob/master/base/Dockerfile)) : contains MPICH and essential build tools. Intended to be used as development environment for developing MPI programs.

`onbuild image` ([Dockerfile](https://github.com/blakelohnwiley/alpine-mpich/blob/master/onbuild/Dockerfile)) : inherits base image with network setup for cluster. Can be used like base image but intended to be used to build image that contains compiled MPI program in order to deploy to a cluster.

`cluster` ([project scaffolder](https://github.com/blakelohnwiley/alpine-mpich/blob/master/cluster/Dockerfile)) : is a directory containing a setup for deploying MPI programs to a cluster of containers. Include a runner script to automate Docker commands.


*Below is instruction for building the Docker image yourself if you don't want to use the pre-built base or onbuild image.*

----

## Build Instruction

The images are prebuilt and hosted at Docker Hub, but in case you want to build them yourself:

```sh
$ git clone https://github.com/blakelohnwiley/alpine-mpich.git

$ cd alpine-mpich

$ docker build -t alpine-linux-3-4 base/

$ docker build -t alpine-linux-3-4-on-build onbuild/

$ docker build -t cluster cluster/
```

# License MIT
Copyright © Nikyle Nguyen
