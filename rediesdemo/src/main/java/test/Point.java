package test;

import java.math.BigDecimal;

public class Point {
    private BigDecimal ra;
    private BigDecimal de;

    public Point(BigDecimal ra, BigDecimal de) {
        this.ra = ra;
        this.de = de;
    }

    public BigDecimal getRa() {
        return ra;
    }

//    public void setRa(double ra) {
//        this.ra = ra;
//    }

    public BigDecimal getDe() {
        return de;
    }

//    public void setDe(double de) {
//        this.de = de;
//    }
}
