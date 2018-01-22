jnr-fuse
==
[![Build Status](https://travis-ci.org/SerCeMan/jnr-fuse.svg?branch=master)](https://travis-ci.org/SerCeMan/jnr-fuse)  [ ![Download](https://api.bintray.com/packages/serce/maven/jnr-fuse/images/download.svg) ](https://bintray.com/serce/maven/jnr-fuse/_latestVersion)  [![Join the chat at https://gitter.im/SerCeMan/jnr-fuse](https://badges.gitter.im/SerCeMan/jnr-fuse.svg)](https://gitter.im/SerCeMan/jnr-fuse?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

jnr-fuse is a FUSE implementation in java using Java Native Runtime (JNR). 

## Project Goals

The main goal of the project is to provide an easy way to create a high-performance filesystem in userspace.

## About technologies
[FUSE](https://github.com/libfuse/libfuse) (Filesystem in Userspace)  is an OS mechanism for unix-like OS that lets non-privileged users create their own file systems without editing kernel code. 

[Java Native Runtime](https://github.com/jnr/jnr-ffi) (JNR) is high-performance Java API for binding native libraries and native memory.

## Get it
### Gradle
```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'com.github.serceman:jnr-fuse:0.5.1'
}
````
### Maven
```xml
<repositories>
    <repository>
        <id>central</id>
        <name>bintray</name>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.serceman</groupId>
        <artifactId>jnr-fuse</artifactId>
        <version>0.5.1</version>
    </dependency>
</dependencies>
```

## How to use
For implementing your own filesystem you just need to extend FuseStubFS class and implement methods you need. 

See [some examples](https://github.com/SerCeMan/jnr-fuse/tree/master/src/main/java/ru/serce/jnrfuse/examples).

See [blog article about the implementation](http://serce.me/posts/22-06-2015-jnr-fuse/)

## Projects using jnr-fuse
* [Alluxio](https://github.com/Alluxio/alluxio/tree/master/integration/fuse): Alluxio is a memory-centric distributed storage system
* [mux2fs](https://github.com/tfiskgul/mux2fs) Muxes subtitles into Matroska files as a FUSE filesystem

## Supported platforms
| Supported platforms                                           |     |      |
|---------------------------------------------------------------|-----|------|
| Linux                                                         | x64 | x86  |
| MacOS (via [osxfuse](https://osxfuse.github.io/))             | x64 | x86  |
| Windows (via [winfsp](https://github.com/billziss-gh/winfsp/))| x64 | n/a  |

## Installation and troubleshooting

See [instructions](https://github.com/SerCeMan/jnr-fuse/blob/master/INSTALLATION.md).


