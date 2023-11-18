package com.model;

public class Date {
    Integer hour;
    Integer minute;
    Integer seconds;
    Date(){
        this.hour = 0;
        this.minute = 0;
        this.seconds =0;
    }
    Date(Integer hour, Integer minute , Integer seconds){
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }
    Date(String hour, String minute , String seconds){
        this.hour =   Integer.parseInt(hour);
        this.minute = Integer.parseInt(minute);
        this.seconds = Integer.parseInt(seconds);
    }

    public Boolean isGreaterThan(Date theDate){
        if(this.hour > theDate.hour){
            return false;
        } else if(this.hour == theDate.hour){
            if(this.minute > theDate.minute){
                return  false;
            } else if (this.minute == theDate.minute) {
                if(this.seconds > theDate.seconds){
                    return false;
                }
                else return true;
            }
            return true;
        }
        return true;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = Integer.parseInt(hour);;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = Integer.parseInt(minute);
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = Integer.parseInt(seconds);
    }

    @Override
    public String toString() {
        return hour + ":"+ minute +":"+ seconds+"\n \n";
    }
}
