package pl.edu.agh.to.mosti.fetcher;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class IntervalController {

    private Map<Long, Timestamp> fetchTimestamp = new HashMap<>();

    public boolean contains(long id ) {
        return fetchTimestamp.containsKey(id);
    }

    public boolean elapsed(long id, long interval) {
        Timestamp lastFetchTimestamp = fetchTimestamp.get(id);
        Timestamp minFetchTimestamp = new Timestamp( lastFetchTimestamp.getTime() + ( interval * 1000 ) );

        return minFetchTimestamp.before( getCurrentTimestamp());
    }

    public void setCheckpoint(long id) {
        fetchTimestamp.put(id, getCurrentTimestamp());
    }

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}

