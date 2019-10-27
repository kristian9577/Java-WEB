package services;

import java.util.List;

public interface MappingService {
    <T, K> T map(K Object,Class<T> klass);

    <T, K> List<T> map(List<K> Object, Class<T> klass);
}
