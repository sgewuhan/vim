package com.sg.vim.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import com.sg.vim.print.control.PrintContent;

public class PrintView extends ViewPart {

    private PrintContent content;

    public PrintView() {
    }

    @Override
    public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit( parent.getDisplay() );
        ScrolledForm form = toolkit.createScrolledForm( parent );
        ManagedForm mform = new ManagedForm(toolkit, form);
        Composite body = form.getBody();
        body.setLayout(new FillLayout());
        content = new PrintContent(mform,body,SWT.NONE);
    }

    @Override
    public void setFocus() {
        content.setFocus();
    }

}
