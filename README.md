jnr-fuse
==
[![Build Status](https://travis-ci.org/SerCeMan/jnr-fuse.svg?branch=master)](https://travis-ci.org/SerCeMan/jnr-fuse)  [ ![Download](https://api.bintray.com/packages/serce/maven/jnr-fuse/images/download.svg) ](https://bintray.com/serce/maven/jnr-fuse/_latestVersion)  [![Dependency Status](https://www.versioneye.com/user/projects/55798fad666636001e000005/badge.svg?style=flat)](https://www.versioneye.com/user/projects/55798fad666636001e000005)

jnr-fuse is FUSE implementation in java using Java Native Runtime (JNR). 

## Project Goals
Main goal of the project is to provide easy way to create high-performance filesystem in userspace.

## About technologies
[FUSE](http://fuse.sourceforge.net/) (Filesystem in Userspace)  is an OS mechanism for unix-like OS that lets non-privileged users create their own file systems without editing kernel code. 

[Java Native Runtime](https://github.com/jnr/jnr-ffi) (JNR) is high-performance Java API for binding native libraries and native memory.

## Get it
### Gradle
```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'com.github.serceman:jnr-fuse:0.2.1'
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
            <version>0.2.1</version>
        </dependency>
    </dependencies>
```

## How to use
For implementing your own filesystem you need just extend FuseStubFS class and implement methods you need. 

See [some examples](https://github.com/SerCeMan/jnr-fuse/tree/master/src/main/java/ru/serce/jnrfuse/examples).

## Projects using jnr-fuse
* [Tachyon-FUSE](https://github.com/ibm-research-ireland/tachyon-fuse): Mount a Tachyon DFS using FUSE

## Supported platforms
| Supported platforms |     |      |
|---------------------|-----|------|
| Linux               | x64 | x86  |

