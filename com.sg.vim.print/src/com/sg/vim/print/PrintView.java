package com.sg.vim.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.sg.vim.print.control.PrintContent;

public class PrintView extends ViewPart {

    private PrintContent content;

    public PrintView() {
    }

    @Override
    public void createPartControl(Composite parent) {
        content = new PrintContent(parent,SWT.NONE);
        
        
    }

    @Override
    public void setFocus() {
        content.setFocus();
    }

}
