package SpringMVC.RestController;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

   

   @Override
   public void configurePathMatch(PathMatchConfigurer configurer) {
      UrlPathHelper pathHelper = new UrlPathHelper();
      //Enable matrix variable
      pathHelper.setRemoveSemicolonContent(false);
      configurer.setUrlPathHelper(pathHelper);
   }

}