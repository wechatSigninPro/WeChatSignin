package com.nenu.edu.server.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:30 2020/7/4
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<List<Long>> data = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int len = in.nextInt();
            List<Long> temp = new ArrayList<>(len);
            for (int j = 0; j < len; j++) {
                temp.add(in.nextLong());
            }
            data.add(temp);
        }

        for (List<Long> temp : data) {
            int len = temp.size();
            long result = 0;
            if (len > 1) {
                long a = temp.get(len - 1) - temp.get(len - 2);
                long b = temp.get(1) - temp.get(0);
                long min = Math.min(a, b);

                long max = temp.get(len - 1) - temp.get(0);

                for (long i = min; i <= max; i++) {
                    if (test(i, temp)) {
                        result = i;
                        break;
                    }
                }
            }
            System.out.println(result);
        }
    }

    public static boolean test(long t, List<Long> data) {
        boolean flag = true;
        int len = data.size();
        for (Long j : data) {
            long x = j + t;
            long y = j - t;

            if (x <= data.get(len - 1) && !data.contains(x)) {
                flag = false;
                break;
            }

            if (y >= data.get(0) && !data.contains(y)) {
                flag = false;
                break;
            }
        }

        return flag;
    }
}
