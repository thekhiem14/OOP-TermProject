package Summary_Classes;

import java.util.ArrayList;
import java.util.List;

public class Privliege {
    private List<String> privliege;

    public Privliege(List<String> role) {
        this.privliege = new ArrayList<String>(role);
    }

    public void addPrivliege(String newPrivliege){
        this.privliege.add(newPrivliege);
    }

    public void deletePrivliege(String role) {
        if(this.privliege.contains(role)) this.privliege.remove(role);
    }

    public List<String> getPrivliege() {
        return privliege;
    }
}
