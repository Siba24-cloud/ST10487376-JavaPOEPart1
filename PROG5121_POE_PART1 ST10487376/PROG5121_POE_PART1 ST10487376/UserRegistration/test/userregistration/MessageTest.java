package userregistration;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class MessageTest 
{
    private SendMessage message;
    private Object newMessage;
    
    @Before
    public void setUp()
    {
        //Test data from Part 2 brief, Message 1
        message = new SendMessage(0, "+27730712215", "Hi Mike, can you join us for dinner tonight?");
    }
    
    @Test
    public void testCheckMessageID()
    {
        assertTrue("Message ID should be 10 characters or less",
                message.checkMessageID());
    }
    
    @Test
    public void checkRecipientCellValid()
    {
        assertEquals("Cellphone number successfully captured.",
                message.checkRecipientCell());
    }
    
    @Test
    public void testCheckRecipientInvalid()
    {
        SendMessage invalid = new SendMessage(0, "0730712215",
                    "Hi Keegen, did you receive the payment");
        assertEquals("Cellphone number is incorrectly formatted or dose not contain an international code. Please try again.",
                invalid.checkRecipientCell());
    }
    
    @Test
    public void testCheckMessageLengthVaild()
    {
        String message;
        assertEquals("Message ready to send.", 
                message.checkMessageLength());
    }
    
    @Test
    public void testCheckMessageLengthInvalid()
    {
        StringBuilder longText = new StringBuilder();
       
        for(int i = 0; i < 260; i++)
        {
            longText.append("a");
        }
        SendMessage tooLong = new SendMessage(0, "+27730712215", longText.toString());
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                tooLong.checkMessageLength());
    }
    
    @Test
    public void testCreateMessageHash()
    {
        String hash = message.createMessageHash();
        assertTrue("Hash should end with :0:HITONIGHT",
                hash.endsWith(":0:HITONIGHT"));
    }
    
    @Test
    public void testSentMessageSent()
    {
        assertEquals("Message successfully sent.", newMessage.SentMessage(1));
    }
    @Test
    public void testSentMessageDisregard()
    {
        assertEquals("Press 0 to delete the message.", newMessage.SentMessage(2));
    }
    @Test
    public void testSentMessageStore()
    {
        assertEquals("Message successfully stored.", SendMessage.SentMessage(3));
    }
    @Test
    public void testReturnTotalMessage()
    {
        int before = message.returnTotalMessage();
        message.SentMessage(1)
        assertEquals(before + 1, message.returnTotalMessage());
    }
    private void assertEquals(String cellphone_number_is_incorrectly_formatted, boolean checkRecipientCell) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
