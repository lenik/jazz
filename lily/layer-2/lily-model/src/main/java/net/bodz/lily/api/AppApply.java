package net.bodz.lily.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@Table(name = "apply")
@IdType(Integer.class)
public class AppApply
        extends CoMessage<Integer> {

    private static final long serialVersionUID = 1L;

    List<ApiRequest> requests = new ArrayList<>();

    public AppApply() {
    }

    public List<ApiRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<ApiRequest> requests) {
        this.requests = requests;
    }

}
