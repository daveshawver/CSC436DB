package u21g;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.awt.Point;
import javax.swing.JFrame;

public class BindDoubleClicktoRent extends MouseAdapter {
    private User user;
    private FrameandCardHolder mainFrame;

    BindDoubleClicktoRent(FrameandCardHolder mainFrame, User user) {
        this.user = user;
        this.mainFrame = mainFrame;
    }
    public void mousePressed (MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
        int row = table.rowAtPoint(point);
        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
        final Object obj = (Object)table.getValueAt(row,0);
         RentAnItem.rentAnItem(obj.toString(), user, mainFrame);
       }
    }
}