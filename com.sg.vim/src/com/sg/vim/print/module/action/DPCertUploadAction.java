package com.sg.vim.print.module.action;

import org.eclipse.swt.graphics.Image;

import com.sg.ui.ImageResource;
import com.sg.ui.UI;

public class DPCertUploadAction implements ModuleAction {

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    @Override
    public Image getImage() {
        return UI.getImage(ImageResource.UPLOAD_32);
    }

    @Override
    public String getText() {
        return "ÉÏ´«";
    }

}
