package com.artena.llm.ide.notification;

import com.intellij.notification.impl.NotificationFullContent;

class FullContentConfigurableNotification extends ConfigurableNotification implements NotificationFullContent {
    FullContentConfigurableNotification(NotificationConfig config) {
        super(config);
    }
}
