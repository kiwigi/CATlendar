public class School extends Event{

  public School(String name, String start, String end, String date){
    setName(name);
    setStart(start);
    setEnd(end);
    setDate(date);
    super.eventType = "School";
  }

  // Creates a flag in the hashmap - labels event "School"
  public void setName(String name){
    super.eventName = name + " (SCHOOL)";
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
