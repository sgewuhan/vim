package com.sg.vim.cocinfo.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.util.file.FileUtil;
import com.sg.vim.cocinfo.Activator;

public class ProductCodeLabelProvider extends ColumnLabelProvider {

  public ProductCodeLabelProvider() {
  }

//  /*
//   * (non-Javadoc)
//   * 
//   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
//   */
//  @Override
//  public Image getImage(Object element) {
//    if (element instanceof DBObject) {
//      if (((DBObject) element).get("cocinfo") != null) {
//        return Activator.getImage("link_16.png");
//      }
//    }
//
//    return super.getImage(element);
//  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element) {
    DBObject data = (DBObject) element;
    Object productCode = data.get("e_01");
    Object modelCode = data.get("f_0_2c");
    Object cocinfo_name = data.get("cocinfo_name");
    Object cocinfo_id = data.get("cocinfo_id");

    StringBuilder builder = new StringBuilder();

    builder
        .append("<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:Î¢ÈíÑÅºÚ;font-size:11pt'>");

    builder.append(productCode);
    builder.append(" ");
    builder.append(modelCode);
    if(cocinfo_id!=null){
      builder.append("<img src=\"");
      builder.append(FileUtil.getImageURL("link_16.png", Activator.PLUGIN_ID, "image"));
      builder.append("\"  width='16' height='16' style='padding-right:4px;padding-top:4px;'/>");
      builder.append("  ");
      builder.append("<a href=\""+cocinfo_id+"\" target=\"_rwt\">"+cocinfo_name+"</a>");
    }
    builder.append("</span>");

    return builder.toString();

  }

}
