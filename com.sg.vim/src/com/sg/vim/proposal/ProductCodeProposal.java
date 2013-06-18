package com.sg.vim.proposal;

import com.sg.ui.part.editor.field.value.OptionProposal;
import com.sg.vim.option.ProductCodeOption;

public class ProductCodeProposal extends OptionProposal{
    
    private ProductCodeOption op;

    public ProductCodeProposal(){
        super();
        op = new ProductCodeOption();
        loadEnumerate(op.getOption(null, null, null, null));
    }
    
}
