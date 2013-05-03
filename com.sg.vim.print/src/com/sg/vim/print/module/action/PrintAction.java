package com.sg.vim.print.module.action;

import org.eclipse.swt.graphics.Image;

import com.sg.ui.ImageResource;
import com.sg.ui.UI;

public abstract class PrintAction implements ModuleAction {

    @Override
    public Image getImage() {
        return UI.getImage(ImageResource.PRINT_32);
    }

    @Override
    public String getText() {
        return "¥Ú”°";
    }


}
