Properties file comparator written in Java

Use CompareProperties class to run. 

Arg1: Path to file one
Arg2: Path to file two
Arg3: Mode

Mode:
count : compares only the number of keys in the files   
1: checks for missing keys in file one in relation to file two.   
2: checks for missing keys in file two in relation to file one.   
v: compares also differences in trimmed values. Case sensitive. 
m: Merges the tow files (values in file 2 have higher prio, means 2 is merged in 1)  


Update from 15th April 2014:
Added executable jar support and Usage help by using -? parameter

Update from 8th February 2019:
Support for merging files:

CompareProperties 1.properties 2.properties 12vm 

2.properties is merged into 1.properties, results are in a new merged.properties-File