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

package me.n1ar4.jar.analyzer.plugins.listener;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import me.n1ar4.jar.analyzer.gui.MainForm;
import me.n1ar4.jar.analyzer.plugins.repeater.SocketUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ListenUtilForm {
    public JPanel listenUtilPanel;
    private JTextField portText;
    private JButton listenButton;
    private JTextArea terminalArea;
    private JScrollPane terminalScroll;
    private JButton sendButton;
    private JTextField sendText;
    private JLabel portLabel;
    private JPanel centerPanel;
    private JPanel sendPanel;

    private static Thread t;
    private static boolean isRunning = false;

    private void initLang() {
        portLabel.setText("Port");
        listenButton.setText("Start Listen");
        sendButton.setText("Send");
    }

    public ListenUtilForm() {

        initLang();

        listenButton.addActionListener(e -> {
            if (isRunning) {
                isRunning = false;
                t.interrupt();
                t = null;
                listenButton.setText("Start Listen");
                SocketUtil.area.setText(null);
            } else {
                String portStr = portText.getText().trim();
                int port = Integer.parseInt(portStr);
                t = new Thread(() -> SocketUtil.serve(port, terminalArea));
                t.start();
                isRunning = true;
                listenButton.setText("Stop Listen");
            }
        });
        sendButton.addActionListener(e -> SocketUtil.sendServe(sendText.getText()));
    }

    public static void start() {
        JFrame frame = new JFrame("Jar Analyzer - Listener");
        frame.setContentPane(new ListenUtilForm().listenUtilPanel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(MainForm.getInstance().getMasterPanel());
        frame.pack();
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
        listenUtilPanel = new JPanel();
        listenUtilPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        listenUtilPanel.setBackground(new Color(-1120293));
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.setBackground(new Color(-1120293));
        listenUtilPanel.add(centerPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        portLabel = new JLabel();
        portLabel.setText("监听端口");
        centerPanel.add(portLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portText = new JTextField();
        centerPanel.add(portText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        listenButton = new JButton();
        listenButton.setText("开始监听端口");
        centerPanel.add(listenButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        terminalScroll = new JScrollPane();
        terminalScroll.setBackground(new Color(-1120293));
        terminalScroll.setForeground(new Color(-12828863));
        listenUtilPanel.add(terminalScroll, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(500, 300), new Dimension(500, 300), new Dimension(500, 300), 0, false));
        terminalScroll.setBorder(BorderFactory.createTitledBorder(null, "terminal", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        terminalArea = new JTextArea();
        terminalArea.setBackground(new Color(-12828863));
        terminalArea.setEditable(true);
        terminalArea.setEnabled(true);
        terminalArea.setForeground(new Color(-16711895));
        terminalScroll.setViewportView(terminalArea);
        sendPanel = new JPanel();
        sendPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        sendPanel.setBackground(new Color(-1120293));
        listenUtilPanel.add(sendPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sendButton = new JButton();
        sendButton.setText("发送");
        sendPanel.add(sendButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sendText = new JTextField();
        sendPanel.add(sendText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return listenUtilPanel;
    }

}
