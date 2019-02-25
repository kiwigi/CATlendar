/*/ THIS CODE IS EXCLUSIVELY FOR TEXT-BASED USE OF THE APP /*/

import java.util.Scanner;

public class UserInteract{
  private AllCalendar calendar = new AllCalendar();

  //welcome
  public void welcome(){
    System.out.println("Welcome to your calendar!");
  }

  //view (monthly, daily, or weekly)
  public void pickView(){
    System.out.println("What view would you like to see? MONTHLY, WEEKLY, or DAILY?");
    Scanner input = new Scanner(System.in);
    String response = input.nextLine();
    response = response.toUpperCase();
    if (response.equals("MONTHLY")){
      calendar.monthlyCalendar(viewMonth());
    } else if (response.equals("WEEKLY")){
      calendar.weeklyCalendar(calendar.getHashmap(), viewWeek());
    } else if (response.equals("DAILY")){
      calendar.dailyCalendar(calendar.getHashmap(), viewDay());
    } else {
      System.out.println("INVALID");
      pickView();
    }
    viewAgain();
  }

  //monthly calendar
  public int viewMonth(){
    System.out.print("Enter month (1-12): ");
    Scanner input2 = new Scanner(System.in);
    int month = input2.nextInt();
    while (month < 1 || month > 12){
      System.out.println("Invalid entry, please try again: ");
      month = input2.nextInt();
    }
    return month;
  }

  //weekly calendar
  public String viewWeek(){
    System.out.println("Please enter the Sunday of the week you'd like to view DD/MM: ");
    Scanner input3 = new Scanner(System.in);
    String date = input3.nextLine();
    System.out.println("Week of " + date + "/2017");
    return date;
  }

  //daily calendar
  public String viewDay(){
    System.out.println("Please enter the date you'd like to view DD/MM: ");
    Scanner input4 = new Scanner(System.in);
    String date = input4.nextLine();
    System.out.println("Day of " + date + "/2017");
    return date;
  }

  //add event
  public void addEvent(){
    System.out.println("Would you like to add an event?");
    Scanner input5 = new Scanner(System.in);
    String response = input5.nextLine();
    response = response.toUpperCase();
    if (response.equals("YES")){
      System.out.println("Please enter the date of your event. DD/MM");
      String date = input5.nextLine();
      System.out.println("Please enter the starting time of your event. HH:00");
      String start = input5.nextLine();
      System.out.println("Please enter the end time of your event. HH:00");
      String end = input5.nextLine();
      System.out.println("Please enter the name of your event.");
      String name = input5.nextLine();
      System.out.println("Please enter the type of the event. WORK, PERSONAL, or SCHOOL:");
      String eventType = input5.nextLine();
      calendar.eventAdd(calendar.getHashmap(), date, start, end, name, eventType);
      calendar.dailyCalendar(calendar.getHashmap(), date);
      addEvent();
    } else if (!(response.equals("NO")) && !(response.equals("YES"))){
      System.out.println("INVALID");
      addEvent();
    }
  }

  //delete event
  public void delEvent(){
    System.out.println("Would you like to delete an event?");
    Scanner input6 = new Scanner(System.in);
    String response = input6.nextLine();
    response = response.toUpperCase();
    if (response.equals("YES")){
      System.out.println("Please enter the date of your event. DD/MM");
      String date= input6.nextLine();
      System.out.println("Please enter the starting time of your event. HH:00");
      String start = input6.nextLine();
      System.out.println("Please enter the end time of your event. HH:00");
      String end = input6.nextLine();
      calendar.eventDel(calendar.getHashmap(), date, start, end);
      calendar.dailyCalendar(calendar.getHashmap(), date);
      delEvent();
    } else if (!(response.equals("NO")) && !(response.equals("YES"))){
      System.out.println("INVALID");
      delEvent();
    }
  }

  // loops again to see calendar after an action occurs
  public void viewAgain(){
    System.out.println("Would you like to view your calendar again? Yes or No");
    Scanner input7 = new Scanner(System.in);
    String response = input7.nextLine();
    response = response.toUpperCase();
    if (response.equals("YES")){
      pickView();
    } else if (!(response.equals("NO")) && !(response.equals("YES"))){
      System.out.println("INVALID");
      viewAgain();
    }
  }

  public static void main(String[] args){
    UserInteract ui = new UserInteract();
    ui.welcome();
    ui.pickView();
    ui.addEvent();
    ui.delEvent();
    ui.viewAgain();
  }

}
