package com.artena.llm.ide.notification;

import com.google.common.collect.Lists;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class NotificationConfigBuilder {

    private final Project project;
    private final List<AnAction> actions = Lists.newArrayList();
    @NotNull
    private String title = "";
    private String subtitle;
    @NotNull
    private String content = "";
    @Nullable
    private String dropdownText;
    @Nullable
    private Notification.CollapseActionsDirection collapseDirection;
    @Nullable
    private Icon icon;
    private boolean actionIcons;
    private NotificationDisplayType displayType = NotificationDisplayType.BALLOON;
    private boolean isFullContent;
    private boolean isImportant;
    @NotNull
    private NotificationType notificationType = NotificationType.INFORMATION;
    @Nullable
    private AnAction contextHelpAction;

    private NotificationConfigBuilder(Project project) {
        this.project = project;
    }

    public static NotificationConfigBuilder create(Project project) {
        return new NotificationConfigBuilder(project);
    }

    static NotificationConfigBuilder create(Project project, NotificationConfig config) {
        NotificationConfigBuilder builder = new NotificationConfigBuilder(project);
        builder.title = config.title;
        builder.subtitle = config.subtitle;
        builder.content = config.content;
        builder.dropdownText = config.dropdownText;
        builder.collapseDirection = config.collapseDirection;
        builder.icon = config.icon;
        builder.actionIcons = config.actionIcons;
        builder.displayType = config.displayType;
        builder.isFullContent = config.isFullContent;
        builder.isImportant = config.isImportant;
        builder.notificationType = config.notificationType;
        builder.contextHelpAction = config.contextHelpAction;

        builder.actions.addAll(config.actions);

        return builder;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public void setDropdownText(@Nullable String dropdownText) {
        this.dropdownText = dropdownText;
    }

    public void setCollapseDirection(@Nullable Notification.CollapseActionsDirection collapseDirection) {
        this.collapseDirection = collapseDirection;
    }

    public void setIcon(@Nullable Icon icon) {
        this.icon = icon;
    }

    public void setDisplayType(@NotNull NotificationDisplayType displayType) {
        this.displayType = displayType;
    }

    public void setFullContent(boolean fullContent) {
        isFullContent = fullContent;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public void setNotificationType(@NotNull NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public void addAction(AnAction actions) {
        this.actions.add(actions);
    }

    public void setActionIcons(boolean actionIcons) {
        this.actionIcons = actionIcons;
    }

    public void setContextHelpAction(@Nullable AnAction contextHelpAction) {
        this.contextHelpAction = contextHelpAction;
    }

    public Notification build() {
        NotificationConfig config = new NotificationConfig(title,
                subtitle,
                content,
                dropdownText,
                collapseDirection,
                icon,
                displayType,
                isFullContent,
                isImportant,
                notificationType,
                actions,
                actionIcons,
                contextHelpAction);

        if (this.isFullContent) {
            return new FullContentConfigurableNotification(config);
        }
        return new ConfigurableNotification(config);
    }

    public void resetActions() {
        this.actions.clear();
    }

    public void addDefaultActions() {
    }
}
