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

public class Main extends JFrame implements ActionListener{
    //GUI Instance Variables
    private JPanel panel;
    private JFrame frame;
    private JButton addEvent,delEvent, enter;
    private TextField text;
    private ImageIcon image;
    private ImageIcon image2;
    private ImageIcon gif;
    private String[] soundList = {"No Sound", "Meow Mix", "Club REMIX"};
    private JComboBox soundBox = new JComboBox(soundList);
    private JComboBox viewTypes;
    private JLabel displayMessage, title,clubTitle, label, partyGif;
    private JLabel displayMessage2 = new JLabel("");
    GridBagConstraints constraints = new GridBagConstraints();
    // Sound Instance Variables
    private File meowSound = new File("meow.WAV");
    private File meowSong = new File ("meow-mix.WAV");
    private File meowRemix = new File("meow-mix-remix.WAV");
    private Clip meowClip, meowSongClip;
    // Class Instances
    private Controller controller = new Controller();

    public Main(){
        InterfaceConstructor();
    }

    //constructs the main menu
    public void InterfaceConstructor(){

        // Creates Panel
        panel= new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);

        // Creates Titles and their respective images
        image= new ImageIcon(getClass().getResource("cat-lendar.png"));
        title=new JLabel(image);
        constraints.gridx = 0;
        constraints.gridy= 0;
        panel.add(title, constraints);

        image2= new ImageIcon(getClass().getResource("club-title.png"));
        clubTitle=new JLabel(image2);
        constraints.gridx = 0;
        constraints.gridy= 0;
        panel.add(clubTitle, constraints);
        clubTitle.setVisible(false);

        gif = new ImageIcon(getClass().getResource("cat-dj-small.gif"));
        partyGif=new JLabel(gif);
        constraints.gridx = 2;
        constraints.gridy= 2;
        panel.add(partyGif, constraints);
        partyGif.setVisible(false);

        // Creates text field for input
        text = new TextField(20);
        constraints.gridx = 1;
        constraints.gridy= 4;
        panel.add(text, constraints);
        text.setVisible(false);

        // Creates Add Event Button
        addEvent = new JButton("ADD EVENT");
        addEvent.setPreferredSize(new Dimension(300, 180));
        constraints.gridx = 0;
        constraints.gridy= 2;
        addEvent.addActionListener(this);
        ImageIcon catAdd = new ImageIcon(getClass().getResource("im-hungry.png"));
        addEvent.setIcon(catAdd);
        addEvent.setBackground(Color.PINK);
        panel.add(addEvent, constraints);

        // Creates Enter button
        enter = new JButton("Enter");
        enter.setBackground(Color.PINK);
        enter.addActionListener(new ViewAction());
        constraints.gridx = 2;
        constraints.gridy= 4;
        panel.add(enter, constraints);
        enter.setVisible(false);

        // Creates Delete Event button
        delEvent = new JButton("DELETE EVENT");
        delEvent.setPreferredSize(new Dimension(300, 180));
        constraints.gridx = 1;
        constraints.gridy= 2;
        delEvent.addActionListener(this);
        ImageIcon catDel = new ImageIcon(getClass().getResource("tres-quatro.png"));
        delEvent.setIcon(catDel);
        delEvent.setBackground(Color.PINK);
        panel.add(delEvent, constraints);

        // Adds Sound ComboBox
        soundBox.addActionListener(new SoundAction());
        panel.add(soundBox);

        // Adds Display message
        displayMessage = new JLabel("Select a CATlendar view or you can ADD or DELETE an event!", SwingConstants.CENTER);
        displayMessage.setHorizontalAlignment(SwingConstants.RIGHT);
        constraints.gridx = 1;
        constraints.gridy= 3;
        panel.add(displayMessage, constraints);

        // Creates View ComboBox
        String[] viewList = { "Weekly", "Daily", "Monthly" };
        viewTypes = new JComboBox(viewList);
        viewTypes.setSelectedIndex(0);
        viewTypes.addActionListener(new ViewAction());
        constraints.gridx = 0;
        constraints.gridy= 4;
        panel.add(viewTypes, constraints);

