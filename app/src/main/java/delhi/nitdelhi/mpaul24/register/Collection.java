package delhi.nitdelhi.mpaul24.register;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mpaul24 on 10/6/2017.
 */

public class Collection {
    ArrayList<Record> list = new ArrayList<>();
    static Collection c;

    static Collection getInstance(){
        if(c==null){
            c = new Collection();
            return c;
        }
        return c;
    }

    static void RestoreObject(Collection col){
        c = col;
    }

    void add(Record record){
        list.add(record);
    }

    void delete(int n){
        list.remove(n);
    }

    Record get(int n){
        return list.get(n);
    }

    int getSize(){
        return list.size();
    }

    void clear(){
        list.clear();
    }

}
