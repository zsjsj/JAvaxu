package com_9_6;

import org.junit.Test;

import java.util.Random;

public class nmatrix {

    public int[][] arr(){
        Random r = new Random();
        int[][] arr = new int[5][5];
        for (int i = 0;i<5;i++){
            for (int j=0;j<5;j++){
                arr[i][j]=r.nextInt(100);
            }
        }
        return arr;
    }
    @Test
    public void nxn(){
        int[][] a = arr();
        int[][] b= arr();
        int[][] a1 = new int[5][5];
        int count = 0;
        for (int i=0;i<5;i++){
            for (int j =0 ;j<5;j++){
                for (int k =0;k<5;k++){
                    a1[i][j]+=a[i][k]*b[k][j];
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
