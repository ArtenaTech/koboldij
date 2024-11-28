package com.artena.llm.ide.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

@State(
        name = "com.artena.llm.ide.config.AppSettings",
        storages = @Storage("KoboldIJPlugin.xml")
)
public final class AppSettings implements PersistentStateComponent<AppSettings.State> {

    private State myState = new State();

    public static AppSettings getInstance() {
        return ApplicationManager.getApplication()
                .getService(AppSettings.class);
    }

    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }

    public static class State {

        public static final int COUNT_CONFIG = 5;

        // State of Plugin
        @NonNls
        public String apiUrl = "http://localhost:5001/api/extra/generate/stream";

        public boolean apiExtra = true;

        public String promptMemory = "";

        public String promptDescription = "";

        public String stopSequence = "You: ,\nYou ,\nKoboldAI: ";

        public int maxContextLength = 8192;

        public int maxLength = 1024;

        // State of Query window
        public String[] templateName = new String[COUNT_CONFIG];

        public String[] prompts = new String[COUNT_CONFIG];

        public int[] queryType = new int[COUNT_CONFIG];

        public int[] replaceType = new int[COUNT_CONFIG];

        public int[] contextType = new int[COUNT_CONFIG];

        public int[] promptType = new int[COUNT_CONFIG];

        public String[] llMRequest = new String[COUNT_CONFIG];

        public String[] parameters = new String[COUNT_CONFIG];
    }

}
