package com.artena.llm.ide.koboldij;

import com.artena.llm.kobold.KoboldAiChatModel;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.WriteActionAware;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.DumbProgressIndicator;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public abstract class BaseLLMAction extends AnAction implements WriteActionAware {

    protected static KoboldAiChatModel model;

    public BaseLLMAction() {
        if (model == null) {
            model = KoboldAiChatModel.builder().build();
        }
    }

    public BaseLLMAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
        if (model == null) {
            model = KoboldAiChatModel.builder().build();
        }
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        final Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = event.getRequiredData(CommonDataKeys.PROJECT);
        final Document document = editor.getDocument();
        final PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
        final Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        int start = primaryCaret.getSelectionStart();
        int end = primaryCaret.getSelectionEnd();
        execute(psiFile, document, primaryCaret, start, end);
        primaryCaret.removeSelection();
    }

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Project project = event.getProject();
        final Editor editor = event.getData(CommonDataKeys.EDITOR);
        event.getPresentation().setEnabledAndVisible(
                project != null && editor != null && editor.getCaretModel().getCaretCount() > 0
        );
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    abstract void execute(PsiFile psiFile, Document document, Caret primaryCaret, int start, int end);

    protected String generateLLMQuery(Document document, Caret primaryCaret, int start, int end) {
        getBackGroundTask(null, document, start, end).run(new DumbProgressIndicator());
        return "";
    }

    protected Task.Backgroundable getBackGroundTask(String llmQuery, Document document, int start, int end) {
        return new Task.Backgroundable(null, "LLM Request processing",
                false) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setText("LLM Request processing...");
                indicator.setFraction(0.0);
                llmQueryAction(llmQuery, document, start, end);
                indicator.setFraction(1.0);
            }
        };
    }

    /**
     * Executing llm query
     */
    protected void llmQueryAction(String llmQuery, Document document, int start, int end) {
        model.generate(llmQuery);
    }
}
