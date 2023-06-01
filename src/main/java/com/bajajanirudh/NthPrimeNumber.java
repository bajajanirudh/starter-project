package com.bajajanirudh;

import java.util.ArrayList;
public class NthPrimeNumber
{
    public static int num=1;
    public NthPrimeNumber (int index){
        num=1;
        int n = index;
        int count=0, i;
        while (count < n)
        {
            num=num+1;
            for (i = 2; i <= num; i++)
            {
                //determines the modulo and compare it with 0
                if (num % i == 0)
                {
                    //breaks the loop if the above condition returns true
                    break;
                }
            }
            if (i == num)
            {
                //increments the count variable by 1 if the number is prime
                count = count+1;
            }
        }
    }
}