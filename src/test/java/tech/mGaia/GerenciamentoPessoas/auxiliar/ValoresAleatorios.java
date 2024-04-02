package tech.mGaia.GerenciamentoPessoas.auxiliar;

import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Random;

@Profile("teste")
public class ValoresAleatorios {

    private static final Random random = new Random();

    public static Integer getInteiro() {
        return random.nextInt();
    }

    public static Integer getInteiro(Integer max) {
        return random.nextInt(max);
    }

    public static Integer getInteiro(Integer min, Integer max) {
        return random.nextInt(max - min) + min;
    }

    public static Integer getInteiroPositivo(Integer min, Integer max) {
        return random.nextInt((max - min + 1)) + min;
    }

    public static Long getLong() {
        return random.nextLong();
    }

    public static LocalDate getLocalDate() {
        return LocalDate.of(Year.now().getValue(), Month.of(getInteiroPositivo(1, 12)), getInteiroPositivo(1, 30));
    }

}