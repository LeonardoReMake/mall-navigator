package ru.navigator.mall_navigator.model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Shop implements PersistentEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Mall mall;

    @OneToOne(cascade = CascadeType.ALL)
    private Point relatedPoint;

    public Shop() {}

    public Shop(String name) {
        this.name = name;
    }

    public Shop(String name, Point point) {
        this.name = name;
        this.relatedPoint = point;
    }

    public Shop(String name, Mall mall, Point relatedPoint) {
        this.name = name;
        this.mall = mall;
        this.relatedPoint = relatedPoint;
    }

    public Point getRelatedPoint() {
        return relatedPoint;
    }

    public void setRelatedPoint(Point relatedPoint) {
        this.relatedPoint = relatedPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
