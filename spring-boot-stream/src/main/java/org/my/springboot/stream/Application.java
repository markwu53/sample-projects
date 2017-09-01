package org.my.springboot.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@FunctionalInterface
interface TriFunction<A, B, C, R> {

    R apply(A a, B b, C c);

    default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c) -> after.apply(apply(a, b, c));
    }
}

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired private ApplicationContext context;
    @Autowired String myAnotherBean;

    public static Map<String, String> myData = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;

        {
            put("A", "avalue");
            put("B", "bvalue");
            put("C", null);
            put("D", "dvalue");
        }

    };
 
    public Boolean mytest(Map.Entry<String, String> e) {
        return e.getValue() == null;
    }
    public Function<Map.Entry<String, String>, Boolean> atest = (Map.Entry<String, String> e) -> e.getValue() == null;
    public Predicate<Map.Entry<String, String>> btest = (Map.Entry<String, String> e) -> e.getValue() == null;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println(myAnotherBean);
        /*
        Map<Boolean, List<Map.Entry<String, String>>> md = myData.entrySet().stream().collect(Collectors.partitioningBy(
                (Predicate<Map.Entry<String, String>>) ((Function<Map.Entry<String, String>, String>) Map.Entry<String, String>::getValue).andThen(
                        (Function<Object, Boolean>) Objects::isNull)));
        Map<Boolean, List<Map.Entry<String, String>>> md = myData.entrySet().stream().collect(
                Collectors.partitioningBy(e -> e.getValue() == null));
        Map<Boolean, List<Map.Entry<String, String>>> md = myData.entrySet().stream().collect(
                Collectors.partitioningBy(this::mytest));
        Map<Boolean, List<Map.Entry<String, String>>> md = myData.entrySet().stream().collect(
                Collectors.partitioningBy(
                        //(Predicate<Map.Entry<String, String>>) atest
                        //e -> atest.apply(e)
                        //btest
                        e -> ((Function<Map.Entry<String, String>, String>) Map.Entry<String, String>::getValue)
                        .andThen(Objects::isNull).apply(e)
                ));
        Map<Boolean, List<Map.Entry<String, String>>> md =
        myData.entrySet().stream().collect(Collectors.groupingBy(
                //((Function<Map.Entry<String, String>, String>) Map.Entry<String, String>::getValue).andThen(Objects::isNull)
                e -> e.getValue() == null
                ));
                        */
        myData.entrySet().stream().filter(e -> e.getValue() != null)
        .forEach(e -> System.out.println(String.format("%s: %s", e.getKey(), e.getValue())));
        //md.get(false).stream().forEach(e -> System.out.println(String.format("%s: %s", e.getKey(), e.getValue())));
        //showBeans();
        //System.out.println(((Supplier<Consumer<String>>) this::genFunc).getClass().getName());
        //Stream.iterate(0, i -> i + 1).limit(5).forEach(System.out::println);
        //System.out.println(((Consumer<String>) this::show).getClass().getName());
        //System.out.println(Application.class.getClass().getName());
        //System.out.println(Application.class.getName());
        //System.out.println(Consumer.class.getName());
        //Arrays.stream(List.class.getTypeParameters()).forEach(a -> System.out.println(a.getName()));
        //Arrays.stream(Arrays.asList("Hello", "World").getClass().getTypeParameters()).forEach(a -> System.out.println(a.getName()));
    }

    public void show(String message) {
        System.out.println(message);
    }

    public Consumer<String> genFunc() {
        //return System.out::println;
        //return this::show;
        //return (String s) -> System.out.println(s);
        return (String s) -> ((Consumer<String>) System.out::println).accept(s);
    }

    public void myBiConsumer(TreeMap<String, String> t, String s) {
        //t.put(s, s);
        t.put(context.getBean(s).getClass().getName(), s);
        //((TriFunction<TreeMap<String, String>, String, String, String>) TreeMap<String, String>::put).apply(t, s, s);
    }

    public void showBeans() throws Exception {
        //Map<String, String> m = new TreeMap<String, String>();
        Arrays.stream(context.getBeanDefinitionNames())
            //.filter(((Predicate<String>)s -> s.startsWith("org.")).negate())
            //.filter(s -> ((Function<String, Boolean>)s::startsWith).andThen((Function<Boolean, Boolean>)b -> !b).apply("org."))
            //.filter(s -> ((Function<Boolean, Boolean>) b -> !b).compose((Function<String, Boolean>)s::startsWith).apply("org."))
            //.filter(s -> ((Function<Boolean, Boolean>) b -> !b).apply(((Function<String, Boolean>)s::startsWith).apply("org.")))
            .filter(s -> !s.startsWith("org."))
            .filter(s -> !s.startsWith("spring."))
            //.collect(TreeMap<String, String>::new, (t, s) -> t.put(context.getBean(s).getClass().getName(), s), (a, b) -> a.putAll(b))
            //.collect(TreeMap<String, String>::new, (BiConsumer<TreeMap<String, String>, String>) this::myConsumer, (a, b) -> a.putAll(b))
            //.collect(TreeMap<String, String>::new, this::myConsumer, (a, b) -> a.putAll(b))
            //.collect(TreeMap<String, String>::new, this::myBiConsumer, (a, b) -> a.putAll(b))
            .collect(TreeMap<String, String>::new,
                    (t, s) -> 
                    ((Function<String, Object>) context::getBean)
                    .andThen((Function<Object, Class<? extends Object>>) Object::getClass)
                    .andThen((Function<Class<? extends Object>, String>) Class::getName)
                    .andThen((Function<String, Function<String, String>>) s1 -> s2 -> //t.put(s1,  s2)
                    ((BiFunction<String, String, String>) t::put).apply(s1, s2))
                    .apply(s).apply(s),
                    (a, b) -> a.putAll(b))
            /*
            .collect(TreeMap<String, String>::new,
                    (t, s) -> (
                            (Function<String, Function<String, String>>)
                            s1 -> s2 -> t.put(s1,  s2))
                            .compose((Function<Class<?>, String>) Class::getName)
                            .compose((Function<Object, Class<?>>) Object::getClass)
                            .compose((Function<String, Object>) context::getBean)
                    .apply(s).apply(s),
                    (a, b) -> a.putAll(b))
            .collect(TreeMap<String, String>::new,
                    (a, b) -> (
                            (Function<String, Function<String, Function<TreeMap<String, String>, String>>>)
                            s1 -> s2 -> t -> t.put(s1, s2))
                            .compose((Function<Class<?>, String>) Class::getName)
                            .compose((Function<Object, Class<?>>) Object::getClass)
                            .compose((Function<String, Object>) context::getBean)
                    .apply(b).apply(b).apply(a),
                    (a, b) -> a.putAll(b))
            .collect(TreeMap<String, String>::new, 
                    (t, s) -> ((BiFunction<String, String, String>)t::put).apply(((Function<String, Object>)context::getBean)
                            .andThen((Function<Object, Class<?>>) Object::getClass)
                            .andThen((Function<Class<?>, String>) Class::getName).apply(s), s)
                    , (a, b) -> a.putAll(b))
                    */
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
                    //return () -> { return new ArrayList<String>(Arrays.asList("Hello", "World")); };
                    return () -> new ArrayList<String>(Arrays.asList("Hello", "World"));
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
