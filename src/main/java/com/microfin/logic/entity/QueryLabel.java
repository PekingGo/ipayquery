package com.microfin.logic.entity;

public class QueryLabel {

    private String text;
    private boolean seen;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the seen
     */
    public boolean isSeen() {
        return seen;
    }

    /**
     * @param seen
     *            the seen to set
     */
    public void setSeen(boolean seen) {
        this.seen = seen;
    }

}
