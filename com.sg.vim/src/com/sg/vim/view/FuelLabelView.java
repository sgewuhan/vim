package com.sg.vim.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;

import com.mobnut.commons.util.Utils;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.DataObjectEditor;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.vim.VimUtils;
import com.sg.vim.job.DataAssembly;
import com.sg.vim.model.IVIMFields;

public class FuelLabelView extends GenericPrintabelView {

    private DataObjectEditor editor;

    @Override
    protected String getMessageReprintTitle() {
        return "ȼ�ͱ�ʶ����";
    }

    @Override
    protected String getMessageRemoveTitle() {
        return "ȼ�ͱ�ʶ����";
    }

    @Override
    protected String getMessagePrintTitle() {
        return "ȼ�ͱ�ʶ��ӡ";
    }

    @Override
    protected DBObject remove(List<ObjectId> idList, String memo) {
        return VimUtils.saveRemoveData(idList, memo, IVIMFields.COL_FUELABEL);
    }

    @Override
    protected void print(Browser browser, DBObject dbObject) throws Exception {
        VimUtils.printFuelLabel(browser, dbObject);
    }

    @Override
    protected void reprint(Browser browser, DBObject dbObject) throws Exception {
        VimUtils.printFuelLabel(browser, dbObject);
    }

    public void doUpload() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
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
        try {
            VimUtils.uploadFuelLabel(dataList);
            DBObject setting = VimUtils.saveUploadData(idList, "", IVIMFields.COL_FUELABEL,
                    IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD);

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "ȼ�������ϴ�", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }
    }

    public void doCancel() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        String memo = getMemo("ȼ�ͱ�ʶ����");
        if (Utils.isNullOrEmpty(memo)) {
            return;
        }

        List<String> numberList = new ArrayList<String>();
        List<ObjectId> idList = new ArrayList<ObjectId>();
        List<DBObject> dataList = new ArrayList<DBObject>();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            numberList.add((String) dataItem.get(IVIMFields.F_0_6b));
            idList.add((ObjectId) dataItem.get("_id"));
            dataList.add(dataItem);
        }
        try {
            VimUtils.deleteFuelLabel(numberList, memo);
            DBObject setting = VimUtils.saveCancelData(idList, memo);

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }
            
            getNavigator().getViewer().update(dataList.toArray(), null);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "ȼ�ͱ�ʶ����", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }
    }

    public void doReUpload() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }
        String memo = getMemo("ȼ�ͱ�ʶ����");
        if (Utils.isNullOrEmpty(memo)) {
            return;
        }

        try {

            List<ObjectId> idList = new ArrayList<ObjectId>();
            List<DBObject> dataList = new ArrayList<DBObject>();
            Iterator<?> iter = selection.iterator();
            while (iter.hasNext()) {
                DBObject dataItem = (DBObject) iter.next();
                idList.add((ObjectId) dataItem.get("_id"));
                dataList.add(dataItem);
            }
            VimUtils.uploadFuelLabel2(dataList, memo);
            DBObject setting = VimUtils.saveUploadData(idList, memo, IVIMFields.COL_FUELABEL,
                    IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD2);
            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }

            getNavigator().getViewer().update(dataList.toArray(), null);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "ȼ�ͱ�ʶ����", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }

    }

    public void doReAssembly() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            final ObjectId _id = (ObjectId) dataItem.get("_id");
            // ȡ��vin
            final String vin = (String) dataItem.get(IVIMFields.mVeh_Clsbdh);
            new DataAssembly(vin) {

                @Override
                protected void asyncAssemblyDone(IStatus result, SQLRow mesRawData,
                        DBObject productCodeData, DBObject cocData, DBObject confData,
                        DBObject dpcocData, DBObject dpconfData) {
                    try {
                        DataObjectEditorInput input = VimUtils.getFLInput(cocData, confData,
                                productCodeData, mesRawData, null, vin, _id);
                        input.save();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

        }
    }
    
    public void doEdit() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }
        
        if(editor!=null){
            UIUtils.showMessage(getSite().getShell(), "����ȼ�ͱ�ʶ����",
                    "������ͬʱ���¶���ȼ�ͱ�ʶ���ݡ�", SWT.OK);
        }

        final DBObject data = (DBObject) selection.getFirstElement();

        IEditorSaveHandler saveHandler = new IEditorSaveHandler() {

            private String memo;

            @Override
            public boolean doSaveBefore(DataObjectEditorInput input, IProgressMonitor monitor,
                    String operation) throws Exception {

                int yes = UIUtils.showMessage(getSite().getShell(), "����ȼ�ͱ�ʶ����",
                        "��ϣ��ʹ�õ�ǰ�����ݸ���������ȼ�ͱ�ʶ��������\n�����ϵͳ�����¹���ȼ�ͱ�ʶ�����������ݡ�", SWT.OK | SWT.CANCEL
                                | SWT.ICON_QUESTION);
                if (yes != SWT.OK) {
                    return false;
                }

                memo = input.getData().getText(IVIMFields.mVeh_A_update_memo);
                input.getData().getData().removeField(IVIMFields.mVeh_A_update_memo);
                List<DBObject> certList = new ArrayList<DBObject>();
                certList.add(input.getData().getData());
                VimUtils.updateFuellabel(certList, memo);
                return true;
            }

            @Override
            public boolean doSaveAfter(DataObjectEditorInput input, IProgressMonitor monitor,
                    String operation) throws Exception {
                VimUtils.saveUpdateData((ObjectId) data.get("_id"), memo);
                // data��inputͬ��
                Iterator<String> iter = data.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    if (key.startsWith("Veh_")) {
                        data.put(key, input.getData().getValue(key));
                    }
                }
                getNavigator().getViewer().update(data, null);
                UIUtils.showMessage(getSite().getShell(), "����ȼ�ͱ�ʶ����", "���ύ���¹���ȼ�ͱ�ʶ�����������ݡ�\n�༭�������رա�",
                        SWT.OK | SWT.ICON_INFORMATION);
                editor.close(false);
                editor = null;
                return true;
            }
        };
        try {
            editor = UIUtils.open((ObjectId) data.get("_id"),
                    "com.sg.vim.print.editor.fuellabel_edit", true, false, saveHandler);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "����ȼ�ͱ�ʶ����", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }

    }

}
