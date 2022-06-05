package cp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cp.Food;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Freezer implements Container<Food> {

    private List<Food> items = new ArrayList<Food>();
    private int capacity;

    public Freezer(int capacity) {
        this.capacity = capacity;
    }


    @Override
    public boolean add(Food item) {
        if(item.getSize() > getFreeSpace()) return false;
        items.add(item);
        return true;
    }

    @Override
    public boolean remove(Food item) {
        return items.remove(item);
    }

    @Override
    public List<Food> getAll() {
        return Collections.unmodifiableList(items);
    }

    public int getOccupiedSpace() {
        int space = 0;
        for (int i =0; i<items.size(); i++)
        {
            space += items.get(i).getSize();
        }
        return space;
    }

    public int getFreeSpace() {
        return capacity - getOccupiedSpace();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("items", items)
                .append("capacity", capacity)
                .toString();
    }
}
