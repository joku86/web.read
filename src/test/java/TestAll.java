import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TestAll {
    @Test
    public void testDateFormat() throws Exception {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm 'Uhr'", Locale.GERMANY);
        LocalDateTime parse = LocalDateTime.parse("23.08.2018 13:00 Uhr", formatter);
        System.out.println(parse);
    }
}
