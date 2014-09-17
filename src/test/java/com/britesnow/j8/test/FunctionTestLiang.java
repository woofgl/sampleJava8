package com.britesnow.j8.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

public class FunctionTestLiang {
    
    @Test
    public void testfunction(){
        Function<String, String> frist = x -> x + "1 ";
        Function<String, String> second = x -> x + "2 ";
        Function<Integer,Integer> adds = x -> x + 6;
        Function<Integer,Integer> products = x -> x * 5;
        
        //apply
        System.out.println(frist.apply("show1 "));
        System.out.println(second.apply("show2 "));
        
        System.out.println("8 + 6 = " + adds.apply(8));
        System.out.println("8 * 5 = " + products.apply(8) + "\n");
        
        //andThen
        System.out.println(frist.andThen(second).apply("frist and then second: "));
        System.out.println(second.andThen(frist).apply("second and then frist: "));
        
        System.out.println("(6 + 6) * 5 = " + adds.andThen(products).apply(6));
        System.out.println("(8 * 5) + 6 = " + products.andThen(adds).apply(8) + "\n");
        
        //compose
        System.out.println(frist.compose(second).apply("frist compose second: "));
        System.out.println(second.compose(frist).apply("second compose frist: "));
        
        System.out.println("6 + (6 * 5) = " + adds.compose(products).apply(6));
        System.out.println("(8 + 6) * 5= " + products.compose(adds).apply(8) + "\n");
    }
    
  
    @Test
       public void testPredicate(){
            List<String> list = new ArrayList();
            list.add("Jira");
            list.add("Lily");
            list.add("Lily Jun");
            list.add("John");
            list.add("Jila zhou");
            
            Predicate<String> startsWithJ = n -> n.startsWith("J");
            Predicate<String> fourLetterLong = n -> n.length() == 4;
            //and
            list.stream()
            .filter(startsWithJ.and(fourLetterLong))
            .forEach((n) -> System.out.print("\nName, which starts with 'J' and four letter long is : " + n +"\n"));  
            
            System.out.println("\n" + "or:");
            list.stream().filter(startsWithJ.or(fourLetterLong)).forEach(System.out::println);
            
            System.out.println("\n" + "isEqual:");
            list.stream().filter(Predicate.isEqual("leaf")).forEach(System.out::println);
            list.stream().filter(Predicate.isEqual("Lily")).forEach(System.out::println);
            
            System.out.println("\n" + "negate:");
            list.stream().filter(startsWithJ.negate()).forEach(System.out::println);
            
       }
    
    @Test
    public void testConsumer(){
        Consumer<Integer> consumer = x -> {
            x += 5;
            System.out.println(x);
        };
        System.out.println("\n" + "2 + 5 = ");
        consumer.accept(2);
        
        Consumer<Integer> consumer2 = x -> {
            x -=3;
            System.out.println(x);
        };
        System.out.println("\n" + "10 + 5 and then 10 - 3");
        consumer.andThen(consumer2).accept(10);
        
    }
    
    @Test
    public void testSupplier(){
        Supplier supplier = () -> { return 1 ;};
        System.out.println("\n" + supplier.get() + "\n");
    }
    
    @Test
    public void testBinaryOperator(){
        BinaryOperator<Integer> binaryOperator = (a, b) -> {return a + b;};
        Integer x = binaryOperator.apply(3, 5);
        System.out.println("aplly:" + x);
        
    }
}
