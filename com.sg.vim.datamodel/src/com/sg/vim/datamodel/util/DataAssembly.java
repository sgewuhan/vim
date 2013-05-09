package com.sg.vim.datamodel.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.swt.widgets.Display;

import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.vim.datamodel.DataModelActivator;
import com.sg.vim.datamodel.IVIMFields;

public abstract class DataAssembly extends Job {

    private String vin;
    private SQLRow mesRawData;
    private DBObject productCodeData;
    private DBObject cocData;
    private DBObject confData;
    private DBObject dpcocData;
    private DBObject dpconfData;

    public DataAssembly(String vin) {
        super("��װ�ϸ�֤����VIN" + vin);
        this.vin = vin;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        // ��ѯ���ݿ��Ƿ��ж�Ӧvin�ĳ�Ʒ���¼
        String message = "���ڲ�ѯ�ⲿ���ݿ��е�ƥ��VIN�ĳ�Ʒ��¼";
        try {
            mesRawData = VimUtils.getProductCode(vin);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }
        Object productCode = mesRawData.getValue(VimUtils.FIELD_PRODUCT_CODE);
        if (!(productCode instanceof String)) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, "��ѯMES��Ʒ��¼,MES���ݿ�ĳ�Ʒ�벻���ַ�������");
        }

        // ��ȡ��Ʒ���Ӧ��Ʒ������ LNBMDLAA6CU000289
        // ���泵�� productcodeinfo.f_0_2c
        message = "���ڲ�ѯVIM���ݿ��еĳ�Ʒ���¼";
        try {
            productCodeData = VimUtils.getProductCodeInfo((String) productCode);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }

        // ��ѯCOC������
        message = "���ڲ�ѯ��Ʒ�복��һ������Ϣ";
        try {
            cocData = VimUtils.getCOCInfo(productCodeData);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }
        // ��ѯ��������
        message = "���ڲ�ѯ��Ʒ�복��������Ϣ";
        try {
            confData = VimUtils.getConfInfo(productCodeData);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }
        // ��������й�����
        // �ж����޵���ID
        /**
         * ����VINʱ���е����ͺź͵���ID��Ϊ�գ����ȴ�ӡ���̺ϸ�֤�����ϵͳ������̨���ĵ��̺ϸ�֤�Ͳ��ô�ӡ���̺ϸ�֤�� ��ֱ�Ӵ�ӡ�����ϸ�֤��
         * ����Ѵ��ڴ˳��������ϸ�֤�Ͳ��ô�ӡ������ӡ�˵��̺ϸ�֤���ٴ�ӡ�����ϸ�֤ʱ���������ϸ�֤λ�ã� ��ӡ�������ϸ�֤��ź͵��̺ϸ�֤��š�
         */
        Object dpId = cocData.get(IVIMFields.C_12);
        if ((dpId instanceof String) && (dpId.toString().length() > 0)) {
            message = "���ڲ�ѯ��Ʒ�������Ϣ";
            // �ҵ���Ӧ�ĵ�����Ϣ
            try {
                dpcocData = VimUtils.getCOCInfoById((String) dpId);
                dpconfData = VimUtils.getConfInfoById((String) dpId);
            } catch (Exception e) {
                return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
            }

        }

        return Status.OK_STATUS;
    }

    public void start(final Display display) {
        addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                final IStatus result = event.getJob().getResult();
                display.asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        asyncAssemblyDone(result,mesRawData,productCodeData,cocData,confData,dpcocData,dpconfData);
                    }


                });

            }

        });
        setUser(true);
        schedule();        
    }
    
    protected abstract void asyncAssemblyDone(IStatus result, SQLRow mesRawData, DBObject productCodeData, DBObject cocData, DBObject confData, DBObject dpcocData, DBObject dpconfData);
}
