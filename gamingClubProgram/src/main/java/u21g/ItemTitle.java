package u21g;

public class ItemTitle {

    String itemTitle;
    int itemTitleNum;
    int totalCopies;
    boolean isAvailable;
    int availableCopies;

    ItemTitle() {}

    ItemTitle(int itemTitleNum, String itemTitle, int totalCopies, int availableCopies)
    {   this.itemTitle = itemTitle;
        this.itemTitleNum = itemTitleNum;
        this.totalCopies = totalCopies;
        this.isAvailable = (availableCopies==0) ? false : true;
        this.availableCopies = availableCopies;
    }
}