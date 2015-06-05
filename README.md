jnr-fuse
==
[![Build Status](https://travis-ci.org/SerCeMan/jnr-fuse.svg?branch=master)](https://travis-ci.org/SerCeMan/jnr-fuse) 

jnr-fuse is FUSE implementation in java using Java Native Runtime (JNR). 

## Project Goals
Main goal of the project is to provide easy way to create high-performance filesystem in userspace.

## About technologies
[FUSE](http://fuse.sourceforge.net/) (Filesystem in Userspace)  is an OS mechanism for unix-like OS that lets non-privileged users create their own file systems without editing kernel code. 

[Java Native Runtime](https://github.com/jnr/jnr-ffi) (JNR) is high-performance Java API for binding native libraries and native memory.

## How To
For implementing your own filesystem you need just extend FuseStubFS class and implement methods you need. 

See [some examples](https://github.com/SerCeMan/jnr-fuse/tree/master/src/main/java/ru/serce/jnrfuse/examples).

## Supported platforms
Now only Linux x64 supported. Linux x32 would be supported soon.

