package com.sg.vim.view;

import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import com.mobnut.commons.util.Utils;
import com.mongodb.DBObject;
import com.sg.ui.part.FilterPanel;
import com.sg.ui.part.view.TableNavigator;
import com.sg.ui.viewer.filter.ConditionDefinition;
import com.sg.vim.model.IVIMFields;

public class ProductBindTargetView extends TableNavigator implements 
        ISelectionListener {

    protected boolean canTransfer;

    public ProductBindTargetView() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sg.ui.part.view.TableNavigator#createPartControl(org.eclipse.swt. widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        getViewSite().getPage().addPostSelectionListener("com.sg.vim.productcode", this);
        super.createPartControl(parent);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (selection.isEmpty()) {
            return;
        }

        String productCode = getSelectedProductCode((IStructuredSelection) selection);

        navi.createFilterPanel();
        FilterPanel dash = (FilterPanel) navi.getCurrentDashPanel();

        if (productCode == null) {
            dash.doRemoveAllConditions();
            canTransfer = false;
            return;
        } else {
            String field = IVIMFields.F_0_2C1;
            String type = Utils.TYPE_STRING;
            String name = "³µÐÍ´úºÅ";
            ConditionDefinition conditionDefinition = new ConditionDefinition(field, name, type,
                    Utils.OPERATOR_EQUAL, productCode, null);
            dash.doSetCondition(new ConditionDefinition[] { conditionDefinition });
            canTransfer = true;

        }
    }

    private String getSelectedProductCode(IStructuredSelection selection) {
        String result = null;
        @SuppressWarnings("rawtypes")
        Iterator iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject data = (DBObject) iter.next();
            Object itm = data.get(IVIMFields.F_0_2C1);
            if (result != null && !result.equals(itm)) {
                return null;
            }
            result = (String) itm;
        }
        return result;
    }

    public boolean canBind() {
        return canTransfer;
    }


}
