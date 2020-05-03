## Problem

We define super digit of an integer X using the following rules:

Given an integer, we need to find the super digit of the integer.

* If X has only digit, then its super digit is X.
* Otherwise, the super digit of X is equal to the super digit of the sum of the digits of X.

For example, the super digit of 9875 will be calculated as:

```
super_digit(9875)       9+8+7+5 = 29 
super_digit(29) 	2 + 9 = 11
super_digit(11)		1 + 1 = 2
super_digit(2)		= 2  
```

You are given two numbers n and k. The number p is created by concatenating the string n k times. 
Continuing the above example where n = 9875, assume your value k = 4. 
Your initial p = 9875 9875 9875 9875(spaces added for clarity).

```
superDigit(p) = superDigit(9875987598759875)
                  5+7+8+9+5+7+8+9+5+7+8+9+5+7+8+9 = 116
superDigit(p) = superDigit(116)
                  1+1+6 = 8
superDigit(p) = superDigit(8)
```

All of the digits of p sum to 116. The digits of 116 sum to 8. 8 is only one digit, so it's the super digit.

### Function Description

Complete the function superDigit in the editor below. It must return the calculated super digit as an integer.

superDigit has the following parameter(s):

* n: a string representation of an integer.
* k: an integer, the times to concatenate n to make p.

### Input Format

The first line contains two space separated integers, n and k. 

###  Constraints

* 1 <= n <= 10^100000
* 1 <= k <= 10^5

### Output Format 

Return the super digit of p, where p is created as described above.

### Sample Input
```
148 3
```
### Sample Output
```
3
```
### Explanation
```
super_digit(P) = super_digit(148148148) 
               = super_digit(1+4+8+1+4+8+1+4+8)
               = super_digit(39)
               = super_digit(3+9)
               = super_digit(12)
               = super_digit(1+2)
               = super_digit(3)
               = 3.
```
### Sample Input
```
9875 4
```
### Sample Output
```
8
```
### Sample Input
```
123 3
```
### Sample Output
```
9
```
### Explanation
```
super_digit(P) = super_digit(123123123) 
               = super_digit(1+2+3+1+2+3+1+2+3)
               = super_digit(18)
               = super_digit(1+8)
               = super_digit(9)
               = 9
```