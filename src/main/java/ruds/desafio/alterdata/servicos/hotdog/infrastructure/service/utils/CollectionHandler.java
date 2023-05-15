package ruds.desafio.alterdata.servicos.hotdog.infrastructure.service.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CollectionHandler {
    
    public <T> List<T> arrayToList(T array[]) {
        return (List<T>) Arrays.stream(array).collect(Collectors.toList());
    }

}
