package com.sg.vim.view;

import java.util.Date;

import org.eclipse.jface.action.Action;

import com.mobnut.commons.util.Utils;
import com.sg.ui.part.Navigator;
import com.sg.ui.part.QueryPanel;
import com.sg.ui.viewer.filter.ConditionDefinition;
import com.sg.vim.model.IVIMFields;

public class QueryReUploadAction extends Action {

    private Navigator navi;

    // ����ǰ��ӡδ�ϴ��ļ�¼
    public QueryReUploadAction(Navigator navigator) {
        setText("������");
        this.navi = navigator;
    }


    @Override
    public void run() {
        navi.createQueryPanel();
        QueryPanel dash = (QueryPanel) navi.getCurrentDashPanel();
        
        // �õ�����ǰ������
        Date date = Utils.getDateBefore(new Date(), 2);
        ConditionDefinition[] cd = new ConditionDefinition[2];
        cd[0] = new ConditionDefinition(IVIMFields.LIFECYCLE, "״̬", Utils.TYPE_STRING,
                Utils.OPERATOR_EQUAL, IVIMFields.LC_PRINTED, null);
        cd[1] = new ConditionDefinition(IVIMFields.PRINTDATE, "��ӡ����", Utils.TYPE_DATE,
                Utils.OPERATOR_LESSEQUAL, date, null);
        dash.doSetCondition(cd);
        dash.excuteQuery();
        super.run();
    }
}
