package com.artena.llm.ide.config;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

final class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent settingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "KoboldIJ Global Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new AppSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        if (settingsComponent.getApiUrl().equals(state.apiUrl) &&
                settingsComponent.getPromptMemory().equals(state.promptMemory) &&
                settingsComponent.getPromptDescription().equals(state.promptDescription) &&
                settingsComponent.getStopSequence().equals(state.stopSequence)) {
            settingsComponent.getMaxContextLength();
        }
        return true;
    }

    @Override
    public void apply() {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        state.apiUrl = settingsComponent.getApiUrl();

        state.promptMemory = settingsComponent.getPromptMemory();
        state.promptDescription = settingsComponent.getPromptDescription();
        state.stopSequence = settingsComponent.getStopSequence();
        try {
            state.maxContextLength = Integer.parseInt(settingsComponent.getMaxContextLength());
        } catch (NumberFormatException exc) {
            settingsComponent.setMaxContextLength("8192");
        }

        try {
            state.maxLength = Integer.parseInt(settingsComponent.getMaxLength());
        } catch (NumberFormatException exc) {
            settingsComponent.setMaxLength("512");
        }

        state.apiExtra = settingsComponent.isExtraApi();
    }

    @Override
    public void reset() {
        AppSettings.State state =
                Objects.requireNonNull(AppSettings.getInstance().getState());
        settingsComponent.setApiUrl(state.apiUrl);
        settingsComponent.setPromptMemory(state.promptMemory);
        settingsComponent.setPromptDescription(state.promptDescription);
        settingsComponent.setStopSequence(state.stopSequence);
        settingsComponent.setMaxContextLength(state.maxContextLength + "");
        settingsComponent.setMaxLength(state.maxLength + "");
        settingsComponent.setExtraApi(state.apiExtra);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

}
