package c322spring2024homework2.c322spring2024homework2.model;

public enum Builder{
    FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY;
    public String toString(){
        switch (this){
            case FENDER: return "Fender";
            case MARTIN: return "Martin";
            case GIBSON: return "Gibson";
            case COLLINGS: return "Collings";
            case OLSON: return "Olson";
            case RYAN: return "Ryan";
            case PRS: return "Prs";
            default: return "Unspecified";
        }
    }
}
