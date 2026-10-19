package chat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testCheckMessageID() {
        Message m = new Message("+27838968976", "Hi");
        assertTrue(m.checkMessageID());
    }

    @Test
    public void testCheckRecipientCorrect() {
        Message m = new Message("+27838968976", "Hello");
        assertEquals(1, m.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientIncorrect() {
        Message m = new Message("0838968976", "Hello");
        assertEquals(0, m.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {
        Message m = new Message("+27838968976", "Hi Mike are you coming tonight");
        m.createMessageHash();
        String hash = m.getHash();
        // should be like 00:0:HITONIGHT
        assertTrue(hash.contains("HITONIGHT"));
        assertTrue(hash.contains(":"));
    }

    @Test
    public void testSendMessage() {
        Message m = new Message("+27838968976", "Hello");
        String result = m.sentMessage("1");
        assertEquals("Message successfully sent.", result);
    }
}