public abstract class Event{
  protected String eventName;
  protected String eventDate;
  protected String eventStart;
  protected String eventEnd;
  protected String eventType;

  // abstracts are set by Work / Personal / School subclasses
  // values set the super class instance variables (Event class)
  public abstract void setName(String name);
  public abstract void setStart(String start);
  public abstract void setEnd(String end);
  public abstract void setDate(String date);

  public String getName(){
    return this.eventName;
  }

  public String getStart(){
    return this.eventStart;
  }

  public String getEnd(){
    return this.eventEnd;
  }

  public String getDate(){
    return this.eventDate;
  }
}
