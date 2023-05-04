import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI extends JPanel {
    private AddressBook addressBook;
    private JTextField nameField, phoneField, emailField, searchField;
    private JTextArea contactListArea;

    public UI(AddressBook addressBook) {
        this.addressBook = addressBook;

        // Set layout
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField(20);
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        inputPanel.add(emailField);
        add(inputPanel, BorderLayout.NORTH);

        // Create buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());
        buttonPanel.add(addButton);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new UpdateButtonListener());
        buttonPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteButtonListener());
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Create search field
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        // Create contact list
        JPanel contactListPanel = new JPanel(new BorderLayout());
        contactListArea = new JTextArea(20, 40);
        contactListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contactListArea);
        contactListPanel.add(scrollPane);
        add(contactListPanel, BorderLayout.EAST);

        updateContactList();
    }

    private void clearInputFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    private void updateContactList() {
        StringBuilder sb = new StringBuilder();
        for (Contact contact : addressBook.getContacts()) {
            sb.append(contact.toString()).append("\n");
        }
        contactListArea.setText(sb.toString());
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            Contact contact = new Contact(name, phone, email);
            addressBook.addContact(contact);
            clearInputFields();
            updateContactList();
        }
    }

    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            Contact contact = new Contact(name, phone, email);
            addressBook.updateContact(contact);
            clearInputFields();
            updateContactList();
        }
    }

    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            Contact contact = new Contact(name, phone, email);
            addressBook.deleteContact(contact);
            clearInputFields();
            updateContactList();
        }
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchTerm = searchField.getText();
            Contact contact = addressBook.searchContact(searchTerm);
            if (contact != null) {
                nameField.setText(contact.getName());
                phoneField.setText(contact.getPhoneNumber());
                emailField.setText(contact.getEmailAddress());
            } else {
                JOptionPane.showMessageDialog(UI.this, "No matching contact found.", "Search", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

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