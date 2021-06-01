//Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jonas Emil Gehrke og Jimmi Paw Pisalita

package gruppe6.eksamensprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EksamensprojektApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EksamensprojektApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EksamensprojektApplication.class, args);
    }

}
