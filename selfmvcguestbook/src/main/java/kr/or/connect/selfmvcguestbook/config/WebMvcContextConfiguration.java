package kr.or.connect.selfmvcguestbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kr.or.connect.selfmvcguestbook.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	// "web.xml" url을 "/"로 바꿔주면서 모든 요청이 들어왔을 때 서블릿을 실행해주게 됩니다.
	// 이 때, 컨트롤러의 URL에 매핑되어있는 요청만 들어오는 게 아니라CSS,이미지,자바스크립트 등등의 모든 요청들을 다 받아옵니다.
	// 이 부분이 없다면 모두 컨트롤러가 가진 "RequestMapping"에서 찾으려고 하면서 오류를 발생시킵니다.
	// 그렇기 때문에 이 부분은 반드시 필요한 부분입니다.
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// "addResourceHandler("/css/**")" -> "/css/**" 이렇게 시작하는 url요청은
		// "addResourceLocations("/css/")" -> 어플리케이션 루트 디렉터리 아래에 있는 "/css/"에서 찾게 해주는 메서드 입니다.
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 

	// 매핑 정보가 없는 URL요청은 Spring의 "DefaultServletHttpRequestHandler"가 처리하도록 해줍니다.
	// 그 다음 "DefaultServletHttpRequestHandler"는 WAS의 DefaultServlet에게 해당일을 넘기게 됩니다.
	// 그러면 WAS는 DefaultServlet이 static한 자원(CSS,html 등)을 읽어서 보여주게 하는 것입니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    	// "DefaultServletHandlerConfigurer" 객체의 "enable()" 메서드를 호출함으로써
    	// "DefaultServletHandler"를 사용하도록 해줍니다.
    	configurer.enable();
    }
   
    // 컨트롤러 클래스를 작성하지 않고도 매핑할 수 있도록 해주는 메서드 입니다.
    // 하지만 프로젝트가 만들어질 때 자동으로 만들었던 index.jsp파일이 있다면
    // 웹 어플리케이션 자체가 주소가 없었을 때 index로 시작되는 파일을 찾는 설정을 가지고 있기 때문에
    // main이 실행되지 않는 것을 볼 수 있습니다.
    // 이 때는 파일의 이름이 index가 아니거나 없거나 다른 위치에 있다면 index.jsp를 찾지 않습니다.  
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    		// "/"요청이 들어오면 "guestbook"이라는 이름의 뷰로 보여주도록 합니다.
    		// "guestbook"이라는 이름만 가지고서는 뷰 정보를 찾아낼 수 없기 때문에
    		// 아래의 "getInternalResourceViewResolver"라는 메서드에서 설정된 형태로 뷰를 사용하게 됩니다.
        registry.addViewController("/").setViewName("guestbook");
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        // resolver의 Prefix는 이름 앞쪽에, Suffix는 이름 뒤쪽에다 붙여 줍니다.
        // 그러면 뷰 정보는 "/WEB-INF/views/main.jsp"가 되는 것입니다.
        // 위의 메서드가 실행되려면 "/WEB-INF/views/main.jsp"가 존재해야 합니다.
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}