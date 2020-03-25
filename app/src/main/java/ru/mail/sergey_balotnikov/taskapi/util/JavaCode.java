package ru.mail.sergey_balotnikov.taskapi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JavaCode {
    public void doSomething(){
        List<Boolean> list= Arrays.asList(true, false, true);
        List<Integer> in = Arrays.asList(1,2,3,4);
        for(int i = 0; i<list.size(); i++){
            System.out.println(in.get(i));
        }
    }
}
