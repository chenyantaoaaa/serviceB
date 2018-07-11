package com.springcloud.arithmetic;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by yantao.chen on 2018-07-11.
 */
public class demo1 {
    public static void main(String[] args) {
        int n = 5;
        int sum = n * n;
        //按顺序拿到所有需要打印的数字
        Queue<Integer> queue = new ArrayBlockingQueue <Integer>(n*n);
        int curNum = 0;
        for (int i = 0; i < sum; i++) {
            queue.add(curNum);
            if(curNum == 9){
                curNum = 0;
            }else{
                curNum ++;
            }
        }
        int printCount = n;
        int nowCount = printCount;
        int way = 1;//1右 2下 3左 4上
        int time = 1;
        while (nowCount>0){
            if(way == 1) {
                System.out.print(queue.poll());
            }else if(way == 2){
                System.out.println("");
                for (int j = 0; j < printCount; j++) {
                    System.out.print(" ");
                }
                System.out.print(+queue.poll());
            }else if(way == 3){

            }else{
                
            }
            if(nowCount-1>0) {
                nowCount--;
            }else{
                if(time == 1) {
                    time = 0;
                    printCount = n-1;
                }else{
                    time = time + 1;
                }
                if(printCount == 0){
                    break;
                }
                nowCount = printCount;
                if(way == 4){
                    way = 1;
                }else{
                    way = way + 1;
                }
            }
        }
    }
}
