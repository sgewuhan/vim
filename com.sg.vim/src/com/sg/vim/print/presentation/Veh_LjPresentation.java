package com.sg.vim.print.presentation;

import com.mobnut.commons.util.Utils;
import com.sg.ui.part.editor.field.AbstractFieldPart;
import com.sg.ui.part.editor.field.value.IValuePresentation;
import com.sg.vim.datamodel.IVIMFields;

public class Veh_LjPresentation implements IValuePresentation {

    public Veh_LjPresentation() {
    }

    @Override
    public String getPresentValue(AbstractFieldPart part) {
        Object wkc = part.getData().getValue(IVIMFields.mVeh_Qlj);
        Object wkk = part.getData().getValue(IVIMFields.mVeh_Hlj);
        if (Utils.isNullOrEmptyString(wkc) || Utils.isNullOrEmptyString(wkk)) {
            return "";
        } else {
            return "" + wkc + "/" + wkk;
        }
    }

}
