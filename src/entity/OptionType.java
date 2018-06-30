package entity;

import java.util.List;

public class OptionType {

    private int Id;
    private String Name;
    private List<ItemOption> ItemOptions;

    public List<ItemOption> getItemOptions() {
        return ItemOptions;
    }

    public void setItemOptions(List<ItemOption> itemOptions) {
        ItemOptions = itemOptions;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
