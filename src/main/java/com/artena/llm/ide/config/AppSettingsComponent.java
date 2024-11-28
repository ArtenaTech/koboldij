package com.artena.llm.ide.config;

import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.*;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBDimension;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class AppSettingsComponent {

    private final JPanel pRoot;

    private final JBTextField apiUrl = new JBTextField();

    private final JBTextArea promptMemory = new JBTextArea();

    private final JBTextArea promptDescription = new JBTextArea();

    private final JBTextField stopSequence = new JBTextField();

    private final JBTextField maxContextLength = new JBTextField();

    private final JBTextField maxLength = new JBTextField();

    private final JBCheckBox apiType = new JBCheckBox("Extra API");

    public AppSettingsComponent() {
        apiType.setEnabled(false);

        JBScrollPane promptScroll = new JBScrollPane(promptMemory);
        promptScroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        promptScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        promptScroll.setMaximumSize(new JBDimension(-1, 100));
        promptScroll.setPreferredSize(new JBDimension(-1, 100));
        promptScroll.setMaximumSize(new JBDimension(-1, 150));
        promptScroll.setBorder(IdeBorderFactory.createBorder());

        JBScrollPane descriptionScroll = new JBScrollPane(promptDescription);
        descriptionScroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScroll.setMaximumSize(new JBDimension(-1, 50));
        descriptionScroll.setPreferredSize(new JBDimension(-1, 50));
        descriptionScroll.setMaximumSize(new JBDimension(-1, 100));

        pRoot = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("URL:"), apiUrl, 1, false)
                .addComponent(apiType, 1)
                .addLabeledComponent(new JBLabel("Memory:"), promptScroll, 1, false)
                .addLabeledComponent(new JBLabel("Prompt:"), descriptionScroll, 1, false)
                .addLabeledComponent(new JBLabel("StopWord:"), stopSequence, 1, false)
                .addLabeledComponent(new JBLabel("ContextLength:"), maxContextLength, 1, false)
                .addLabeledComponent(new JBLabel("Length:"), maxLength, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return pRoot;
    }

    public JComponent getPreferredFocusedComponent() {
        return promptMemory;
    }

    @NotNull
    public String getApiUrl() {
        return apiUrl.getText();
    }

    public void setApiUrl(@NotNull String url) {
        apiUrl.setText(url);
    }

    public String getPromptMemory() {
        return promptMemory.getText();
    }

    public void setPromptMemory(@NotNull String prompt) {
        promptMemory.setText(prompt);
    }

    public String getPromptDescription() {
        return promptDescription.getText();
    }

    public void setPromptDescription(@NotNull String description) {
        promptDescription.setText(description);
    }

    public String getStopSequence() {
        return stopSequence.getText();
    }

    public void setStopSequence(@NotNull String sequence) {
        stopSequence.setText(sequence);
    }

    public String getMaxContextLength() {
        return maxContextLength.getText();
    }

    public void setMaxContextLength(@NotNull String maxLength) {
        maxContextLength.setText(maxLength);
    }

    public String getMaxLength() {
        return maxLength.getText();
    }

    public void setMaxLength(@NotNull String length) {
        maxLength.setText(length);
    }

    public boolean isExtraApi() {
        return apiType.isSelected();
    }

    public void setExtraApi(boolean extraApi) {
        apiType.setSelected(extraApi);
    }

}
