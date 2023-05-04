import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(Contact contact) {
        int index = findContactIndex(contact);
        if (index != -1) {
            contacts.set(index, contact);
        }
    }

    public void deleteContact(Contact contact) {
        int index = findContactIndex(contact);
        if (index != -1) {
            contacts.remove(index);
        }
    }

    public Contact searchContact(String searchTerm) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(searchTerm) || contact.getPhoneNumber().equals(searchTerm)) {
                return contact;
            }
        }
        return null;
    }

    private int findContactIndex(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).equals(contact)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
