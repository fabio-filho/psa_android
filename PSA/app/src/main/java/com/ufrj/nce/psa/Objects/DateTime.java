package com.ufrj.nce.psa.Objects;

import java.util.Calendar;

/**
 * Created by fabiofilho on 2/7/15.
 */
public class DateTime {

    public DateTime(String dateTime){

        if (dateTime == null) this.dateTime = getNullDateTime();

        if (dateTime.length() == 19 )
            this.dateTime = dateTime;
        else if(dateTime.length() == 8){
            this.dateTime = dateTime;
            isTime = true;
        }
        else
            this.dateTime = getNullDateTime();
    }


    public DateTime(){

        Calendar calendar = Calendar.getInstance();

        String month, day, hour, minut, second;

        if ((calendar.get(Calendar.MONTH)+1) < 10)
            month = "0"+(calendar.get(Calendar.MONTH)+1);
        else
            month = ""+(calendar.get(Calendar.MONTH)+1);

        if ((calendar.get(Calendar.DAY_OF_MONTH)) < 10)
            day = "0"+(calendar.get(Calendar.DAY_OF_MONTH));
        else
            day = ""+(calendar.get(Calendar.DAY_OF_MONTH));

        if ((calendar.get(Calendar.HOUR_OF_DAY)) < 10)
            hour = "0"+(calendar.get(Calendar.HOUR_OF_DAY));
        else
            hour = ""+(calendar.get(Calendar.HOUR_OF_DAY));

        if ((calendar.get(Calendar.MINUTE)) < 10)
            minut = "0"+(calendar.get(Calendar.MINUTE));
        else
            minut = ""+(calendar.get(Calendar.MINUTE));

        if ((calendar.get(Calendar.SECOND)) < 10)
            second = "0"+(calendar.get(Calendar.SECOND));
        else
            second = ""+(calendar.get(Calendar.SECOND));


        this.dateTime = calendar.get(Calendar.YEAR) + "-" + month+ "-" + day
                + " " + hour+ ":" + minut+ ":" + second;

    }


    private Boolean isTime = false;
    private String dateTime;


    public static String getNullDateTime(){
        //Functions.Log("getNullDateTime", "Null datetime was inputted. For value: " + dateTime);
        return "0000-00-00 00:00:00";
    }

    public Boolean isNull(){

        if (toString().equals(getNullDateTime()))
            return true;
        else
            return false;
    }

    public Boolean isTime(){
        return this.isTime;
    }

    public String getHours(){
        if(isTime)
            return String.copyValueOf(dateTime.toCharArray(), 0,2);
        else
            return String.copyValueOf(dateTime.toCharArray(), 11,2);
    }

    public String getMinuts(){
        if(isTime)
            return String.copyValueOf(dateTime.toCharArray(), 3,2);
        else
            return String.copyValueOf(dateTime.toCharArray(), 14,2);
    }

    public String getSeconds(){
        if(isTime)
            return String.copyValueOf(dateTime.toCharArray(), 6,2);
        else
            return String.copyValueOf(dateTime.toCharArray(), 17,2);
    }

    public int getHours_Int(){
        if (isTime)
            return Integer.valueOf(String.copyValueOf(dateTime.toCharArray(), 0,2));
        else
            return Integer.valueOf(String.copyValueOf(dateTime.toCharArray(), 11,2));
    }

    public int getMinuts_Int(){
        if (isTime)
            return Integer.valueOf(String.copyValueOf(dateTime.toCharArray(), 3,2));
        else
            return Integer.valueOf(String.copyValueOf(dateTime.toCharArray(), 14,2));
    }

    public int getSeconds_Int(){
        if (isTime)
            return Integer.valueOf(String.copyValueOf(dateTime.toCharArray(), 6,2));
        else
            return Integer.valueOf(String.copyValueOf(dateTime.toCharArray(), 17,2));
    }

    public String getTime(){

        if (isTime)
            return String.copyValueOf(dateTime.toCharArray(),0,8);
        else
            return String.copyValueOf(dateTime.toCharArray(),11,8);

    }


    public String getYear(){
        if (isTime)
            return "0000";
        else
            return String.valueOf(dateTime.toCharArray(), 0, 4);
    }

    public int getYear_Int(){
        if (isTime)
            return 0;
        else
            return Integer.valueOf(String.valueOf(dateTime.toCharArray(), 0, 4));
    }

    public String getMonth(){
        if(isTime)
            return "00";
        else
            return String.valueOf(dateTime.toCharArray(), 5, 2);
    }

    public int getMonth_Int(){
        if(isTime)
            return 0;
        else
            return Integer.valueOf(String.valueOf(dateTime.toCharArray(), 5, 2));
    }

    public String getDay(){
        if (isTime)
            return "00";
        else
            return String.valueOf(dateTime.toCharArray(), 8, 2);
    }

    public int getDay_Int(){
        if(isTime)
            return 0;
        else
            return Integer.valueOf(String.valueOf(dateTime.toCharArray(), 8, 2));
    }


    public String getDate(){
        return getDay()+"/"+getMonth()+"/"+getYear();
    }



