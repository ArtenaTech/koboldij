package com.artena.llm.ide.koboldij;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.psi.PsiFile;

public class CustomRequestAction extends BaseLLMAction {
    @Override
    void execute(PsiFile psiFile, Document document, Caret primaryCaret, int start, int end) {
        String llmQuery = generateLLMQuery(document, primaryCaret, start, end);
        if (llmQuery.trim().length() > 1) {
            Task.Backgroundable llmTask = getBackGroundTask(llmQuery, document, start, end);
            ProgressManager.getInstance().run(llmTask);
        }
    }
}
