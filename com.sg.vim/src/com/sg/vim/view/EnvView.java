package com.sg.vim.view;

import org.eclipse.jface.viewers.IStructuredSelection;

import com.sg.ui.part.view.TableNavigator;
import com.sg.vim.VimUtils;

public class EnvView extends TableNavigator {

    public EnvView() {
    }

    public void doUpload(IStructuredSelection sel) {
        try {
            VimUtils.uploadEnv(sel.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
