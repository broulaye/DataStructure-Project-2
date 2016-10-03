# DataStructure-Project-2
Milestones

Milestone 1: Due by Wednesday, September 28 at 11pm
* Pass 2 Web-CAT reference tests.
One of the auto-grader's reference tests is passed simply by submitting something that has the right class name (SearchTree). The other can be passed by being able to insert just one track (that is, two records into a single leaf node in the 2-3 Tree) and then print the tree.

Milestone 2: Due by Monday, October 3 at 11pm
* Pass 6 Web-CAT tests
* Have 90% code coverage from your own JUnit tests with NONE of your own tests failed
* Have at least 1 point for style
In practice, this means that you have some of the easier 2-3 insert tests working.
 
Milestone 3: Due by Wednesday, October 5 at 11pm
* Pass 9 Web-CAT tests
* Have 90% code coverage from your own JUnit tests with NONE of your own tests failed
* Have at least 5 points for style
In practice, this means that you have completed 2-3 Insert, and have some simple removes (from leaf nodes) working.
 
 
Test Descriptions and point values:
 
1. Communications - Test that the main class is named correctly and that the project can be uploaded properly to Web-CAT. [0]
2. Sample - The posted sample test. [4]
3. Insert One Pair - Insert one one track. [1]
4. Insert One Artist With Ten Songs - Insert ten songs by the same artist. [2]
5. Insert Two Pairs - Insert two songs by different artists. [2]
6. Insert Twenty Pairs - Insert twenty different songs by different artists. [2]
7. Insert Twenty Pairs With Duplicate - Insertion with some duplicates. [2]
8. Insert Complicated - A more complicated combination of insertions, including duplicates. [5]
9. Delete One Pair To Empty - Insert one track and then delete it. [2]
10. Delete Without Updating Parent - Includes a deletion that does not require updating the parent's keys. [2]
11. Delete With Update To Parent - A deletion that does require updating the parent's keys. [2]
12. Delete Borrow Key - Includes a delete that requires borrowing keys from siblings. [2]
13. Delete Merge - Includes a deletion that requires merging. [2]
14. Remove Unique Pairs - Test some remove operations. [2]
15. Remove One Key Multiple Value - Remove an artist with multiple songs and a song with multiple artists. [2]
16. Remove Complex - A more complicated series of deletes. [5]
17. List Not Exist - Try to list an artist or song that does not exist. [1]
18. List Single Value - List an artist or song with a single record. [1]
19. List One Key Multiple Value - List an artist or song with multiple records. [1]
20. All Cmd Random - Complicated combination of commands without database expansion. [5]
21. All Cmd Random With DB Expand - Complicated combination of commands including database expansions. [5]
