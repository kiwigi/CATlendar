import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class AllCalendar{
  private int numDays = 0;
  private int daysWeek = 7;
  private int year = 2017;
  private HashMap<String,HashMap<String,String>> hmapMain = new HashMap<String,HashMap<String,String>>();
  private Event event;

  public AllCalendar(){
    initialHashmap(getHashmap());
  }

  //calculates day of year 1-365 for each date
  public int calcYear(String date){
    String [] splitDate=date.split("/");
    String month=splitDate[1];
    String day= splitDate[0];
    String newDate = month+"/"+day+"/"+"2017";
    Calendar cal = new GregorianCalendar();
    cal.setTime(new Date(newDate));
    int dayOfYear=cal.get(Calendar.DAY_OF_YEAR);
    return dayOfYear;
  }

  // Following Method from https://stackoverflow.com/questions/43699795/making-an-interactive-calendar-in-java
  // figures out what day of the week the first day of the year falls on
  public void firstDayY(int year){
    int month = 13;
    year--;
    daysWeek = (1 + (int)(((month + 1) * 26) / 10.0) + year + (int)(year / 4.0) + 6 * (int)(year / 100.0) + (int)(year / 400.0)) % 7;
    String day = "";
    switch(daysWeek) {
      case 0: day = "Saturday"; break;
      case 1: day = "Sunday"; break;
      case 2: day = "Monday"; break;
      case 3: day = "Tuesday"; break;
      case 4: day = "Wednesday"; break;
      case 5: day = "Thursday"; break;
      default: day = "Friday"; break;
    }
  }

  // Following Method from https://stackoverflow.com/questions/43699795/making-an-interactive-calendar-in-java
  // figures out what day of the week the first day of the month falls on
  public void firstDayM(int month, int year){
    if(month==1 || month==2){
      month += 12;
      year--;
    }
    daysWeek = (1 + (int)(((month + 1) * 26) / 10.0) + year + (int)(year / 4.0) + 6 * (int)(year / 100.0) + (int)(year / 400.0)) % 7;
    String day = "";
    switch(daysWeek) {
      case 0: day = "Saturday"; break;
      case 1: day = "Sunday"; break;
      case 2: day = "Monday"; break;
      case 3: day = "Tuesday"; break;
      case 4: day = "Wednesday"; break;
      case 5: day = "Thursday"; break;
      default: day = "Friday"; break;
    }
  }

  // tracks number of days in the month for the monthly layout
  public void numDaysM(int month, int year){
    int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    numDays = days[month];
  }

  // Prints the calendar to the console
  public void printMonth(int month, int year){
    // sets up the calendar
    String[] monthNames = {"","January","February","March","April","May","June","July","August","September","October","November","December"};
    System.out.println("    " + monthNames[month] + " " + year);
    System.out.println("Su Mo Tu We Th Fr Sa");
    // offsets the calendar dates to fit with the days of the week
    // the If statement corrects for months starting on Saturday (prints 1 under Saturday, not Sunday)
    if (daysWeek == 0){
      for (int count = 1; count < 7 ; count++){
        System.out.print("   ");
      }
    // the Else statement accounts for months starting on any other days (Sun through Fri)
    } else {
        for (int count = 1; count < daysWeek ; count++){
          System.out.print("   ");
        }
      }
    // prints days in 7 day lines, at the end of 7 days ((1+0-1)%7 == 0 ) moves to the next line
    for (int count = 1; count <= numDays; count++) {
      System.out.printf("%2d ", count);
      if (((count + daysWeek - 1) % 7 == 0) || (count == numDays)) {
        System.out.println();
      }
    }
    System.out.println();
  }

  public void monthlyCalendar(int month){
    System.out.println();
    firstDayY(year);
    firstDayM(month, year);
    numDaysM(month, year);
    printMonth(month, year);
  }

  public HashMap<String,HashMap<String,String>> getHashmap(){
    return new HashMap<String,HashMap<String,String>>(hmapMain);
  }

  public void setHashmap(HashMap<String,HashMap<String,String>> newHmap){
    this.hmapMain = new HashMap<String,HashMap<String,String>>(newHmap);
  }

  //creates initial hashmap with no events and time entered as keys (24hr clock)
  public void initialHashmap(HashMap<String,HashMap<String,String>> hashmap){
    int daysYear = 1;
    while(daysYear <= 365){
      String day = Integer.toString(daysYear);
      daysYear++;
      HashMap<String,String> hmapHour = new HashMap<String,String>();
      int timeHashmap = 0;
      while(timeHashmap < 24){
        String timestring1 = String.format("%02d", timeHashmap);
        String timestring2 = (timestring1 + ":00");
        timeHashmap++;
        hmapHour.put(timestring2, " ");
      }
      hashmap.put(day,hmapHour);
    }
    setHashmap(hashmap);
  }

  public void weeklyCalendar(HashMap<String,HashMap<String,String>> hmap, String date){
    System.out.println();
    for (int count = 0; count < 7; count++){
      ArrayList<String> daysOfWeek = new ArrayList<String>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));
      int timeDay = 0;
      String hashDay = Integer.toString(calcYear(date) + count);
      System.out.println(daysOfWeek.get(count));
      System.out.println("Time" + "   Event");
      while(timeDay < 24){
        String time1 = String.format("%02d", timeDay);
        String time2 = (time1 + ":00");
        String value = hmap.get(hashDay).get(time2);
        System.out.println(time2 + "  " + value);
        timeDay++;
      }
      System.out.println();
    }
  }

  // Initializes hashmap for daily and weekly calendars. The hashmap is used to save the events.
  public void dailyCalendar(HashMap<String,HashMap<String,String>> hmap, String date){
    String hashDay = Integer.toString(calcYear(date));
    int timeDay = 0;
    System.out.println();
    System.out.println(date + "/2017");
    System.out.println("Time" + "   Event");
    while(timeDay < 24){
      String time1 = String.format("%02d", timeDay);
      String time2 = (time1 + ":00");
      String value = hmap.get(hashDay).get(time2);
      System.out.println(time2 + "  " + value);
      timeDay++;
    }
  }

  // Saves event info to the Hashmap used to create daily and weekly calendars.
  public void eventAdd(HashMap<String,HashMap<String,String>> hmap, String date, String start, String end, String name, String type){
    String eventType = type.toUpperCase();
    // creates new event based on event type
    if (eventType.equals("WORK")){
      event = new Work(name, start, end, date);
    } else if (eventType.equals("PERSONAL")){
      event = new Personal(name, start, end, date);
    } else if (eventType.equals("SCHOOL")){
      event = new School(name, start, end, date);
    }
    // uses collected information to save the event to the hashmap
    String dayOfYear = Integer.toString(calcYear(event.getDate()));
    String[] startPart = event.getStart().split (":");
    int startInt = Integer.parseInt(startPart[0]);
    String[] endPart = event.getEnd().split (":");
    int endInt = Integer.parseInt(endPart[0]);
    int totalTime = (endInt - startInt);
    HashMap<String,String> inner = hmap.get(dayOfYear);
    for(int count = 0; count < totalTime; count++){
      int time = (startInt + count);
      String timeFormat = String.format("%02d", time);
      String timeFormat2 = (timeFormat + ":00");
      if (inner.get(timeFormat2).equals(" ")){
        inner.put(timeFormat2, event.getName());
      } else {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("fur-real-small.png");
        JOptionPane.showMessageDialog(null,"Oh meow! Error adding event: " + timeFormat2,"TIME CONFLICT MEOW!!",JOptionPane.QUESTION_MESSAGE,icon);
      }
    }
    // updates the hashmap
    setHashmap(hmap);
  }

  public void eventDel(HashMap<String,HashMap<String,String>> hmap, String date, String start, String end){
    // uses info input to find and delete event in the hashmap
    String dayOfYear = Integer.toString(calcYear(date));
    String[] startPart = start.split (":");
    int startInt = Integer.parseInt(startPart[0]);
    String[] endPart = end.split (":");
    int endInt = Integer.parseInt(endPart[0]);
    int totalTime = (endInt - startInt);
    HashMap<String,String> inner = hmap.get(dayOfYear);
    for(int count =0; count < totalTime; count++){
      int time = (startInt + count);
      String timeFormat = String.format("%02d", time);
      String timeFormat2 = (timeFormat + ":00");
      if (!(inner.get(timeFormat2).equals(" "))){
        inner.put(timeFormat2, " ");
      } else {
        // No Event Delete Error Popup
        JFrame frame = new JFrame();
        ImageIcon icon2 = new ImageIcon("error-event.png");
        JOptionPane.showMessageDialog(null,"Oh meow! There's no event: " + timeFormat2,"NO EVENT FOUND!?!?!",JOptionPane.QUESTION_MESSAGE,icon2);
      }
    }
    // updates the hashmap
    setHashmap(hmap);
  }
}
