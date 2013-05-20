package com.sg.vim.function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.mobnut.admin.functions.UserLabelProvider;
import com.mobnut.portal.Portal;
import com.mobnut.portal.db.Account;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ScenarioSettingEditor extends EditorPart implements ISelectionChangedListener {

    private TableViewer userTable;
    private Account service;
    private Button[] buttons;

    public ScenarioSettingEditor() {
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
    }

    @Override
    public void doSaveAs() {

    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        setSite(site);
        setInput(input);
        service = new Account();
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void createPartControl(Composite parent) {
        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        createUserList(sashForm);
        createScenariosList(sashForm);
    }

    private void createScenariosList(SashForm sashForm) {
        Composite panel = new Composite(sashForm, SWT.BORDER);
        panel.setLayout(new RowLayout(SWT.VERTICAL));

        Label l = new Label(panel, SWT.NONE);
        l.setText("选择用户的可用场景");
        // 读取系统所有的场景
        List<String[]> defs = Portal.getDefault().getScenariosDefinitionList();
        buttons = new Button[defs.size()];
        for (int i = 0; i < defs.size(); i++) {
            Button button = new Button(panel, SWT.CHECK);
            button.setData(defs.get(i)[0]);
            button.setText(defs.get(i)[1]);
            button.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    ISelection sel = userTable.getSelection();
                    if (sel instanceof IStructuredSelection) {
                        DBObject userData = (DBObject) ((IStructuredSelection) sel)
                                .getFirstElement();
                        setUserScenario(userData);
                    }
                }

            });
            buttons[i] = button;
        }
    }

    private void createUserList(SashForm sashForm) {
        userTable = new TableViewer(sashForm, SWT.BORDER | SWT.VIRTUAL);
        userTable.getTable().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        userTable.getTable().setData(RWT.CUSTOM_ITEM_HEIGHT, new Integer(64));
        userTable.setContentProvider(ArrayContentProvider.getInstance());

        TableViewerColumn col = new TableViewerColumn(userTable, SWT.LEFT);
        col.getColumn().setText("userid");
        col.getColumn().setWidth(120);
        col.setLabelProvider(new UserLabelProvider());

        // 查询用户列表
        try {
            DBCursor cur = service.find(null, null);
            List<DBObject> userlist = cur.toArray();
            userTable.setInput(userlist);
        } catch (Exception e) {
        }
        col.getColumn().pack();
        userTable.addSelectionChangedListener(this);
    }

    @Override
    public void setFocus() {

    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection sel = (IStructuredSelection) event.getSelection();
        if (sel == null) {
            select(null);
        }
        DBObject userData = (DBObject) sel.getFirstElement();
        select(userData);
    }

    private void select(DBObject userData) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setSelection(false);
        }

        Object s = userData.get(Account.FIELD_SCENARIO);
        if (s instanceof List) {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            Iterator<Object> iter = ((List) s).iterator();
            while (iter.hasNext()) {
                String pid = (String) iter.next();
                for (int i = 0; i < buttons.length; i++) {
                    Object id = buttons[i].getData();
                    if (pid.equals(id)) {
                        buttons[i].setSelection(true);
                    }
                }
            }
        }
    }

    protected void setUserScenario(DBObject userData) {
        List<String> scenarios = new ArrayList<String>();
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getSelection()) {
                String fid = (String) buttons[i].getData();
                scenarios.add(fid);
            }
        }

        try {
            service.update((ObjectId) userData.get(Account.FIELD_SYSID),
                    new BasicDBObject().append(Account.FIELD_SCENARIO, scenarios));
            userData.put(Account.FIELD_SCENARIO, scenarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
