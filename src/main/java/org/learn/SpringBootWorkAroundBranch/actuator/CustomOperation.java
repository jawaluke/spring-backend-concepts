package org.learn.SpringBootWorkAroundBranch.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "customRaja")
public class CustomOperation {

    String raja = "pandiya";

    @ReadOperation(produces = "application/json")
    public String getRajaOperation() {
        return raja;
    }

    @WriteOperation(produces = "application/json")
    public void postRajaOperation(String raja) {
        this.raja = raja;
    }

}
