
package aplcassignment;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author rainy
 */
public class CountryData {
    private LocalDate date;
    private int number;

    public CountryData(LocalDate date, int number) {
        this.date = date;
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getData() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }   
}
