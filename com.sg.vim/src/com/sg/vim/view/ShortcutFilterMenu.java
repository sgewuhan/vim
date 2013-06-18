package com.sg.vim.view;

import org.eclipse.jface.action.MenuManager;

import com.sg.ui.part.Navigator;
import com.sg.ui.viewer.table.CTableViewer;

public class ShortcutFilterMenu extends MenuManager {

    private Navigator navigator;

    public ShortcutFilterMenu(Navigator navigator) {
        super();
        this.navigator = navigator;
        createMenu();
    }

    private void createMenu() {
        add(new QueryUploadAction(navigator));
        add(new QueryReUploadAction(navigator));
    }

}
