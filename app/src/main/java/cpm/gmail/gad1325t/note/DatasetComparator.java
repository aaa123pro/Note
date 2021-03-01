package cpm.gmail.gad1325t.note;

import java.util.Comparator;

public class DatasetComparator implements Comparator<Dataset> {
    public int compare(Dataset d1,Dataset d2){
        return d1.getTime().compareTo(d2.getTime());
    }
}
