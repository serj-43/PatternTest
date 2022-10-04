import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.MeetGenerator;
import utils.MeetingData;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;


@Data
public class MettingTest {

    public static MeetingData person1, person2;

    @BeforeEach
    void initOperations(){
        open("http://localhost:9999");
    }

    @Test
    void shouldPlanMeeting() {
        MeetingData person = MeetGenerator.MeetInfo.newPerson("ru");
        String planDate = MeetGenerator.generateDate(3);
        $x("//*[@data-test-id='city']//input").setValue(person.getCity());
        $x("//*[@data-test-id='date']//input").setValue(planDate);
        $x("//*[@data-test-id='name']//input").setValue(person.getName());
        $x("//*[@data-test-id='phone']//input").setValue(person.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $x("//button//*[text()='Запланировать']").click();
        $x("//*[@data-test-id='success-notification']//*[@class='notification__title'][text()='Успешно!']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='success-notification']//*[@class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + planDate));
    }

    @Test
    void shouldRePlanMeeting() {
        MeetingData person = MeetGenerator.MeetInfo.newPerson("ru");
        String planDate = MeetGenerator.generateDate(3);
        $x("//*[@data-test-id='city']//input").setValue(person.getCity());
        $x("//*[@data-test-id='date']//input").setValue(planDate);
        $x("//*[@data-test-id='name']//input").setValue(person.getName());
        $x("//*[@data-test-id='phone']//input").setValue(person.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $x("//button//*[text()='Запланировать']").click();
        $x("//*[@data-test-id='success-notification']//*[@class='notification__title'][text()='Успешно!']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='success-notification']//*[@class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + planDate));
        planDate = MeetGenerator.generateDate(5);
        $x("//*[@data-test-id='date']//input").sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").setValue(planDate);
        $x("//button//*[text()='Запланировать']").click();
        $x("//*[@data-test-id='replan-notification']//*[@class='notification__title'][text()='Необходимо подтверждение']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='replan-notification']//button//*[text()='Перепланировать']").click();
        $x("//*[@data-test-id='success-notification']//*[@class='notification__title'][text()='Успешно!']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='success-notification']//*[@class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + planDate));
    }

}
