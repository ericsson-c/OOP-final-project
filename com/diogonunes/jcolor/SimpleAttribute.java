package com.diogonunes.jcolor;


class SimpleAttribute extends Attribute {

    private static final long serialVersionUID = 11L;
    private final String _code;

    /**
     * Constructor. Maps an attribute to an Ansi code.
     *
     * @param code Ansi code that represents the attribute.
     */
    SimpleAttribute(String code) {
        _code = code;
    }

    @Override
    public String toString() {
        return _code;
    }

}
