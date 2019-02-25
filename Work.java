public class Work extends Event{

  public Work(String name, String start, String end, String date){
    setName(name);
    setStart(start);
    setEnd(end);
    setDate(date);
    super.eventType = "Work";
  }

  // Creates a flag in the hashmap - labels event "WORK"
  public void setName(String name){
    super.eventName = name + " (WORK)";
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
