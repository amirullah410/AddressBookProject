import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        UI ui = new UI(addressBook);

        JFrame frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ui);
        frame.pack();
        frame.setVisible(true);
    }
}
