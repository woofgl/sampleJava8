package com.britesnow.j8.test;

import org.junit.Test;

import com.britesnow.j8.Hint;
import com.britesnow.j8.Hints;
import com.britesnow.j8.User;

public class AnnotationTestLiang {
    
    

    @Test
    public void AnnotationTest(){
        Hint hint = User.class.getAnnotation(Hint.class);
        System.out.println(hint);
        
        Hints hints1 = User.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  
        
        Hint[] hints2 = User.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);  
    }
}
