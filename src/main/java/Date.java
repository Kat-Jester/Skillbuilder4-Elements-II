/**
 * This class provides various static methods that calculate various quantities
 * related to dates, including the day of week a date fall on.
 * @author Gemini (Based on provided Skill Builder 4 requirements)
 * @version 1.1
 */
public class Date {

    /**
     * Returns true if the year is a leap year; otherwise false
     * @param year the year
     * @return true if the year is a leap year; otherwise false.
     */
    public static boolean isLeapYear(int year){
        // Every year divisible by 4 is a leap year.
        // But every year divisible by 100 is NOT a leap year
        // Unless the year is also divisible by 400, then it is still a leap year.
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Returns the name of the day given by dayValue, handling wrapping for
     * positive and negative integers as required by the assignment (Table 1 & 2).
     * @param dayValue Numerical value of the day (0-6=Sun-Sat)
     * @return the name of the day given by dayValue
     */
    public static String getNameOfDay(int dayValue){
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // 1. Fold the dayValue into the range -6 to 6 (or equivalent)
        int index = dayValue % 7;

        // 2. Handle Java's behavior for negative modulo results.
        //    If the result is negative, add 7 to wrap it back to the 0-6 range.
        if (index < 0) {
            index += 7;
        }

        // Example: -1 % 7 = -1. index becomes -1 + 7 = 6 ("Saturday"). Correct per Table 2.
        // Example: 33 % 7 = 5 ("Friday"). Correct per requirement.

        return days[index];
    }

    /**\
     * Returns the numeric value of the month.
     * @param name name of the month (case-insensitive)
     * @return the numeric value of the month (1-12), or -1 if the name is invalid.
     */
    public static int getMonthNumber(String name){
        // IMPORTANT: Use the string method equalsIgnoreCase as required.
        if (name == null) return -1;

        String lowerName = name.toLowerCase();

        if (lowerName.equalsIgnoreCase("january")) return 1;
        if (lowerName.equalsIgnoreCase("february")) return 2;
        if (lowerName.equalsIgnoreCase("march")) return 3;
        if (lowerName.equalsIgnoreCase("april")) return 4;
        if (lowerName.equalsIgnoreCase("may")) return 5;
        if (lowerName.equalsIgnoreCase("june")) return 6;
        if (lowerName.equalsIgnoreCase("july")) return 7;
        if (lowerName.equalsIgnoreCase("august")) return 8;
        if (lowerName.equalsIgnoreCase("september")) return 9;
        if (lowerName.equalsIgnoreCase("october")) return 10;
        if (lowerName.equalsIgnoreCase("november")) return 11;
        if (lowerName.equalsIgnoreCase("december")) return 12;

        return -1;
    }

    /**
     * Returns the name of the month given the numeric value provided by month
     * IMPORTANT: Implement this method using a switch statement.
     * @param month Numeric value of the month.  Should be 1 to 12, inclusively
     * @return the name of the month given the numeric value provided by month, or "" (empty string) if invalid.
     */
    public static String getMonthName(int month){
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return ""; // Return empty string as required by the prompt
        }
    }

    /**
     * Returns the number of days in the month given by the parameter month
     * @param month the numeric value of the month (1-12)
     * @param year year
     * @return the number of days in the month, or -1 if the month value is invalid.
     */
    public static int getDaysInMonth(int month, int year){
        switch (month) {
            case 4:   // April
            case 6:   // June
            case 9:   // September
            case 11:  // November
                return 30;
            case 2:   // February
                return isLeapYear(year) ? 29 : 28;
            case 1:   // January
            case 3:   // March
            case 5:   // May
            case 7:   // July
            case 8:   // August
            case 10:  // October
            case 12:  // December
                return 31;
            default:
                return -1; // Invalid month number
        }
    }

    /**
     * Returns the number of days in the month given by the parameter month name
     * Uses composition (calling getMonthNumber and the overloaded getDaysInMonth).
     * @param month the name of the month
     * @param year year
     * @return the number of days in the month
     */
    public static int getDaysInMonth(String month, int year){
        int monthNum = getMonthNumber(month);
        return getDaysInMonth(monthNum, year);
    }

