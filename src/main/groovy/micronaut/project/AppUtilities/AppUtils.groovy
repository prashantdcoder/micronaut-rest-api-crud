package micronaut.project.AppUtilities

import java.sql.Date
import java.util.Date
import java.text.SimpleDateFormat

class AppUtils {


    static String parseSqlDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy")
        return simpleDateFormat.format(date)
    }

    static Date parseStringDateToSql(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy")
        Date sqlDate = new Date(simpleDateFormat.parse(date).getTime())
        return sqlDate
    }
}
