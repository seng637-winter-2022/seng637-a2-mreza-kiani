**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:              | 16  |   
|------------------------|-----|
| Shahryar Soltanpour    |     |
| Mohammad Reza Kianifar |     |
| Muhammad Raihan        |     |

# Link of demo video

[Video Link](https://uofc-my.sharepoint.com/:v:/g/personal/shahryar_soltanpour_ucalgary_ca/EXWPxt-PmldEiKqqr3gGE1EBPMeAtTKsmYDa1GJDswXPjQ?e=UUwOgj)

It works for everyone with logged-in university account on outlook. Please [tell us](mailto:mohammadreza.kianifa@ucalgary.ca) if you had any problem.

# 1 Introduction

In this assignment, we want to test 5 methods for 2 classes of the JFreeChart library.
The technique that we are going to use is black-box testing, and our system under test (SUT) is a jar file.
JFreeChart is an open-source library for chart calculation, creation, and display. This framework supports different
chart types, such as pie charts, bar charts, line charts, histograms, etc. We also have access to its related Javadoc.
First, we have to read the java doc and also run the available demo to get more familiar with the JFreeChart.
Then we should write tests for 5 methods of DataUtilities class and 5 other methods in the Range class.
Black-box testing is a method of software testing that focuses on the functionality of an application without knowing 
the internal code structure, implementation details, and internal paths. Black Box Testing mainly uses the input
and output of the applications. We added all our test cases in the _src_ directory. Also, the required libraries are
inside the _lib_ directory. For running the project, you should open the project with an IDE like IntelliJ. 
Then you should open the _Project Structure_ and add the libraries to the project. Then you will be able to run the 
test suits that we wrote for each class.

# 2 Detailed description of unit test strategy

First we ran the JFreeChart demo to get familiar with the library and its features. Then we took a look at the Javadoc
for the two Range & DataUtilities class, and tried to know their methods. For each method, we tried to use specific
technique to do black box testing. The technique we used are:

* Equivalence class testing
* Boundary value analysis
* Robustness testing
* Worst case testing

By using these techniques, we tried to cover all the expected behaviour of a method. In some cases, more specifically
for DataUtilities class, we used mocking to mimic the behaviour of a not-implemented or complex class, so then we were
able to continue our unit testing. But there was a main disadvantage of using a mock object:
It is very coupled with the unit tests. In fact, sometimes the unit test is working based on the expected behaviour of
our mock. And if we use mocks more than it's necessary, our testing maintenance would go rise.

Finally, we tested 5 methods of Range class and 5 methods of DataUtilities class. You can see specific description for
each one in the table bellow:

| Class         |       Method Name       |                                                                         Description                                                                         |                                                                                                                                                                                                                                                   Testing Strategy                                                                                                                                                                                                                                                    |
|:--------------|:-----------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Range         |       intersects        |                                     Returns true if the range intersects with the specified range, and false otherwise                                      |                                                                       We chose Equivalence class testing and boundary value analysis and worst case testing for this method.<br><br> For Range(-1, 1):<br> intersects(-2, -1) was used for boundary testing.<br>intersects(Double.NaN, Double.NaN), (0.5, -0.5) for worst case testing.<br>And these values for Equivalence testing: (-0.5, 0.5), (-2, 2), (-1.5, 0.5), (-0.5, 1.5), (-2, -1.5)                                                                       |
| Range         |   combineIgnoringNaN    |                   Returns a new range that spans both range1 and range2. This method has a special handling to ignore Double.NaN values.                    |                                                                 We used Equivalence class testing and robustness testing for this method. Because it was a combine method that ignores null, all possible formats were expected.<br><br>These inputs were tested for ECT:<br>Range(-1, 1), Range(1, 2)<br>Range(-1, 1.5), Range(0.5, 2)<br>Range(-1, 0.5), Range(1.5, 2)<br>and (null, null), (r, null), (null, r) were tested for robustness testing                                                                 |
| DataUtilities |  calculateColumnTotal   |                                          Returns the total of the values in one column of the supplied data table.                                          | For this method, we used mocking for different behaviour of Values2D. Then we used Equivalence class testing and robustness testing.<br><br>For ECT, we used values with different number of rows, e.g. 0, 1, 2 to see if it works well or not. For rows 2, we also checked different index of the summation. <br> For robustness testing, we passed some null as part or whole of the values to see if we receive the suitable exception or not. Also, we used index lower than zero and larger than number of rows. |
| DataUtilities |    calculateRowTotal    |                                           Returns the total of the values in one row of the supplied data table.                                            |                                                                                                                                                                                   We did the same thing here in compared with calculateColumnTotal. The different is that here we controlled the tests using different height size.                                                                                                                                                                                   |
| DataUtilities |    createNumberArray    |                                          Constructs an array of Number objects from an array of double primitives.                                          |                                                                           We used equivalence class testing, boundary analysis, robustness testing and worst case testing for this method. Equivalence class testing inputs: <br>Data = {1, 2, 3} <br> Data = {1.2, 2.3, 3.9} <br> Worst case input: <br> Data = {1, 2.3, 3.9} <br> Inputs for robustness testing: <br> Data = null <br> Inputs for boundary value analysis: <br> Data = {}                                                                           |
| DataUtilities |   createNumberArray2D   |                        Constructs an array of arrays of Number objects from a corresponding structure containing double primitives.                         |                                                     We used equivalence class testing, boundary analysis, robustness testing for this method. Equivalence class testing inputs: <br>Data = {{1, 2, 3, 4}, {5, 6, 7}} <br> Data = {{1.2, 2.3, 3.5, 4.7}, {5.2, 6.3, 7.9}} <br> Worst case input: <br> Data = {{1, 2.3, 3.5, 4.7}, {5.2, 6.3, 7}}<br> Inputs for robustness testing: <br> Data = null <br> Inputs for boundary value analysis: <br> Data = {{},{}}                                                      |                                                                                                       |
| DataUtilities | getCumulativePercentage |                 Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance.                 |                                                                                                                              We've used boundary value analysis with input: <br> Data= (0,24) <br> Equivalence class testing: <br> Data = {(1,4),(2,5),(3,6)} <br> Robustness testing: <br> Data={(1,4),(2,null),(3,6)} <br> Data={(1,4),(null,5),(3,6)} <br> Data={(1,4),(2,-5),(3,6)}                                                                                                                               |

# 3 Test cases developed

For this section, please refer to the [Excel](https://github.com/seng637-winter-2022/seng637-a2-mreza-kiani/blob/main/Test%20Cases.xlsx) file located the main directory.

# 4 How the team work/effort was divided and managed

We divided the methods into three parts (3, 4, 3), and after planning the tests, we wrote our test cases.
Then, each of us double-checked the test cases of the others.
In the end, we added 2 test suites for running the whole tests for a class together.
Finally, we answered all of the questions of the report separately and merged them at the end.
It's worth mentioning that we used Discord for our communication and Zoom meeting for capturing the demo. 

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
