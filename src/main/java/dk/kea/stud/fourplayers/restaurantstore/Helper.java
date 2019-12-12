package dk.kea.stud.fourplayers.restaurantstore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

  public static String fmtTime(LocalDateTime dateTime) {

    return dateTime.format(formatter);
  }

}
