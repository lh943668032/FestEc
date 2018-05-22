package com.lh.ec.icon;

import com.joanzapata.iconify.Icon;

public enum EcIcons implements Icon {

    icon_zhifubao('\ue6b8'),
    icon_pay('\ue630');
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("_","-");
    }

    @Override
    public char character() {
        return character;
    }
}
