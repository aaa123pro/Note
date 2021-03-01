package cpm.gmail.gad1325t.note;

public class Dataset {
    private String name;
    private String time;

    public Dataset(String name,String time){
        this.name=name;
        this.time=time;
    }
    public String getName(){
        return name;
    }
    public String getTime(){
        return time;
    }
}
