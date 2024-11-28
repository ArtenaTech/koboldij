package com.artena.llm.ide.koboldij;

import com.artena.llm.ide.config.AppSettings;
import com.artena.llm.ide.config.AppSettings.State;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import icons.PluginIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DynamicActionGroup extends ActionGroup {
    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        State state = AppSettings.getInstance().getState();
        int count = State.COUNT_CONFIG;
        List<AnAction> actions = new ArrayList<>();
        for (int i = 1; i < count; i++) {
            String name = "Template Query " + i;
            if (state != null) {
                String storedName = state.templateName[i];
                if (storedName != null && !storedName.isEmpty()) {
                    name = storedName;
                }
            }
            TemplateRequestAction action = new TemplateRequestAction("Template", "Generate code by LLM query", PluginIcons.popup_icon);
            actions.add(action);
        }
        return actions.toArray(new AnAction[0]);
    }
}
