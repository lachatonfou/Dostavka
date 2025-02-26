import org.example.RaschetDostavki;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestRaschet {

    @ParameterizedTest
    @CsvFileSource(resources = "Pairwise.csv", numLinesToSkip = 0, delimiter = ';')
    @Tag("Positive")
    void testWithCsvFileSource(int rasstoyanie, boolean gabarity, boolean hrupkost, String zagruzhennost, int Stoim) {
        RaschetDostavki raschet = new RaschetDostavki();
        assertEquals(Stoim, raschet.StoimostDostavki(rasstoyanie, gabarity, hrupkost, zagruzhennost));
    }

    @Test
    @Tag("Negative")
    void exeption_test1(){
        RaschetDostavki raschet = new RaschetDostavki();
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> raschet.StoimostDostavki(-10, true, true, "Obichnaya"));
        assertEquals("Расстояние не может быть меньше 0", thrown.getMessage());
    }

    @Test
    @Tag("Negative")
    void exeption_test2(){
        RaschetDostavki raschet = new RaschetDostavki();
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> raschet.StoimostDostavki(35, true, true, "Obichnaya"));
        assertEquals("Нельзя возить хрупкий товар на расстояние свыше 30 км. Расчет доставки невозможен", thrown.getMessage());
    }
}
