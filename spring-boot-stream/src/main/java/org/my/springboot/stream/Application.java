package org.my.springboot.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired ApplicationContext context;
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        showBeans();
    }

    public void myConsumer(TreeMap<String, String> t, String s) {
        t.put(context.getBean(s).getClass().getName(), s);
    }

    public void showBeans() {
        //Map<String, String> m = new TreeMap<String, String>();
        Arrays.stream(context.getBeanDefinitionNames())
            //.filter(((Predicate<String>)s -> s.startsWith("org.")).negate())
            .filter(s -> ((Function<String, Boolean>)s::startsWith).andThen((Function<Boolean, Boolean>)b -> !b).apply("org."))
            //.filter(s -> ((Function<Boolean, Boolean>) b -> !b).compose((Function<String, Boolean>)s::startsWith).apply("org."))
            //.filter(s -> !s.startsWith("org."))
            .filter(s -> !s.startsWith("spring."))
            //.collect(TreeMap<String, String>::new, (t, s) -> t.put(context.getBean(s).getClass().getName(), s), (a, b) -> a.putAll(b))
            //.collect(TreeMap<String, String>::new, (BiConsumer<TreeMap<String, String>, String>) this::myConsumer, (a, b) -> a.putAll(b))
            .collect(TreeMap<String, String>::new, this::myConsumer, (a, b) -> a.putAll(b))
            .forEach((k, v) -> System.out.println(String.format("%s: %s", k, v)))
            //.collect(Collectors.groupingBy(s -> context.getBean(s).getClass().getName()))
            //.forEach((k, v) -> System.out.println(String.format("%s: %s", k, v.get(0))))
            ;
    }

    public void showBeans2() {
        Arrays.stream(context.getBeanDefinitionNames())
            .filter(s -> !s.startsWith("org."))
            .filter(s -> !s.startsWith("spring."))
            .collect(new Collector<String, List<String>, List<String>>() {

                @Override
                public Supplier<List<String>> supplier() {
                    //return ArrayList<String>::new;
                    //return () -> { return new ArrayList<String>(); };
                    //return () -> { return Lists.newArrayList("Hello", "World"); };
                    return () -> { return new ArrayList<String>(Arrays.asList("Hello", "World")); };
                }

                @Override
                public BiConsumer<List<String>, String> accumulator() {
                    //System.out.println("in accumulator");
                    //return List::add;
                    return (a, b) -> { a.add(b); a.add(b); /*System.out.println("in accumulator inside: " + b)*/; };
                }

                @Override
                public BinaryOperator<List<String>> combiner() {
                    //System.out.println("in combiner");
                    //return (left, right) -> { left.addAll(right); System.out.println("in combiner inside"); return left; };
                    return null;
                }

                @Override
                public Function<List<String>, List<String>> finisher() {
                    //System.out.println("in finisher");
                    //return a -> a;
                    return null;
                }

                @Override
                public Set<java.util.stream.Collector.Characteristics> characteristics() {
                    //System.out.println("in characteristics");
                    return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
                }
 
            }).stream()
            .forEach(System.out::println);
            //.collect(Collectors.toList())
            //.map(s -> new String[] { s, context.getBean(s).getClass().getName() })
            //.forEach(ss -> { System.out.println(String.format("%s: %s", ss[0], ss[1])); });
    }

}
