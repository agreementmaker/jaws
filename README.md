[![Build Status](https://travis-ci.org/agreementmaker/jaws.svg?branch=master)](https://travis-ci.org/agreementmaker/jaws)
[![Coverage Status](https://coveralls.io/repos/github/agreementmaker/jaws/badge.svg?branch=master)](https://coveralls.io/github/agreementmaker/jaws?branch=master)
![MIT License](https://img.shields.io/badge/license-MIT-blue.svg)

# Java API for WordNet Searching (JAWS)

JAWS is an API that provides Java applications the ability to access WordNet. It is a simple and fast API that is compatible with both the 2.1 and 3.0 versions of the WordNet database files.

To use JAWS in your application you must do the following:
* Obtain a copy of the [WordNet database files](https://wordnet.princeton.edu/download).  To download them with Maven, run `mvn generate-test-resources` which will download WordNet 3.0 into the `target/WordNet-3.0/dict` directory (make sure you [setup your `toolchains.xml` file](#building-with-maven)).
* Generate a JAR file containing the compiled JAWS code.  Executing `mvn package` will generate a JAR and put it in the `target/` directory.
* Include the JAWS JAR file in your Java Virtual Machine's class path.
* Use the `wordnet.database.dir` system property to specify the directory where the WordNet database files are located.  If you used Maven to download the files, this directory will be `PROJECT_DIR/target/WordNet-3.0/dict`, where `PROJECT_DIR` is the path to where you checked out this repository.

For an example, see the [WordNetDatabaseTest](https://github.com/agreementmaker/jaws/blob/master/src/test/java/edu/smu/tspell/wordnet/api/WordNetDatabaseTest.java#L20).

## History

JAWS was developed by Terrill Brett Spell as a class project in CSE 8337 in Spring 2007, as part of the SMU Database Research Group.  Brett Spell was an adjunct member of the faculty in the Computer Science and Engineering (CSE) department at Southern Methodist University.

This repository is based on the original [JAWS 1.3 source code](http://lyle.smu.edu/~tspell/jaws/#downloads) (it's the [first commit](https://github.com/agreementmaker/jaws/commit/ebf3c29f81b2456015cfcf6c2be312ce171bd0f8) to this repository). 

## Building with Maven

This project uses the [Maven Toolchains Plugin](https://maven.apache.org/guides/mini/guide-using-toolchains.html).  To setup a JDK toolchain, you can start by downloading a JDK from [Adoptium](https://adoptium.net).  The long-term support (LTS) release for the JDK is `21`, as of Nov 2023.  Extract the JDK to a directory on your computer, which we reference as `/path/to/jdk21`.  Finally, put the following in your `~/.m2/toolchains.xml` file:

```
<?xml version="1.0" encoding="UTF8"?>
<toolchains>
  <!-- JDK toolchains -->
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>21</version>
      <vendor>openjdk</vendor>
    </provides>
    <configuration>
      <jdkHome>/path/to/jdk21</jdkHome>
    </configuration>
  </toolchain>
</toolchains>
```

IMPORTANT: Make sure to replace `/path/to/jdk21` with the actual path to where you extracted the JDK.

## References

* http://stackoverflow.com/questions/5976537/wordnet-similarity-in-java-jaws-jwnl-or-java-wnsimilarity

## Other JAWS repositories on GitHub

* https://github.com/fcr/JAWS
* https://github.com/ptorrestr/jaws
* https://github.com/jaytaylor/jaws
