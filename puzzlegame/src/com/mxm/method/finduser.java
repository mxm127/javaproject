package com.mxm.method;

import com.mxm.ui.User;

import java.util.ArrayList;

public class finduser {
    public static int findu(ArrayList<User> arr, String name){
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getName() .equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
