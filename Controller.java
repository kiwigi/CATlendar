/*/ THIS CODE IS EXCLUSIVELY FOR GUI-BASED USE OF THE APP /*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Controller extends JFrame{
  //Monthly Calendar Instance Variables
  private JLabel labelMonth;
  private JButton buttonPrev, buttonNext;
  private JTable calendarTable;
  private JFrame mainFrame;
  private Container pane;
  private DefaultTableModel modelCalendar;
  private JScrollPane scrollCalendar;
  private JPanel calendarPanel;
  private int realYear = 2017;
  private int realMonth, currentMonth;
  // Create and Delete Events Instance Variables
  private JTextField nameField, endField, startField, dateField;
  private JPanel myPanel;
  private JComboBox typeBox;
  private String type;
  // Instance of AllCalendar Class
  protected AllCalendar calendar = new AllCalendar();

  // Creates Day View calendar
  public void dailyCalendar(HashMap<String,HashMap<String,String>> hmap, String date){
    String hashDay = Integer.toString(calendar.calcYear(date));
    JFrame window = new JFrame(date + "/2017");
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
    Object time[][] = { {"00:00", " "},{"01:00", " "},{"02:00", " "},{"03:00", " "},{"04:00", " "},{"05:00", " "},{"06:00", " "},
    {"07:00", " "}, {"08:00", " "},{"09:00", " "},{"10:00", " "},{"11:00", " "},{"12:00", " "},{"13:00", " "},{"14:00", " "},
    {"15:00", " "},{"16:00", " "},{"17:00", " "}, {"18:00", " "},{"19:00", " "},{"20:00", " "},{"21:00", " "},{"22:00", " "},
    {"23:00", " "} };
    Object titles[] = {"Time", "Event"};
    JTable tableDay = new JTable(time, titles);
    tableDay.setEnabled(false);
    int timeDay = 0;
    while(timeDay < 24){
      String time1 = String.format("%02d", timeDay);
      String time2 = (time1 + ":00");
      String value = hmap.get(hashDay).get(time2);
      tableDay.setValueAt(time2, timeDay,0);
      tableDay.setValueAt(value, timeDay, 1);
      timeDay++;
    }
    tableDay.setEnabled(false);
    JScrollPane scroll = new JScrollPane(tableDay);
    window.add(scroll, BorderLayout.CENTER);
    window.setSize(470, 430);
    window.setVisible(true);
    window.setResizable(false);
    tableDay.setShowGrid(true);
    tableDay.setGridColor(Color.PINK);
    tableDay.getTableHeader().setResizingAllowed(false);
    tableDay.getTableHeader().setReorderingAllowed(false);
  }

  // Creates Week View Calendar
  public void weeklyCalendar(HashMap<String,HashMap<String,String>> hmap, String date){
    JFrame window = new JFrame("Week of " + date + "/2017");
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
    Object time[][] = {
    {"00:00", " ", " ", " ", " ", " ", " ", " "},{"01:00", " ", " ", " ", " ", " ", " ", " "},
    {"02:00", " ", " ", " ", " ", " ", " ", " "},{"03:00", " ", " ", " ", " ", " ", " ", " "},
    {"04:00", " ", " ", " ", " ", " ", " ", " "},{"05:00", " ", " ", " ", " ", " ", " ", " "},
    {"06:00", " ", " ", " ", " ", " ", " ", " "},{"07:00", " ", " ", " ", " ", " ", " ", " "},
    {"08:00", " ", " ", " ", " ", " ", " ", " "},{"09:00", " ", " ", " ", " ", " ", " ", " "},
    {"10:00", " ", " ", " ", " ", " ", " ", " "},{"11:00", " ", " ", " ", " ", " ", " ", " "},
    {"12:00", " ", " ", " ", " ", " ", " ", " "},{"13:00", " ", " ", " ", " ", " ", " ", " "},
    {"14:00", " ", " ", " ", " ", " ", " ", " "},{"15:00", " ", " ", " ", " ", " ", " ", " "},
    {"16:00", " ", " ", " ", " ", " ", " ", " "},{"17:00", " ", " ", " ", " ", " ", " ", " "},
    {"18:00", " ", " ", " ", " ", " ", " ", " "},{"19:00", " ", " ", " ", " ", " ", " ", " "},
    {"20:00", " ", " ", " ", " ", " ", " ", " "},{"21:00", " ", " ", " ", " ", " ", " ", " "},
    {"22:00", " ", " ", " ", " ", " ", " ", " "},{"23:00", " ", " ", " ", " ", " ", " ", " "} };
    Object titles[] = {"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",};
    JTable tableWeek = new JTable(time, titles);
    tableWeek.getTableHeader().setResizingAllowed(false);
    tableWeek.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(tableWeek);
    window.add(scroll, BorderLayout.CENTER);
    window.setSize(470, 430);
    window.setVisible(true);
    tableWeek.setShowGrid(true);
    tableWeek.setGridColor(Color.PINK);
    tableWeek.setEnabled(false);
    for (int count = 0; count < 7; count++){
      ArrayList<String> daysOfWeek = new ArrayList<String>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));
      int timeDay = 0;
      String hashDay = Integer.toString(calendar.calcYear(date) + count);
      while(timeDay < 24){
        String time1 = String.format("%02d", timeDay);
        String time2 = (time1 + ":00");
        String value = hmap.get(hashDay).get(time2);
        tableWeek.setValueAt(time2, timeDay, 0);
        tableWeek.setValueAt(value, timeDay, count + 1);
        timeDay++;
      }
    }
  }

  // Creates event to be saved to hashmap
  public void createEvent(){
    nameField = new JTextField(20);
    startField = new JTextField(5);
    endField = new JTextField(5);
    dateField = new JTextField(5);
    myPanel = new JPanel();
    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
    myPanel.add(new JLabel("Name of Event: "));
    myPanel.add(nameField);
    myPanel.add(new JLabel("Start Time (24H) HH:00: "));
    myPanel.add(startField);
    myPanel.add(new JLabel("End Time (24H) HH:00: "));
    myPanel.add(endField);
    myPanel.add(new JLabel("Date DD/MM: "));
    myPanel.add(dateField);
    myPanel.add(new JLabel("Type of Event: "));

    String[] typeStrings = {"Work", "Personal", "School"};
    typeBox = new JComboBox(typeStrings);
    typeBox.addActionListener(new typeBoxAction());
    myPanel.add(typeBox);
    int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter event information", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
       startField.getText();
       endField.getText();
       dateField.getText();
       nameField.getText();
       calendar.eventAdd(calendar.getHashmap(), dateField.getText(), startField.getText(), endField.getText(), nameField.getText(), type);
     }
  }

  // Deletes an event from a hashmap
  public void deleteEvent(){
    startField = new JTextField(5);
    endField = new JTextField(5);
    dateField = new JTextField(5);
    myPanel = new JPanel();
    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
    myPanel.add(new JLabel("Start Time HH:00:"));
    myPanel.add(startField);
    myPanel.add(new JLabel("End Time HH:00:"));
    myPanel.add(endField);
    myPanel.add(new JLabel("Date DD/MM:"));
    myPanel.add(dateField);
    int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter event information", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
       startField.getText();
       endField.getText();
       dateField.getText();
       calendar.eventDel(calendar.getHashmap(), dateField.getText(), startField.getText(), endField.getText());
    }
  }

  //Reference for the following code (154 - 257) http://javahungry.blogspot.com/2013/06/calendar-implementation-gui-based.html
  // Builds monthly calendar view
  public void monthlyCalendar(){
    mainFrame = new JFrame ("2017 Calendar");
    mainFrame.setSize(325, 375);
    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
    pane = mainFrame.getContentPane();
    pane.setLayout(null);

    labelMonth = new JLabel ("January");
    labelMonth.setBounds(160-labelMonth.getPreferredSize().width/2, 25, 100, 25);

    buttonPrev = new JButton ("<<");
    buttonPrev.addActionListener(new buttonPreviousAction());
    buttonPrev.setBounds(10, 20, 50, 25);
    buttonNext = new JButton (">>");
    buttonNext.addActionListener(new buttonNextAction());
    buttonNext.setBounds(260, 20, 50, 25);

    modelCalendar = new DefaultTableModel(){
      public boolean isCellEditable(int rowIndex, int colIndex){
        return false;
      }
    };

    calendarTable = new JTable(modelCalendar);
    scrollCalendar = new JScrollPane(calendarTable);
    scrollCalendar.setBounds(10, 50, 300, 250);

    calendarPanel = new JPanel(null);
    calendarPanel.setBounds(2, 10, 320, 335);
    calendarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.PINK, 10), "2017"));
    calendarPanel.add(labelMonth);
    calendarPanel.add(buttonPrev);
    calendarPanel.add(buttonNext);
    calendarPanel.add(scrollCalendar);
    pane.add(calendarPanel);

    String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    for (int index = 0; index < 7; index++){
        modelCalendar.addColumn(daysOfWeek[index]);
    }

    calendarTable.setRowSelectionAllowed(false);
    calendarTable.getTableHeader().setResizingAllowed(false);
    calendarTable.getTableHeader().setReorderingAllowed(false);
    calendarTable.setRowHeight(38);
    modelCalendar.setColumnCount(7);
    modelCalendar.setRowCount(6);

    GregorianCalendar calendar = new GregorianCalendar();
    realMonth = calendar.get(GregorianCalendar.MONTH);
    currentMonth = realMonth;
    refreshMonthly (realMonth, realYear);
  }

  public void refreshMonthly(int month, int year){
      String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

      buttonPrev.setEnabled(true);
      buttonNext.setEnabled(true);
      if (month == 0){
        buttonPrev.setEnabled(false);
      } else if (month == 11){
        buttonNext.setEnabled(false);
      }

      labelMonth.setText(months[month]);
      labelMonth.setBounds(140, 25, 180, 25);

      //clears the calendar
      for (int row = 0; row < 6; row++){
          for (int column = 0; column < 7; column++){
              modelCalendar.setValueAt(null, row, column);
          }
      }

      GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
      int numberOfDays = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
      int startOfMonth = calendar.get(GregorianCalendar.DAY_OF_WEEK);

      //redraw the calendar when you refresh it for a new month
      for (int day = 1; day <= numberOfDays; day++){
          int row = new Integer((day + startOfMonth - 2)/ 7);
          int column  = (day + startOfMonth - 2) % 7;
          modelCalendar.setValueAt(day, row, column);
      }
  }

  public class buttonPreviousAction implements ActionListener{
      public void actionPerformed (ActionEvent e){
          currentMonth--;
          refreshMonthly(currentMonth, realYear);
      }
  }

  public class buttonNextAction implements ActionListener{
      public void actionPerformed (ActionEvent e){
          currentMonth++;
          refreshMonthly(currentMonth, realYear);
      }
  }

  // Action listener for type of event (Work / Personal / School)
  public class typeBoxAction implements ActionListener{
      public void actionPerformed (ActionEvent e){
        type = String.valueOf(typeBox.getSelectedItem());
      }
  }

}
