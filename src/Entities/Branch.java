package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Branch {
    private String branchName;
    private List<String> vehicles = Collections.synchronizedList(new ArrayList<String>());

    public Branch(String branchName) {
        this.branchName = branchName;

    }

    public String getBranchName() {
        return branchName;
    }


    public List<String> getVehicles() {
        return vehicles;
    }


}
