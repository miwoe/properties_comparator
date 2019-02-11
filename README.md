### Properties file comparator and merge tool

Properties file comparator and merge tool written in Java
Feel free to use, branch, extend.


#### Usage 

Use CompareProperties class to run. 

* Arg1: Path to file one
* Arg2: Path to file two
* Arg3: Mode

##### Available modes
_count_ : compares only the number of keys in the files   
_1_: checks for missing keys in file one in relation to file two.   
_2_: checks for missing keys in file two in relation to file one.   
_v_: compares also differences in trimmed values. Case sensitive. 
_m_: Merges the two files (values in file 2 have higher prio, means 2 is merged in 1)  

#### Usage 



_Use case: One wants to merge two properties._

`CompareProperties 1.properties 2.properties m `

2.properties is merged into 1.properties, results are in a new merged.properties-File

##### 1.properties:

    a=1
    b=2

##### 2.properties:

    b=3
    c=42

##### Result merged.properties:

    a=1
    b=3
    c=42

_Use case: One wants to know which properties are missing or are different.

`CompareProperties 1.properties 2.properties 1v `

##### 1.properties:

    a=1
    b=2

##### 2.properties:

    b=3
    c=42


##### Log-Output:

    b does not have same value( '2' <-> '3' )
    1.properties does not contain key c
    Size 1.properties = 2 ==Size 2.properties = 2

Or:

`CompareProperties 1.properties 2.properties 12v `

##### Log-Output:
    2.properties does not contain key a
    b does not have same value( '3' <-> '2' )
    1.properties does not contain key c
    Size 1.properties = 2 ==Size 2.properties = 2


#### Updates

Update from 15th April 2014:
Added executable jar support and Usage help by using -? parameter

Update from 8th February 2019:
Support for merging files:



