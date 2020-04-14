package com.tieto.javabootcamp.model.text;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	public abstract Long getId();
}
