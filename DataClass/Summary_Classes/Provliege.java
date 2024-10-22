package Summary_Classes;

import java.util.ArrayList;
import java.util.List;

public class Provliege {
    private List<String> privliege;

    public Provliege(List<String> role) {
        this.privliege = new ArrayList<String>(role);
    }

    public void addRow(String newPrivliege){
        this.privliege.add(newPrivliege);
    }

    public void deleteRoles(String role) {
        if(this.privliege.contains(role)) this.privliege.remove(role);
    }

    public List<String> getPrivliege() {
        return privliege;
    }
}
