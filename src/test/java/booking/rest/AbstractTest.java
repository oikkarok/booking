package booking.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public abstract class AbstractTest implements BookingInterfaceTest {
    
    protected String token;
    
    protected static final String username = "oikkarok";
    
    protected static final String password = "oikkarok";

    protected static List<Entry> entryList = new ArrayList<>();
    
    @Override
    public void shouldNotAllowAccessToUnauthenticatedUsersTest() throws Exception {

    }
    
    @Override
    public void shouldGenerateAuthTokenTest() throws Exception {
       
    }

}
