package chat;

import javax.swing.JOptionPane;

public class ChatMain {
    public static void main(String[] args) {
        int num = Integer.parseInt(JOptionPane.showInputDialog("How many messages you want to send?"));
        int sent = 0;

        for (int i = 0; i < num; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient (+27...):");
            String text = JOptionPane.showInputDialog("Enter your message (max 250 chars):");

            Message m = new Message(recipient, text);

            if (text.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (text.length() - 250) + ", please reduce size.");
                continue;
            }

            if (m.checkRecipientCell() == 0) {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                continue;
            }

            String choice = JOptionPane.showInputDialog("1) Send  2) Disregard  3) Store \nChoose:");
            String result = m.sentMessage(choice);
            JOptionPane.showMessageDialog(null, result + "\nID: " + m.getMessageID() + "\nHash: " + m.createMessageHash());

            if (choice.equals("1")) sent++;
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + sent);
    }
}