package com.britesnow.j8.test;

import java.io.File;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;

import jdk.nashorn.api.scripting.ScriptObjectMirror;


public class JSTestLiang {
    private final String baseDirStr = "src/test/resources/jstest/";
    
    @Test
    public void testJS() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine1 = engineManager.getEngineByName("nashorn");
       
        engine1.eval("var x = 3");
        engine1.eval("var y = 5");
        engine1.eval("z = x + y;");
        engine1.eval("print(z)");

        ScriptEngine engine2 = engineManager.getEngineByName("nashorn");
        try {
            engine2.eval("print(x)");
            engine2.eval("print(y)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        engine2.eval("print(new Date());");
        engine2.eval("function test(){print('this is a new engine');}");
        engine2.eval("test();");

        ScriptObjectMirror testFunc = (ScriptObjectMirror) engine2.eval("test");
        testFunc.call(null);

        Object jsDate = engine2.eval("new Date()");
        System.out.println(jsDate);

        Invocable invocable = (Invocable) engine2;
        invocable.invokeMethod(jsDate, "setDate", 1);
        invocable.invokeMethod(jsDate, "setMonth", 0);
        
        System.out.println(jsDate);
        invocable.invokeFunction("test");

    }
    
    @Test
    public void testJava() throws Exception {
        
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        File javaExtendJS = new File("src/test/resources/jstest/sample/javaextend.js");
        // convert java type to js object
        engine.eval(new FileReader(javaExtendJS));
    }
    
    @Test
    public void testJavaT() throws Exception {

        String mustacheBaseDir = baseDirStr + "sample/";
        
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        
        File mustacheJS = new File(mustacheBaseDir,"stream.js");
        engine.eval("print(new Date());");
        engine.eval(new FileReader(mustacheJS));
        
    }
}