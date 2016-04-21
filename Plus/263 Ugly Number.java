/*
263 Ugly Number

Write a program to check whether a given number is an ugly number. 

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7. 

Note that 1 is typically treated as an ugly number. 

*/

/*****
1. 可以整除 num % i == 0;
2. 除尽， 最后 num == 1

411
1. 1的corn case
2. 显式表达更好。
*****/

public boolean isUgly(int num) {
	if(num==1) return true;
	if(num==0) return false;
	while(num%2==0) num=num>>1;
	while(num%3==0) num=num/3;
	while(num%5==0) num=num/5;
	return num==1;
}

// i == 4 will skipped due to i = 2 was tranversed already.
for (int i=2; i<6 && num>0; i++)
	while (num % i == 0)
		num /= i;
return num == 1;
