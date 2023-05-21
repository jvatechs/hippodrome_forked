import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Incubating;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {
    private final Horse horse = new Horse("Roach", 10, 50);
    private final Horse horse_2args = new Horse("Roach", 10);

    @Test
    public void checkConstructorException_When_FirstArg_Is_Null() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 0);
        });

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void checkConstructorException_When_FirstArg_Is_Null_Or_Blank(String firstArg) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(firstArg, 0);
        });

        assertEquals("Name cannot be blank.",
                exception.getMessage());
    }

    @Test
    public void checkConstructorException_When_SecondArg_Is_Negative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           new Horse("input", -10);
        });

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void checkConstructorException_When_ThirdArg_Is_Negative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("input", 0, -10);
        });

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void check_GetName_Return() {
        assertEquals("Roach", horse.getName());
    }

    @Test
    public void check_GetSpeed_Return() {
        assertEquals(10, horse.getSpeed());
    }

    @Test
    public void check_GetDistance_Return() {
        assertEquals(50, horse.getDistance());
        assertEquals(0, horse_2args.getDistance());
    }

    @Test //here must be test for move()
    public void checkMove_IsCall_GetRandomDoubleWithArgs() {

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

//    @Test
//    void tryVerifyStaticMethod() {
//        try (MockedStatic<Animal> mockedStatic = mockStatic(Animal.class)) {
//            mockedStatic.verify(() -> Animal.getRandomNumber(1, 10), times(1));
//        }
//    }
}