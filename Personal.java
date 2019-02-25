public class Personal extends Event{

  public Personal(String name, String start, String end, String date){
    setName(name);
    setStart(start);
    setEnd(end);
    setDate(date);
    super.eventType = "Personal";
  }

  // Creates a flag in the hashmap - labels event "Personal"
  public void setName(String name){
    super.eventName = name + " (PERSONAL)";
  }

  public void setStart(String start){
    super.eventStart = start;
  }

  public void setEnd(String end){
    super.eventEnd = end;
  }

  public void setDate(String date){
    super.eventDate = date;
  }
}
