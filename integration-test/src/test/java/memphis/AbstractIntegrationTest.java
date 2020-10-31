package memphis;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = MemphisApplication.class
)
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    protected final MockMvc mockMvc;

    protected AbstractIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


}
