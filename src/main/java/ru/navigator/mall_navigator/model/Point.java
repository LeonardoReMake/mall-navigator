package ru.navigator.mall_navigator.model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Класс, который представялет собой узел на графе.
 */
@Entity
public class Point implements PersistentEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Координаты на карте магазина **/
    private double x;

    private double y;

    @ManyToOne
    private Mall mall;

    public Point() {}

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, Mall mall) {
        this.x = x;
        this.y = y;
        this.mall = mall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Mall getMall() {
        return mall;
    }

    public void setMall(Mall mall) {
        this.mall = mall;
    }

    @Override
    public Long getPrimaryKey() {
        return id;
    }
}
