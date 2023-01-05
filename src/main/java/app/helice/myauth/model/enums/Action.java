package app.helice.myauth.model.enums;

public enum Action {
    C("C"), R("R"), U("U"), D("D");

    private String action;

    private Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
