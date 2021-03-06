package com.templatetasks.java.quarkus.event;

import com.templatetasks.java.quarkus.dto.Jedi;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 12:51
 */
public record NewJediMemberEvent(Jedi jedi) {
}
