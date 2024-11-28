package com.artena.llm.ide.notification;

import com.intellij.notification.Notification;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

class ConfigurableNotification extends Notification {
    @NotNull
    final NotificationConfig config;

    ConfigurableNotification(@NotNull NotificationConfig config) {
        super("llm.notifications", config.title, config.content, config.notificationType);

        this.config = config;

        setTitle(config.title, config.subtitle);
        setContent(config.content);
        setIcon(config.icon);
        setImportant(config.isImportant);

        if (config.dropdownText != null) {
            setDropDownText(config.dropdownText);
        }

        setContextHelpAction(config.contextHelpAction);

        for (AnAction action : config.actions) {
            addAction(action);
        }
    }
}
