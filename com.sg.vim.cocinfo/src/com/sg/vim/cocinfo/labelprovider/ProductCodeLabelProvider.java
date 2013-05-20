package com.sg.vim.cocinfo.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mobnut.commons.util.Utils;
import com.mobnut.commons.util.file.FileUtil;
import com.mongodb.DBObject;
import com.sg.vim.cocinfo.COCInfoActivator;
import com.sg.vim.datamodel.IVIMFields;

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
//        Object cocinfo_name = data.get(IVIMFields.COC_NAME);
        Object cocinfo_id = data.get(IVIMFields.COC_ID);
        Object cocinfo_id2 = data.get(IVIMFields.COC_ID2);
//        Object cfginfo_name = data.get(IVIMFields.CFG_NAME);
        Object cfginfo_id = data.get(IVIMFields.CFG_ID);
        String tag1 = cocinfo_id == null ? "Î´Ö¸¶¨    " : "´ò¿ª    ";
        String tag1_1 = cocinfo_id2 == null ? "Î´Ö¸¶¨    " : "´ò¿ª    ";
        String tag2 = cfginfo_id == null ? "Î´Ö¸¶¨    " : "´ò¿ª    ";
        
        //¸ÖÆ¬µ¯»É
        Object f_c6 = data.get(IVIMFields.F_C6);

        StringBuilder builder = new StringBuilder();

        builder.append("<span style='float:left;padding:0px 4px 4px 4px;FONT-FAMILY:Î¢ÈíÑÅºÚ;font-size:11pt'><b>");

        builder.append(productCode);
        builder.append(" [");
        builder.append(modelCode);
        builder.append(" ]");
        builder.append("</b><br/>");
        builder.append(productName);
        builder.append("<br/><small>");
        if (cocinfo_id == null) {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("unlink1_1216.png", COCInfoActivator.PLUGIN_ID, "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" Õû³µ COC: ");
            builder.append(tag1);
        } else {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("link1_1216.png", COCInfoActivator.PLUGIN_ID, "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" COC: ");
            builder.append("<a href=\"com.sg.vim.editor.cocinfo@" + cocinfo_id
                    + "\" target=\"_rwt\">" + tag1 + "</a>");
        }
        if (cocinfo_id2 == null) {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("unlink1_1216.png", COCInfoActivator.PLUGIN_ID, "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" µ×ÅÌ COC: ");
            builder.append(tag1);
        } else {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("link1_1216.png", COCInfoActivator.PLUGIN_ID, "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" COC: ");
            builder.append("<a href=\"com.sg.vim.editor.cocinfo@" + cocinfo_id2
                    + "\" target=\"_rwt\">" + tag1_1 + "</a>");
        }

        if (cfginfo_id == null) {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("unlink2_1216.png", COCInfoActivator.PLUGIN_ID, "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" ÅäÖÃÐòºÅ: ");
            builder.append(tag2);
        } else {
            builder.append("<img src=\"");
            builder.append(FileUtil.getImageURL("link2_1216.png", COCInfoActivator.PLUGIN_ID, "image"));
            builder.append("\"  width='16' height='12' style='padding-right:4px;padding-top:4px;'/>");
            builder.append(" ÅäÖÃÐòºÅ: ");
            builder.append("<a href=\"com.sg.vim.editor.configcode@" + cfginfo_id
                    + "\" target=\"_rwt\">" + tag2 + "</a>");
        }
        
        if(!Utils.isNullOrEmptyString(f_c6)){
            builder.append(" ¸ÖÆ¬µ¯»É: ");
            builder.append(f_c6);
        }else{
            builder.append(" ¸ÖÆ¬µ¯»É: ");
            builder.append("Î´Éè¶¨");
        }
        builder.append("</small></span>");

        return builder.toString();

    }

}
