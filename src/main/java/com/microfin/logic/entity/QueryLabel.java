package com.microfin.logic.entity;

public class QueryLabel {

    private String label;
    private String text;
    private boolean txtSeen;
    private boolean seen;

    public QueryLabel(String label,String text ,boolean txtSeen,boolean seen){
        this.label = label;
        this.text  = text;
        this.txtSeen = txtSeen;
        this.seen = seen;
    }
    public  QueryLabel(){

    }
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isTxtSeen() {
        return txtSeen;
    }

    public void setTxtSeen(boolean txtSeen) {
        this.txtSeen = txtSeen;
    }
}
