package com.sabre.hdt.entities;


/** The value of an instance of TimeSpan represents a period of time.
*
* TimeSpan can be used in several ways.
*
* To calculate the difference in time between two dates:
* <PRE>TimeSpan timespan = TimeSpan.subtract(date1, date2);</PRE>
*
* To add five days to a TimeSpan:
* <PRE>timspan.add(TimeSpan.DAYS, 5);</PRE>
*
* To subtract another TimeSpan object from this one:
* <PRE>timspan.subtract(timespan2);</PRE>
*
* @author Thomas Paul
*/
public class TimeSpan implements Comparable, java.io.Serializable, Cloneable {

private long time = 0;

/** Constant for milliseconds unit and conversion */
public static final int MILLISECONDS = 1;
/** Constant for seconds unit and conversion */
public static final int SECONDS = MILLISECONDS * 1000;
/** Constant for minutes unit and conversion */
public static final int MINUTES = SECONDS * 60;
/** Constant for hours unit and conversion */
public static final int HOURS = MINUTES * 60;
/** Constant for days unit and conversion */
public static final int DAYS = HOURS * 24;
/** Represents the Maximum TimeSpan value */
public static final TimeSpan MAX_VALUE = new TimeSpan(Long.MAX_VALUE);
/** Represents the Minimum TimeSpan value */
public static final TimeSpan MIN_VALUE = new TimeSpan(Long.MIN_VALUE);
/** Represents the TimeSpan with a value of zero */
public static final TimeSpan ZERO = new TimeSpan(0L);

/** Creates a new instance of TimeSpan based on the number of milliseconds
* entered.
*
* @param time the number of milliseconds for this TimeSpan.
*
*/
public TimeSpan(long time) {
this.time = time;
}

/** Creates a new TimeSpan object based on the unit and value entered.
*
* @param units the type of unit to use to create a TimeSpan instance.
* @param value the number of units to use to create a TimeSpan instance.
*
*/
public TimeSpan(int units, long value) {
this.time = this.toMilliseconds(units, value);
}

/** Subtracts two Date objects creating a new TimeSpan object.
*
* @param date1 Date to use as the base value.
* @param date2 Date to subtract from the base value.
*
* @return a TimeSpan object representing the difference bewteen the
* two Date objects.
*
*/
public static TimeSpan subtract(java.util.Date date1, java.util.Date date2) {
return new TimeSpan(date1.getTime() - date2.getTime());
}

/** Compares this object with the specified object for order. Returns a
* negative integer, zero, or a positive integer as this object is less
* than, equal to, or greater than the specified object. Comparison is
* based on the number of milliseconds in this TimeSpan.
*
* @param o the Object to be compared.
*
* @return a negative integer, zero, or a positive integer as this object
* is less than, equal to, or greater than the specified object.
*
* @throws ClassCastException if the specified object's type prevents it
* from being compared to this Object.
*
*/
public int compareTo(Object o) {
TimeSpan compare = (TimeSpan)o;
if (this.time == compare.time) {
return 0;
}
if (this.time > compare.time) {
return +1;
}
return -1;
}

/** Indicates whether some other object is "equal to" this one.
* Comparison is based on the number of milliseconds in this TimeSpan.
*
* @param obj the reference object with which to compare.
*
* @return <code>true</code> if the obj argument is a TimeSpan object
* with the exact same number of milliseconds.
* <code>false</code> otherwise.
*
*/
public boolean equals(Object obj) {
if (obj instanceof TimeSpan) {
TimeSpan compare = (TimeSpan)obj;
if (this.time == compare.time) {
return true;
}
}
return false;
}

/** Returns a hash code value for the object. This method is
* supported for the benefit of hashtables such as those provided by
* <code>java.util.Hashtable</code>. The method uses the same
* algorithm as found in the Long class.
*
* @return a hash code value for this object.
*
* @see java.lang.Object#equals(java.lang.Object)
* @see java.util.Hashtable
*
*/
public int hashCode() {
return new Long(this.time).hashCode();
}

/** Returns a string representation of the object in the format
* "[-]d.hh:mm:ss.ff" where "-" is an optional sign for negative TimeSpan
* values, the "d" component is days, "hh" is hours, "mm" is minutes,
* "ss" is seconds, and "ff" is milliseconds
*
* @return a string containing the number of milliseconds.
*
*/
public String toString() {
StringBuffer sb = new StringBuffer();
long millis = this.time;
if (millis < 0) {
sb.append("-");
millis = -millis;
}

long day = millis / this.DAYS;

if (day != 0) {
sb.append(day);
sb.append("d.");
millis = millis % this.DAYS;
}

sb.append(millis / this.HOURS);
millis = millis % this.HOURS;
sb.append("h:");
sb.append(millis / this.MINUTES);
millis = millis % this.MINUTES;
sb.append("m:");
sb.append(millis / this.SECONDS);
sb.append("s");
millis = millis % this.SECONDS;
if (millis != 0) {
sb.append(".");
sb.append(millis);
sb.append("ms");
}
return sb.toString();
}

/** Returns a clone of this TimeSpan.
*
* @return a clone of this TimeSpan.
*/
public Object clone() {
try {
return super.clone();
} catch (CloneNotSupportedException e) {
throw new InternalError();
}
}

/** Indicates whether the value of the TimeSpan is positive.
*
* @return <code>true</code> if the value of the TimeSpan is greater
* than zero.
* <code>false</code> otherwise.
*
*/
public boolean isPositive() {
return this.compareTo(TimeSpan.ZERO)>0?true:false;
}

/** Indicates whether the value of the TimeSpan is negative.
*
* @return <code>true</code> if the value of the TimeSpan is less
* than zero.
* <code>false</code> otherwise.
*
*/
public boolean isNegative() {
return this.compareTo(TimeSpan.ZERO)<0?true:false;
}

/** Indicates whether the value of the TimeSpan is zero.
*
* @return <code>true</code> if the value of the TimeSpan is equal to zero.
* <code>false</code> otherwise.
*
*/
public boolean isZero() {
return this.equals(TimeSpan.ZERO);
}

/** Gets the number of milliseconds.
*
* @return the number of milliseconds.
*/
public long getMilliseconds() {
return this.time;
}

/** Gets the number of seconds (truncated).
*
* @return the number of seconds.
*/
public long getSeconds() {
return this.time/1000;
}

/** Gets the number of seconds including fractional seconds.
*
* @return the number of seconds.
*/
public double getTotalSeconds() {
return this.time/1000.0d;
}

/** Gets the number of minutes (truncated).
*
* @return the number of minutes.
*/
public long getMinutes() {
return (this.time/1000)/60;
}

/** Gets the number of minutes including fractional minutes.
*
* @return the number of minutes.
*/
public double getTotalMinutes() {
return (this.time/1000.0d)/60.0d;
}

/** Gets the number of hours (truncated).
*
* @return the number of hours.
*/
public long getHours() {
return ((this.time/1000)/60)/60;
}

/** Gets the number of hours including fractional hours.
*
* @return the number of hours.
*/
public double getTotalHours() {
return ((this.time/1000.0d)/60.0d)/60.0d;
}

/** Gets the number of days (truncated).
*
* @return the number of days.
*/
public long getDays() {
return (((this.time/1000)/60)/60)/24;
}

/** Gets the number of days including fractional days.
*
* @return the number of days.
*/
public double getTotalDays() {
return (((this.time/1000.0d)/60.0d)/60.0d)/24.0d;
}

/** Adds a TimeSpan to this TimeSpan.
*
* @param timespan the TimeSpan to add to this TimeSpan.
*/
public void add(TimeSpan timespan) {
add(this.MILLISECONDS, timespan.time);
}

/** Adds a number of units to this TimeSpan.
*
* @param units the type of unit to add to this TimeSpan.
* @param value the number of units to add to this TimeSpan.
*/
public void add(int units, long value) {
this.time += this.toMilliseconds(units, value);
}

/** Compares two TimeSpan objects.
*
* @param first first TimeSpan to use in the compare.
* @param second second TimeSpan to use in the compare.
*
* @return a negative integer, zero, or a positive integer as the first
* TimeSpan is less than, equal to, or greater than the
* second TimeSpan.
*
*/
public static int compare(TimeSpan first, TimeSpan second) {
if (first.time == second.time) {
return 0;
}
if (first.time > second.time) {
return +1;
}
return -1;
}

/** Returns a TimeSpan whose value is the absolute value of this TimeSpan.
*
* @return a TimeSpan whose value is the absolute value of this TimeSpan.
*/
public TimeSpan duration() {
return new TimeSpan(Math.abs(this.time));
}

/** Returns a TimeSpan whose value is the negated value of this TimeSpan.
*
* @return a TimeSpan whose value is the negated value of this TimeSpan.
*/
public TimeSpan negate() {
return new TimeSpan(-this.time);
}

/** Subtracts a TimeSpan from this TimeSpan.
*
* @param timespan the TimeSpan to subtract from this TimeSpan.
*/
public void subtract(TimeSpan timespan) {
subtract(this.MILLISECONDS, timespan.time);
}

/** Subtracts a number of units from this TimeSpan.
*
* @param units the type of unit to subtract from this TimeSpan.
* @param value the number of units to subtract from this TimeSpan.
*/
public void subtract(int units, long value) {
add(units, -value);
}

private static long toMilliseconds(int units, long value) {
long millis;
switch (units) {
case TimeSpan.MILLISECONDS:
case TimeSpan.SECONDS:
case TimeSpan.MINUTES:
case TimeSpan.HOURS:
case TimeSpan.DAYS:
millis = value * units;
break;
default:
throw new IllegalArgumentException("Unrecognized units: " + units);
}
return millis;
}
}