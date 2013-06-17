package com.sg.vim.print.presentation;

import com.mobnut.commons.util.Utils;
import com.sg.ui.part.editor.field.AbstractFieldPart;
import com.sg.ui.part.editor.field.value.IValuePresentation;
import com.sg.vim.datamodel.IVIMFields;

public class Veh_WkcPresentation implements IValuePresentation {

    public Veh_WkcPresentation() {
    }

    @Override
    public String getPresentValue(AbstractFieldPart part) {
        Object wkc = part.getData().getValue(IVIMFields.mVeh_Wkc);
        Object wkk = part.getData().getValue(IVIMFields.mVeh_Wkk);
        Object wkg = part.getData().getValue(IVIMFields.mVeh_Wkg);
        if (Utils.isNullOrEmptyString(wkc) || Utils.isNullOrEmptyString(wkk)
                || Utils.isNullOrEmptyString(wkg)) {
            return "";
        } else {
            return "" + wkc + "/" + wkk + "/" + wkg;
        }
    }

}
