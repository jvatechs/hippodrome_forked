import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    private static List<Horse> horses;
    private final List<Horse> horsesMocked = new ArrayList<>();
    private Hippodrome hippodrome;

    @BeforeAll
    public static void setHorses() {
        horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse " + i, i, i));
        }
    }

    @Test
    void checkConstructorException_When_Arg_Is_Null() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void checkConstructorException_When_Arg_Is_EmptyList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    void check_ReturnedList_Equality_In_GetHorses() {
        hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void check_Are_AllHorses_Moving() {
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horsesMocked.add(horse);
        }
        hippodrome = new Hippodrome(horsesMocked);
        hippodrome.move();
        hippodrome.getHorses()
                .forEach(horse -> {
                    Mockito.verify(horse).move();
                });
    }

    @Test
    void check_Horse_With_Most_Distance_Value() {
        hippodrome = new Hippodrome(horses);
        Horse mostDistanceValueHorse = hippodrome.getWinner();
        assertEquals(30.0, mostDistanceValueHorse.getDistance());
    }
}