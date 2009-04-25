/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemDateTime extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemDateTime";

    public ISWbemDateTime() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemDateTime(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemDateTime(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getValue() {
        return Dispatch.get(this, "Value").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param value
     *            an input-parameter of type String
     */
    public void setValue(String value) {
        Dispatch.put(this, "Value", value);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getYear() {
        return Dispatch.get(this, "Year").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param year
     *            an input-parameter of type int
     */
    public void setYear(int year) {
        Dispatch.put(this, "Year", new Variant(year));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getYearSpecified() {
        return Dispatch.get(this, "YearSpecified").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param yearSpecified
     *            an input-parameter of type boolean
     */
    public void setYearSpecified(boolean yearSpecified) {
        Dispatch.put(this, "YearSpecified", new Variant(yearSpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getMonth() {
        return Dispatch.get(this, "Month").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param month
     *            an input-parameter of type int
     */
    public void setMonth(int month) {
        Dispatch.put(this, "Month", new Variant(month));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getMonthSpecified() {
        return Dispatch.get(this, "MonthSpecified").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param monthSpecified
     *            an input-parameter of type boolean
     */
    public void setMonthSpecified(boolean monthSpecified) {
        Dispatch.put(this, "MonthSpecified", new Variant(monthSpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getDay() {
        return Dispatch.get(this, "Day").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param day
     *            an input-parameter of type int
     */
    public void setDay(int day) {
        Dispatch.put(this, "Day", new Variant(day));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getDaySpecified() {
        return Dispatch.get(this, "DaySpecified").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param daySpecified
     *            an input-parameter of type boolean
     */
    public void setDaySpecified(boolean daySpecified) {
        Dispatch.put(this, "DaySpecified", new Variant(daySpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getHours() {
        return Dispatch.get(this, "Hours").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param hours
     *            an input-parameter of type int
     */
    public void setHours(int hours) {
        Dispatch.put(this, "Hours", new Variant(hours));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getHoursSpecified() {
        return Dispatch.get(this, "HoursSpecified").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param hoursSpecified
     *            an input-parameter of type boolean
     */
    public void setHoursSpecified(boolean hoursSpecified) {
        Dispatch.put(this, "HoursSpecified", new Variant(hoursSpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getMinutes() {
        return Dispatch.get(this, "Minutes").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param minutes
     *            an input-parameter of type int
     */
    public void setMinutes(int minutes) {
        Dispatch.put(this, "Minutes", new Variant(minutes));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getMinutesSpecified() {
        return Dispatch.get(this, "MinutesSpecified").changeType(Variant.VariantBoolean)
                .getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param minutesSpecified
     *            an input-parameter of type boolean
     */
    public void setMinutesSpecified(boolean minutesSpecified) {
        Dispatch.put(this, "MinutesSpecified", new Variant(minutesSpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getSeconds() {
        return Dispatch.get(this, "Seconds").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param seconds
     *            an input-parameter of type int
     */
    public void setSeconds(int seconds) {
        Dispatch.put(this, "Seconds", new Variant(seconds));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getSecondsSpecified() {
        return Dispatch.get(this, "SecondsSpecified").changeType(Variant.VariantBoolean)
                .getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param secondsSpecified
     *            an input-parameter of type boolean
     */
    public void setSecondsSpecified(boolean secondsSpecified) {
        Dispatch.put(this, "SecondsSpecified", new Variant(secondsSpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getMicroseconds() {
        return Dispatch.get(this, "Microseconds").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param microseconds
     *            an input-parameter of type int
     */
    public void setMicroseconds(int microseconds) {
        Dispatch.put(this, "Microseconds", new Variant(microseconds));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getMicrosecondsSpecified() {
        return Dispatch.get(this, "MicrosecondsSpecified").changeType(Variant.VariantBoolean)
                .getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param microsecondsSpecified
     *            an input-parameter of type boolean
     */
    public void setMicrosecondsSpecified(boolean microsecondsSpecified) {
        Dispatch.put(this, "MicrosecondsSpecified", new Variant(microsecondsSpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getUTC() {
        return Dispatch.get(this, "UTC").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param uTC
     *            an input-parameter of type int
     */
    public void setUTC(int uTC) {
        Dispatch.put(this, "UTC", new Variant(uTC));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getUTCSpecified() {
        return Dispatch.get(this, "UTCSpecified").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param uTCSpecified
     *            an input-parameter of type boolean
     */
    public void setUTCSpecified(boolean uTCSpecified) {
        Dispatch.put(this, "UTCSpecified", new Variant(uTCSpecified));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsInterval() {
        return Dispatch.get(this, "IsInterval").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param isInterval
     *            an input-parameter of type boolean
     */
    public void setIsInterval(boolean isInterval) {
        Dispatch.put(this, "IsInterval", new Variant(isInterval));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param bIsLocal
     *            an input-parameter of type boolean
     * @return the result is of type java.util.Date
     */
    public java.util.Date getVarDate(boolean bIsLocal) {
        return Dispatch.call(this, "GetVarDate", new Variant(bIsLocal)).getJavaDate();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type java.util.Date
     */
    public java.util.Date getVarDate() {
        return Dispatch.call(this, "GetVarDate").getJavaDate();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param dVarDate
     *            an input-parameter of type java.util.Date
     * @param bIsLocal
     *            an input-parameter of type boolean
     */
    public void setVarDate(java.util.Date dVarDate, boolean bIsLocal) {
        Dispatch.call(this, "SetVarDate", new Variant(dVarDate), new Variant(bIsLocal));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param dVarDate
     *            an input-parameter of type java.util.Date
     */
    public void setVarDate(java.util.Date dVarDate) {
        Dispatch.call(this, "SetVarDate", new Variant(dVarDate));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param bIsLocal
     *            an input-parameter of type boolean
     * @return the result is of type String
     */
    public String getFileTime(boolean bIsLocal) {
        return Dispatch.call(this, "GetFileTime", new Variant(bIsLocal)).toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getFileTime() {
        return Dispatch.call(this, "GetFileTime").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strFileTime
     *            an input-parameter of type String
     * @param bIsLocal
     *            an input-parameter of type boolean
     */
    public void setFileTime(String strFileTime, boolean bIsLocal) {
        Dispatch.call(this, "SetFileTime", strFileTime, new Variant(bIsLocal));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strFileTime
     *            an input-parameter of type String
     */
    public void setFileTime(String strFileTime) {
        Dispatch.call(this, "SetFileTime", strFileTime);
    }

    static long zoneOffset = java.util.Calendar.getInstance().get(java.util.Calendar.ZONE_OFFSET);

    static double javaDateToComDate(java.util.Date javaDate) {

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(javaDate);
        long gmtOffset = (cal.get(java.util.Calendar.ZONE_OFFSET) + cal
                .get(java.util.Calendar.DST_OFFSET));

        long millis = javaDate.getTime() + gmtOffset;
        return 25569D + millis / 86400000D;
    }

}
