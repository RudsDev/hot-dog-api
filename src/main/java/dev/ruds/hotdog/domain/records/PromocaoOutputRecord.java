package dev.ruds.hotdog.domain.records;

public record PromocaoOutputRecord(
    Long id,
    String nome,
    Double baseCalculo,
    Integer tipoCalculo 
) {}
