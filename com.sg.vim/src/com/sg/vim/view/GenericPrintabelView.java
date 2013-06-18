package com.sg.vim.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

import com.mobnut.commons.util.Utils;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.part.view.TableNavigator;


public abstract class GenericPrintabelView extends TableNavigator {

    private Browser browser;

    public GenericPrintabelView() {
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
            reprint(browser, dbObject);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), getMessageReprintTitle(), e.getMessage(),
                    SWT.ICON_ERROR | SWT.OK);
        }
    }

    protected abstract void reprint(Browser browser, DBObject dbObject) throws Exception;

    protected abstract String getMessageReprintTitle();

    protected abstract String getMessageRemoveTitle();

    protected abstract String getMessagePrintTitle();

    protected abstract DBObject remove(List<ObjectId> idList, String memo);

    protected abstract void print(Browser browser, DBObject dbObject) throws Exception;

    public void doRemove(DBObject coc) {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        String memo = getMemo(getMessageRemoveTitle());
        if (Utils.isNullOrEmpty(memo)) {
            return;
        }

        List<ObjectId> idList = new ArrayList<ObjectId>();
        List<DBObject> dataList = new ArrayList<DBObject>();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            idList.add((ObjectId) dataItem.get("_id"));
            dataList.add(dataItem);
        }

        DBObject set = remove(idList, memo);
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).putAll(set);
            getNavigator().getViewer().update(dataList.get(i), null);
        }
    }

    protected String getMemo(String title) {
        IInputValidator validator = new IInputValidator() {

            @Override
            public String isValid(String newText) {
                if (Utils.isNullOrEmpty(newText)) {
                    return "您必须输入原因";
                }
                return null;
            }
        };
        InputDialog d = new InputDialog(getSite().getShell(), title, "请输入原因", "", validator);
        if (InputDialog.OK != d.open()) {
            return null;
        }
        String memo = d.getValue();
        return memo;
    }

    public void doPrint(Object[] dboArray) {
        for (int i = 0; i < dboArray.length; i++) {
            try {
                print(browser, (DBObject) dboArray[i]);
                getNavigator().getViewer().update((DBObject) dboArray[i], null);

            } catch (Exception e) {
                UIUtils.showMessage(getSite().getShell(), getMessagePrintTitle(), e.getMessage(),
                        SWT.ICON_ERROR | SWT.OK);
            }
        }
    }
    

}
