package com.sg.vim.print.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.part.view.TableNavigator;
import com.sg.vim.datamodel.util.VimUtils;

public class COCPaperView extends TableNavigator {

    private Browser browser;

    public COCPaperView() {
    }

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FormLayout());
        createBrowser(parent);
        super.createPartControl(parent);
    }

    private void createBrowser(Composite parent) {
        browser = new Browser(parent, SWT.NONE);
        FormData fd = new FormData();
        browser.setLayoutData(fd);
        fd.top = new FormAttachment(0, 0);
        fd.left = new FormAttachment(0, 0);
        fd.width = 1;
        fd.height = 1;
    }

    public void doRePrint(DBObject dbObject) {
        try {
            VimUtils.printCOC(browser, dbObject);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "≤π¥ÚCOC÷§ È", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }
    }

}
