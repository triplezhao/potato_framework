package com.potato.chips.base;

import com.potato.library.data.PotatoBaseParser;

/**
 * @author ztw 这个类只提供基础的解析方法，每个接口对应的解析方法在.parse.api包下面。
 */
public abstract class BaseParser extends PotatoBaseParser {

    public BaseParser(String jsonStr) {
        super(jsonStr);
    }
}
