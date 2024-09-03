/*
 * MIT License
 *
 * Copyright (c) 2023-2024 4ra1n (Jar Analyzer Team)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.n1ar4.jar.analyzer.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import me.n1ar4.jar.analyzer.engine.DecompileEngine;
import me.n1ar4.jar.analyzer.starter.Const;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExportForm {
    private JPanel masterPanel;
    private JTextField outputDirText;
    private JLabel outputDirLabel;
    private JRadioButton fernRadio;
    private JLabel engineLabel;
    private JTextField jarsText;
    private JButton startBtn;
    private JLabel actionLabel;
    private JLabel jarLabel;

    private static volatile boolean isRunning = false;

    public ExportForm() {
        fernRadio.setEnabled(false);
        fernRadio.setSelected(true);
        outputDirText.setText("jar-analyzer-export");
        ArrayList<String> path = MainForm.getEngine().getJarsPath();
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s);
            sb.append(" ");
        }
        jarsText.setText(sb.toString());
        startBtn.addActionListener(e -> {
            if (outputDirText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(masterPanel, "please enter the output directory");
                return;
            }
            if (isRunning) {
                JOptionPane.showMessageDialog(masterPanel, "decompile is running...");
                return;
            }
            new Thread(() -> {
                isRunning = true;
                boolean success = DecompileEngine.decompileJars(path, outputDirText.getText());
                if (success) {
                    JOptionPane.showMessageDialog(masterPanel, "jars decompiled successfully");
                    isRunning = false;
                }
            }).start();
            JOptionPane.showMessageDialog(masterPanel, "decompiling please wait...");
        });
    }

    public static void start() {
        JFrame frame = new JFrame(Const.ExportForm);
        frame.setContentPane(new ExportForm().masterPanel);
        frame.pack();
        frame.setAlwaysOnTop(false);
        frame.setLocationRelativeTo(MainForm.getInstance().getMasterPanel());
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayoutManager(5, 2, new Insets(5, 5, 5, 5), -1, -1));
        outputDirLabel = new JLabel();
        outputDirLabel.setText("OUTPUT DIR");
        masterPanel.add(outputDirLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final Spacer spacer1 = new Spacer();
        masterPanel.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        outputDirText = new JTextField();
        masterPanel.add(outputDirText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        engineLabel = new JLabel();
        engineLabel.setText("ENGINE");
        masterPanel.add(engineLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        fernRadio = new JRadioButton();
        fernRadio.setText(" FernFlower (from jetbrains/intellij-community)");
        masterPanel.add(fernRadio, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actionLabel = new JLabel();
        actionLabel.setText("ACTION");
        masterPanel.add(actionLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        jarLabel = new JLabel();
        jarLabel.setText("DECOMPILE JAR");
        masterPanel.add(jarLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        jarsText = new JTextField();
        masterPanel.add(jarsText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, -1), new Dimension(150, -1), null, 0, false));
        startBtn = new JButton();
        startBtn.setText("START EXPORT");
        masterPanel.add(startBtn, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return masterPanel;
    }

}
