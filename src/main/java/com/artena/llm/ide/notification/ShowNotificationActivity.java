package com.artena.llm.ide.notification;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShowNotificationActivity implements ProjectActivity {

    public static void showSimpleMessage(Project project, String title, String msg, NotificationDisplayType displayType) {
        NotificationConfigBuilder builder = NotificationConfigBuilder.create(project);
        builder.setNotificationType(NotificationType.INFORMATION);
        builder.setDisplayType(displayType);
        builder.setTitle(title);
        builder.setContent("<html>" + msg + "</html>");
        builder.build().notify(project);
    }

    @Override
    public @Nullable Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        return null;
    }
}
