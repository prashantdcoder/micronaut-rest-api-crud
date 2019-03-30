package micronaut.project.AppUtilities


import java.text.SimpleDateFormat

class AppUtils {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy")

    static String parseSqlDateToString(Date date) {
        return dateFormat.format(date)
    }

    static Date parseStringDateToSql(String date) {
        return new Date(dateFormat.parse(date).getTime())
    }
}
