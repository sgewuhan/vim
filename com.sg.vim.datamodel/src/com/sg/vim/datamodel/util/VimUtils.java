package com.sg.vim.datamodel.util;

import com.mobnut.commons.util.Utils;

public class VimUtils {

    /**
     * 检查输入的VIN是否合法
     * @param inputVin
     */
    public static boolean checkVIN(String vin) {
        //17位字母和数字组成：LDC913L2240000023
        //0-9,A-Z但是不能有字母O,I,Q
        String pattern = "[0-9ABCDEFGHJKLMNPQRSTUVWXYZ]{17}";
        return Utils.isPatternMatched(vin, pattern);
    }
    

}
