package simple.spring.maven;

import org.springframework.stereotype.Service;

@Service
public class MyBean {
        public String getStr() {
                return "string";
        }
}
