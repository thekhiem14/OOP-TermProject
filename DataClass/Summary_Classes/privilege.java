package Summary_Classes;

import java.util.ArrayList;
import java.util.List;

public class privilege {
    private List<String> privilege;

    public privilege(List<String> role) {
        this.privilege = new ArrayList<String>(role);
    }

    public void addprivilege(String newprivilege){
        this.privilege.add(newprivilege);
    }

    public void deleteprivilege(String role) {
        if(this.privilege.contains(role)) this.privilege.remove(role);
    }

    public List<String> getprivilege() {
        return privilege;
    }
}
