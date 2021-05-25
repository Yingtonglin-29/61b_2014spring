import java.util.ArrayList;

public class test {
    public static boolean isLeapYear(int year) {
        if ((year % 4) != 0) return false;
        else {
            if ((year % 100) == 0) {
                if ((year % 400) == 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
    }

    public static int daysInMonth(int month, int year) {
        if (month == 2) {
          if (isLeapYear(year) == true) {
            return 29;
          }
          return 28;
        }
        else {
          switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
              return 30;
            default:
              return 31;
          } 
        }
    }

    public static String toString(int month, int year, int day) {
        StringBuilder dateString = new StringBuilder();
        dateString.append(month);
        dateString.append('/');
        dateString.append(day);
        dateString.append('/');
        dateString.append(year);
        return dateString.toString();
    }
    
    public static void main(String[] argv) {
        System.out.print(toString(4,2021,31));
      }
}
