package com.artena.llm.ide.koboldij;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;


public class SimpleRequestAction extends BaseLLMAction {

    public void update(@NotNull final AnActionEvent event) {
        final Project project = event.getProject();
        final Editor editor = event.getData(CommonDataKeys.EDITOR);
        event.getPresentation().setEnabledAndVisible(
                project != null && editor != null && editor.getSelectionModel().hasSelection()
        );
    }

    @Override
    void execute(PsiFile psiFile, Document document, Caret primaryCaret, int start, int end) {
        String llmQuery = generateLLMQuery(document, primaryCaret, start, end);
        if (llmQuery.trim().length() > 1) {
            Task.Backgroundable llmTask = getBackGroundTask(llmQuery, document, start, end);
            ProgressManager.getInstance().run(llmTask);
        }
    }
}
