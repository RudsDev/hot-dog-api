package dev.ruds.hotdog.domain.models;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public interface Vendavel {
	public BigDecimal preco();
}
