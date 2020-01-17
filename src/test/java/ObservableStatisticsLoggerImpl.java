import com.azeevg.StatisticsData;

import java.util.ArrayList;
import java.util.List;

public class ObservableStatisticsLoggerImpl implements ObservableStatisticsLogger {
    List<StatisticsData> loggedStats = new ArrayList<>();

    @Override
    public List<StatisticsData> getStats() {
        return loggedStats;
    }

    @Override
    public void log(StatisticsData e) {
        loggedStats.add(e);
    }
}
