package chat;

import java.util.Random;

public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String hash;
    private static int totalSent = 0;

    public Message(String recipient, String message) {
        this.messageID = generateID();
        this.recipient = recipient;
        this.message = message;
    }

    private String generateID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += rand.nextInt(10);
        }
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public int checkRecipientCell() {
        if (recipient.length() <= 12 && recipient.startsWith("+27") && recipient.charAt(3) != '0') {
            return 1;
        }
        return 0;
    }

    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = message.trim().split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();
        // remove punctuation
        first = first.replaceAll("[^A-Z]", "");
        last = last.replaceAll("[^A-Z]", "");
        hash = firstTwo + ":" + totalSent + ":" + first + last;
        return hash;
    }

    public String sentMessage(String choice) {
        if (choice.equals("1")) {
            totalSent++;
            createMessageHash();
            return "Message successfully sent.";
        } else if (choice.equals("2")) {
            return "Message disregarded.";
        } else {
            return "Message successfully stored.";
        }
    }

    // getters for tests
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getHash() { return hash; }
    public static int getTotalSent() { return totalSent; }
}