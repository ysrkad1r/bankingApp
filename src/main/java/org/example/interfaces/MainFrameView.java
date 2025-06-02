package org.example.interfaces;

import org.example.ui.common.LoginPanel;

public interface MainFrameView {
    void showPanel(String panelName);
    LoginPanel getLoginPanel();
}
