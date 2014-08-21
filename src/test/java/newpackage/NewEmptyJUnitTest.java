package newpackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.epam.training.taranovski.spring.core.BeanFactory;
import com.epam.training.taranovski.spring.core.BeanFactoryImpl;
import com.epam.training.taranovski.spring.core.GenericXmlApplicationContext;
import com.epam.training.taranovski.spring.interfaces.GreetingService;
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        GenericXmlApplicationContext context = GenericXmlApplicationContext.getContext(".\\src\\main\\java\\mySpringConfig.xml");
        BeanFactory factory = BeanFactoryImpl.getInstance();

        GreetingService greetingService = factory.getBean("GreetingService", GreetingService.class);
        greetingService.sayHello();
        greetingService.showReferenceItem();

        GreetingService greetingService1 = factory.getBean("GreetingService", GreetingService.class);
        greetingService1.sayHello();
        greetingService1.showReferenceItem();

        greetingService1.setSomeConstructorString("i am a singleton!");
        greetingService1.sayHello();
        greetingService.sayHello();
    }
}
