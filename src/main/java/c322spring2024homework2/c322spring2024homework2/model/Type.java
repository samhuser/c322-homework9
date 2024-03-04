package c322spring2024homework2.c322spring2024homework2.model;

public enum Type {
    ACOUSTIC, ELECTRIC ,ANY;
    public String toString(){
        switch (this){
            case ACOUSTIC: return "acoustic";
            case ELECTRIC: return "electric";
            default: return "unspecified";
        }
    }
}
