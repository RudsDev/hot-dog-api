package dev.ruds.hotdog.domain.records;

import java.util.List;

public record LancheRecord(
    String nome,
    List<Long> ingredientes 
) {}
