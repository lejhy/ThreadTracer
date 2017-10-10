package BackEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Filtration {
    private Map<Integer, Function<List<Thread>, List<Thread>>> filters = new HashMap<>();
    public final int FILTER_DEAMONS = 0;

    public Filtration(){

        filters.put(FILTER_DEAMONS, (Filtration::filterDeamons));
    }
    public List<Thread> applyfilter(List<Thread> threads, int functionID){
        return filters.get(functionID).apply(threads);
    }


    private static List<Thread> filterDeamons(List<Thread> threads){
        List<Thread> returnable = new ArrayList<>();
        for(Thread current: threads){
            if(current.isDaemon())
                returnable.add(current);
        }
        return returnable;
    }
}
