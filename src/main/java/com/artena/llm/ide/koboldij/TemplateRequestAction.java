package com.artena.llm.ide.koboldij;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateRequestAction extends BaseLLMAction {

    public TemplateRequestAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    void execute(PsiFile psiFile, Document document, Caret caret, int start, int end) {
        String llmQuery = generateLLMQuery(document, caret, start, end);
        if (llmQuery.trim().length() > 1) {
            Task.Backgroundable llmTask = getBackGroundTask(llmQuery, document, start, end);
            ProgressManager.getInstance().run(llmTask);
        }
    }
}
