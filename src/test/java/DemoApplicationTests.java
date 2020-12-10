import com.Application;
import com.service.UserBookService;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@WebMvcTest(UserService.class)
public class DemoApplicationTests {

    @Autowired
    UserBookService userBookService;

    @Autowired
    UserService userService;

    @Test
    public void checkBookAvailable() {
        boolean isAvailable= userBookService.isBookAvailable(1);
        assertTrue(isAvailable);
    }
    @Test
    public void checkUserName() {
        boolean isAvailable= userService.checkUserName("demo123");
        assertTrue(isAvailable);
    }

}