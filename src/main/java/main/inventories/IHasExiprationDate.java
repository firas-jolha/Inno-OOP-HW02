package main.inventories;

import java.time.LocalDate;

public interface IHasExiprationDate {
    void setExpirationDate(LocalDate date);
    LocalDate getExpirationDate();
}
