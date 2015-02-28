package simple.spring.maven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

        public static void main(String[] args) {
                @SuppressWarnings("resource")
                ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/config.xml");
                Main p = context.getBean(Main.class);
                p.start(args);
        }

        @Autowired
        private MyBean myBean;

        private void start(String[] args) {
                System.out.println("my beans method: " + myBean.getStr());
        }
}