    public static Boolean checkTimeIsBetweenFromCurTime(int start_hour, int start_minuts,
                                                        int end_hour,
                                                        int end_minuts){

        Calendar calendarDevice = Calendar.getInstance();

        if (start_hour > end_hour) {

            if (end_hour < calendarDevice.get(Calendar.HOUR_OF_DAY)) {

                if (start_hour < calendarDevice.get(Calendar.HOUR_OF_DAY))
                    return true;

                if (start_hour == calendarDevice.get(Calendar.HOUR_OF_DAY))
                    return (start_minuts <= calendarDevice.get(Calendar.MINUTE));

            }else{

                if ( end_hour == calendarDevice.get(Calendar.HOUR_OF_DAY))
                    return (end_minuts >= calendarDevice.get(Calendar.MINUTE));

                return true;
            }

        }

        else if (start_hour < end_hour) {

            if (start_hour <= calendarDevice.get(Calendar.HOUR_OF_DAY) &&
                    end_hour >= calendarDevice.get(Calendar.HOUR_OF_DAY))

                if (start_hour == calendarDevice.get(Calendar.HOUR_OF_DAY))
                    return (start_minuts <= calendarDevice.get(Calendar.MINUTE));

                else if (end_hour == calendarDevice.get(Calendar.HOUR_OF_DAY))
                    return (end_minuts >= calendarDevice.get(Calendar.MINUTE));
                else
                    return true;

        }

        else
            if (start_hour == calendarDevice.get(Calendar.HOUR_OF_DAY) &&
                        end_hour == calendarDevice.get(Calendar.HOUR_OF_DAY))
                return checkTimeMinuteFromCurTime(start_minuts, end_minuts);


        return false;
    }


    private static Boolean checkTimeMinuteFromCurTime(int start_minuts, int end_minuts){

        Calendar calendarDevice = Calendar.getInstance();

        if (calendarDevice.get(Calendar.MINUTE) >= start_minuts &&
                calendarDevice.get(Calendar.MINUTE) <= end_minuts)
            return true;
        else
            return false;
    }


    public static Boolean checkTimeIsBetweenFromCurTime(DateTime dt_start, DateTime dt_end){

        return checkTimeIsBetweenFromCurTime(dt_start.getHours_Int(),dt_start.getMinuts_Int(),
                dt_end.getHours_Int(),dt_end.getMinuts_Int());
    }


    public static Boolean checkDateIsBetweenFromCurDate(int start_day, int start_month, int start_year,
                                                        int end_day, int end_month, int end_year){

        Calendar calendar = Calendar.getInstance();

        if ( start_year <= calendar.get(Calendar.YEAR) && end_year >= calendar.get(Calendar.YEAR))
            if (start_month-1 <= calendar.get(Calendar.MONTH) && end_month-1 >= calendar.get(Calendar.MONTH))
                if (start_day <= calendar.get(Calendar.DAY_OF_MONTH) && end_day >= calendar.get(Calendar.DAY_OF_MONTH))
                    return true;

        return false;
    }


    public static Boolean checkDateIsBetweenFromCurDate(DateTime dt_start, DateTime dt_end){

        return checkDateIsBetweenFromCurDate(dt_start.getDay_Int(), dt_start.getMonth_Int(), dt_start.getYear_Int(),
                dt_end.getDay_Int(),dt_end.getMonth_Int(), dt_end.getYear_Int());
    }

    public static Boolean checkDateIsToday(int start_day, int start_month, int start_year){

        Calendar calendar = Calendar.getInstance();

        if (start_year == calendar.get(Calendar.YEAR) && start_month-1 == calendar.get(Calendar.MONTH)
                && start_day == calendar.get(Calendar.DAY_OF_MONTH))
            return true;

        return false;
    }

    public static Boolean checkDateIsToday(DateTime dt){

        return checkDateIsToday(dt.getDay_Int(), dt.getMonth_Int(),dt.getYear_Int());
    }

    public static Boolean checkCurDateTimeisBeetween(DateTime dt_start, DateTime dt_end){

        Calendar calendarCur = Calendar.getInstance(),
                calendarStart = Calendar.getInstance(),
                calendarEnd = Calendar.getInstance();

        calendarStart.set(dt_start.getYear_Int(),dt_start.getMonth_Int()-1,
                dt_start.getDay_Int(),dt_start.getHours_Int(),dt_start.getMinuts_Int());

        calendarEnd.set(dt_end.getYear_Int(),dt_end.getMonth_Int()-1,
                dt_end.getDay_Int(),dt_end.getHours_Int(),dt_end.getMinuts_Int());

        return (calendarCur.compareTo(calendarStart) >= 0 && calendarCur.compareTo(calendarEnd) <= 0);
    }


    @Override
    public String toString() {
        return dateTime;
    }

    public String toStringNumbers(){ return getYear()+getMonth()+getDay()+getHours()+getMinuts()+getSeconds();}

    public int toIntNumbers(){
        return Integer.parseInt(getYear()+getMonth()+getDay()+getHours()+getMinuts()+getSeconds());
    }

}
