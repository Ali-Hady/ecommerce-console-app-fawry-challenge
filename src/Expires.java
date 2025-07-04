import java.time.LocalDate;

public interface Expires {
    LocalDate getExpirationDate();
    void setExpirationDate(LocalDate expirationDate);

    default void setExpirationDateByDays(int numberOfDays) {
        if (numberOfDays < 0) {
            throw new IllegalArgumentException("Number of days cannot be negative");
        }
        setExpirationDate(LocalDate.now().plusDays(numberOfDays));
    }

    default void setExpirationDateByWeeks(int numberOfWeeks) {
        if (numberOfWeeks < 0) {
            throw new IllegalArgumentException("Number of weeks cannot be negative");
        }
        setExpirationDate(LocalDate.now().plusWeeks(numberOfWeeks));
    }

    default void setExpirationDateByMonths(int numberOfMonths) {
        if (numberOfMonths < 0) {
            throw new IllegalArgumentException("Number of months cannot be negative");
        }
        setExpirationDate(LocalDate.now().plusMonths(numberOfMonths));
    }

    default void setExpirationDateByYears(int numberOfYears) {
        if (numberOfYears < 0) {
            throw new IllegalArgumentException("Number of years cannot be negative");
        }
        setExpirationDate(LocalDate.now().plusYears(numberOfYears));
    }
}
