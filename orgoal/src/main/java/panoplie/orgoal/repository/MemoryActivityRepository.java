package panoplie.orgoal.repository;

import panoplie.orgoal.domain.Activity;

import java.util.*;

public class MemoryActivityRepository implements ActivityRepository {

    private final static Map<Integer, Activity> store = new HashMap<>();
    private static Integer aid = 0;

    @Override
    public List<Activity> search(String query) {
        List<Activity> list = new LinkedList<>();
        for (Activity value : store.values()) {
            if (value.getTitle().contains(query) || value.getContent().contains(query)) {
                list.add(value);
            }
        }
        return list;
    }

    @Override
    public List<Activity> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Activity findById(int id) {
        return store.get(id);
    }

    @Override
    public void save(Activity activity) {
        activity.setAid(++aid);
        store.put(activity.getAid(), activity);
    }

    public static void clearStore() {
        store.clear();
        aid = 0;
    }
}
