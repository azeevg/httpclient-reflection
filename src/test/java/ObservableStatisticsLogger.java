import com.azeevg.StatisticsData;
import com.azeevg.StatisticsLogger;

import java.util.List;

public interface ObservableStatisticsLogger extends StatisticsLogger {
    List<StatisticsData> getStats();
}
