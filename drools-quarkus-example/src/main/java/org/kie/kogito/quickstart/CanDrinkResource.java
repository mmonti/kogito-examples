package org.kie.kogito.quickstart;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.group.artifact.ActionExecutor;
import org.kie.api.runtime.KieSession;
import org.kie.kogito.Application;
import org.kie.kogito.rules.KieRuntimeBuilder;
import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnits;
import org.kie.kogito.rules.impl.SessionMemory;

@Path("/candrink/{name}/{age}")
public class CanDrinkResource {

    @Inject @Named("canDrinkKS")
    RuleUnit<SessionMemory> ruleUnit;

    @Inject
    Application application;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String canDrink( @PathParam("name") String name, @PathParam("age") int age ) {
        Result result = new Result();
        Person person = new Person( name, age );

        ActionExecutor e = new ActionExecutor();
        RuleUnits ruleUnits = application.ruleUnits();
        KieRuntimeBuilder kieRuntimeBuilder = ruleUnits.ruleRuntimeBuilder();
        KieSession session = kieRuntimeBuilder.newKieSession();
        session.setGlobal("e", e);
        session.insert(result);
        session.insert(person);
        session.fireAllRules();

        return result.toString();
    }
}