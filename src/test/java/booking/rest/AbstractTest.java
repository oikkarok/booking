package booking.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractTest implements BookingInterfaceTest {

    protected String token;

    protected static final String username = "oikkarok";

    protected static final String password = "oikkarok";

    @SuppressWarnings("rawtypes")
    protected static List<Entry> entryList = new ArrayList<>();

    @Override
    public void shouldNotAllowAccessToUnauthenticatedUsersTest() throws Exception {

    }

    @Override
    public void shouldGenerateAuthTokenTest() throws Exception {

    }

    @Override
    public String asJsonString(Object obj) {
	try {
	    return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

}