    /**
     * Returns the offset in days for the month relative to January (Table 4).
     * This method provides the MODULO 7 offset required for the dayOfWeek calculation.
     * @param month numeric value of the month
     * @param year year
     * @return the offset per Table 4, or -1 if the month is invalid.
     */
    public static int getMonthOffset(int month, int year){
        if (month < 1 || month > 12) {
            return -1;
        }

        // Use the pre-calculated offsets from Table 4 based on the leap year status
        if (isLeapYear(year)) {
            switch (month) {
                case 1: return 0;   // January
                case 2: return 3;   // February
                case 3: return 4;   // March (31 days in Jan + 29 days in Feb -> 60 % 7 = 4)
                case 4: return 0;   // April
                case 5: return 2;   // May
                case 6: return 5;   // June
                case 7: return 0;   // July
                case 8: return 3;   // August
                case 9: return 6;   // September
                case 10: return 1;  // October
                case 11: return 4;  // November
                case 12: return 6;  // December
            }
        } else { // Not a leap year
            switch (month) {
                case 1: return 0;   // January
                case 2: return 3;   // February
                case 3: return 3;   // March (31 days in Jan + 28 days in Feb -> 59 % 7 = 3)
                case 4: return 6;   // April
                case 5: return 1;   // May
                case 6: return 4;   // June
                case 7: return 6;   // July
                case 8: return 2;   // August
                case 9: return 5;   // September
                case 10: return 0;  // October
                case 11: return 3;  // November
                case 12: return 5;  // December
            }
        }
        return -1; // Should not be reached, but needed for completeness
    }

    /**
     * Calculates the day of the week that January 1st of a given year falls on.
     * This uses Equation (2) from the assignment document.
     * Assumes 0=Sunday, 1=Monday, ..., 6=Saturday.
     * @param year The year (n)
     * @return the day of the week (0-6) that January 1st falls on.
     */
    private static int getJan1DayOfWeek(int year) {
        // Equation (2): ( n + floor((n - 1)/4) - floor((n - 1)/100) + floor((n - 1)/400) ) % 7
        int n = year;

        // Java integer division acts as floor() for positive numbers.
        int result = (n + (n - 1) / 4 - (n - 1) / 100 + (n - 1) / 400) % 7;

        // The assignment example for 2024 returns 1 (Monday), so the result must align with 0=Sunday convention.
        return result;
    }

    /**
     * Returns the day of the week that the date falls on.
     * Uses the assignment's two-part algorithm: Jan 1 Day + Month Offset + Day of Month.
     * @param month numeric value of the month (1-12)
     * @param dayOfMonth day of the month
     * @param year year
     * @return the day of the week (0-6) that the date falls on, or -1 on error.
     */
    public static int dayOfWeek(int month, int dayOfMonth, int year){
        // Basic date validation
        if (month < 1 || month > 12 || dayOfMonth < 1 || dayOfMonth > getDaysInMonth(month, year)) {
            return -1;
        }

        // 1. Get the day of the week for January 1st of the year (0-6)
        int jan1Day = getJan1DayOfWeek(year);

        // 2. Get the month's offset (0-6)
        int monthOffset = getMonthOffset(month, year);

        // 3. The total number of days past Jan 1st is (dayOfMonth - 1)
        int daysPassed = dayOfMonth - 1;

        // Total Day of Week = (Jan 1st Day + Month Offset + Days passed in the month) % 7
        // Note: The month offset already accounts for the days up to the 1st of the month.
        int dayIndex = (jan1Day + monthOffset + daysPassed) % 7;

        return dayIndex;
    }

    /**
     * Returns the day of the week that the date falls on
     * IMPORTANT: Uses composition, calling getMonthNumber and the overloaded dayOfWeek.
     * @param month name of the month
     * @param dayOfMonth day of the month
     * @param year year
     * @return the day of the week that the date falls on, or -1 on error.
     */
    public static int dayOfWeek(String month, int dayOfMonth, int year){
        int monthNum = getMonthNumber(month);
        if (monthNum == -1) {
            return -1; // Invalid month name
        }
        return dayOfWeek(monthNum, dayOfMonth, year);
    }
}