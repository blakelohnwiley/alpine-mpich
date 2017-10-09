# Alpine MPICH

Docker image of Alpine Linux with  [MPICH](http://www.mpich.org/) -- portable implementation of Message Passing Interface (MPI) standard. Designed for MPI program development and deployment.

Provide solution for MPI Cluster Automation with Docker containers using either Docker Compose or Docker Swarm Mode.

----

        Best Paper Award at IEEE CCWC 2017

**N. Nguyen** and **D. Bein**, "[Distributed MPI cluster with Docker Swarm mode](http://ieeexplore.ieee.org/document/7868429/)," 2017 IEEE 7th Annual Computing and Communication Workshop and Conference (CCWC), Las Vegas, NV, USA, 2017, pp. 1-7.

----

Image usage instruction: 
[https://hub.docker.com/r/nlknguyen/alpine-mpich](https://hub.docker.com/r/nlknguyen/alpine-mpich)


Distributed MPI cluster setup instruction: [https://github.com/NLKNguyen/alpine-mpich/tree/master/cluster](https://github.com/NLKNguyen/alpine-mpich/tree/master/cluster)  
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

Since the onbuild image inherits the base image, if you use a different tag name (`nlknguyen/alpine-mpich`), you must change the first line in `onbuild/Dockerfile` to inherits `FROM` your custom tag name.

# License MIT
Copyright © Nikyle Nguyen
