package com.xjt.test;


import com.xjt.util.TimeUtil;

public class test {

    public static void main(String[] args) {
        String timeStamp = TimeUtil.timeStamp();
        System.out.println("timeStamp="+timeStamp); //运行输出:timeStamp=1470278082
        System.out.println(System.currentTimeMillis());//运行输出:1470278082980
        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数

        String date = TimeUtil.timeStamp2Date(timeStamp, null);
        System.out.println("date="+date);//运行输出:date=2016-08-04 10:34:42

        String timeStamp2 = TimeUtil.date2TimeStamp(date, null);
        System.out.println(timeStamp2);  //运行输出:1470278082
    }
}
