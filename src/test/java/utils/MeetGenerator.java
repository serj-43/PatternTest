package utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class MeetGenerator {

    @UtilityClass
    public static class MeetInfo {
        public static MeetingData newPerson(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new MeetingData(faker.name().fullName(), faker.phoneNumber().phoneNumber(), faker.address().city());
        }
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
