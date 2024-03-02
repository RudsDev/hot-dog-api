package dev.ruds.hotdog.domain.records;

import java.util.List;

public record PromocaoRecord(
    String nome,
    Double baseCalculo,
    Integer tipoCalculo,
    List<ItemPromocaoRecord> itens 
) {}
