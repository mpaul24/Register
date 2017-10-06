package delhi.nitdelhi.mpaul24.register;

import java.util.ArrayList;

/**
 * Created by Mpaul24 on 10/6/2017.
 */

public class Collection {
    ArrayList<String> list = new ArrayList<>();
    static Collection c;

    static Collection getInstance(){
        if(c==null){
            c = new Collection();
            return c;
        }
        return c;
    }

    void add(String record){
        list.add(record);
    }

    void delete(int n){
        list.remove(n);
    }

    String get(int n){
        return list.get(n);
    }

    int getSize(){
        return list.size();
    }

}
