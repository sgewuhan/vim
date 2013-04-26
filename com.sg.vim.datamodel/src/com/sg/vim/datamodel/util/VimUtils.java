package com.sg.vim.datamodel.util;

import com.mobnut.commons.util.Utils;

public class VimUtils {

    /**
     * ��������VIN�Ƿ�Ϸ�
     * @param inputVin
     */
    public static boolean checkVIN(String vin) {
        //17λ��ĸ��������ɣ�LDC913L2240000023
        //0-9,A-Z���ǲ�������ĸO,I,Q
        String pattern = "[0-9ABCDEFGHJKLMNPQRSTUVWXYZ]{17}";
        return Utils.isPatternMatched(vin, pattern);
    }
    

}
