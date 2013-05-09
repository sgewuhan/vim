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
        super("组装合格证数据VIN" + vin);
        this.vin = vin;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        // 查询数据库是否有对应vin的成品码记录
        String message = "正在查询外部数据库中的匹配VIN的成品记录";
        try {
            mesRawData = VimUtils.getProductCode(vin);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }
        Object productCode = mesRawData.getValue(VimUtils.FIELD_PRODUCT_CODE);
        if (!(productCode instanceof String)) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, "查询MES成品记录,MES数据库的成品码不是字符串类型");
        }

        // 获取成品码对应成品码数据 LNBMDLAA6CU000289
        // 公告车型 productcodeinfo.f_0_2c
        message = "正在查询VIM数据库中的成品码记录";
        try {
            productCodeData = VimUtils.getProductCodeInfo((String) productCode);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }

        // 查询COC绑定数据
        message = "正在查询成品码车型一致性信息";
        try {
            cocData = VimUtils.getCOCInfo(productCodeData);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }
        // 查询配置数据
        message = "正在查询成品码车型配置信息";
        try {
            confData = VimUtils.getConfInfo(productCodeData);
        } catch (Exception e) {
            return new Status(Status.ERROR, DataModelActivator.PLUGIN_ID, message, e);
        }
        // 处理底盘有关数据
        // 判断有无底盘ID
        /**
         * 输入VIN时，有底盘型号和底盘ID不为空，而先打印底盘合格证。如果系统中有这台车的底盘合格证就不用打印底盘合格证， 就直接打印整车合格证，
         * 如果已存在此车的整车合格证就不用打印，。打印了底盘合格证后，再打印整车合格证时，在整车合格证位置， 打印出整车合格证编号和底盘合格证编号。
         */
        Object dpId = cocData.get(IVIMFields.C_12);
        if ((dpId instanceof String) && (dpId.toString().length() > 0)) {
            message = "正在查询成品码底盘信息";
            // 找到对应的底盘信息
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