        // Creates main window frame
        panel.setVisible(true);
        frame = new JFrame("CATlendar");
        frame.setVisible(true);
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
    }

    //plays meow with button activation
    public void playMeow(File sound){
      try{
          meowClip = AudioSystem.getClip();
          meowClip.open(AudioSystem.getAudioInputStream(sound));
          meowClip.start();
      }catch(Exception e){}
    }

    // plays music with change of music combobox
    public void playMeowSong(File sound){
      try{
          meowSongClip = AudioSystem.getClip();
          meowSongClip.open(AudioSystem.getAudioInputStream(sound));
          meowSongClip.start();
      }catch(Exception e){}
    }

    //stops sound clip
    public void stopClip(File sound){
      try{
        if (meowClip.isRunning()){
          meowClip.stop();
        }
      }catch(Exception e){}
    }

    public void clubModeON(){
      clubTitle.setVisible(true);
      title.setVisible(false);
      panel.setBackground(Color.BLACK);
      partyGif.setVisible(true);


    }
    public void clubModeOFF(){
      title.setVisible(true);
      clubTitle.setVisible(false);
      panel.setBackground(Color.WHITE);
      partyGif.setVisible(false);
    }


    //corresponding to what the user choses to do this method will change the event hashmap or display a calendar view.
    public void actionPerformed (ActionEvent e){
        String viewSelect = (String)viewTypes.getSelectedItem();
        if(e.getActionCommand().equals("ADD EVENT")){
            playMeowSong(meowSound);
            controller.createEvent();
        }else if(e.getActionCommand().equals("DELETE EVENT")){
            playMeowSong(meowSound);
            controller.deleteEvent();
        }else{
            panel.remove(displayMessage2);
            constraints.gridx = 1;
            constraints.gridy= 3;
            panel.add(displayMessage, constraints);
            panel.remove(text);
        }
      }

    //action listener for music combobox
    public class SoundAction implements ActionListener{
      public void actionPerformed (ActionEvent e){
      String soundSelect = (String)soundBox.getSelectedItem();
      int soundIndex = soundBox.getSelectedIndex();
        switch (soundIndex){
          case 0: // No Song
            stopClip(meowSong);
            stopClip(meowRemix);
            clubModeOFF();
            ImageIcon catDel = new ImageIcon(getClass().getResource("tres-quatro.png"));
            delEvent.setIcon(catDel);
            ImageIcon catAdd = new ImageIcon(getClass().getResource("im-hungry.png"));
            addEvent.setIcon(catAdd);
            break;
          case 1: // Meow Mix Original
            stopClip(meowRemix);
            playMeow(meowSong);
            clubModeOFF();
            catDel = new ImageIcon(getClass().getResource("tres-quatro.png"));
            delEvent.setIcon(catDel);
            catAdd = new ImageIcon(getClass().getResource("im-hungry.png"));
            addEvent.setIcon(catAdd);
            break;
          case 2: // Remix Meow Mix Song
            stopClip(meowSong);
            playMeow(meowRemix);
            clubModeON();
            ImageIcon thugCat = new ImageIcon(getClass().getResource("tres-quatro-thug.png"));
            delEvent.setIcon(thugCat);
            ImageIcon hungryThug = new ImageIcon(getClass().getResource("im-hungry-thug.png"));
            addEvent.setIcon(hungryThug);
            break;
        }
      }
    }


  // action listener for View combobox
  public class ViewAction implements ActionListener{
      public void actionPerformed (ActionEvent e){
      int selection = viewTypes.getSelectedIndex();
        switch (selection){
          // Weekly View (Case 0)
          case 0: displayMessage.setText("Enter the Sunday of the week you'd like to view! DD/MM");
              constraints.gridx = 1;
              constraints.gridy= 4;
              panel.add(text, constraints);
              constraints.gridx = 2;
              constraints.gridy= 4;
              text.setVisible(true);
              enter.setVisible(true);
              panel.add(enter, constraints);
              String date = text.getText();
              if(e.getActionCommand().equals("Enter")){
                controller.weeklyCalendar(controller.calendar.getHashmap(), date);
                displayMessage.setText("Select a CATlendar view or you can ADD or DELETE an event!");
                panel.remove(text);
                panel.remove(enter);
              }
              break;
          // Daily View (Case 1)
          case 1: displayMessage.setText("Enter the date you'd like to view! DD/MM");
              text.setVisible(true);
              enter.setVisible(true);
              constraints.gridx = 1;
              constraints.gridy= 4;
              panel.add(text, constraints);
              constraints.gridx = 2;
              constraints.gridy= 4;
              panel.add(enter, constraints);
              date = text.getText();
              if(e.getActionCommand().equals("Enter")){
                controller.dailyCalendar(controller.calendar.getHashmap(), date);
                displayMessage.setText("Select a CATlendar view or you can ADD or DELETE an event!");
                panel.remove(text);
                panel.remove(enter);
              }
              break;
          // Monthly View (Case 2)
          case 2: displayMessage.setText("Press Enter to view monthly");
              text.setVisible(false);
              constraints.gridx = 2;
              constraints.gridy= 4;
              panel.add(enter, constraints);
              if(e.getActionCommand().equals("Enter")){
                controller.monthlyCalendar();
                displayMessage.setText("Select a CATlendar view or you can ADD or DELETE an event!");
                panel.remove(enter);
              }
              break;
          }
       }
    }

    public static void main(String [] args){
        new Main();
    }
}
