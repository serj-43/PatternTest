import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.MeetGenerator;
import utils.MeetingData;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static utils.MeetingData.generateDate;

import java.time.Duration;


@Data
public class MettingTest {

    public static MeetingData person1, person2;

    @BeforeAll
    public static void initOperations(){
        person1 = MeetGenerator.MeetInfo.Person("ru");
        person2 = MeetGenerator.MeetInfo.Person("ru");
        open("http://localhost:9999");
    }

    @BeforeEach
    void clearingData(){
        $x("//*[@data-test-id='city']//input").sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        $x("//*[@data-test-id='name']//input").sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        $x("//*[@data-test-id='phone']//input").sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
    }

    @Test
    void shouldPlanMeeting1() {
        String planDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").setValue(person1.getCity());
        $x("//*[@data-test-id='date']//input").setValue(planDate);
        $x("//*[@data-test-id='name']//input").setValue(person1.getName());
        $x("//*[@data-test-id='phone']//input").setValue(person1.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $x("//button//*[text()='Запланировать']").click();
        $x("//*[@data-test-id='success-notification']//*[@class='notification__title'][text()='Успешно!']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='success-notification']//*[@class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + planDate));
    }

    @Test
    void shouldPlanMeeting2() {
        String planDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").setValue(person2.getCity());
        $x("//*[@data-test-id='date']//input").setValue(planDate);
        $x("//*[@data-test-id='name']//input").setValue(person2.getName());
        $x("//*[@data-test-id='phone']//input").setValue(person2.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $x("//button//*[text()='Запланировать']").click();
        $x("//*[@data-test-id='success-notification']//*[@class='notification__title'][text()='Успешно!']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='success-notification']//*[@class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + planDate));
    }

    @Test
    void shouldRePlanMeeting1() {
        String planDate = generateDate(5);
        $x("//*[@data-test-id='city']//input").setValue(person1.getCity());
        $x("//*[@data-test-id='date']//input").setValue(planDate);
        $x("//*[@data-test-id='name']//input").setValue(person1.getName());
        $x("//*[@data-test-id='phone']//input").setValue(person1.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $x("//button//*[text()='Запланировать']").click();
        $x("//*[@data-test-id='replan-notification']//*[@class='notification__title'][text()='Необходимо подтверждение']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='replan-notification']//button//*[text()='Перепланировать']").click();
        $x("//*[@data-test-id='success-notification']//*[@class='notification__title'][text()='Успешно!']").should(visible, Duration.ofSeconds(20));
        $x("//*[@data-test-id='success-notification']//*[@class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + planDate));
    }

}
