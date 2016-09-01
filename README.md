[![Build Status](https://travis-ci.org/agreementmaker/jaws.svg?branch=master)](https://travis-ci.org/agreementmaker/jaws)
[![Coverage Status](https://coveralls.io/repos/agreementmaker/jaws/badge.svg?branch=master&service=github)](https://coveralls.io/github/agreementmaker/jaws?branch=master)
![MIT License](https://img.shields.io/badge/license-MIT-blue.svg)

# Java API for WordNet Searching (JAWS)

JAWS is an API that provides Java applications the ability to access Wordnet. It is a simple and fast API that is compatible with both the 2.1 and 3.0 versions of the WordNet database files.

To use JAWS in your application you must do the following:
* Obtain a copy of the [WordNet database files](https://wordnet.princeton.edu/wordnet/download/current-version/).  Executing `mvn generate-test-resources` will download WordNet 3.0 into the `target/` directory.
* Generate a JAR file containing the compiled JAWS code.  Executing `mvn package` will generate a JAR and put it in the `target/` directory.
* Include the JAWS JAR file in your Java Virtual Machine's class path.
* Use the `wordnet.database.dir` system property to specify the directory where the WordNet database files are located.

For an example, see the [WordNetDatabaseTest](https://github.com/agreementmaker/jaws/blob/master/src/test/java/edu/smu/tspell/wordnet/api/WordNetDatabaseTest.java#L20).

## History

JAWS was developed by Brett Spell as a class project in CSE 8337 in Spring 2007, as part of the [SMU Database Research Group](http://lyle.smu.edu/cse/dbgroup/data.htm).  Brett Spell is an adjunct member of the faculty in the [Computer Science and Engineering (CSE) department](http://lyle.smu.edu/computer/people_adjunct.php) at Southern Methodist University.

This repository is based on the original [JAWS 1.3 source code](http://lyle.smu.edu/~tspell/jaws/#downloads). 

## References

* http://del.icio.us/url/49235d589448f326cade6b3812ba9f07
* http://blog.intelligencecomputing.io/twitter/2750/java-api-for-wordnet-searching-jaws
* http://stackoverflow.com/questions/5976537/wordnet-similarity-in-java-jaws-jwnl-or-java-wnsimilarity

## Other JAWS repositories on GitHub

* https://github.com/fcr/JAWS
* https://github.com/ptorrestr/jaws
* https://github.com/jaytaylor/jaws
