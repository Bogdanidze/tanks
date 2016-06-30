package ua.study.element.barrier;

public enum BarrierType {

    BRICK("B"), WATER("W"), CONCRETE("C"), TARGET("T");

    private final String id;

    BarrierType(final String id) {
        this.id = id;
    }

    public static BarrierType getBarrierType(final String id) {
        for (BarrierType barrierType : values()) {
            if (barrierType.id.equals(id)) {
                return barrierType;
            }
        }
        return null;
    }
}
