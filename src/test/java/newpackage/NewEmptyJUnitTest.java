package newpackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.epam.training.taranovski.spring.core.BeanFactory;
import com.epam.training.taranovski.spring.core.GenericXmlApplicationContext;
import com.epam.training.taranovski.spring.interfaces.GreetingService;
import com.epam.training.taranovski.spring.interfaces.SomeBean;
import com.epam.training.taranovski.spring.interfaces.SomeBean1;
import com.epam.training.taranovski.spring.interfaces.SomeBean2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alyx
 */
public class NewEmptyJUnitTest {

    GenericXmlApplicationContext context;
    BeanFactory factory;

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        context = GenericXmlApplicationContext.getContext(".\\src\\main\\java\\mySpringConfig.xml");
        factory = context.getBeanFactory();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        GreetingService greetingService = factory.getBean("GreetingService", GreetingService.class);

        assertTrue("Hello World!".equals(greetingService.sayHello()));
        
        SomeBean someBean = factory.getBean("SomeBean", SomeBean.class);

        assertTrue(someBean.toString().equals(greetingService.showReferenceItem()));

    }

    @Test
    public void hello1() {

        GreetingService greetingService = factory.getBean("GreetingService", GreetingService.class);
        greetingService.sayHello();
        greetingService.showReferenceItem();

        greetingService.setSomeConstructorString("i am a singleton!");
        greetingService.sayHello();

        SomeBean someBean = factory.getBean("SomeBean", SomeBean.class);
        someBean.showMyContents();

        SomeBean1 someBean1 = factory.getBean("SomeBean1", SomeBean1.class);
        someBean1.showMySelf();

        SomeBean2 someBean2 = factory.getBean("SomeBean2", SomeBean2.class);
        someBean2.showYourSelf();
    }

}
