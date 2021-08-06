package com.templatetasks.java.quarkus.impl;

import com.templatetasks.java.quarkus.dto.Jedi;
import com.templatetasks.java.quarkus.JediService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 20:29
 */
@ApplicationScoped
@Alternative
public class InMemoryJediService implements JediService {

    private final Map<String, Jedi> jediOrder = Map.of(
            "Qui-Gonn", new Jedi("Qui-Gonn Jinn", "Jedi Master"),
            "Obi-Wan", new Jedi("Obi-Wan Kenobi", "Jedi Master"),
            "Anakin", new Jedi("Anakin Skywalker", "...")
    );

    @Override
    public Jedi getJedi(String name) {
        return jediOrder.get(name);
    }
}