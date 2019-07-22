package com.group.artifact;

import org.kie.kogito.quickstart.Person;
import org.kie.kogito.quickstart.Result;

public class ActionExecutor {

    public void execute(final Result r, final Person p, final String param) {
        r.setValue(p.getName() +" of " + p.getAge() + " " + param);
    }
}
