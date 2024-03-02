package com.mxm.method;

import java.util.Random;

public class codeUtil {
    private static char[][] arr = new char[2][26];

    public static String getcode() {
        getletter();
        char[] narr = new char[5];
        Random ra = new Random();
        int l = narr.length;
        for (int i = 0; i < l - 1; i++) {
            narr[i] = arr[ra.nextInt(2)][ra.nextInt(26)];
        }
        int index = ra.nextInt(4);
        narr[l - 1] = narr[index];
        narr[index] = (char) (ra.nextInt(10) + '0');
        return new String(narr);
    }

    private static void getletter() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (char) ('A' + j + 32 * i);
            }
        }
    }
}
