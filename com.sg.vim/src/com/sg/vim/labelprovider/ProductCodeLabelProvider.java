package com.sg.vim.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mobnut.commons.util.Utils;
import com.mobnut.commons.util.file.FileUtil;
import com.mongodb.DBObject;
import com.sg.vim.Vim;
import com.sg.vim.model.IVIMFields;

public class ProductCodeLabelProvider extends ColumnLabelProvider {

    public ProductCodeLabelProvider() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        DBObject data = (DBObject) element;
        Object productCode = data.get(IVIMFields.E_02);
        Object productName = data.get(IVIMFields.E_03);
        Object modelCode = data.get(IVIMFields.F_0_2C1);
        // Object cocinfo_name = data.get(IVIMFields.COC_NAME);
        Object cocinfo_id = data.get(IVIMFields.COC_ID);
        Object cocinfo_id2 = data.get(IVIMFields.COC_ID2);
        Object c_12 = data.get(IVIMFields.C_12);
        // Object cfginfo_name = data.get(IVIMFields.CFG_NAME);
        Object cfginfo_id = data.get(IVIMFields.CFG_ID);
        String tag1 = cocinfo_id == null ? "δָ��    " : "��    ";
        String tag1_1 = cocinfo_id2 == null ? "δָ��    " : "��    ";
        String tag2 = cfginfo_id == null ? "δָ��    " : "��    ";
        boolean needDP = !Utils.isNullOrEmptyString(c_12);
        
        
        
        // ��Ƭ����
        Object f_c6 = data.get(IVIMFields.F_C6);

        StringBuilder builder = new StringBuilder();

        builder.append("<span style='float:left;padding:0px 4px 4px 4px;FONT-FAMILY:΢���ź�;font-size:11pt'><b>");

        builder.append(productCode);
        builder.append(" [");
        builder.append(modelCode);
        builder.append(" ]");
        
        if(isFinished(data)){
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("checked_16.png", Vim.PLUGIN_ID,
                    "image"));
            builder.append("\"  width='16' height='16' style='padding-right:4px;padding-top:4px;'/>");
        }
        
        builder.append("</b><br/>");
        builder.append(productName);
        builder.append("<br/><small>");
        if (cocinfo_id == null) {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("unlink1_1216.png", Vim.PLUGIN_ID,
                    "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" ����: ");
            builder.append(tag1);
        } else {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("link1_1216.png", Vim.PLUGIN_ID,
                    "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" ����: ");
            builder.append("<a href=\"com.sg.vim.editor.cocinfo@" + cocinfo_id
                    + "\" target=\"_rwt\">" + tag1 + "</a>");
        }
        if (needDP) {
            if (cocinfo_id2 == null) {
                builder.append("<img src=\"");
                builder.append(FileUtil.getImageURL("unlink1_1216.png", Vim.PLUGIN_ID,
                        "image"));
                builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
                builder.append(" ����: ");
                builder.append(tag1_1);
            } else {
                builder.append("<img src=\"");
                builder.append(FileUtil.getImageURL("link1_1216.png", Vim.PLUGIN_ID,
                        "image"));
                builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
                builder.append(" ����: ");
                builder.append("<a href=\"com.sg.vim.editor.cocinfo@" + cocinfo_id2
                        + "\" target=\"_rwt\">" + tag1_1 + "</a>");
            }
        }

        if (cfginfo_id == null) {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("unlink2_1216.png", Vim.PLUGIN_ID,
                    "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" ����: ");
            builder.append(tag2);
        } else {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("link2_1216.png", Vim.PLUGIN_ID,
                    "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" ����: ");
            builder.append("<a href=\"com.sg.vim.editor.configcode@" + cfginfo_id
                    + "\" target=\"_rwt\">" + tag2 + "</a>");
        }

        if (!Utils.isNullOrEmptyString(f_c6)) {
            builder.append(" ��Ƭ����: ");
            builder.append(f_c6);
        } else {
            builder.append(" ��Ƭ����: ");
            builder.append("δ�趨");
        }
        builder.append("</small></span>");

        return builder.toString();

    }

    private boolean isFinished(DBObject data) {
        Object cocinfo_id = data.get(IVIMFields.COC_ID);
        Object cocinfo_id2 = data.get(IVIMFields.COC_ID2);
        Object c_12 = data.get(IVIMFields.C_12);
        Object cfginfo_id = data.get(IVIMFields.CFG_ID);
        Object f_c6 = data.get(IVIMFields.F_C6);

        if(cocinfo_id==null||cfginfo_id==null||Utils.isNullOrEmptyString(f_c6)){
            return false;
        }else{
            if(c_12!=null&&cocinfo_id2==null){
                return false;
            }
        }
        return true;
    }

}
