package testembedded;

import EmbeddedServer.*;
import MessageObjects.*;

public class TestEmbedded {

    public static void main(String[] args) {
       EmbeddedServer server = new EmbeddedServer();
       MessageAck message = server.add("ADOO","1234", "adminadoo");
       System.out.println(message.getStatus()+" | "+message.getDescription());
    }
}

