package utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;

@UtilityClass
public class RandomValuesUtils {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    public String getEmailAddress() {
        return FAKER.internet().emailAddress();
    }

    public <T> T getElementFromList(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public String getRandomAlphanumericString(int length) {
        return RandomStringUtils.insecure().nextAlphanumeric(length);
    }

    public int nextInt(int from, int to) {
        return RANDOM.nextInt(from, to);
    }
}
