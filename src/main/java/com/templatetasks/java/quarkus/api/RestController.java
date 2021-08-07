package com.templatetasks.java.quarkus.api;

import com.templatetasks.java.quarkus.JediService;
import com.templatetasks.java.quarkus.RandomStringService;
import com.templatetasks.java.quarkus.dto.Jedi;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 18:07
 */
@Path("/examples/quarkus")
@Slf4j
public class RestController {

    @Inject
    JediService jediService;

    @Inject
    RandomStringService sampleService;

    @GET
    @Path("/plain")
    @Produces(MediaType.TEXT_PLAIN)
    public String testPlain() {
        logger.info("New request received, processing");
        String result = sampleService.randomString();
        logger.info("Request processed: {}", result);
        return result;
    }

    @GET
    @Path("/jedi/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testJson(@PathParam("name") String name) {
        logger.info("New Jedi request received, processing");
        Jedi jedi = jediService.getJedi(name);
        if (jedi == null) {
            logger.info("Nothing found by name '{}'", name);
            return Response.status(Response.Status.NOT_FOUND)
                           .build();
        } else {
            logger.info("Jedi found: {}", jedi);
            return Response.ok(jedi)
                           .build();
        }
    }
}